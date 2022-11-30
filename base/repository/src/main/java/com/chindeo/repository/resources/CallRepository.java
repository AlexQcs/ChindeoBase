package com.chindeo.repository.resources;

import com.chindeo.repository.data.api.CallApi;
import com.chindeo.repository.data.model.nurses.MessageCallRecentBean;
import com.chindeo.repository.data.model.nurses.NursesChatBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.params.call.ChatHistoryParams;
import com.chindeo.repository.data.model.response.call.AudioBroadcastResourceBean;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;

import java.util.List;

import io.reactivex.Flowable;

public class CallRepository {

    private volatile static CallRepository INSTANCE = null;

    public static CallRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (CallRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CallRepository();
                }
            }
        }
        return INSTANCE;
    }


    public Flowable<List<MessageCallRecentBean>> getObtainRecentContact(String locCode) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(CallApi.class)
                .getObtainRecentContact(locCode)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     *
     * @param params {@link ChatHistoryParams}
     * @return
     */
    public Flowable<List<NursesChatBean>> getChatHistory(ChatHistoryParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(CallApi.class)
                .getChatHistory(RequestContent.createRepositoryParams(params))
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * @param type  快速条目 type 1:护理白板,2:床旁屏,3:护士站,4:护士站主机发广播的内容
     * @return
     */
    public Flowable<List<String>> getQuickReplyList(String type) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(CallApi.class)
                .getQuickReplyList(type)
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<List<AudioBroadcastResourceBean>> getBroadcastWordList() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(CallApi.class)
                .getBroadcastWordList()
                .compose(new HttpResultFlowableTransformer<>());
    }

}
