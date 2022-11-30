package com.caimao.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView


public class CdoTextView  : AppCompatTextView {
    /**
     * 底色背景
     */
    private var norDrawable = GradientDrawable()

    /**
     * 禁用点击背景
     */
    private var unableDrawable = GradientDrawable()

    /**
     * 涟漪
     */
    private lateinit var rippleDrawable: RippleDrawable

    /**
     * 涟漪颜色
     */
    private var rippleColor = Color.parseColor("#22000000")

    /**
     * 圆角位置
     */
    public val TOP_LEFT = 0x30
    public val TOP_RIGHT = 0x50
    public val BOTTOM_LEFT = 0x03
    public val BOTTOM_RIGHT = 0x05

    /**
     *  top-left, top-right, bottom-right, bottom-left
     *  每个角一对 (x,y)  4个角一共4对 8 length
     */
    private var radii = FloatArray(8)

    /**
     * shape颜色
     */
    private var shape = GradientDrawable.RECTANGLE

    /**
     * 实心背景颜色
     */
    private var solidColors = Color.TRANSPARENT

    /**
     * 边框颜色
     */
    private var strokeColor = Color.TRANSPARENT

    /**
     * 禁用点击时的实心颜色
     */
    private var unableSolidColor = Color.TRANSPARENT

    /**
     * 禁用点击时的边框颜色
     */
    private var unableStrokeColor = Color.TRANSPARENT

    /**
     * 禁用点击时的字体颜色
     */
    private var unableTextColor = Color.parseColor("#9F9F9F")

    /**
     * 圆角位置
     */
    private var corners = 0

    /**
     * 半径
     */
    private var radius = 0F

    /**
     * 边框颜色
     */
    private var strokeWidth = 0

    /**end
     * 开始颜色
     */
    private var startColor = Color.TRANSPARENT

    /**
     * 过渡中间颜色
     */
    private var centerColor = Color.TRANSPARENT

    /**
     * 结束颜色
     */
    private var endColor = Color.TRANSPARENT

