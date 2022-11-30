package com.chindeo.repository.data.transform.flowable;


import com.chindeo.repository.contants.HttpResultCode;
import com.chindeo.repository.data.exception.ResponseErrorException;
import com.chindeo.repository.data.model.response.HttpResult;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.FlowableOperator;
import io.reactivex.functions.Function;

public class HttpResultFlowableFunction<T> implements Function<HttpResult<T>, Publisher<T>> {

    @Override
    public Publisher<T> apply(final HttpResult<T> response) throws Exception {
        Flowable<T> flowable;
        if (response.data == null) {
            flowable = Flowable.empty();
        } else {
            flowable = Flowable.just(response.data);
        }
        return flowable.lift(new FlowableOperator<T, T>() {
            public Subscriber<? super T> apply(Subscriber<? super T> observer) throws Exception {
                if (response.code != HttpResultCode.SUCCESS) {
                    observer.onError(new ResponseErrorException(response.code, response.errorMsg));
                }
                return observer;
            }
        });
    }

}
