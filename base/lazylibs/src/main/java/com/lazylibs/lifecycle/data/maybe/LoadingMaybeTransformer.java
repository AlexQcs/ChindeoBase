package com.lazylibs.lifecycle.data.maybe;

import com.lazylibs.lifecycle.data.ILoadingState;
import com.lazylibs.lifecycle.data.LoadingDialogState;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 */

public class LoadingMaybeTransformer<T> implements MaybeTransformer<T, T> {

    private ILoadingState loadingContainer;

    public LoadingMaybeTransformer() {
        this.loadingContainer = LoadingDialogState.create();
    }

    public LoadingMaybeTransformer(ILoadingState container) {
        this.loadingContainer = container == null ?
                LoadingDialogState.create() : container;
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
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
                }).doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onComplete();
                        }
                    }
                });
    }

}
