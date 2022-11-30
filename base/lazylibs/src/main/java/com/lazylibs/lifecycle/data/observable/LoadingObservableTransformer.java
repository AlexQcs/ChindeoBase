package com.lazylibs.lifecycle.data.observable;

import com.lazylibs.lifecycle.data.ILoadingState;
import com.lazylibs.lifecycle.data.LoadingDialogState;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 */

public class LoadingObservableTransformer<T> implements ObservableTransformer<T, T> {

    private ILoadingState loadingContainer;

    public LoadingObservableTransformer() {
        this.loadingContainer = LoadingDialogState.create();
    }

    public LoadingObservableTransformer(ILoadingState container) {
        this.loadingContainer = container == null ?
                LoadingDialogState.create() : container;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
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
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onError(throwable);
                        }
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
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
