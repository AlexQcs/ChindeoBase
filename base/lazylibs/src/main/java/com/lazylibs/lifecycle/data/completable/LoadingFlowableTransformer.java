package com.lazylibs.lifecycle.data.completable;

import com.lazylibs.lifecycle.data.ILoadingState;
import com.lazylibs.lifecycle.data.LoadingDialogState;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 */

public class LoadingFlowableTransformer implements CompletableTransformer {

    private ILoadingState loadingContainer;

    public LoadingFlowableTransformer() {
        this.loadingContainer = LoadingDialogState.create();
    }

    public LoadingFlowableTransformer(ILoadingState container) {
        this.loadingContainer = container == null ?
                LoadingDialogState.create() : container;
    }

    /**
     * Applies a function to the upstream Completable and returns a CompletableSource.
     *
     * @param upstream the upstream Completable instance
     * @return the transformed CompletableSource instance
     */
    @Override
    public CompletableSource apply(Completable upstream) {
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
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (loadingContainer != null) {
                            loadingContainer.onComplete();
                        }
                    }
                });
    }

}
