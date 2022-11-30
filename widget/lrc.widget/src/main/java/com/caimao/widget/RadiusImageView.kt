package com.caimao.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatImageView
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.math.max
import kotlin.math.min

/**设置图片圆角 圆形 椭圆 以及边框等
 * 设置radius的值会覆盖四个角的圆角
 * Created by zqs on 2021/7/13
 */
class RadiusImageView: AppCompatImageView {
    companion object{
        private const val COLOR_DRAWABLE_DIMEN = 2
        private val BITMAP_CONFIG = Bitmap.Config.ARGB_8888
        private const val DEFAULT_BORDER_COLOR: Int = Color.GRAY
        //矩形
        const val RECT: Int = 0
        //圆
        const val CIRCLE: Int = 1
        //椭圆
        const val OVAL: Int = 2
    }

    @IntDef(RECT, CIRCLE, OVAL)
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention
    annotation class ShapeMode

    //圆角大小
    private var mLeftTopRadius: Int = 0
    private var mRightTopRadius: Int = 0
    private var mRightBottomRadius: Int = 0
    private var mLeftBottomRadius: Int = 0

    //图片的形状
    private var mShape: Int = RECT
    //图片
    private var mBitmap: Bitmap? = null
    //bitmap的画笔
    private var mBitmapShader: BitmapShader? = null
    //控件的宽高
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    // 是否选择
    private var mIsSelected: Boolean = false
    //选择的边框宽度
    private var mSelectedBorderWidth: Int = 0
    //默认的边框宽度
    private var mBorderWidth: Int = 0
    //上一次缩放类型
    private var mLastCalculateScaleType: ScaleType? = null
    //是否需要重置Shader
    private var mNeedResetShader: Boolean = false
    //缩放Matrix
    private var mMatrix: Matrix = Matrix()
    //
    private var mRectF: RectF = RectF()
    //bitmap画笔
    private var mBitmapPaint: Paint? = null
    //选中时图片的color
    private var mSelectedColorFilter: ColorFilter? = null
    //默认的图片的color
    private var mColorFilter: ColorFilter? = null
    private var mSelectedMaskColor: Int = Color.TRANSPARENT
    //
    private var mDrawRectF: RectF = RectF()
    //边框画笔
    private var mBorderPaint: Paint = Paint()
    //边框选中的颜色
    private var mSelectedBorderColor: Int = Color.WHITE
    //边框默认颜色
    private var mBorderColor: Int = Color.BLACK
    //绘制圆角的path
    private var mRoundPath: Path = Path()
    //是否有点击效果
    private var mIsTouchSelectModeEnabled: Boolean = false

    constructor(context: Context?): this(context, null)

