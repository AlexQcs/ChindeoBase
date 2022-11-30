package com.caimao.widget.tablayoutniubility;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/8 12:10
 * @UpdateUser:
 * @UpdateDate: 2020/8/8 12:10
 * @UpdateRemark:
 * @Version:
 */
class TabNoScrollView extends LinearLayout {
    private TabAdapterNoScroll tabNoScrollAdapter;
    private SparseArray<TabNoScrollViewHolder> sparseArrayViewHolder;

    public TabNoScrollView(Context context) {
        this(context, null);
    }

    public TabNoScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        sparseArrayViewHolder = new SparseArray<>();
    }

    public void setAdapter(final TabAdapterNoScroll tabNoScrollAdapter) {
        this.tabNoScrollAdapter = tabNoScrollAdapter;
        tabNoScrollAdapter.setTabNoScrollViewCallback(new TabNoScrollCallback() {
            @Override
            public void notifyDataSetChanged() {
                dataSetChanged();
            }

            @Override
            public void notifyItemChanged(int position) {
                ItemChanged(position);
            }
        });

    }

    private void dataSetChanged(){
        removeAllViews();
        for (int i = 0; i < tabNoScrollAdapter.getItemCount(); i++) {
            TabNoScrollViewHolder tabNoScrollViewHolder = tabNoScrollAdapter.onCreateViewHolder(i, tabNoScrollAdapter.getList_bean().get(i), this);
            tabNoScrollViewHolder.setPositionAdapter(i);
            sparseArrayViewHolder.put(i, tabNoScrollViewHolder);
            addView(tabNoScrollViewHolder.itemView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            ItemChanged(i);
        }
    }
    private void ItemChanged(int position){
        TabNoScrollViewHolder tabNoScrollViewHolder = getViewHolder(position);
        tabNoScrollAdapter.onBindViewHolder(tabNoScrollViewHolder,position);
    }

    public TabAdapterNoScroll getAdapter() {
        return tabNoScrollAdapter;
    }
    public TabNoScrollViewHolder getViewHolder(int position){
        return sparseArrayViewHolder.get(position);
    }
}
