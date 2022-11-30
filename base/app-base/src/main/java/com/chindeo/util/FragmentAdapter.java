package com.chindeo.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * Created by Android Studio.
 * User: Dennis
 * Date: 2020/3/17
 * Time: 16:31
 * desc:viewpager2适配器
 */
public class FragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> mFragments;

    public FragmentAdapter(@NonNull Fragment fragment, List<Fragment> fragments) {
        super(fragment);
        this.mFragments = fragments;
    }
//
//    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
//        super(fragmentActivity);
//        this.mFragments = fragments;
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}
