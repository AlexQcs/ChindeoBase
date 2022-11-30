package com.lazylibs.lifecycle.data;

/**
 */

public class LoadingDialogState {

    private static IBuilder builder;

    public static void init(IBuilder state) {
        builder = state;
    }

    public static ILoadingState create() {
        if (builder == null) {
            builder = new IBuilder() {
                @Override
                public ILoadingState onCreateLoadingState() {
                    return new SimpleLoadingState();
                }
            };
        }
        return builder.onCreateLoadingState();
    }

    public interface IBuilder {

        ILoadingState onCreateLoadingState();

    }

}
