package com.chindeo.ui

import android.content.*
import android.database.ContentObserver
import android.os.Bundle
import android.view.WindowManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.chindeo.base.R
import com.chindeo.constants.UpgradeActionConstants
import com.chindeo.repository.contants.AppModule
import com.lazylibs.component.ui.fragment.BaseSupportDialogFragment
import com.lazylibs.util.SizeUtils
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * apk下载
 */
class ApkDownLoadDialogFragment : BaseSupportDialogFragment() {

    private var packageName: String? = null
    private var downloadId: Long = 0
    private lateinit var module: AppModule
    var listener: DialogInterface.OnDismissListener? = null

    var observer: ContentObserver? = null

    companion object {

        fun newInstance(name: String): ApkDownLoadDialogFragment {
            val args = Bundle()
            args.putString("name", name)
            val fragment = ApkDownLoadDialogFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        dialog!!.setCanceledOnTouchOutside(false)
        val window = dialog!!.window
        if (window != null) {
            val params = window.attributes
            params.width = SizeUtils.dp2px(context, 300f)
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = params
        }
    }

    override fun init(savedInstanceState: Bundle?) {
        if (context == null || message == null) {
            return
        }
        packageName = arguments?.getString("name")
        downloadId = arguments?.getLong("id")!!
        module = AppModule.getModule(packageName)
        message.text = "${module.appName}正在下载:"
        val filter = IntentFilter(UpgradeActionConstants.ACTION_INSTALL_FINISH)
        filter.addAction(UpgradeActionConstants.ACTION_DOWNLOAD_PROGRESS)
        LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(object : BroadcastReceiver() {
                    override fun onReceive(context: Context?, intent: Intent?) {
                        if (intent!!.action == UpgradeActionConstants.ACTION_INSTALL_FINISH) {
                            if (observer != null) {
                                context?.contentResolver?.unregisterContentObserver(observer!!)
                            }
                            dismissAllowingStateLoss()
                        } else if (intent.action == UpgradeActionConstants.ACTION_DOWNLOAD_PROGRESS) {
                            if (message != null) {
                                val progress = intent.getIntExtra(UpgradeActionConstants.EXTRA_DOWNLOAD_PROGRESS, 0)
                                if (progress == 100) {
                                    message.text = "${module.appName}正在安裝"
                                } else {
                                    message.text = "${module.appName}正在下载:$progress%"
                                }
                            }
                        }
                    }

                }, filter)

        /* context?.contentResolver?.registerContentObserver(Uri.parse("content://downloads/"),
                 true, DownloadObserver(Handler(), context, downloadId, DownloadObserver.ProgressHandler { progress ->
             message?.let {
                 if (progress == "100") {
                     message.text = "${module.appName}正在安裝"
                 } else {
                     message.text = "${module.appName}正在下载:$progress%"
                 }
             }
         }).also {
             observer = it
         })*/
    }

    override fun initData() {
    }

    fun setOnDismissListener(listener: DialogInterface.OnDismissListener) {
        this.listener = listener
    }


    override fun layoutRes(): Int {
        return R.layout.dialog_loading
    }

}