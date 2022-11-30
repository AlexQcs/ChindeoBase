package com.chindeo.repository.resources;


import com.chindeo.repository.data.api.PdaApi;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.pda.PdaDetailBean;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;

import io.reactivex.Flowable;

public class PdaRepository {

    private volatile static PdaRepository INSTANCE = null;

    public static PdaRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (PdaRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PdaRepository();
                }
            }
        }
        return INSTANCE;
    }

    public Flowable<PdaDetailBean> getPdaDetail(String deviceId){
        RequestContent params=RequestContent.createRepositoryParams();
        params.put("mac",deviceId);
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(PdaApi.class)
                .getPdaDetailInfo(params)
                .compose(new  HttpResultFlowableTransformer<>());
    }
}
