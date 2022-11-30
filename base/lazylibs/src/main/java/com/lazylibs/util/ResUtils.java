package com.lazylibs.util;

import android.content.Context;
import android.content.res.Resources;


/**
 * 项目名: HFL-ReCode
 * 包 名: com.caimao.hfl.award.utils
 * Copyright © 2018, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public class ResUtils {

    /**
     * return String from <string/>
     *
     * @param str
     * @return
     */
    public static String str(Context cxt, Object str) {
        return cxt.getResources().getString(str instanceof String ? iStr(cxt, str.toString()) : (Integer) str);
    }

    /**
     * 根据资源名获取图片文件ID
     *
     * @param resName 资源名
     * @return
     */
    public static int iMipMap(Context cxt, String resName) {
        return rId(cxt, resName, "mipmap");
    }

    /**
     * 根据资源名获取图片文件ID
     *
     * @param resName 资源名
     * @return
     */
    public static int iDraw(Context cxt, String resName) {
        return rId(cxt, resName, "drawable");
    }

    /**
     * 根据资源名获取动画ID
     *
     * @param resName 资源名
     * @return
     */
    public static int iAnim(Context cxt, String resName) {
        return rId(cxt, resName, "anim");
    }

    /**
     * 根据资源名获取attrID
     *
     * @param resName 资源名
     * @return
     */
    public static int iAttr(Context cxt, String resName) {
        return rId(cxt, resName, "attr");
    }

    /**
     * 根据资源名获取颜色ID
     *
     * @param resName 资源名
     * @return
     */
    public static int iColor(Context cxt, String resName) {
        return rId(cxt, resName, "color");
    }

    /**
     * 根据资源名获取dimenID
     *
     * @param resName 资源名
     * @return
     */
    public static int iDimen(Context cxt, String resName) {
        return rId(cxt, resName, "dimen");
    }

    /**
     * 根据资源名获取样式ID
     *
     * @param resName 资源名
     * @return
     */
    public static int iStyle(Context cxt, String resName) {
        return rId(cxt, resName, "style");
    }

    /**
     * 根据资源名获取ID
     *
     * @param resName 资源名
     * @return
     */
    public static int iStyleable(Context cxt, String resName) {
        return rId(cxt, resName, "styleable");
    }

    /**
     * 根据资源名获取字符资源ID
     *
     * @param resName 资源名
     * @return
     */
    public static int iStr(Context cxt, String resName) {
        return rId(cxt, resName, "string");
    }

    /**
     * 根据资源名获取ID<br/>
     * 如定义{@code <}TextView android:id="@+id/tv001" ...
     * ,调用iId("tv001"),则返回R.id.tv001的整型值
     *
     * @param resName 资源名
     * @return
     */
    public static int iId(Context cxt, String resName) {
        return rId(cxt, resName, "id");
    }

    /**
     * 根据资源名获取布局ID
     *
     * @param resName 资源名
     * @return 布局ID
     */
    public static int iLayout(Context cxt, String resName) {
        return rId(cxt, resName, "layout");
    }


    /**
     * 根据资源ID获取资源名字符串
     *
     * @param resId 资源ID
     * @return 资源名字符串[如传入R.id.abc，将返回“abc”]
     */
    public static String nId(Context cxt, int resId) {
        try {
            return cxt.getResources().getResourceEntryName(resId);
        } catch (Resources.NotFoundException e) {
            try {
                return cxt.getResources().getResourceName(resId);
            } catch (Resources.NotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 根据资源名字获取资源ID，如根据"tv001" 获取R.id.tv001
     *
     * @param resName 资源名字
     * @param type    资源类型<br/>
     *                [drawable|anim|layout|color|attr|color|dimen|style|styleable|
     *                string]<br/>
     * @return 资源ID
     */
    public static int rId(Context cxt, String resName, String type) {
        return cxt.getResources().getIdentifier(resName, type, cxt.getPackageName());
    }

}
