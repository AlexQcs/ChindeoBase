package com.lazylibs.util;

import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by lazy2b on 18/3/26.
 */

public class TextViewUtils {
    public static void setTextSize(TextView textView, int dp) {
        if (textView != null) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dp);
        }
    }
}
