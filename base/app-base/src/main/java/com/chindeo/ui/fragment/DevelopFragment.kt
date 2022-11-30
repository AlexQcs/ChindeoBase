package com.chindeo.ui.fragment

import android.os.Bundle
import com.chindeo.base.R
import com.lazylibs.component.ui.fragment.BaseSupportFragment
import kotlinx.android.synthetic.main.base_fragment_develop.*

class DevelopFragment : BaseSupportFragment() {

    override fun init(savedInstanceState: Bundle?) {
        tv.text = arguments?.getString("text") ?: "开发中"
    }

    override fun initData() {
    }

    override fun layoutRes(): Int {
        return R.layout.base_fragment_develop
    }

    companion object {
        fun newInstance(text: String?): DevelopFragment {
            val args = Bundle()

            val fragment = DevelopFragment()
            fragment.arguments = args
            args.putString("text", text)
            return fragment
        }
    }
}