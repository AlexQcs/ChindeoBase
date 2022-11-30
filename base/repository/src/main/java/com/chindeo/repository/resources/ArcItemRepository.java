package com.chindeo.repository.resources;

import com.chindeo.repository.data.api.ArcItemApi;
import com.chindeo.repository.data.model.response.arcitem.PatientImageCaseBean;
import com.chindeo.repository.data.model.response.arcitem.PatientInfo;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;

import java.util.List;

import io.reactivex.Flowable;

public class ArcItemRepository {

    private volatile static ArcItemRepository INSTANCE = null;

    public static ArcItemRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (ArcItemRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ArcItemRepository();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 病人信息
     * @param admNo
     * @return
     */
    public Flowable<PatientInfo> getPatient(String admNo) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(ArcItemApi.class)
                .getPatientInfo(admNo)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 病人病例
     * @param admId
     * @return
     */
    public Flowable<List<PatientImageCaseBean>> getPatientImageCase(String admId) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(ArcItemApi.class)
                .getPatientImageCase(admId)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 病人病例
     * @param admNo
     * @return
     */
    public Flowable<String> getPatientHtmlCase(String admNo) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(ArcItemApi.class)
                .getPatientHtmlCase(admNo)
                .compose(new HttpResultFlowableTransformer<>());
    }


}
