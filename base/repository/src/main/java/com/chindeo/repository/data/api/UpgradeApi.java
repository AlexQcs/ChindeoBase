package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.LicenseTokenBean;
import com.chindeo.repository.data.model.response.upgrade.PrimaryAppBean;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

/**
 * 更新api
 * 静态域名 resUrl
 */
public interface UpgradeApi {


    /**
     * 获取当前设备的主应用包信息
     *
     * @param
     * @return {@link PrimaryAppBean}
     */
    @GET(ApiConstants.API_APP_UPGRADE)
    Flowable<HttpResult<PrimaryAppBean>> getAppUpgrade(@Query("device_id")String deviceId);

    /**
     * 获取主应用与插件的列表信息
     * @param primaryPackage
     * @return
     */
    @GET(ApiConstants.API_APP_APK_INFO)
    Flowable<ResponseBody> getPlugInList(@Path("package") String primaryPackage, @Path(value = "env", encoded = true) String env);

    @GET(ApiConstants.API_APP_APK_URL)
    Flowable<ResponseBody> getApkPath(@Path("package") String downLoadPackage);

}
