package com.lazylibs.adapter.rv;


import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class BaseRvItemTouchHelperAdapter extends BaseRvAdapter {

    private ItemTouchHelper mItemTouchHelper;

    private ItemTouchHelper.Callback mCallback;

    private ItemTouchHelper getItemTouchHelper() {
        if (mItemTouchHelper == null) {
            mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

                @Override
                public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                    super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                    if (mCallback != null) {
                        mCallback.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                    }
                }

                @Override
                public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                    if (mCallback != null) {
                        return mCallback.getMovementFlags(recyclerView, viewHolder);
                    }
                    return 0;
                }

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    if (mCallback != null) {
                        return mCallback.onMove(recyclerView, viewHolder, target);
                    }
                    return viewHolder.getItemViewType() == target.getItemViewType();
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    if (mCallback != null) {
                        mCallback.onSwiped(viewHolder, direction);
                    }
                }
            });
        }
        return mItemTouchHelper;
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.mItemTouchHelper = itemTouchHelper;
    }

    public void setCallback(ItemTouchHelper.Callback callback) {
        this.mCallback = callback;
    }

}
