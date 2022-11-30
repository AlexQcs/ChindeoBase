package com.lazylibs.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;

public class DrawableTintUtil {

    /**
     * Drawable 颜色转化类
     *
     * @param drawable 源Drawable
     * @param color    color资源
     * @return 改变颜色后的Drawable
     */
    @SuppressWarnings("UnusedReturnValue")
    public static Drawable tintDrawable(@NonNull Drawable drawable, @ColorInt int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }

    /**
     * Drawable 颜色转化类
     *
     * @param drawable 源Drawable
     * @param colors   ColorStateList
     * @return 改变颜色后的Drawable
     */
    @SuppressWarnings("UnusedReturnValue")
    public static Drawable tintListDrawable(@NonNull Drawable drawable, @NonNull ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static void tintDrawable(TextView tv, @ColorInt int color) {
        Drawable[] drawables = tv.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                DrawableTintUtil.tintDrawable(drawable, color);
            }
        }
        tv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static void tintListDrawable(TextView tv, ColorStateList colorStateList) {
        Drawable[] drawables = tv.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                DrawableTintUtil.tintListDrawable(drawable, colorStateList);
            }
        }
        tv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }
}
