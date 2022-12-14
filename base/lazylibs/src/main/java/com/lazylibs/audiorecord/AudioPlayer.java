package com.lazylibs.audiorecord;


import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;


public class AudioPlayer {

    private static final String TAG = AudioPlayer.class.getSimpleName();
    private static AudioPlayer sInstance = new AudioPlayer();
    private static String CURRENT_RECORD_FILE = Environment.getExternalStorageDirectory() + "/chindeo_app"+ "auto_";
    private static int MAGIC_NUMBER = 500;
    private static int MIN_RECORD_DURATION = 1000;
    private Callback mRecordCallback;
    private Callback mPlayCallback;

    private String mAudioRecordPath;
    private MediaPlayer mPlayer;
    private MediaRecorder mRecorder;
    private Handler mHandler;

    private AudioPlayer() {
        mHandler = new Handler();
    }

    public static AudioPlayer getInstance() {
        return sInstance;
    }


    public void startRecord(Callback callback) {
        mRecordCallback = callback;
        try {
            mAudioRecordPath = CURRENT_RECORD_FILE + System.currentTimeMillis() + ".m4a";
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 使用mp4容器并且后缀改为.m4a，来兼容小程序的播放
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mRecorder.setOutputFile(mAudioRecordPath);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mRecorder.setAudioChannels(1);
            //音质频率
            mRecorder.setAudioEncodingBitRate(128000);
            //采样频率
            mRecorder.setAudioSamplingRate(44100);
            mRecorder.prepare();
            mRecorder.start();
            // 最大录制时间之后需要停止录制
            mHandler.removeCallbacksAndMessages(null);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopInternalRecord();
                    onRecordCompleted(true);
                    mRecordCallback = null;
                }
            }, 1000 * 1000);
        } catch (Exception e) {
            stopInternalRecord();
            onRecordCompleted(false);
        }
    }

    public void stopRecord() {
        stopInternalRecord();
        onRecordCompleted(true);
        mRecordCallback = null;
    }

    private void stopInternalRecord() {
        mHandler.removeCallbacksAndMessages(null);
        if (mRecorder == null) {
            return;
        }
        mRecorder.release();
        mRecorder = null;
    }

    public void startPlay(String filePath, Callback callback) {
        stopPlay();
        mAudioRecordPath = filePath;
        mPlayCallback = callback;
        try {
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(filePath);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopInternalPlay();
                    onPlayCompleted(true);
                }
            });
            mPlayer.prepare();
            mPlayer.start();
        } catch (Exception e) {
            stopInternalPlay();
            onPlayCompleted(false);
        }
    }

    public void stopPlay() {
        Log.e("TAG", "停止播放 --- ");
        stopInternalPlay();
        onPlayCompleted(false);
        mPlayCallback = null;
    }

    private void stopInternalPlay() {
        if (mPlayer == null) {
            return;
        }
        mPlayer.release();
        mPlayer = null;
    }

    public boolean isPlaying() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            return true;
        }
        return false;
    }

    private void onPlayCompleted(boolean success) {
        if (mPlayCallback != null) {
            mPlayCallback.onCompletion(success);
        }
        mPlayer = null;
    }

    private void onRecordCompleted(boolean success) {
        if (mRecordCallback != null) {
            mRecordCallback.onCompletion(success);
        }
        mRecorder = null;
    }

    public String getPath() {
        return mAudioRecordPath;
    }

    public int getDuration() {
        if (TextUtils.isEmpty(mAudioRecordPath)) {
            return 0;
        }
        int duration = 0;
        // 通过初始化播放器的方式来获取真实的音频长度
        try {
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(mAudioRecordPath);
            mp.prepare();
            duration = mp.getDuration();
            // 语音长度如果是59s多，因为外部会/1000取整，会一直显示59'，所以这里对长度进行处理，达到四舍五入的效果
            if (duration < MIN_RECORD_DURATION) {
                duration = 0;
            } else {
                duration = duration + MAGIC_NUMBER;
            }
        } catch (Exception e) {
        }
        if (duration < 0) {
            duration = 0;
        }
        return duration;
    }

    public interface Callback {
        void onCompletion(Boolean success);
    }

}
