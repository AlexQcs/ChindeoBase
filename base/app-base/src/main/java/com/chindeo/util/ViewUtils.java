package com.chindeo.util;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.chindeo.base.R;
import com.lazylibs.util.DrawableTintUtil;


public class ViewUtils {

    public static void append(SpannableStringBuilder ssb, CharSequence text, Object what, int flags) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (what == null) {
                ssb.append(text);
            } else {
                ssb.append(text, what, flags);
            }
        } else {
            int start = ssb.length();
            ssb.append(text);
            ssb.setSpan(what, start, ssb.length(), flags);
        }
    }

    public static void setSpan(SpannableStringBuilder ssb, CharSequence text, Object what, int flags) {
        int start = ssb.toString().indexOf(text.toString());
        int end = start + text.length();
        if (start != -1 && start < end && start <= ssb.length() && end <= ssb.length()) {
            ssb.setSpan(what, start, end, flags);
        }
    }

    public static boolean isShrink(TextView tv, int width, int shrinkLineCount) {
        return isShrink(tv, tv.getText().toString(), width, shrinkLineCount);
    }

    public static boolean isShrink(TextView tv, String message, int width, int shrinkLineCount) {
        StaticLayout staticLayout;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder builder = StaticLayout.Builder.obtain(message, 0, message.length(), tv.getPaint(), width);
            staticLayout = builder.setIncludePad(false)
                    .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    .setLineSpacing(tv.getLineSpacingExtra(),
                            tv.getLineSpacingMultiplier())
                    .build();
        } else {
            staticLayout = new StaticLayout(
                    message, tv.getPaint(), width, Layout.Alignment.ALIGN_NORMAL,
                    tv.getLineSpacingMultiplier(), tv.getLineSpacingExtra(), false);
        }
        return staticLayout.getLineCount() > shrinkLineCount;
    }


    public static int getLineCount(TextView tv, CharSequence message, int width) {
        StaticLayout staticLayout;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder builder = StaticLayout.Builder.obtain(message, 0, message.length(), tv.getPaint(), width);
            staticLayout = builder.setIncludePad(false)
                    .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    .setLineSpacing(tv.getLineSpacingExtra(),
                            tv.getLineSpacingMultiplier())
                    .build();
        } else {
            staticLayout = new StaticLayout(
                    message, tv.getPaint(), width, Layout.Alignment.ALIGN_NORMAL,
                    tv.getLineSpacingMultiplier(), tv.getLineSpacingExtra(), false);
        }
        return staticLayout.getLineCount();
    }

//    public static void drawableTintListDefault(TextView tv) {
//        drawableTint(tv, ContextCompat.getColorStateList(tv.getContext(),
//                R.color.guess_default_icon_color_selector));
//    }

    public static void drawableTintDefault(TextView tv) {
        drawableTint(tv, ContextCompat.getColorStateList(tv.getContext(),
                R.color.colorPrimary));
    }

    public static void drawableTint(TextView tv, ColorStateList colorStateList) {
        Drawable[] drawables = tv.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                DrawableTintUtil.tintListDrawable(drawable, colorStateList);
            }
        }
        tv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static void drawableTint(TextView tv, @ColorInt int color) {
        Drawable[] drawables = tv.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                DrawableTintUtil.tintDrawable(drawable, color);
            }
        }
        tv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }


    public static void setBackgroundTintList(View view, ColorStateList colorStateList) {
        ViewCompat.setBackgroundTintList(view, colorStateList);
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

}
