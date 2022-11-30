package com.lazylibs.adapter.rv;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lazylibs.adapter.IAdapter;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;

public abstract class BaseItemViewBinder<T, VH extends RecyclerView.ViewHolder> extends ItemViewBinder<T, VH> implements IAdapter<T> {

    public void add(T t) {
        add(getAdapter().getItems().size(), t);
    }

    public void add(int position, T t) {
        Items items = ((Items) getAdapter().getItems());
        // 处理越界问题导致崩溃
        if (position < 0 || position > items.size()) {
            return;
        }
        items.add(position, t);
        getAdapter().notifyItemInserted(position);
    }

    public void remove(T t) {
        int index = getAdapter().getItems().indexOf(t);
        if (index != -1) {
            remove(index);
        }
    }

    public void remove(int position) {
        Items items = ((Items) getAdapter().getItems());
        // 处理越界问题导致崩溃
        if (position < 0 || position >= items.size()) {
            return;
        }
        items.remove(position);
        getAdapter().notifyItemRemoved(position);
    }

    public void addAll(List<T> data) {
        addAll(getAdapter().getItems().size(), data);
    }

    public void addAll(int position, List<T> data) {
        Items items = ((Items) getAdapter().getItems());
        // 处理越界问题导致崩溃
        if (position < 0 || position > items.size()) {
            return;
        }
        items.addAll(position, data);
        getAdapter().notifyItemRangeInserted(position, data.size());
    }

    public void setData(@NonNull List<T> data) {
        getAdapter().setItems(data);
        getAdapter().notifyDataSetChanged();
    }

}
