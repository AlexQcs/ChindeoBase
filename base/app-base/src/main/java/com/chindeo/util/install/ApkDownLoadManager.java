package com.chindeo.util.install;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.apkfuns.logutils.LogUtils;
import com.chindeo.constants.UpgradeActionConstants;
import com.chindeo.model.base.LauncherAppConfig;
import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.contants.AppModule;
import com.chindeo.repository.contants.DeviceType;
import com.chindeo.repository.contants.FilePathConstants;
import com.chindeo.repository.data.livedata.DeviceInfoLiveData;
import com.chindeo.repository.data.livedata.PrimaryAppInfoLiveData;
import com.chindeo.repository.data.model.response.upgrade.PluginBean;
import com.chindeo.repository.mmkv.impl.SettingHostCache;
import com.chindeo.repository.util.AppConfigManager;
import com.chindeo.repository.util.CommandUtil;
import com.lazylibs.BuildConfig;
import com.lazylibs.util.AppUtils;
import com.lazylibs.util.FileUtils;
import com.lazylibs.util.ToastUtils;

import org.reactivestreams.Publisher;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ApkDownLoadManager {


    private DownloadManager downloadManager;
    private long downloadId = 0;
    private DisposableSingleObserver<Boolean> installDis;

    private static List<String> ignoreList;

    public void clearIgnoreList(Context context) {
        if (ignoreList != null && !ignoreList.isEmpty()) {
            ignoreList = new ArrayList<>();
            ToastUtils.showShort(context, "已清楚忽略，请耐心等待更新服务器版本");
        }
    }

    public boolean hasIgnore() {
        return ignoreList != null && !ignoreList.isEmpty();
    }

    public String ignoreListString() {
        if (hasIgnore()) {
            StringBuilder sb = new StringBuilder();
            for (String s : ignoreList) {
                sb.append(s).append(",\n");
            }
            return sb.toString();
        }
        return "";
    }


    private ApkDownLoadManager() {
        ignoreList = ((LauncherAppConfig) AppConfigManager.get(LauncherAppConfig.class)).ignoreUpdateList();
    }

    private static ApkDownLoadManager INSTANCE;

    public static ApkDownLoadManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApkDownLoadManager();
        }
        return INSTANCE;
    }


    public interface DownLoadListener {

        void installStart(String packageName);

        void installFinish(String packageName);

        void error(String message);

        void isNew(String pName);

    }

    public interface ProgressListener {
        void progress(String packageName, int progress);
    }

    public static boolean checkInstall(Context context, PluginBean bean) {
        LogUtils.d("检查安装状态->" + bean.packageName);
        AppUtils.AppInfo appInfo = AppUtils.getAppInfo(context, bean.packageName);
        if (appInfo != null) {
            String packagePath = appInfo.getPackagePath();
            String installAppMd5 = FileUtils.getFileMD5ToString(packagePath);
            if (BuildConfig.DEBUG) {
                if (ignoreList.contains(bean.packageName)) {
                    LogUtils.d("忽略包" + bean.packageName);
                    return true;
                }
            }
            if (bean.hash.equals(installAppMd5)) {
                LogUtils.d("已经是最新hash包" + bean.packageName);
                return true;
            } else {
                LogUtils.d(bean.packageName + "不是最新的hash包: remote->" + bean.hash + ", installed->" + installAppMd5);
            }
        }
        if (appInfo == null) {
            LogUtils.e("包信息获取不到 ！！ 异常！！" + bean.packageName);
        }
        if (AppModule.getModule(bean.packageName) == null) {
            LogUtils.d("未定义的新增插件" + bean.packageName);
            return true;
        }
        if (BuildConfig.DEBUG) {
            if (ignoreList.contains(bean.packageName)) {
                LogUtils.d("忽略包" + bean.packageName);
                return true;
            }
        }
        return false;
    }

    public static boolean checkInstall(Context context, List<PluginBean> beanList) {
        List<PluginBean> destList = new ArrayList<>(beanList.size());
        Collections.addAll(destList, new PluginBean[beanList.size()]);
        Collections.copy(destList, beanList);
        for (PluginBean pluginBean : destList) {
            if (!checkInstall(context, pluginBean)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true不需要更新
     */
    public boolean checkAndDownLoad(Context context, PluginBean pluginBean, DownLoadListener listener) {
        LogUtils.d(pluginBean.packageName + " 检查和下载线程" + Thread.currentThread().getName());
        String packageName = pluginBean.packageName;
        String remoteMd5 = pluginBean.hash;
        if (AppModule.getModule(packageName) == null) {
            pluginBean.status = PluginBean.Status.TEST;
            listener.isNew(packageName);
            return true;
        }
        if (BuildConfig.DEBUG) {
            if (ignoreList.contains(pluginBean.packageName)) {
                pluginBean.status = PluginBean.Status.TEST;
                listener.isNew(packageName);
                return true;
            }
        }

        String downLoadUrl = null;
        LogUtils.i("当前app更新下载->" + pluginBean);
        if (AppModule.getModule(packageName) != null && AppModule.getModule(packageName).isPlugin) {
            downLoadUrl = SettingHostCache.cacheFormatWebResHost() + ApiConstants.API_PLUGIN_APK_URL.replace("{appName}", AppModule.getModule(packageName).appName);
        } else {
            downLoadUrl = SettingHostCache.cacheFormatWebResHost() + ApiConstants.API_APP_APK_URL.replace("{package}", packageName);
        }
        downLoadUrl = downLoadUrl.replace("{env}", PrimaryAppInfoLiveData.INSTANCE.getAppEnv());
        LogUtils.i(packageName + "下载地址" + downLoadUrl);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downLoadUrl));
        //设置漫游条件下是否可以下载
        request.setAllowedOverRoaming(false);
        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        //设置通知标题
        request.setTitle(packageName + "正在下载");
        request.setVisibleInDownloadsUi(true);

        //设置文件存放路径
        File file = new File(FilePathConstants.getDir(FilePathConstants.APP_DOWNLOAD), packageName + ".apk");

        String packagePath = "";
        String installAppMd5 = "";
        AppUtils.AppInfo appInfo = AppUtils.getAppInfo(context, packageName);
        if (appInfo != null) {
            packagePath = appInfo.getPackagePath();
            LogUtils.d("比较md5值 : 文件路径 " + packagePath);
            installAppMd5 = FileUtils.getFileMD5ToString(packagePath);
        }
        LogUtils.d("比较md5值 : 本地app " + packageName + " md5 =" + installAppMd5 + " Remote md5 >> " + remoteMd5);
        LogUtils.d("校验结果 是最新版本" + installAppMd5.equals(remoteMd5));
        if (remoteMd5.equals(installAppMd5)) {
            pluginBean.status = PluginBean.Status.INSTALL;
            listener.isNew(pluginBean.packageName);
            return false;
        }

        if (file.exists()) {
            String fileMd5 = FileUtils.getFileMD5ToString(file.getPath());
            if (remoteMd5.equals(fileMd5)) {
                pluginBean.status = PluginBean.Status.DOWNLOAD_FINISH;
                installApk(context, pluginBean, file.getPath(), packageName, listener);
                return false;
            } else {
                File[] files = file.getParentFile().listFiles();
                for (File f : files) {
                    if (f.getName().contains(packageName)) {
                        Log.e(ApkDownLoadManager.class.getName(), "文件删除->" + f.getName());
                        f.delete();
                    }
                }
            }
        }
        Intent intent = new Intent(UpgradeActionConstants.ACTION_START_DOWNLOAD);
        intent.putExtra(UpgradeActionConstants.EXTRA_PACKAGE_NAME, packageName);
        intent.putExtra(UpgradeActionConstants.EXTRA_DOWNLOAD_ID, downloadId);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        DownloadUtil.Companion.get().download(downLoadUrl, packageName + ".apk", new DownloadUtil.DownloadListener() {
            @Override
            public void onDownloadSuccess() {
                pluginBean.status = PluginBean.Status.DOWNLOAD_FINISH;
                installApk(context, pluginBean, file.getPath(), packageName, listener);
            }

            @Override
            public void onDownloading(int progress) {
                Intent intent = new Intent(UpgradeActionConstants.ACTION_DOWNLOAD_PROGRESS);
                intent.putExtra(UpgradeActionConstants.EXTRA_DOWNLOAD_ID, packageName + ".apk");
                intent.putExtra(UpgradeActionConstants.EXTRA_DOWNLOAD_PROGRESS, progress);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }

            @Override
            public void onDownloadFailed() {
                listener.error("下载文件异常");
            }
        });

        return false;
    }


    public void installApk(Context context, PluginBean bean, String filePath, String packageName, DownLoadListener listener) {
        listener.installStart(bean.packageName);
        bean.status = PluginBean.Status.UN_INSTALL;


        if (bean.packageName.equals(AppModule.LAUNCHER.packageName)) {
            //启动器更新
            if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M) {
                AutoInstallerUtil.execute(context, context.getPackageName(), filePath);
            } else {
                AppUtils.installApk(context, context.getPackageName(), filePath);
            }

            return;
        }

//                .flatMap(new Function<Boolean, Publisher<Boolean>>() {
//            @Override
//            public Publisher<Boolean> apply(@NonNull Boolean aBoolean) throws Exception {
//                if (CommandUtil.checkRooted()) {
//                    return Flowable.just(aBoolean)
//                            .compose(new ResultFlowableTransformer<>());
//                }
//                return Flowable.just(aBoolean);
//            }
//        })
        installDis = Flowable.just(DeviceInfoLiveData.getDeviceType() == DeviceType.TB)
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        installDis = null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Boolean, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Boolean aBoolean) throws Exception {
                        if (CommandUtil.checkRooted()) {  //插件统一先卸载再安装
                            CommandUtil.executeClientUninstall(packageName);
                        }
                        return aBoolean;
                    }
                })
                .flatMap(new Function<Boolean, Publisher<Boolean>>() {
                    @Override
                    public Publisher<Boolean> apply(@NonNull Boolean isTb) throws Exception {
                        if (CommandUtil.checkRooted()) {
                            if (isTb) {//天波设备
                                return Flowable.just(CommandUtil.executeSilentInstallTb(filePath, packageName));
                            } else {
                                return Flowable.just(CommandUtil.executeClientInstall(filePath));
                            }
                        } else {
                            return Flowable.just(false);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .singleOrError()
                .subscribeWith(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(@NonNull Boolean finish) {
                        if (finish) {
                            ToastUtils.showShort(context, "安装成功" + packageName);
                            Intent intent = new Intent(UpgradeActionConstants.ACTION_INSTALL_FINISH);
                            intent.putExtra(UpgradeActionConstants.EXTRA_PACKAGE_NAME, packageName);
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                            bean.status = PluginBean.Status.INSTALL;
                            listener.installFinish(packageName);
                        } else {
                            AppUtils.installApp(context, filePath, context.getPackageName() + ".fileProvider");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

}
