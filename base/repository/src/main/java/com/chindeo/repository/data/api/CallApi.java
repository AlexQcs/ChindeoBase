package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.nurses.MessageCallRecentBean;
import com.chindeo.repository.data.model.nurses.NursesChatBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.call.AudioBroadcastResourceBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface CallApi {

    /**
     *
     * @param locCode 病区编码
     * @return
     */
    @GET(ApiConstants.API_CALL_RECENT)
    Flowable<HttpResult<List<MessageCallRecentBean>>> getObtainRecentContact(@Query("locCode") String locCode);

    @GET(ApiConstants.API_CHAT_HISTORY)
    Flowable<HttpResult<List<NursesChatBean>>> getChatHistory(@QueryMap RequestContent params);

    @GET(ApiConstants.API_CHAT_QUICK_REPLY_LIST)
    Flowable<HttpResult<List<String>>> getQuickReplyList(@Query("type") String type);

    @GET(ApiConstants.API_BROADCAST_WORD_LIST)
    Flowable<HttpResult<List<AudioBroadcastResourceBean>>> getBroadcastWordList();


}
