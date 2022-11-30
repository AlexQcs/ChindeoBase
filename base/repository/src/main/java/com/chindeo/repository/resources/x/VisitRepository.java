package com.chindeo.repository.resources.x;

import com.chindeo.repository.data.api.NursesApi;
import com.chindeo.repository.data.api.VisitApi;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.bed.EventEnrollmentBean;
import com.chindeo.repository.data.model.response.nurses.NursesVisitListBean;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;
import com.lazylibs.http.data.flowable.ResultFlowableTransformer;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * 远程探视Repository
 * Created by xiemaohui on 2022/8/26
 */
public class VisitRepository {
    private volatile static VisitRepository INSTANCE = null;

    public static VisitRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (VisitRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new VisitRepository();
                }
            }
        }
        return INSTANCE;
    }

//    /**
//     * 登录获取token
//     */
//    public Flowable<String> login(Map<String,String> map){
//        return HttpUtilFactory.create(HttpUtilImp.class)
//                .getApi(VisitApi.class)
//                .login(map)
//                .compose(new HttpResultFlowableTransformer<>());
//    }
//
//    /**
//     * 刷新token
//     */
//    public Flowable<String> refresh(){
//        return HttpUtilFactory.create(HttpUtilImp.class)
//                .getApi(VisitApi.class)
//                .refresh()
//                .compose(new HttpResultFlowableTransformer<>());
//    }

    /**
     * 获取探视预约时间列表
     */
    public  Flowable<List<NursesVisitListBean>> getVisitTimes(Map<String,String> map){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(VisitApi.class)
                .getVisitTimes(map)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * pass
     */
    public Flowable<String> visitPass(String code){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(VisitApi.class)
                .visitPass(code)
                .compose(new HttpResultFlowableTransformer<>());
    }

//    /**
//     * refuse
//     */
//    public  Flowable<ResponseBody> visitRefuse(String code,String message){
//        return HttpUtilFactory.create(HttpUtilImp.class)
//                .getApi(VisitApi.class)
//                .visitRefuse(code,message)
//                .compose(new ResultFlowableTransformer<>());
//    }
    /**
     * refuse
     */
    public  Flowable<String> visitRefuse(String code,String message){
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(VisitApi.class)
                .visitRefuse(code,message)
                .compose(new HttpResultFlowableTransformer<>());
    }

}
