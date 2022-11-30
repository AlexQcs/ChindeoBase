package com.lazylibs.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

/**
 * @auther: wr
 * @date: 2018-12-07 11:02
 * @describe: 用于兼容tint着色器
 */
public class ViewCompatUtils {

    public static void setViewWhiteBackgroundTint(Context context, View view){
        setViewBackgroundTint(context, view, android.R.color.white);
    }

    //着色器兼容5.0以下
    public static void setViewBackgroundTint(Context context, View view, @ColorRes int resId){
        //获得相关的Drawable
        Drawable drawable = view.getBackground();
        if(drawable == null){
            throw new RuntimeException("drawable can't null");
        }

        drawable = newDrawableList(context, drawable, resId);
        ViewCompat.setBackground(view, drawable);
    }

    //着色器兼容5.0以下 多状态
    public static void setViewBackgroundTintList(Context context, View view, @ColorRes int resId){
        //获得相关的Drawable
        Drawable drawable = view.getBackground();
        if(drawable == null){
            throw new RuntimeException("drawable can't null");
        }
        drawable = newDrawableList(context, drawable, resId);
        ViewCompat.setBackground(view, drawable);
    }

    //新建一个有状态的drawable 兼容很有问题
    public static Drawable newDrawable(Context context, Drawable drawable, @ColorRes int resId){
        //获得ConstantState状态
        Drawable.ConstantState state = drawable.getConstantState();
        //这里我的理解是 如果该drawable有状态，就创建一个新的drawable出来
        drawable = state == null ? drawable : state.newDrawable().mutate();
        Drawable tintDrawable = DrawableCompat.wrap(drawable);
        int color = ContextCompat.getColor(context, resId);
        //DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintList(drawable, ColorStateList.valueOf(color));
        return tintDrawable;
    }

    //新建一个有状态的drawable 多状态
    public static Drawable newDrawableList(Context context, Drawable drawable, @ColorRes int resId){
        //获得ConstantState状态
        Drawable.ConstantState state = drawable.getConstantState();
        //这里我的理解是 如果该drawable有状态，就创建一个新的drawable出来
        drawable = state == null ? drawable : state.newDrawable().mutate();
        Drawable tintDrawable = DrawableCompat.wrap(drawable);
        ColorStateList tintList = AppCompatResources.getColorStateList(context, resId);
        DrawableCompat.setTintList(tintDrawable, tintList);
        return tintDrawable;
    }
}
