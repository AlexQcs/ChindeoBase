package com.chindeo.repository.resources;

import com.chindeo.repository.data.api.BedApi;
import com.chindeo.repository.data.model.common.PageLimit;
import com.chindeo.repository.data.model.nurses.ListDeviceBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.params.bed.BedListParams;
import com.chindeo.repository.data.model.params.bed.BedQueryPriceDetailParams;
import com.chindeo.repository.data.model.params.bed.BedUpdateParams;
import com.chindeo.repository.data.model.params.bed.BedZhenliaoPlanParams;
import com.chindeo.repository.data.model.params.bed.RemindReadParams;
import com.chindeo.repository.data.model.params.nurses.HostTrusteeShipParams;
import com.chindeo.repository.data.model.response.adm.AdmZhenliaoListBean;
import com.chindeo.repository.data.model.response.bed.BedDetailBean;
import com.chindeo.repository.data.model.response.bed.BedDeviceConfigBean;
import com.chindeo.repository.data.model.response.bed.BedQueryPriceListBean;
import com.chindeo.repository.data.model.response.bed.BedQueryQindanSumBean;
import com.chindeo.repository.data.model.response.bed.BedRoleAuthorityBean;
import com.chindeo.repository.data.model.response.bed.BedUnReadNoticeBean;
import com.chindeo.repository.data.model.response.bed.BedVolumeDeleteBean;
import com.chindeo.repository.data.model.response.bed.BedVolumeDetailBean;
import com.chindeo.repository.data.model.response.bed.BedVolumeSetBean;
import com.chindeo.repository.data.model.response.bed.EventEnrollmentBean;
import com.chindeo.repository.data.model.response.bed.HostTrusteeShipBean;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;
import com.lazylibs.http.data.flowable.ResultFlowableTransformer;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public class BedRepository {

    private volatile static BedRepository INSTANCE = null;

    public static BedRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (BedRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BedRepository();
                }
            }
        }
        return INSTANCE;
    }


    public Flowable<ListDeviceBean> getListDevice(BedListParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .getListDevice(RequestContent.create(params))
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Completable bedUpdate(BedUpdateParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .bedUpdate(RequestContent.create(params))
                .compose(new HttpResultFlowableTransformer<>())
                .ignoreElements();
    }

    /**
     * 设置紧急消息已读
     */
    public Flowable<String> remindRead(RemindReadParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .remindRead(RequestContent.create(params))
                .compose(new HttpResultFlowableTransformer<>());
    }


    /**
     * 获取床旁设备配置信息
     * @return
     */
    public Flowable<BedDeviceConfigBean> getDeviceConfig() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .deviceConfig(RequestContent.createRepositoryParams())
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取病床详情
     * @return
     */
    public Flowable<BedDetailBean> getDetail(String bedId) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .getDetail(bedId)
                .compose(new HttpResultFlowableTransformer<>());
    }



    /**
     * 获取未读消息
     * @return
     */
    public Flowable<List<BedUnReadNoticeBean>> messageUnRead(String admId) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .messageUnRead(admId)
                .compose(new HttpResultFlowableTransformer<>());
    }




    /**
     * 获取菜单列表
     * @return
     */
    public Flowable<BedRoleAuthorityBean> bedMenu() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .bedMenu()
                .compose(new HttpResultFlowableTransformer<>());
    }



    /**
     * 获取查询-清单-费用总览
     * @return
     */
    public Flowable<BedQueryQindanSumBean> getQueryQindan(String admNo) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .getQueryQindan(admNo)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取查询-清单-详情
     * @return
     */
    public Flowable<String> getQueryQindanDetail(String admNo, String date) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .getQueryQindanDetail(admNo, date)
                .compose(new HttpResultFlowableTransformer<>());
    }


    /**
     * 获取查询-物价分类
     * @return
     */
    public Flowable<List<String>> getQueryPriceCategory(){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .getQueryPriceCategory()
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取诊疗-详情
     * @return
     */
    public Flowable<List<AdmZhenliaoListBean>> getZhenliaoPlan(BedZhenliaoPlanParams params){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .getZhenliaoPlan(RequestContent.create(params))
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 设置消息提醒已读
     */
    public Flowable<String> setMessageRead(int msgId) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BedApi.class)
                .setMessageRead(msgId)
                .compose(new HttpResultFlowableTransformer<>());
    }
}
