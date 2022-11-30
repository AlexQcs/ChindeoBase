package com.chindeo.repository.data.livedata;


import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.chindeo.repository.contants.AppModule;
import com.chindeo.repository.data.model.common.PrimaryAppInfo;
import com.chindeo.repository.data.model.response.upgrade.PluginBean;
import com.chindeo.repository.mmkv.impl.AppModuleCache;
import com.chindeo.repository.mmkv.impl.AppSettingCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 主运行工程相关信息 (床旁/护士站等)
 */
public enum PrimaryAppInfoLiveData {

    INSTANCE;

    private MutableLiveData<PrimaryAppInfo> liveData;

    PrimaryAppInfoLiveData() {
    }

    public MutableLiveData<PrimaryAppInfo> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<PrimaryAppInfo>() {
                @Nullable
                @Override
                public PrimaryAppInfo getValue() {
                    return super.getValue() == null ? new PrimaryAppInfo() : super.getValue();
                }
            };
        }
        return liveData;
    }

    public void setPrimaryApp(String packageName, String env) {
        assert packageName != null;
        AppModuleCache.setPrimaryModule(AppModule.getModule(packageName));
        PrimaryAppInfo app = getLiveData().getValue();
        app.primaryAppPackageName = packageName;
        app.env = TextUtils.isEmpty(env) || "prod".equals(env) ? "" : env;
        AppModuleCache.setAppPackageEnv(env);
        getLiveData().postValue(app);
    }

    public AppModule getPrimaryApp() {
        return AppModule.getModule(getLiveData().getValue().primaryAppPackageName);
    }

    public String getAppEnv() {
        return TextUtils.isEmpty(getLiveData().getValue().env) ? "" : getLiveData().getValue().env;
    }

    public void setPluginStringList(List<String> pluginPackageStringList) {
        PrimaryAppInfo app = getLiveData().getValue();
        app.pluginPackageStringList = pluginPackageStringList;
        getLiveData().postValue(app);
    }

    /**
     * 获取chindeo产品包名列表
     * @return
     */
    public List<String> getAllRelatedPackageStringList() {
        PrimaryAppInfo info = getLiveData().getValue();
        List<String> temp = new ArrayList<>();
        temp.addAll(info.pluginPackageStringList);
        temp.add(info.primaryAppPackageName);
        return temp;
    }

    public void setPluginList(List<PluginBean> pluginPackageList) {
        PrimaryAppInfo app = getLiveData().getValue();
        List<PluginBean> temp = new ArrayList<>(pluginPackageList);
        app.pluginPackageList = temp;
        getLiveData().postValue(app);
    }

    public List<PluginBean> getAllRelatedPackageList() {
        PrimaryAppInfo info = getLiveData().getValue();
        List<PluginBean> pluginList = info.pluginPackageList;
        return pluginList;
    }


}
