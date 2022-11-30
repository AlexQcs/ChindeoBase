package com.caimao.widget.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/6/13 16:20
 * @UpdateUser:
 * @UpdateDate: 2020/6/13 16:20
 * @UpdateRemark:
 * @Version:
 */
public abstract class FragPageAdapterVp<T> extends BaseFragPageAdapterVp<T,TabViewHolder>{

    public FragPageAdapterVp(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

}
