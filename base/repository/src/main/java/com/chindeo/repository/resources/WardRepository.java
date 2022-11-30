package com.chindeo.repository.resources;

import com.alibaba.fastjson.JSONObject;
import com.chindeo.repository.data.api.WardApi;
import com.chindeo.repository.data.model.nurses.WardListBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.params.setting.DevicesStatusParams;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;
import com.lazylibs.http.data.flowable.ResultFlowableTransformer;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public class WardRepository {

    private volatile static WardRepository INSTANCE = null;

    public static WardRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (WardRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WardRepository();
                }
            }
        }
        return INSTANCE;
    }


    public Flowable<List<WardListBean>> getWardList(String locCode, int type) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(WardApi.class)
                .getWardList(locCode, type)
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<ResponseBody> postWardStatus(DevicesStatusParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(WardApi.class)
                .postWardStatus(RequestContent.create(params))
                .compose(new ResultFlowableTransformer<>());
    }

}
