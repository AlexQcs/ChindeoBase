package com.chindeo.repository.data.transform.flowable;


import com.chindeo.repository.data.model.response.HttpResult;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpResultFlowableTransformer<T> implements FlowableTransformer<HttpResult<T>, T> {

    @Override
    public Publisher<T> apply(Flowable<HttpResult<T>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .flatMap(new HttpResultFlowableFunction<T>())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
