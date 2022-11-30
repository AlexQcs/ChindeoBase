package com.lazylibs.lifecycle.data.flowable;

import com.lazylibs.lifecycle.data.ILoadingState;
import com.lazylibs.lifecycle.data.LoadingDialogState;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 */

public class LoadingFlowableTransformer<T> implements FlowableTransformer<T, T> {

    private ILoadingState loadingContainer;

    public LoadingFlowableTransformer() {
        this.loadingContainer = LoadingDialogState.create();
    }

    public LoadingFlowableTransformer(ILoadingState container) {
        this.loadingContainer = container == null ?
                LoadingDialogState.create() : container;
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onLoading();
                        }
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onError(throwable);
                        }
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onComplete();
                        }
                    }
                }).doOnCancel(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onComplete();
                        }
                    }
                });
    }

}
