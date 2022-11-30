package com.chindeo.repository.contants;

public class ApiBaseValue {
//    String CODE_SUCCESS = "200";
//    String CODE_FAIL = "-1";
//    //授权相关
//    String CODE_301 = "301";//跳转到错误页面提示
//    String CODE_401 = "401";//授权信息无效
//    String CODE_402 = "402";//授权格式错误
//    String CODE_403 = "403";//授权时间错误
//    String CODE_404 = "404";//授权信息过期
//
//    String CODE_NONE = "server no error code !";
//    String DATA_NONE = "server no data !";

    public static final String PLUG_CODE_UNINSTALLED = "-1";    //插件未安装
    public static final String PLUG_CODE_STOP = "0";            //插件未启动
    public static final String PLUG_CODE_START = "1";           //插件已就绪

    public static final String PLUG_CODE_UNINSTALLED_ = "未安装";     //插件未安装
    public static final String PLUG_CODE_STOP_ = "未启动";            //插件未启动
    public static final String PLUG_CODE_START_ = "已就绪";           //插件已就绪

    public static int CALL_RELEASE_TYPE = 0;           //硬件按钮呼叫类型  0，说明是拨打音视频  1说明是床旁巡视
    public static final int CALL_RELEASE_TYPE_NORMAL = 0;         //硬件按钮呼叫类型  0，说明是拨打音视频
    public static final int CALL_RELEASE_TYPE_CPXS = 1;           //硬件按钮呼叫类型   1说明是床旁巡视

}
