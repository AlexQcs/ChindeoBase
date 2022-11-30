package com.chindeo.util;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.TextUtils;
import android.util.Log;


import java.util.HashMap;
import java.util.Locale;

public class TextToSpeechUtil {
    public interface OnSpeakCompletedListener {
        //type 0start 1done 2error
        void onSpeakCompleted(int type, String utteranceId);
    }

    public static void setOnSpeakCompletedListener(OnSpeakCompletedListener onSpeakCompletedListener) {
        TextToSpeechUtil.onSpeakCompletedListener = onSpeakCompletedListener;
    }

    public static OnSpeakCompletedListener onSpeakCompletedListener;

    //初始化
    public static void init(Context context) {
        if (textToSpeech == null) {
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = textToSpeech.setLanguage(Locale.CHINA);
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e(TextToSpeechUtil.class.getName(), "数据丢失或不支持");
                        }
                        textToSpeech.setOnUtteranceProgressListener(onUtteranceProgressListener);
                    }
                }
            }, "com.google.android.tts");
        }
    }

    private static TextToSpeech textToSpeech;

    //播放
    public static void stop() {
        if (textToSpeech == null) {
            return;
        }
        textToSpeech.stop();
    }

    private static UtteranceProgressListener onUtteranceProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {
            Log.e(TextToSpeechUtil.class.getName(), "onStart:" + utteranceId);
            if (onSpeakCompletedListener != null) {
                onSpeakCompletedListener.onSpeakCompleted(0, utteranceId);
            }
        }

        @Override
        public void onDone(String utteranceId) {
            Log.e(TextToSpeechUtil.class.getName(), "onDone:" + utteranceId);

            if (onSpeakCompletedListener != null) {
                onSpeakCompletedListener.onSpeakCompleted(1, utteranceId);
            }
        }

        @Override
        public void onError(String utteranceId) {
            Log.e(TextToSpeechUtil.class.getName(), "onError" + utteranceId);
            if (onSpeakCompletedListener != null) {
                onSpeakCompletedListener.onSpeakCompleted(2, utteranceId);
            }
        }
    };

    //播放
    public static void speak(String text) {
        // todo 可加队列/需考虑文章情况
        speak(text, null);
    }

    //播放
    public static void speak(String text, OnSpeakCompletedListener listener) {
        if (textToSpeech == null || TextUtils.isEmpty(text)) {
            return;
        }

        Log.v(TextToSpeechUtil.class.getName(), "speak" + text);
        setOnSpeakCompletedListener(listener);
        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是2常规
        textToSpeech.setPitch(1f);
        //设定语速 ，默认1.0正常语速
        textToSpeech.setSpeechRate(1f);
        //朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
        final HashMap<String, String> ttsOptions = new HashMap<>();
        ttsOptions.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "utterance");//utterance，这个参数随便写，用于监听播报完成的回调中
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, ttsOptions);

    }
}