    private var gradientOrientation = 0


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.CdoTextView)

        typedArray?.let {
            /**
             * 形状
             */
            shape = it.getInt(R.styleable.CdoTextView_shape, GradientDrawable.RECTANGLE)

            /**
             * 实心位置颜色
             */
            solidColors = it.getColor(R.styleable.CdoTextView_solid_color, Color.TRANSPARENT)

            /**
             * 边框颜色
             */
            strokeColor = it.getColor(R.styleable.CdoTextView_stroke_color, Color.TRANSPARENT)

            /**
             * 禁用点击的背景颜色
             */
            unableSolidColor =
                    it.getColor(R.styleable.CdoTextView_unable_solid_color, Color.TRANSPARENT)

            /**
             * 禁用点击的边框颜色
             */
            unableStrokeColor =
                    it.getColor(R.styleable.CdoTextView_unable_stroke_color, Color.TRANSPARENT)

            /**
             * 禁用点击的字体颜色
             */
            unableTextColor =
                    it.getColor(R.styleable.CdoTextView_unable_text_color, unableTextColor)

            /**
             * 边框宽度
             */
            strokeWidth = it.getDimensionPixelOffset(R.styleable.CdoTextView_stroke_width, 0)

            /**
             * 圆角位置
             */
            corners = it.getInt(R.styleable.CdoTextView_corners, 0)

            /**
             * 圆角半径
             */
            radius = it.getDimension(R.styleable.CdoTextView_radius, 0F)

            /**
             * 涟漪颜色
             */
            rippleColor = it.getColor(R.styleable.CdoTextView_ripple_color, rippleColor)

            /**
             * 把涟漪颜色透明化
             */
            rippleColor = Color.argb(
                    0x22,
                    Color.red(rippleColor),
                    Color.green(rippleColor),
                    Color.blue(rippleColor)
            )
            /**
             * 渐变色开始颜色
             */
            startColor = it.getColor(R.styleable.CdoTextView_start_color, startColor)
            /**
             * 渐变色过渡颜色
             */
            endColor = it.getColor(R.styleable.CdoTextView_end_color, endColor)
            /**
             * 渐变色结束颜色
             */
            centerColor = it.getColor(R.styleable.CdoTextView_center_color, centerColor)
            /**
             * 渐变色方向
             */

            gradientOrientation = it.getInt(R.styleable.CdoTextView_gradient_orientation, 0)


            setShape()
            setRadii()
            setShapeColor()
            setStroke()
            setBackgroundDrawable()
            setGradientOrientation()
            setGradientColor()
            //设置文字居中
//            gravity = Gravity.CENTER
        }
    }


    /**
     * 设置背景drawable
     */
    private fun setBackgroundDrawable() {
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(intArrayOf(android.R.attr.state_enabled), norDrawable)
        stateListDrawable.addState(intArrayOf(-android.R.attr.state_enabled), unableDrawable)
        rippleDrawable = RippleDrawable(ColorStateList.valueOf(rippleColor), stateListDrawable, null)


        background = rippleDrawable

        var colorStateList = ColorStateList(
                arrayOf(
                        intArrayOf(android.R.attr.state_enabled),
                        intArrayOf(-android.R.attr.state_enabled)
                ),
                intArrayOf(currentTextColor, unableTextColor)
        )
        setTextColor(colorStateList)
    }

    /**
     * 设置边框
     */
    private fun setStroke() {
        norDrawable.setStroke(strokeWidth, strokeColor)
        unableDrawable.setStroke(strokeWidth, unableStrokeColor)
    }

    /**
     * 设置边框颜色
     */
    fun setStrokeColor(color: Int) {
        norDrawable.setStroke(strokeWidth, color)
        unableDrawable.setStroke(strokeWidth, unableStrokeColor)
    }


    /**
     * 设置颜色
     */
    private fun setShapeColor() {
        norDrawable.setColor(solidColors)
        unableDrawable.setColor(unableSolidColor)
    }

    /**
     * 设置颜色
     */
    fun setShapeColor(color: Int) {
        norDrawable.setColor(color)
        unableDrawable.setColor(color)

    }


    /**
     * 设置圆角位置
     */
    private fun setRadii() {

        if (corners == 0) {
            radii = floatArrayOf(
                    radius, radius,
                    radius, radius,
                    radius, radius,
                    radius, radius
            )
        }
        if (corners or TOP_LEFT == corners) {
            radii[0] = radius
            radii[1] = radius
        }
        if (corners or TOP_RIGHT == corners) {
            radii[2] = radius
            radii[3] = radius
        }
        if (corners or BOTTOM_RIGHT == corners) {
            radii[4] = radius
            radii[5] = radius
        }
        if (corners or BOTTOM_LEFT == corners) {
            radii[6] = radius
            radii[7] = radius
        }
        norDrawable.cornerRadii = radii
        unableDrawable.cornerRadii = radii
    }

    /***
     * 设置圆角位置
     *
     *  <flag name="top_left" value="0x30" />
    <flag name="top_right" value="0x50" />
    <flag name="bottom_left" value="0x03" />
    <flag name="bottom_right" value="0x05" />
     */
    fun setRadiusPosition(corners: Int) {
        this.corners = corners
        setRadii()
        requestLayout()
    }


    /**
     * 设置形状
     */
    private fun setShape() {
        //设置形状
        norDrawable.shape = shape
        unableDrawable.shape = shape
    }

    private fun setGradientOrientation() {
        if (gradientOrientation == 0) {

            norDrawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT

        } else {

            norDrawable.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        }
    }

    private fun setGradientColor() {
        if (startColor == Color.TRANSPARENT || endColor == Color.TRANSPARENT) {
            return
        }

        if (centerColor != Color.TRANSPARENT) {
            val colors = intArrayOf(startColor, centerColor, endColor)
            norDrawable.colors = colors

        } else {
            val colors = intArrayOf(startColor, endColor)
            norDrawable.colors = colors
        }
    }


}