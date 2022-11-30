package com.lazylibs.weight.skeleton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;

/**
 */

public class Skeleton {

    private static volatile IStateView stateView;

    public interface IStateView {

        View onCreateView(Context context, ViewGroup container, @ViewState int viewState);

    }

    public static void init(IStateView stateView) {
        Skeleton.stateView = stateView;
    }

    public static IStateView getStateView() {
        if (stateView == null) {
            stateView = new SimpleStateView();
        }
        return stateView;
    }

    /**
     * create view skeleton
     *
     * @param rootView has parent rootView
     * @return ViewSkeleton
     */
    public static ViewSkeleton create(@NonNull View rootView) {
        return createViewSkeleton(rootView);
    }

    /**
     * create view skeleton with customer IStateView
     *
     * @param rootView  has parent rootView
     * @param stateView customer IStateView
     * @return ViewSkeleton
     */
    public static ViewSkeleton create(@NonNull View rootView, @NonNull IStateView stateView) {
        return createViewSkeleton(rootView, stateView);
    }

    private static ViewSkeleton createViewSkeleton(@NonNull View view, IStateView stateView) {
        ViewParent viewParent = view.getParent();
        ViewGroup container = viewParent == null ? null : (ViewGroup) viewParent;
        Context context = view.getContext();
        return new ViewSkeleton.Builder(view)
                .empty(stateView.onCreateView(context, container, ViewState.EMPTY))
                .loading(stateView.onCreateView(context, container, ViewState.LOADING))
                .error(stateView.onCreateView(context, container, ViewState.ERROR))
                .noInternet(stateView.onCreateView(context, container, ViewState.NO_INTERNET))
                .build();
    }

    private static ViewSkeleton createViewSkeleton(@NonNull View view) {
        ViewParent viewParent = view.getParent();
        ViewGroup container = viewParent == null ? null : (ViewGroup) viewParent;
        return new ViewSkeleton.Builder(view)
                .empty(onCreateView(view, container, ViewState.EMPTY))
                .loading(onCreateView(view, container, ViewState.LOADING))
                .error(onCreateView(view, container, ViewState.ERROR))
                .noInternet(onCreateView(view, container, ViewState.NO_INTERNET))
                .build();
    }

    private static View onCreateView(View view, ViewGroup container, @ViewState int viewState) {
        return getStateView().onCreateView(view.getContext(), container, viewState);
    }

}
