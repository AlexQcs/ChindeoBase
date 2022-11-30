package com.lazylibs.lifecycle.data.single;

import com.lazylibs.lifecycle.data.ILoadingState;
import com.lazylibs.lifecycle.data.LoadingDialogState;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 */

public class LoadingSingleTransformer<T> implements SingleTransformer<T, T> {

    private ILoadingState loadingContainer;

    public LoadingSingleTransformer() {
        this.loadingContainer = LoadingDialogState.create();
    }

    public LoadingSingleTransformer(ILoadingState container) {
        this.loadingContainer = container == null ?
                LoadingDialogState.create() : container;
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
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
                })
                .doOnSuccess(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onComplete();
                        }
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onComplete();
                        }
                    }
                });
    }

}
