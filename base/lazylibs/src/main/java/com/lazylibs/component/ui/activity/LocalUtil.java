package com.lazylibs.component.ui.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import com.lazylibs.component.util.LocalConstants;

import java.util.Locale;

class LocalUtil {

    public static Context checkLanguage(Context context) {
        try {
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Locale locale = LocalConstants.getCurrentLanguage(context);
            // 设置语言，版本做兼容
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(locale);
                LocaleList localeList = new LocaleList(locale);
                LocaleList.setDefault(localeList);
                configuration.setLocales(localeList);
                context = context.createConfigurationContext(configuration);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
                context = context.createConfigurationContext(configuration);
            } else {
                configuration.locale = locale;
                resources.updateConfiguration(configuration, displayMetrics);
            }
        }catch (Exception e){}
        return context;
    }

}
