package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.nurses.AudioRecordBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.bed.BedVolumeDeleteBean;
import com.chindeo.repository.data.model.response.bed.BedVolumeDetailBean;
import com.chindeo.repository.data.model.response.bed.BedVolumeSetBean;
import com.chindeo.repository.data.model.response.bed.EventEnrollmentBean;
import com.chindeo.repository.data.model.response.bed.HostTrusteeShipBean;
import com.chindeo.repository.data.model.response.nurses.BatchRingtonParams;
import com.chindeo.repository.data.model.response.nurses.NursesBedMessageBean;
import com.chindeo.repository.data.model.response.nurses.NursesBroadcastTaskBean;
import com.chindeo.repository.data.model.response.nurses.NursesCallHostBean;
import com.chindeo.repository.data.model.response.nurses.NursesVisitBean;
import com.chindeo.repository.data.model.response.nurses.RingtonesAccountsBean;
import com.chindeo.repository.data.model.response.nurses.RingtonesFileByType;
import com.chindeo.repository.data.model.response.nurses.RingtonesGroup;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface NursesApi {


    @GET(ApiConstants.API_NURSES_DOCTOR_RECORD)
    Flowable<HttpResult<List<AudioRecordBean>>> getDoctorRecord(@QueryMap RequestContent params);


    @POST(ApiConstants.API_NURSES_BROADCAST_ADD)
    Flowable<HttpResult<String>> addBroadcastTask(@Body RequestContent params);

    @POST(ApiConstants.API_NURSES_BROADCAST_REMOVE)
    Flowable<HttpResult<String>> removeBroadcastTask(@Query("id")int id);

    @POST(ApiConstants.API_NURSES_BROADCAST_LIST)
    Flowable<HttpResult<List<NursesBroadcastTaskBean>>> getBroadcastTaskList(@Query("locCode")String locCode);

    /**
     * 获取音量控制
     */
    @GET(ApiConstants.API_QUERY_DEVICE_VOLUME_LIST_USING)
    Flowable<HttpResult<List<BedVolumeDetailBean>>> getDeviceVolumeListUsing(@Query("locCode") String locCode, @Query("type")String type);
    /**
     * 获取音量控制
     */
    @POST(ApiConstants.API_SET_DEVICE_VOLUME_LIST_USING)
    Flowable<HttpResult<String>> updaterDeviceVolumeListUsing(@Body List<BedVolumeSetBean> params);


    @POST(ApiConstants.API_SET_DEVICE_VOLUME_DELETE)
    Flowable<HttpResult<String>> deleteDeviceVolumeListUsing(@Body List<BedVolumeDeleteBean> params);

    @GET(ApiConstants.APP_lOC_TRUSTEESHIP_DEVLIST)
    Flowable<HttpResult<HostTrusteeShipBean>> getTrusteeshipDevList(@Query("locCode") String locCode);

    @POST(ApiConstants.APP_lOC_HOSTTRUSTEESHIP_SAVE)
    Flowable<ResponseBody> saveHostTrusteeship(@Body RequestContent params);

    @GET(ApiConstants.API_GET_OBSERVATION_ITEM)
    Flowable<HttpResult<List<EventEnrollmentBean>>> getEventEnrollmentList();

    @POST(ApiConstants.API_POST_ITEM_EVENT_SET)
    Flowable<ResponseBody> setEventEnrollment(@Query("admId") String admId, @Query("eventItemId")String eventItemId);

    @GET(ApiConstants.API_GET_BED_REMINDS)
    Flowable<HttpResult<NursesBedMessageBean>> getNursesBedReminds(@Query("locCode")String locCode);

    @POST(ApiConstants.API_REMIND_READ)
    Flowable<ResponseBody> setNursesBedRemindsRead(@Body RequestContent params);

    @GET(ApiConstants.API_GET_CALL_INFO)
    Flowable<HttpResult<List<NursesVisitBean>>> getCallInfoList(@Query("mac")String mac);
    /**
     * 获取责任分组对应的铃声以及通讯账号
     * @return
     */
    @GET(ApiConstants.RINGTONES_AND_COMMUNICATION_ACCOUNTS)
    Flowable<HttpResult<List<RingtonesAccountsBean>>> getBedRingtones();

    @GET(ApiConstants.RINGTONES_AND_BED_GROUP)
    Flowable<HttpResult<List<RingtonesGroup>>> getRingtonesGroup();

    @GET(ApiConstants.BATCH_GET_FILE_BY_TYPE)
    Flowable<HttpResult<List<RingtonesFileByType>>> getFileByType(@Query("type")String type);

    @PUT(ApiConstants.BATCH_RINGTON)
    Flowable<HttpResult<String>> updaterBatchRington(@Body List<BatchRingtonParams> params);

    @GET(ApiConstants.API_GET_DEVICE_CALL)
    Flowable<HttpResult<List<NursesCallHostBean>>> getCallHostList(@Query("mac") String mac);
}