    constructor(context: Context?, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int):
            super(context!!, attrs, defStyleAttr){
        mBorderPaint.isAntiAlias = true
        mBorderPaint.flags = Paint.ANTI_ALIAS_FLAG
        mBorderPaint.style = Paint.Style.STROKE
        context?.let {
            val arr: TypedArray = it.obtainStyledAttributes(attrs, R.styleable.RadiusImageView,
                defStyleAttr, 0)
            try {
                mIsTouchSelectModeEnabled = arr.getBoolean(
                    R.styleable.RadiusImageView_isTouchSelectModeEnabled,
                    mIsTouchSelectModeEnabled)
                mShape = arr.getInt(R.styleable.RadiusImageView_radius_shape, mShape)
                mBorderWidth = arr.getDimensionPixelSize(
                    R.styleable.RadiusImageView_borderWidth, 0)
                mSelectedBorderWidth = arr.getDimensionPixelSize(
                    R.styleable.RadiusImageView_selectedBorderWidth, 0)
                mBorderColor = arr.getColor(R.styleable.RadiusImageView_borderColor,
                    DEFAULT_BORDER_COLOR)
                mSelectedBorderColor = arr.getColor(
                    R.styleable.RadiusImageView_selectedBorderColor, mBorderColor)
                val maskColor: Int = arr.getColor(R.styleable.RadiusImageView_maskColor,
                    Color.TRANSPARENT)
                if(maskColor != Color.TRANSPARENT){
                    mColorFilter = PorterDuffColorFilter(maskColor, PorterDuff.Mode.DARKEN)
                }
                mSelectedMaskColor = arr.getColor(R.styleable.RadiusImageView_selectedMaskColor,
                    mSelectedMaskColor)
                if(mSelectedMaskColor != Color.TRANSPARENT){
                    mSelectedColorFilter = PorterDuffColorFilter(mSelectedMaskColor,
                        PorterDuff.Mode.DARKEN)
                }
                mLeftTopRadius = arr.getDimensionPixelSize(
                    R.styleable.RadiusImageView_leftTopRadius, 0)
                mRightTopRadius = arr.getDimensionPixelSize(
                    R.styleable.RadiusImageView_rightTopRadius, 0)
                mLeftBottomRadius = arr.getDimensionPixelSize(
                    R.styleable.RadiusImageView_leftBottomRadius, 0)
                mRightBottomRadius = arr.getDimensionPixelSize(
                    R.styleable.RadiusImageView_rightBottomRadius, 0)
                val radius: Int = arr.getDimensionPixelSize(R.styleable.RadiusImageView_radius_radius,
                    0)
                if(radius > 0){
                    mLeftTopRadius = radius
                    mRightTopRadius = radius
                    mLeftBottomRadius = radius
                    mRightBottomRadius = radius
                }
            }finally {
                arr.recycle()
            }
        }
    }

    override fun setAdjustViewBounds(adjustViewBounds: Boolean) {
        if(adjustViewBounds){
            throw IllegalArgumentException("不支持adjustViewBounds")
        }
    }

    fun setBorderWidth(borderWidth: Int){
        if(mBorderWidth != borderWidth){
            mBorderWidth = borderWidth
            invalidate()
        }
    }

    fun setBorderColor(@ColorInt borderColor: Int) {
        if (mBorderColor != borderColor) {
            mBorderColor = borderColor
            invalidate()
        }
    }

    fun setRadius(radius: Int) {
        if (mLeftTopRadius != radius || mRightTopRadius != radius ||
            mLeftBottomRadius != radius || mRightBottomRadius != radius) {
            mLeftTopRadius = radius
            mRightTopRadius = radius
            mLeftBottomRadius = radius
            mRightBottomRadius = radius
            if(mShape == RECT) {
                invalidate()
            }
        }
    }

    fun setSelectedBorderColor(@ColorInt selectedBorderColor: Int) {
        if (mSelectedBorderColor != selectedBorderColor) {
            mSelectedBorderColor = selectedBorderColor
            if (mIsSelected) {
                invalidate()
            }
        }

    }

    fun setSelectedBorderWidth(selectedBorderWidth: Int) {
        if (mSelectedBorderWidth != selectedBorderWidth) {
            mSelectedBorderWidth = selectedBorderWidth
            if (mIsSelected) {
                invalidate()
            }
        }
    }

    fun setSelectedMaskColor(@ColorInt selectedMaskColor: Int){
        if (mSelectedMaskColor != selectedMaskColor) {
            mSelectedMaskColor = selectedMaskColor
            if (mSelectedMaskColor != Color.TRANSPARENT) {
                mSelectedColorFilter = PorterDuffColorFilter(mSelectedMaskColor,
                    PorterDuff.Mode.DARKEN)
            } else {
                mSelectedColorFilter = null
            }
            if (mIsSelected) {
                invalidate()
            }
        }
        mSelectedMaskColor = selectedMaskColor
    }

    fun setShape(@ShapeMode mode: Int){
        if(mShape != mode){
            mShape = mode
            requestLayout()
            invalidate()
        }
    }

    override fun setSelected(selected: Boolean) {
        if(mIsSelected != selected){
            mIsSelected = selected
            invalidate()
        }
    }

    fun setTouchSelectModeEnabled(touchSelectModeEnabled: Boolean){
        mIsTouchSelectModeEnabled = touchSelectModeEnabled
    }

