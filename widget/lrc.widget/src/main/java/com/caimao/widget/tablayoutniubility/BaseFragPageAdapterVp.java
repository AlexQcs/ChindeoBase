package com.caimao.widget.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/6/13 16:20
 * @UpdateUser:
 * @UpdateDate: 2020/6/13 16:20
 * @UpdateRemark:
 * @Version:
 */
public abstract class BaseFragPageAdapterVp<T, V extends IViewHolder> extends FragmentStatePagerAdapter implements IFragPageAdapter<T, V> {
    private List<T> list_bean = new ArrayList<>();
    private FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks;
//    private ILifecycleCallback lifecycleCallback;
    public BaseFragPageAdapterVp(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
//        fragmentLifecycleCallbacks=new FragmentManager.FragmentLifecycleCallbacks() {
//            @Override
//            public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
//                super.onFragmentViewDestroyed(fm, f);
//                fm.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks);
//                if(lifecycleCallback!=null){
//                    lifecycleCallback.onFragmentViewDestroyed();
//                    lifecycleCallback=null;
//                }
//            }
//        };
    }

//    public void setLifecycleCallback(ILifecycleCallback lifecycleCallback) {
//        this.lifecycleCallback = lifecycleCallback;
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return createFragment(list_bean.get(position), position);

    }

    @Override
    public int getCount() {
        return list_bean.size();
    }


    @Override
    public void onTabClick(V holder, int position, T bean) {
    }

    @Override
    public void onTabScrolled(V holderCurrent, int positionCurrent,
                              boolean fromLeft2RightCurrent, float positionOffsetCurrent,
                              V holder2, int position2,
                              boolean fromLeft2Right2, float positionOffset2) {
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     */
    /**
     * @param list_bean
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return (W) this;
    }

    public List<T> getList_bean() {
        return list_bean;
    }

    /**
     * ????????????position?????????Item
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;

    }

    /**
     * ????????????position?????????Item ,??????notify,
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W remove(int position) {
        removeNoNotify(position);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * ??????????????????item
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return (W) this;

    }

    /**
     * ??????????????????item,??????notify
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyDataSetChanged();
        return (W) this;

    }


    /**
     * ??????????????????item
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W addNoNotify(T bean) {
        list_bean.add(bean);
        return (W) this;

    }

    /**
     * ??????????????????item,??????notify
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W add(T bean) {
        addNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * ??????????????????item???position 0
     */

    public <W extends BaseFragPageAdapterVp<T, V>> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return (W) this;

    }

    /**
     * ??????????????????item???position 0,??????notify
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * ??????List
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return (W) this;

    }

    /**
     * ??????List,??????notify
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W add(List<T> beans) {
        addNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * ??????????????????List
     */

    public <W extends BaseFragPageAdapterVp<T, V>> W clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return (W) this;

    }


    /**
     * ??????????????????
     */

    public <W extends BaseFragPageAdapterVp<T, V>> W clearAddNoNotify(T bean) {
        clearAdd(bean);
        return (W) this;

    }

    /**
     * ??????????????????,??????notify
     */

    public <W extends BaseFragPageAdapterVp<T, V>> W clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * ??????????????????List,??????notify
     */

    public <W extends BaseFragPageAdapterVp<T, V>> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * ??????List???position 0
     */

    public <W extends BaseFragPageAdapterVp<T, V>> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return (W) this;

    }

    /**
     * ??????List???position 0,??????notify
     */

    public <W extends BaseFragPageAdapterVp<T, V>> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        //?????????????????????
//        notifyItemRangeInserted(0, beans.size());
        notifyDataSetChanged();
        return (W) this;

    }

    /**
     * ??????list
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W clearNoNotify() {
        list_bean.clear();
        return (W) this;

    }

    /**
     * ??????list
     */
    public <W extends BaseFragPageAdapterVp<T, V>> W clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return (W) this;

    }


    public <W extends BaseFragPageAdapterVp<T, V>> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;

    }

    public <W extends BaseFragPageAdapterVp<T, V>> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyDataSetChanged();
        return (W) this;
    }
}
