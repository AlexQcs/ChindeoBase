package com.caimao.widget.viewpagerbean

import androidx.fragment.app.Fragment

/**
 * Created by zqs on 2021/7/20
 */
class ViewPagerFragmentBean {
    var title: String? = null
    var mTClass: Class<*>? = null
    var mFragment: Fragment? = null
    var id = 0
    var size = 0

    constructor()
    constructor(title: String?, mTClass: Class<*>?) {
        this.title = title
        this.mTClass = mTClass
    }

    constructor(title: String?, mFragment: Fragment?) {
        this.title = title
        this.mFragment = mFragment
    }

}