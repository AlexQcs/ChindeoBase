package com.lazylibs.component.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class LocalConstants {

    private static final String SP_FILE_LANGUAGE = "sp_file_language";

    private final static String SP_KEY_CURRENT_LANGUAGE = "app.current.local.language";

    private final static String SP_KEY_CURRENT_COUNTRY = "app.current.local.country";

    private final static String SP_KEY_CURRENT_VARIANT = "app.current.local.variant";

    public static Locale getCurrentLanguage(Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        Locale defaultLocal = Locale.getDefault();
        String language = sp.getString(SP_KEY_CURRENT_LANGUAGE, defaultLocal.getLanguage());
        String country = sp.getString(SP_KEY_CURRENT_COUNTRY, defaultLocal.getCountry());
        String variant = sp.getString(SP_KEY_CURRENT_VARIANT, defaultLocal.getVariant());
        return new Locale(language, country, variant);
    }

    public static void saveCurrentLanguage(Context context, Locale locale) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_KEY_CURRENT_LANGUAGE, locale.getLanguage());
        editor.putString(SP_KEY_CURRENT_COUNTRY, locale.getCountry());
        editor.putString(SP_KEY_CURRENT_VARIANT, locale.getVariant());
        editor.apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_FILE_LANGUAGE, Context.MODE_PRIVATE);
    }

}
