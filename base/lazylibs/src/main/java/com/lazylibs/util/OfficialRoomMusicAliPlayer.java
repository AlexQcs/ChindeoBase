package com.lazylibs.util;

import android.content.Context;
import android.util.Log;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.aliyun.player.IPlayer;
import com.aliyun.player.source.UrlSource;

import java.util.List;

public class OfficialRoomMusicAliPlayer implements IPlayer.OnCompletionListener, IPlayer.OnPreparedListener {

    public static final String EXTRA_URL_LIST = "url_list";
    private static OfficialRoomMusicAliPlayer instance;
    public static AliPlayer mediaPlayer;
    private boolean isCompletion = false;
    private boolean isPrepared = false;
    private List<String> urlList;
    private int index = -1;

    private OfficialRoomMusicAliPlayer(Context context) {
        initPlayer(context);
    }

    private void initPlayer(Context context) {
        index = -1;
        mediaPlayer = AliPlayerFactory.createAliPlayer(context);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setAutoPlay(true);
    }

    public static OfficialRoomMusicAliPlayer getInstance(Context context) {
        if (instance == null) {
            instance = new OfficialRoomMusicAliPlayer(context);
        }
        return instance;
    }

    public void start() {
        if (mediaPlayer != null && urlList != null && !urlList.isEmpty()) {
            playNext();
            mediaPlayer.start();
        }
    }

    public void setUrlList(List<String> musicList) {
        index = -1;
        urlList = musicList;
    }

    public void playUrl(String musicUrl) {
        isCompletion = false;
        mediaPlayer.reset();
        UrlSource urlSource = new UrlSource();
        urlSource.setUri(musicUrl);
        mediaPlayer.setDataSource(urlSource);
        mediaPlayer.prepare();
    }

    public void playNext() {
        index++;
        playUrl(urlList.get(index % urlList.size()));
    }

    /**
     * 暂停
     */
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void setMute(boolean b) {
        mediaPlayer.setMute(b);
    }

    public static void stop(boolean release) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            if (release) {
                mediaPlayer.release();
                mediaPlayer = null;
                instance = null;
            }
        }
    }

    /**
     * 重播
     */
    public void replay() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
        }
    }

    /**
     * 是否准备完毕
     */
    public boolean isPrepared() {
        return isPrepared;
    }

    /**
     * 是否播放完毕
     */
    public boolean isCompletion() {
        return isCompletion;
    }

    @Override
    public void onCompletion() {
        isCompletion = true;
        Log.e("RoomMusicAliPlayer", "#onCompletion");
        playNext();
    }

    @Override
    public void onPrepared() {
        isPrepared = true;
        Log.e("RoomMusicAliPlayer", "#onPrepared");
    }

}
