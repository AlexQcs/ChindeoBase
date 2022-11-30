package com.caimao.widget.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/6/13 16:20
 * @UpdateUser:
 * @UpdateDate: 2020/6/13 16:20
 * @UpdateRemark:
 * @Version:
 */
public abstract class FragPageAdapterVp2NoScroll<T> extends BaseFragPageAdapterVp2<T, TabNoScrollViewHolder> {
    public FragPageAdapterVp2NoScroll(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public FragPageAdapterVp2NoScroll(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FragPageAdapterVp2NoScroll(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
}