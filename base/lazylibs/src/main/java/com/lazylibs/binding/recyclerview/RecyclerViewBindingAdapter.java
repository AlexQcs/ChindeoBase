package com.lazylibs.binding.recyclerview;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewBindingAdapter {

    public RecyclerViewBindingAdapter() {
    }


    @BindingAdapter(
            value = {"adapter"},
            requireAll = false
    )
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }


    @BindingAdapter(
            value = {"notifyCurrentListChanged"},
            requireAll = false
    )
    public static void notifyCurrentListChanged(RecyclerView recyclerView, boolean notifyCurrentListChanged) {
        if (notifyCurrentListChanged) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    @BindingAdapter(
            value = {"autoScrollToTopWhenInsert", "autoScrollToBottomWhenInsert"},
            requireAll = false
    )
    public static void autoScroll(RecyclerView recyclerView, boolean autoScrollToTopWhenInsert, boolean autoScrollToBottomWhenInsert) {
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    if (autoScrollToTopWhenInsert) {
                        recyclerView.scrollToPosition(0);
                    } else if (autoScrollToBottomWhenInsert) {
                        recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount());
                    }

                }
            });
        }

    }
}
