package com.chindeo.repository.util;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.logging.HttpLoggingInterceptor;

public class LoggerImpl implements HttpLoggingInterceptor.Logger {


    private StringBuffer builder;
    private static final String TAG = "OkHttp";

    //    @Override
//    public void log(@NonNull String message) {
//        String tag = "OkHttp";
//        if (BuildConfig.DEBUG) {
//            try {
//                new JSONObject(message);
//                CLog.t(tag).json(message);
//            } catch (JSONException e) {
//                Platform.get().log(okhttp3.internal.platform.Platform.INFO, message, null);
//            }
//        }
//    }
    @Override
    public void log(@NonNull String message) {
        if (message.contains("http") && (message.startsWith("-->") || message.startsWith("<--"))) {
            builder = new StringBuffer("↓");
        }
        builder.append("\n").append(format(message));
        if (builder.toString().length()>3000){
            String simple = builder.substring(0, 3000);
            builder = new StringBuffer();
            builder.append(simple);
        }
        if (message.contains("END") && (message.startsWith("-->") || message.startsWith("<--"))) {
            LogUtils.d(TAG + ":" + builder.toString());
            builder = new StringBuffer();
        }
    }

    public String format(String message) {
        if (TextUtils.isEmpty(message)) {
            return "";
        }
        message = message.trim();
        if (message.startsWith("{")) {
            try {
                JSONObject jsonObject = new JSONObject(message);
                return jsonObject.toString(2);
            } catch (JSONException e) {
            }
        } else if (message.startsWith("[")) {
            try {
                JSONArray jsonArray = new JSONArray(message);
                return jsonArray.toString(2);
            } catch (JSONException e) {
            }
        }
        return message;
    }
}
