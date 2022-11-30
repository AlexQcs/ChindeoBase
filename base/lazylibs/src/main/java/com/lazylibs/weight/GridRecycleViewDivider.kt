package com.lazylibs.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


open class GridRecycleViewDivider(
    context: Context?,
    orientation: Int,
    dividerHeight: Int,
    dividerColor: Int
) : RecycleViewDivider(context, orientation, dividerHeight, dividerColor) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.adapter == null || parent.layoutManager !is GridLayoutManager) {
            super.getItemOffsets(outRect, view, parent, state)
            return
        }
        val length = parent.adapter!!.itemCount
        val position = parent.getChildAdapterPosition(view)
        (parent.layoutManager as GridLayoutManager)?.let {
            var mod = length % it.spanCount
            if (mOrientation == GridLayoutManager.VERTICAL) {
                mod = if (mod == 0) it.spanCount else mod
                if (position >= length - mod) {
                    outRect[0, 0, 0] = 0
                    return
                }
            } else {
                if ((position + 1) % it.spanCount == 0) {
                    outRect[0, 0, 0] = 0
                    return
                }
            }
        }
        super.getItemOffsets(outRect, view, parent, state)
    }

    /**
     * 绘制纵向列表时的分隔线  这时分隔线是横着的
     * 每次 left相同，top根据child变化，right相同，bottom也变化
     *
     * @param canvas
     * @param parent
     */
    override fun drawVertical(canvas: Canvas, parent: RecyclerView) {
//        val left = parent.paddingLeft
//        val right = parent.measuredWidth - parent.paddingRight
//        val childSize = parent.childCount
//        for (i in 0 until childSize) {
//            val child = parent.getChildAt(i)
//            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
//            val top = child.bottom + layoutParams.bottomMargin
//            val bottom = top + mDividerHeight
//            if (mDivider != null) {
//                mDivider.setBounds(left, top, right, bottom)
//                mDivider.draw(canvas)
//            }
//            if (mPaint != null) {
//                canvas.drawRect(
//                    left.toFloat(),
//                    top.toFloat(),
//                    right.toFloat(),
//                    bottom.toFloat(),
//                    mPaint
//                )
//            }
//        }
    }

    /**
     * 绘制横向列表时的分隔线  这时分隔线是竖着的
     * l、r 变化； t、b 不变
     *
     * @param canvas
     * @param parent
     */
    override fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
//        val top = parent.paddingTop
//        val bottom = parent.measuredHeight - parent.paddingBottom
//        val childSize = parent.childCount
//        for (i in 0 until childSize) {
//            val child = parent.getChildAt(i)
//            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
//            val left = child.right + layoutParams.rightMargin
//            val right = left + mDividerHeight
//            if (mDivider != null) {
//                mDivider.setBounds(left, top, right, bottom)
//                mDivider.draw(canvas)
//            }
//            if (mPaint != null) {
//                canvas.drawRect(
//                    left.toFloat(),
//                    top.toFloat(),
//                    right.toFloat(),
//                    bottom.toFloat(),
//                    mPaint
//                )
//            }
//        }
    }


}