    fun setSelectedColorFilter(cf: ColorFilter){
        if(mSelectedColorFilter == cf){
            return
        }
        mSelectedColorFilter = cf
        if(mIsSelected){
            invalidate()
        }

    }

    override fun setColorFilter(cf: ColorFilter?) {
        if(mColorFilter == cf){
            return
        }
        mColorFilter = cf
        if(!mIsSelected){
            invalidate()
        }
    }

    fun getBorderColor(): Int {
        return mBorderColor
    }

    fun getBorderWidth(): Int {
        return mBorderWidth
    }

    fun getLeftTopRadius(): Int{
        return mLeftTopRadius
    }

    fun getRightTopRadius(): Int{
        return mRightTopRadius
    }

    fun getLeftBottomRadius(): Int{
        return mLeftBottomRadius
    }

    fun getRightBottomRadius(): Int{
        return mRightBottomRadius
    }

    fun getSelectedBorderColor(): Int {
        return mSelectedBorderColor
    }

    fun getSelectedBorderWidth(): Int {
        return mSelectedBorderWidth
    }

    fun getSelectedMaskColor(): Int {
        return mSelectedMaskColor
    }

    @ShapeMode
    fun getShape(): Int{
        return mShape
    }


    override fun isSelected(): Boolean {
        return mIsSelected
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        if(widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY){
            setMeasuredDimension(widthSize, heightSize)
            return
        }
        if(mShape == CIRCLE){
            if(widthMode == MeasureSpec.EXACTLY){
                setMeasuredDimension(widthSize, widthSize)
            } else if(heightMode == MeasureSpec.EXACTLY){
                setMeasuredDimension(heightSize, heightSize)
            } else{
                if(mBitmap == null){

                }else{
                    val w = Math.min(mBitmap!!.width, widthSize)
                    val h = Math.min(mBitmap!!.height, heightSize)
                    val size = Math.min(w, h)
                    setMeasuredDimension(size, size)
                }
            }
            return
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        setupBitmap()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        setupBitmap()
    }

    //设置图片
    private fun setupBitmap() {
        val bm = getBitmap()
        if(bm == mBitmap){
            return
        }
        mBitmap = bm
        if(mBitmap == null){
            mBitmapShader = null
            invalidate()
            return
        }
        mNeedResetShader = true
        mBitmapShader = BitmapShader(mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        if(mBitmapPaint == null){
            mBitmapPaint = Paint()
            mBitmapPaint!!.isAntiAlias = true
        }
        mBitmapPaint!!.shader = mBitmapShader
        requestLayout()
        invalidate()
    }

    //获取bitmap
    private fun getBitmap(): Bitmap? {
        if(drawable == null){
            return null
        }
        if(drawable is BitmapDrawable){
            val bitmap = (drawable as BitmapDrawable).bitmap ?: return null
            val bmWidth = bitmap.width
            val bmHeight = bitmap.height
            if(bmWidth == 0 || bmHeight == 0){
                return null
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                val minScaleX = minimumWidth / bmWidth.toFloat()
                val minScaleY = minimumHeight / bmHeight.toFloat()
                if(minScaleX > 1f || minScaleY > 1f){
                    val scale = max(minScaleX, minScaleY)
                    val matrix = Matrix()
                    matrix.postScale(scale, scale)
                    return Bitmap.createBitmap(bitmap, 0, 0, bmWidth, bmHeight, matrix,
                        false)
                }
            }
            return bitmap
        }
        return try{
            val bitmap = if(drawable is ColorDrawable){

                Bitmap.createBitmap(COLOR_DRAWABLE_DIMEN, COLOR_DRAWABLE_DIMEN, BITMAP_CONFIG)
            } else{
                Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, BITMAP_CONFIG)
            }
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        } catch (e: Exception){
            null
        }
    }

    //根据缩放的Type进行缩放图片
    private fun updateMatrix(matrix: Matrix, bitmap: Bitmap, drawRect: RectF){
        val bmWidth = bitmap.width
        val bmHeight = bitmap.height
        if(scaleType == ScaleType.MATRIX){
            updateScaleTypeMatrix(matrix, drawRect)
        } else if(scaleType == ScaleType.CENTER){
            val left = (mWidth - bmWidth) / 2f
            val top = (mHeight - bmHeight) / 2f
            matrix.postTranslate(left, top)
            drawRect.set(Math.max(0f, left), Math.max(0f, top),
                min(left + bmWidth, mWidth.toFloat()),
                min(top + bmHeight, mHeight.toFloat())
            )
        } else if(scaleType == ScaleType.CENTER_CROP){
            val scaleX = mWidth / bmWidth.toFloat()
            val scaleY = mHeight / bmHeight.toFloat()
            val scale = Math.max(scaleX, scaleY)
            matrix.setScale(scale, scale)
            matrix.postTranslate(-(scale * bmWidth - mWidth) / 2f,
                -(scale * bmHeight - mHeight) / 2f)
            drawRect.set(0f, 0f, mWidth.toFloat(), mHeight.toFloat())
        } else if(scaleType == ScaleType.CENTER_INSIDE){
            val scaleX = mWidth / bmWidth.toFloat()
            val scaleY = mHeight / bmHeight.toFloat()
            if(scaleX >= 1f && scaleY >= 1f){
                val left = (mWidth - bmWidth) / 2f
                val top = (mHeight - bmHeight) / 2f
                matrix.postTranslate(left, top)
                drawRect.set(left, top, left + bmWidth, top + bmHeight)
            } else{
                val scale = min(scaleX, scaleY)
                matrix.setScale(scale, scale)
                val bw = bmWidth * scale
                val bh = bmHeight * scale
                val left = (mWidth - bw) / 2f
                val top = (mHeight - bh) / 2f
                matrix.postTranslate(left, top)
                drawRect.set(left, top, left + bw, top + bh)
            }
        } else if(scaleType == ScaleType.FIT_XY){
            val scaleX = mWidth / bmWidth.toFloat()
            val scaleY = mHeight / bmHeight.toFloat()
            matrix.setScale(scaleX, scaleY)
            drawRect.set(0f, 0f, mWidth.toFloat(), mHeight.toFloat())
        } else{
            val scaleX = mWidth / bmWidth.toFloat()
            val scaleY = mHeight / bmHeight.toFloat()
            val scale = min(scaleX, scaleY)
            matrix.setScale(scale, scale)
            val  bw = bmWidth * scale
            val bh = bmHeight * scale
            when (scaleType) {
                ScaleType.FIT_START -> drawRect.set(0f, 0f, bw, bh)
                ScaleType.FIT_CENTER -> {
                    val left = (mWidth - bw) / 2f
                    val top = (mHeight - bh) / 2f
                    matrix.postTranslate(left, top)
                    drawRect.set(left, top, left + bw, top + bh)
                }
                else -> {
                    val left = mWidth - bw
                    val top = mHeight - bh
                    matrix.postTranslate(left, top)
                    drawRect.set(left, top, mWidth.toFloat(), mHeight.toFloat())
                }
            }
        }

    }

    private fun updateScaleTypeMatrix(matrix: Matrix, drawRect: RectF) {
        matrix.set(getMatrix())
        drawRect.set(0f, 0f, mWidth.toFloat(), mHeight.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        if(width <= 0 || height <= 0){
            return
        }
        canvas?.let {
            val borderWidth = if(mIsSelected) mSelectedBorderWidth else mBorderWidth
            if(mBitmap == null || mBitmapShader == null){
                drawBorder(it, borderWidth)
                return
            }
            if(mWidth != width || mHeight != height || mLastCalculateScaleType != scaleType ||
                mNeedResetShader){
                mWidth = width
                mHeight = height
                mLastCalculateScaleType = scaleType
                upDateBitmapShader()
            }
            drawBitmap(it, borderWidth)
            drawBorder(it, borderWidth)
        }
    }

    //更新BitmapShader
    private fun upDateBitmapShader() {
        mMatrix.reset()
        mNeedResetShader = false
        if(mBitmapShader == null || mBitmap == null){
            return
        }
        updateMatrix(mMatrix, mBitmap!!, mRectF)
        mBitmapShader!!.setLocalMatrix(mMatrix)
        mBitmapPaint!!.shader = mBitmapShader
    }

    //画bitmap
    private fun drawBitmap(it: Canvas, borderWidth: Int) {
        mBitmapPaint!!.colorFilter = if(mIsSelected) mSelectedColorFilter else mColorFilter
        it.save()
        when(mShape){
            CIRCLE -> {
                val minWidth: Float = mRectF.width() / 2f
                val minHeight: Float = mRectF.height() / 2f
                val radius: Float = min(minWidth, minHeight) - borderWidth
                it.drawCircle(mRectF.centerX(), mRectF.centerY(), radius, mBitmapPaint!!)
            }
            else -> {
                mDrawRectF.left = mRectF.left + borderWidth
                mDrawRectF.top = mRectF.top + borderWidth
                mDrawRectF.right = mRectF.right - borderWidth
                mDrawRectF.bottom = mRectF.bottom  - borderWidth
                if(mShape == OVAL){
                    it.drawOval(mDrawRectF, mBitmapPaint!!)
                } else{
                    mRoundPath.reset()
                    val leftTopRadius: Float = mLeftTopRadius.toFloat()
                    val rightTopRadius: Float = mRightTopRadius.toFloat()
                    val leftBottomRadius: Float = mLeftBottomRadius.toFloat()
                    val rightBottomRadius: Float = mRightBottomRadius.toFloat()
                    val array: FloatArray = floatArrayOf(leftTopRadius, leftTopRadius,
                        rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius,
                        leftBottomRadius, leftBottomRadius)
                    mRoundPath.addRoundRect(mDrawRectF, array, Path.Direction.CW)
                    //it.drawPath(mRoundPath, mBitmapPaint)
                    it.clipPath(mRoundPath)
                    it.drawRect(mDrawRectF, mBitmapPaint!!)
                }
            }
        }
        it.restore()
    }

    //画边框
    private fun drawBorder(it: Canvas, borderWidth: Int) {
        if(borderWidth <= 0){
            return
        }
        val halfBorderWidth = borderWidth / 2f
        mBorderPaint.color = if(mIsSelected) mSelectedBorderColor else mBorderColor
        mBorderPaint.strokeWidth = borderWidth.toFloat()

        when(mShape){
            CIRCLE -> {
                val minWidth: Float = mRectF.width() / 2f
                val minHeight: Float = mRectF.height() / 2f
                val radius: Float = min(minWidth, minHeight) - halfBorderWidth
                it.drawCircle(mRectF.centerX(), mRectF.centerY(), radius, mBorderPaint)
            }
            else -> {
                mDrawRectF.left = mRectF.left + halfBorderWidth
                mDrawRectF.top = mRectF.top + halfBorderWidth
                mDrawRectF.right = mRectF.right - halfBorderWidth
                mDrawRectF.bottom = mRectF.bottom - halfBorderWidth
                if(mShape == OVAL){
                    it.drawOval(mDrawRectF, mBorderPaint)
                } else{
                    mRoundPath.reset()
                    val leftTopRadius: Float = mLeftTopRadius.toFloat()
                    val rightTopRadius: Float = mRightTopRadius.toFloat()
                    val leftBottomRadius: Float = mLeftBottomRadius.toFloat()
                    val rightBottomRadius: Float = mRightBottomRadius.toFloat()
                    val array: FloatArray = floatArrayOf(leftTopRadius, leftTopRadius,
                        rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius,
                        leftBottomRadius, leftBottomRadius)
                    mRoundPath.addRoundRect(mDrawRectF, array, Path.Direction.CW)
                    it.drawPath(mRoundPath, mBorderPaint)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(!isClickable){
            isSelected = false
            return super.onTouchEvent(event)
        }
        if (!mIsTouchSelectModeEnabled) {
            return super.onTouchEvent(event)
        }
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> this.isSelected = true
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_SCROLL,
            MotionEvent.ACTION_OUTSIDE,
            MotionEvent.ACTION_CANCEL -> this.isSelected = false
        }
        return super.onTouchEvent(event)
    }

}