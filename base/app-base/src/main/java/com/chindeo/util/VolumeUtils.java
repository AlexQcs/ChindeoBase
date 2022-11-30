package com.chindeo.util;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

/**
 *
 * 音量  亮度 设置
 */
public class VolumeUtils {

    private static VolumeUtils sIntance;

    private VolumeUtils() {
    }
    public static VolumeUtils getInstance() {
        if (sIntance == null) {
            synchronized (VolumeUtils.class) {
                if (sIntance == null) {
                    sIntance = new VolumeUtils();
                }
            }
        }
        return sIntance;
    }

    int flag = AudioManager.FLAG_PLAY_SOUND;
    AudioManager mAudioManager;


    private int getPercentage(int max,int value){//百分比
        return max * value / 100;
    }

    /**
     * 设置百分比音量  全部
     * @param mContext
     * @param value
     */
    public  void setPercentageVolume(Context mContext, int value) {
//        setPercentageVolume(mContext,"铃声音量",AudioManager.STREAM_RING,1);//没效果
//        setPercentageVolume(mContext,"闹钟音量",AudioManager.STREAM_ALARM,1);//没效果
//        setPercentageVolume(mContext,"通知音音量",AudioManager.STREAM_NOTIFICATION,1);//没效果
        setPercentageVolume(mContext,"通话音量",AudioManager.STREAM_VOICE_CALL,value);//好像没啥效果
        setPercentageVolume(mContext,"系统音量",AudioManager.STREAM_SYSTEM,value);//语音播报音量 TextToSpeechUtil.speak(text)
        setPercentageVolume(mContext,"音乐音量",AudioManager.STREAM_MUSIC,value);//MediaPlayer.create
    }

    /**
     * 设置单个百分比音量
     * @param context
     * @param text
     * @param streamType
     * @param value
     */
    public  void setPercentageVolume(Context context,String text,int streamType,int value){


        int max = getMaxVolume(context,streamType);
//        设置音量
//        int setvolume = getPercentage(max,value);
//        setVolume(context, streamType, setvolume);
        //当前音量
        int currVolume = getVolume(context,streamType);
        //不能超过百分之70
        int volume_max = getPercentage(max,value);
        //通话音量
        if(AudioManager.STREAM_VOICE_CALL == streamType) {
            //当前音量不等于70  设置为70
            if(currVolume != volume_max) {
                setVolume(context, streamType, volume_max);
            }
            Log.i("TAG", text + " -- 最大音量 ：" + max + " -- 当前音量： " + getVolume(context,streamType));
        }else {
            //当前音量小于70  设置为70
            if(currVolume < volume_max) {
                setVolume(context, streamType, volume_max);
                Log.i("TAG", text + " -- 最大音量 ：" + max + " -- 当前音量： " + getVolume(context,streamType));
            }
        }

//        int min = 0;
//        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P){
//            min = mAudioManager.getStreamMinVolume(streamType);
//        }

//        Log.e("TAG", text + " -- 最大-" + max + " -- 最小-" + min  + " -- 设置" + volume + " 百分比 -- " + value);

    }

    /**
     * 获取最大音量
     * @param context
     * @param streamType
     * @return
     */
    public int getMaxVolume(Context context,int streamType){
        if(null == mAudioManager) {
            mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        return mAudioManager.getStreamMaxVolume(streamType);
    }

    /**
     * 获取当前音量
     * @param context
     * @param streamType
     * @return
     */
    public int getVolume(Context context,int streamType){
        if(null == mAudioManager) {
            mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        return mAudioManager.getStreamVolume(streamType);
    }

    /**
     * 设置音量
     * @param context
     * @param streamType
     * @param volume
     */
    public void setVolume(Context context,int streamType,int volume){
        if(null == mAudioManager) {
            mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        mAudioManager.setStreamVolume(streamType, volume, flag);
    }
}
