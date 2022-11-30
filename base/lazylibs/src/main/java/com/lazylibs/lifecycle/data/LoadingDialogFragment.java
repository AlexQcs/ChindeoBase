package com.lazylibs.lifecycle.data;

import android.app.DialogFragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.lazylibs.R;


/**
 *
 */

public class LoadingDialogFragment extends DialogFragment {

    public static LoadingDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.lazylibs_LoadingDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View inflate = inflater.inflate(R.layout.lazylibs_fragment_simple_loading, container, false);
        AppCompatImageView progressBar = inflate.findViewById(R.id.connecting_progress);
        // 获取View上的动画背景
        AnimationDrawable spinner = (AnimationDrawable) progressBar.getBackground();
        // 开始动画
        spinner.start();
        return inflate;
    }

}
