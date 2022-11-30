package com.lazylibs.adapter.rv;

import androidx.annotation.NonNull;

import com.lazylibs.adapter.IAdapter;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.TypePool;

public class BaseRvAdapter extends MultiTypeAdapter implements IAdapter<Object> {
    /**
     * Constructs a MultiTypeAdapter with an empty items list.
     */
    public BaseRvAdapter() {
        super(new Items());
    }

    /**
     * Constructs a MultiTypeAdapter with a items list.
     *
     * @param items the items list
     */
    public BaseRvAdapter(@NonNull List<?> items) {
        super(items);
    }

    /**
     * Constructs a MultiTypeAdapter with a items list and an initial capacity of TypePool.
     *
     * @param items           the items list
     * @param initialCapacity the initial capacity of TypePool
     */
    public BaseRvAdapter(@NonNull List<?> items, int initialCapacity) {
        super(items, initialCapacity);
    }

    /**
     * Constructs a MultiTypeAdapter with a items list and a TypePool.
     *
     * @param items the items list
     * @param pool  the type pool
     */
    public BaseRvAdapter(@NonNull List<?> items, @NonNull TypePool pool) {
        super(items, pool);
    }

    public void add(Object o) {
        add(getItems().size(), o);
    }

    public void add(int position, Object o) {
        Items items = (Items) getItems();
        // 处理越界问题导致崩溃
        if (position < 0 || position > items.size()) {
            return;
        }
        items.add(position, o);
        notifyItemInserted(position);
    }

    public void remove(Object o) {
        int index = getItems().indexOf(o);
        if (index != -1) {
            remove(index);
        }
    }

    @SuppressWarnings("unchecked")
    public void remove(int position) {
        Items items = (Items) getItems();
        // 处理越界问题导致崩溃
        if (position < 0 || position >= items.size()) {
            return;
        }
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void addAll(List<Object> data) {
        addAll(getItems().size(), data);
    }

    @SuppressWarnings("unchecked")
    public void addAll(int position, List<Object> data) {
        Items items = (Items) getItems();
        // 处理越界问题导致崩溃
        if (position < 0 || position > items.size()) {
            return;
        }
        items.addAll(position, data);
        notifyItemRangeInserted(position, data.size());
    }

    public void setData(@NonNull List<Object> data) {
        setItems(data);
        notifyDataSetChanged();
    }

}
