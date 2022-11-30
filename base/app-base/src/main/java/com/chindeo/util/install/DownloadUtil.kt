package com.chindeo.util.install

import com.apkfuns.logutils.LogUtils
import com.chindeo.repository.contants.FilePathConstants
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


class DownloadUtil private constructor() {

    private var okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
    private val downloadList: MutableList<String> = mutableListOf()

    companion object {

        private var instance: DownloadUtil? = null
            get() {
                if (field == null) {
                    field = DownloadUtil()
                }
                return field
            }

        @Synchronized
        fun get(): DownloadUtil {
            return instance!!
        }

    }

    fun download(url: String, fileName: String, downloadListener: DownloadListener) {
        if (downloadList.contains(url)){
            return
        }
        downloadList.add(url)
        val request = Request.Builder().url(url).build()
        LogUtils.d("开始下载 -> $url")


        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                LogUtils.e("下载异常 url->$url")
                downloadList.remove(url)
                e.printStackTrace()
                downloadListener.onDownloadFailed()
            }



            override fun onResponse(call: Call, response: Response) {
                val buf = ByteArray(1024)
                var len = 0
                var fos: FileOutputStream? = null
                var inputStream: InputStream? = null
                val file = File(FilePathConstants.getDir(FilePathConstants.APP_DOWNLOAD), fileName)
                if (!file.parentFile.exists()) {
                    file.parentFile.mkdirs()
                }
                try {
                    inputStream = response.body()!!.byteStream()
                    if (inputStream != null) {
                        val total = response.body()!!.contentLength()
                        fos = FileOutputStream(file)
                        var sum = 0
                        while (inputStream.read(buf).also { len = it } != -1) {
                            fos.write(buf, 0, len)
                            sum += len
                            val progress = (sum * 1.0 / total * 100).toInt()
                            LogUtils.d("下载进度 ->$progress size->$sum total->$total")
                            downloadListener.onDownloading(progress)
                        }
                        fos.flush()
                        LogUtils.d("下载完成！！ url->$url")
                        downloadListener.onDownloadSuccess()
                        downloadList.remove(url)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    LogUtils.e("下载中异常 url->$url")
                    downloadListener.onDownloadFailed()
                } finally {
                    downloadList.remove(url)
                    try {
                        inputStream?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    try {
                        fos?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

        })
    }

    interface DownloadListener {
        /**
         * 下载成功
         */
        fun onDownloadSuccess()

        /**
         * @param progress
         * 下载进度
         */
        fun onDownloading(progress: Int)

        /**
         * 下载失败
         */
        fun onDownloadFailed()
    }

}