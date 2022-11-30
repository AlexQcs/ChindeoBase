package com.chindeo.repository.resources;

import com.chindeo.repository.data.api.BusinessCommonApi;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.params.business.ShieldPatientParams;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;

import io.reactivex.Completable;


/**
 * 接口文档定义的业务通用
 */
public class BusinessCommonRepository {


    private volatile static BusinessCommonRepository INSTANCE = null;

    public static BusinessCommonRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (BusinessCommonRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BusinessCommonRepository();
                }
            }
        }
        return INSTANCE;
    }

    public Completable shieldPatient(ShieldPatientParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(BusinessCommonApi.class)
                .shieldPatient(RequestContent.create(params))
                .compose(new HttpResultFlowableTransformer<>())
                .ignoreElements();
    }

}
