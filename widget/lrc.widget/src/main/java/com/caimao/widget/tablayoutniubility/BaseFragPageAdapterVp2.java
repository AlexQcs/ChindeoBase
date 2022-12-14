package com.caimao.widget.tablayoutniubility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


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
public abstract class BaseFragPageAdapterVp2<T,V extends IViewHolder> extends FragmentStateAdapter implements IFragPageAdapter<T,V> {
    private List<T> list_bean = new ArrayList<>();

    public BaseFragPageAdapterVp2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public BaseFragPageAdapterVp2(@NonNull Fragment fragment) {
        super(fragment);
    }

    public BaseFragPageAdapterVp2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public final Fragment createFragment(int position) {
        return createFragment(list_bean.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list_bean.size();
    }


    @Override
    public  void onTabClick(V holder, int position, T bean){}
    @Override
    public  void onTabScrolled(V holderCurrent,int positionCurrent,
                                       boolean fromLeft2RightCurrent,float positionOffsetCurrent,
                               V holder2,int position2,
                                       boolean fromLeft2Right2,float positionOffset2){}
    /**
     * ----------------------------------------------------------------------------------------------------
     */
    /**
     * @param list_bean
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W setList_bean(List<T> list_bean) {
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
    public <W extends BaseFragPageAdapterVp2<T,V>> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;
    }

    /**
     * ????????????position?????????Item ,??????notify,
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W remove(int position) {
        removeNoNotify(position);
        /**
         onBindViewHolder?????????position??????????????????????????????item???position,
         ??????????????????????????????5???item,???????????????notifyItemRemoved(position)???
         onBindViewHolder?????????position?????????4
         */
        notifyItemRemoved(position);
        return (W) this;
    }

    /**
     * ??????????????????item
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return (W) this;
    }

    /**
     * ??????????????????item,??????notify
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyItemInserted(position);
        return (W) this;
    }


    /**
     * ??????????????????item
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W addNoNotify(T bean) {
        list_bean.add(bean);
        return (W) this;
    }

    /**
     * ??????????????????item,??????notify
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W add(T bean) {
        addNoNotify(bean);
        notifyItemInserted(list_bean.size() - 1);
        return (W) this;
    }

    /**
     * ??????????????????item???position 0
     */

    public <W extends BaseFragPageAdapterVp2<T,V>> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return (W) this;
    }

    /**
     * ??????????????????item???position 0,??????notify
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyItemInserted(0);
        return (W) this;
    }

    /**
     * ??????List
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return (W) this;
    }

    /**
     * ??????List,??????notify
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W add(List<T> beans) {
        addNoNotify(beans);
        notifyItemRangeInserted(list_bean.size() - beans.size(), beans.size());
        return (W) this;
    }

    /**
     * ??????????????????List
     */

    public <W extends BaseFragPageAdapterVp2<T,V>> W clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return (W) this;
    }


    /**
     * ??????????????????
     */

    public <W extends BaseFragPageAdapterVp2<T,V>> W clearAddNoNotify(T bean) {
        clearAdd(bean);
        return (W) this;
    }

    /**
     * ??????????????????,??????notify
     */

    public <W extends BaseFragPageAdapterVp2<T,V>> W clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * ??????????????????List,??????notify
     */

    public <W extends BaseFragPageAdapterVp2<T,V>> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * ??????List???position 0
     */

    public <W extends BaseFragPageAdapterVp2<T,V>> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return (W) this;
    }

    /**
     * ??????List???position 0,??????notify
     */

    public <W extends BaseFragPageAdapterVp2<T,V>> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        //?????????????????????
//        notifyItemRangeInserted(0, beans.size());
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * ??????list
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W clearNoNotify() {
        list_bean.clear();
        return (W) this;
    }

    /**
     * ??????list
     */
    public <W extends BaseFragPageAdapterVp2<T,V>> W clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return (W) this;
    }


    public <W extends BaseFragPageAdapterVp2<T,V>> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;
    }

    public <W extends BaseFragPageAdapterVp2<T,V>> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyItemChanged(index);
        return (W) this;
    }
}
