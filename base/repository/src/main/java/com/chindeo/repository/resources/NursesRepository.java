package com.chindeo.repository.resources;

import com.chindeo.repository.data.api.NursesApi;
import com.chindeo.repository.data.livedata.DeviceInfoLiveData;
import com.chindeo.repository.data.model.nurses.AudioRecordBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.params.audio.AudioRecordListParams;
import com.chindeo.repository.data.model.params.nurses.BroadcastTaskParams;
import com.chindeo.repository.data.model.params.nurses.HostTrusteeShipParams;
import com.chindeo.repository.data.model.params.nurses.NursesBedMessageParams;
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
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;
import com.lazylibs.http.data.flowable.ResultFlowableTransformer;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public class NursesRepository {

    private volatile static NursesRepository INSTANCE = null;

    public static NursesRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (NursesRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NursesRepository();
                }
            }
        }
        return INSTANCE;
    }


    public Flowable<List<AudioRecordBean>> getDoctorRecord(AudioRecordListParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getDoctorRecord(RequestContent.create(params))
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Completable addBroadcastTask(BroadcastTaskParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .addBroadcastTask(RequestContent.create(params))
                .compose(new HttpResultFlowableTransformer<>())
                .ignoreElements();
    }

    public Completable removeBroadcastTask(int id) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .removeBroadcastTask(id)
                .compose(new HttpResultFlowableTransformer<>())
                .ignoreElements();
    }

    public Flowable<List<NursesBroadcastTaskBean>> getBroadcastTaskList(String locCode) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getBroadcastTaskList(locCode)
                .compose(new HttpResultFlowableTransformer<>());
    }


    /**
     * 获取床旁音量控制列表
     * @return
     */
    public Flowable<List<BedVolumeDetailBean>> getDeviceVolumeListUsing(String locCode, String type) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getDeviceVolumeListUsing(locCode,type)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取床旁音量控制列表
     * @return
     */
    public Flowable<String> updaterDeviceVolumeListUsing(List<BedVolumeSetBean> params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .updaterDeviceVolumeListUsing(params)
                .compose(new HttpResultFlowableTransformer<>());
    }
    public Flowable<String> deleteDeviceVolumeListUsing(List<BedVolumeDeleteBean> params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .deleteDeviceVolumeListUsing(params)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取病区主机托管信息及主机列表
     */
    public Flowable<HostTrusteeShipBean> getTrusteeshipDevList(String locCode) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getTrusteeshipDevList(locCode)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 保存病区主机托管配置信息
     */
    public  Flowable<ResponseBody> saveHostTrusteeship(HostTrusteeShipParams params){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .saveHostTrusteeship(RequestContent.create(params))
                .compose(new ResultFlowableTransformer<>());
    }

    /**
     * 获取事件登记项列表
     */
    public   Flowable<List<EventEnrollmentBean>> getEventEnrollmentList(){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getEventEnrollmentList()
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 设置事件登记项
     */
    public  Flowable<ResponseBody> setEventEnrollment(String admId, String eventItemId){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .setEventEnrollment(admId,eventItemId)
                .compose(new ResultFlowableTransformer<>());
    }

    /**
     * 获取床位消息提醒
     */
    public  Flowable<NursesBedMessageBean> getNursesBedReminds(String locCode){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getNursesBedReminds(locCode)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 设置床位消息已读
     */
    public  Flowable<ResponseBody> setNursesBedRemindsRead(NursesBedMessageParams params){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .setNursesBedRemindsRead(RequestContent.create(params))
                .compose(new ResultFlowableTransformer<>());
    }

    /**
     * 探视列表
     */
    public  Flowable<List<NursesVisitBean>> getCallInfoList(){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getCallInfoList(DeviceInfoLiveData.getDeviceId())
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取责任分组对应的铃声以及通讯账号
     */
    public  Flowable<List<RingtonesAccountsBean>> getBedRingtones(){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getBedRingtones()
                .compose(new HttpResultFlowableTransformer<>());
    }

    public  Flowable<List<RingtonesGroup>> getRingtonesGroup(){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getRingtonesGroup()
                .compose(new HttpResultFlowableTransformer<>());
    }

    public  Flowable<List<RingtonesFileByType>> getFileByType(String type){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getFileByType(type)
                .compose(new HttpResultFlowableTransformer<>());
    }

    public  Completable updaterBatchRington(List<BatchRingtonParams> params){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .updaterBatchRington(params)
                .compose(new HttpResultFlowableTransformer<>())
                .ignoreElements();
    }

    /**
     护士站主机通话列表
     */
    public Flowable<List<NursesCallHostBean>> getCallHostList() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(NursesApi.class)
                .getCallHostList(DeviceInfoLiveData.getDeviceId())
                .compose(new HttpResultFlowableTransformer<>());
    }

}
