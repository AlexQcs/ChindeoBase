package com.chindeo.util.install;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.apkfuns.logutils.LogUtils;

import java.util.Formatter;

public class DownloadObserver extends ContentObserver {
    private long downid;
    private Context context;
    private ProgressHandler progressHandler;

    public DownloadObserver(Handler handler, Context context, long downid, ProgressHandler progressHandler) {
        super(handler);
        this.downid = downid;
        this.context = context;
        this.progressHandler = progressHandler;
    }

    @Override
    public void onChange(boolean selfChange) {
        //每当/data/data/com.android.providers.download/database/database.db变化后，触发onCHANGE，开始具体查询
        Log.w("onChangeID", String.valueOf(downid));
        super.onChange(selfChange);
        //实例化查询类，这里需要一个刚刚的downid
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downid);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        //这个就是数据库查询啦
        Cursor cursor = downloadManager.query(query);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int mDownload_so_far = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                int mDownload_all = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                double mProgress = (double) mDownload_so_far / mDownload_all;
                Log.w(getClass().getSimpleName(), String.valueOf(mProgress));
                if (progressHandler != null) {
                    progressHandler.onProgress(String.valueOf((int) (mProgress * 100)));
                }
            }
        }else{
            LogUtils.e("下载Cursor异常 null");
        }
    }

    public interface ProgressHandler {
        void onProgress(String progress);
    }
}