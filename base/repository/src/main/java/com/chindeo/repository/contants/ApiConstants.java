package com.chindeo.repository.contants;

public class ApiConstants {


    public static final String API_GET_LICENSE = "license"; // 获取license授权 Token

    private static final String APP_PATH = "app/";

    public static final String API_GET_CIPHER_TEXT = APP_PATH + "verify/cipherText"; // cipherText Token

    public static final String API_APP_UPGRADE = APP_PATH + "upgrade";

    public static final String API_MQTT_INFO = APP_PATH + "mqtt/info"; // 获取MQTT配置信息


    // upgrade
    private static final String UPGRADE_PATH = "upgrade/";
    private static final String UPGRADE_ANDROID_PATH = UPGRADE_PATH + "android{env}/";
    public static final String API_APP_APK_INFO = UPGRADE_ANDROID_PATH + "{package}/app.json";
    public static final String API_APP_APK_URL = UPGRADE_ANDROID_PATH + "{package}/app.apk";
    public static final String API_PLUGIN_APK_URL = UPGRADE_ANDROID_PATH + "{appName}.apk";


    //-------------------------------

    //---------------------------------   nurses 护士站主机        -------------------
    // 病区配置
    public static final String API_SHIELD_PATIENT = "/app/shield/info"; // 屏蔽患者
    public static final String API_GET_BED_REMINDS = "/app/bed/reminds"; //床位消息提醒
    // 根据设备类型返回病区设备状态
    public static final String API_GET_DEVICES_LIST = "/app/device/getStatus";
    //更新房间状态
    public static final String API_POST_DEVICES_STATUS = "/app/device/setStatus";
    public static final String API_GET_OBSERVATION_ITEM = "/app/observation/item"; //获取事件登记项
    public static final String API_POST_ITEM_EVENT_SET = "/app/adm/item-event/set"; //病房患者事件设置
    public static final String APP_lOC_TRUSTEESHIP_DEVLIST=APP_PATH+"loc/trusteeship/devList";//获取病区主机托管信息及主机列表
    public static final String APP_lOC_HOSTTRUSTEESHIP_SAVE=APP_PATH+"loc/hostTrusteeship/save";//保存病区主机托管配置信息
    public static final String API_NURSES_DOCTOR_RECORD = "/app/v2/audio/records"; //医院录音
    public static final String API_NURSES_BROADCAST_ADD = APP_PATH + "broadcast/add"; //添加定时广播任务
    public static final String API_NURSES_BROADCAST_REMOVE = APP_PATH + "broadcast/delete"; //定时广播删除
    public static final String API_NURSES_BROADCAST_LIST = APP_PATH + "broadcast/list"; //添加定时广播列表

    //护士站主机通话列表
    public static final String API_GET_DEVICE_CALL =APP_PATH + "device/call";
    //探视列表
    public static final String API_GET_CALL_INFO = APP_PATH + "call/info";
    //获取责任分组对应的铃声以及通讯账号
    public static final String RINGTONES_AND_COMMUNICATION_ACCOUNTS = APP_PATH + "bedGroup/ringtonesAndCommunicationAccounts";
    //获取护士责任分组和铃声
    public static final String RINGTONES_AND_BED_GROUP = APP_PATH + "bedGroup/ringtonAndBedGroup";
    //批量设置护士责任组铃声
    public static final String BATCH_RINGTON = APP_PATH + "bedGroup/batchRington";
    //获取责任组铃声列表
    public static final String BATCH_GET_FILE_BY_TYPE = APP_PATH + "fileConfig/getFileByType";

    // --------------------------------        bed     -----------------------------

    public static final String OBSERVATION_PATH = APP_PATH + "observation/"; // 生命体征
    public static final String API_OBSERVATION_HEALTH = OBSERVATION_PATH + "bedItems"; // 生命体征
    public static final String API_OBSERVATION_HEALTH_SET = OBSERVATION_PATH + "changeItems"; // 生命体征首页选择
    public static final String API_POST_WRITE_AdmItem = APP_PATH + "/observation/writeAdmItem";//更新单条体征数据

    private static final String BED_PATH = APP_PATH + "bed/";
    public static final String API_NURSES_BED_LIST = BED_PATH + "list-device"; //根据设备获取病区床位信息
    public static final String API_NURSES_BED_UPDATE = BED_PATH + "update"; // 更新字段 留观 留床...
    public static final String API_REMIND_READ = BED_PATH + "remind-read"; // 床位消息已读 紧急呼叫
    public static final String API_BED_DEVICE_CONFIG = BED_PATH + "device/config"; // 床旁获取配置
    public static final String API_BED_DETAIL = BED_PATH + "detail"; // 获取病床详情
    public static final String API_BED_MESSAGE_UN_READ = BED_PATH + "message/unread"; // 未读消息
    public static final String API_BED_MENU = APP_PATH + "perm/obtainBedPerms"; // 床旁菜单

    private static final String BILL_PATH = APP_PATH + "bill-detail/";
    public static final String API_QUERY_QIN_DAN = BILL_PATH + "summary"; // 清单列表
    public static final String API_QUERY_QIN_DAN_DETAIL = BILL_PATH + "daily"; // 清单详情
    public static final String API_QUERY_PRICE_CATEGORY = APP_PATH + "tar/cate-sub"; // 物价分类
    public static final String API_QUERY_PRICE_CATEGORY_DETAIL = APP_PATH + "tar/items"; // 物价分类 - 详情
    public static final String API_TIXING_CATEGORY = APP_PATH + "message/category"; // 提醒分类
    public static final String API_TIXING_UNREAD_LIST = APP_PATH + "bed/message"; //智能提醒列表
    public static final String API_TIXING_SET_READ=APP_PATH+"bed/message/set-read"; //设置消息已读

    //快速缴费
    public static final String APP_BILL_DETAIL_SUMMARY =APP_PATH+"bill-detail/summary";//快速缴费
    public static final String APP_BILL_DETAIL_DEPOSIT=APP_PATH+"bill-detail/deposit"; //缴费记录

    //音量
    public static final String API_QUERY_DEVICE_VOLUME_LIST_USING = APP_PATH + "device/getDeviceVolumeList"; // 获取音量设置列表
    public static final String API_SET_DEVICE_VOLUME_LIST_USING = APP_PATH + "device/batchUpdateVolume"; // 设置音量
    public static final String API_SET_DEVICE_VOLUME_DELETE = APP_PATH + "device/deleteVolumeConfig"; // 设置音量

    public static final String GET_SMTZ_CHART =  "/html/multipage/bssp.html#/smtz";            //生命体征图表三册单
    public static final String GET_FXPG_HTML =  "/html/multipage/bssp.html#/assessment";       //护士风险评估单
    public static final String GET_SMTZ_CHART_WEB="/html/web/bssp.html#/smtz";           //新版生命体征图表三册单
    public static final String GET_FXPG_HTML_WEB="/html/web/bssp.html#/assessment";      //新版护士风险评估单
    public static final String GET_CPXS_HTML =  "/html/web/bssp.html#/cpxs";             //护士床旁巡视评估单
    public static final String GET_YZZX_HTML =  "/html/web/bssp.html#/yzzx";          //医嘱执行

    //验证患者身份证号后四位数字
    public static final String POST_IDENTITY = APP_PATH + "security/identity";

    //评价
    public static final String API_SURVEY_LIST=APP_PATH+"survey/list"; //满意评价
    //医护人员评价
    public static final String GET_YI_HU_EVALUATE_ITEMS = APP_PATH+ "/evaluate/obtainEvaluates";          //获取医护评价的对象
    public static final String POST_EVALUATE_DATA = APP_PATH+ "/evaluate/createEvaluates";                //提交医护评价

    //获取所有医院介绍
    public static final String APP_HOSPITALINTRODUCE_OBTAININTRODUCES="app/hospitalIntroduce/obtainIntroduces";

    //智能家居
    public static final String GET_SMART_DEVICE_LIST = "/service/networkControl/electricityInfo"; //智能家居设备列表
    public static final String GET_SMART_DEVICE_OPERATE =  "/service/networkControl/operateDevice";   //智能家居设备开关

    // call
    private static final String CALL_PATH = APP_PATH + "call/";
    public static final String API_CALL_RECENT = CALL_PATH + "obtainContact"; // 最近联系人
    public static final String API_CHAT_HISTORY = CALL_PATH + "obtainCallHistory"; // 最近聊天历史记录
    public static final String API_CHAT_QUICK_REPLY_LIST = APP_PATH + "nursingProblem/obtainTypeNursingProblem"; // 获取快速呼叫的快速条目 type 1:护理白板,2:床旁屏,3:门旁屏
    public static final String API_BROADCAST_WORD_LIST = APP_PATH + "file/info?type=2";   //护士站主机发广播，获取音频列表

    /**
     * device api
     */
    private static final String DEVICE_PATH = APP_PATH + "device/";
    public static final String API_DEVICE_USER = DEVICE_PATH + "user"; // 获取音视频配置信息
    public static final String API_DEVICE_STATUS_LIST = DEVICE_PATH + "obtainTypeDeviceInfo"; // 获取设备详情列表
    public static final String API_DEVICE_CALL_HISTORY = DEVICE_PATH + "callHistory"; // 获取音视频呼叫记录
    public static final String API_TOP_NOTICE = APP_PATH + "announce/obtainAnnounceTop";//获取置顶公告
    public static final String API_VERSION_INFO = DEVICE_PATH + "versionInfo"; // 获取版本信息
    public static final String API_SERVER_TIME = DEVICE_PATH + "getServerTime"; // 返回服务器的时间戳
    //获取转接列表
    public static final String API_CALL_LIST = DEVICE_PATH + "call/list"; // 返回服务器的时间戳

    // service api
    private static final String SERVICE_PATH = "service/";
    public static final String VOICE_PARSE_IDENTIFY = SERVICE_PATH + "mscIat"; //录音转文字 识别
    public static final String VOICE_PARSE_CONTROL = SERVICE_PATH + "mscAsr"; //录音转文字 指令
    public static final String POST_VOICE_COMMAND_CONTROL = SERVICE_PATH + "watch/command-control";  //手表发送命令给大屏
    //watch
    public static final String WATCH_DEVICE_INFO = SERVICE_PATH + "v2/watch/device-info";

    // aceItem api
    private static final String ARC_ITEM_PATH = APP_PATH + "arc-item/";
    public static final String ARC_ITEM_PATIENT = ARC_ITEM_PATH + "patient/";
    public static final String ARC_ITEM_PATIENT_CASE = ARC_ITEM_PATIENT + "emr"; // 病例
    public static final String ARC_ITEM_PATIENT_JIANYAN = ARC_ITEM_PATIENT + "specimen"; // 查询检验报告数据
    public static final String ARC_ITEM_PATIENT_JIANCHA = ARC_ITEM_PATIENT + "exam"; // 查询检查报告数据

    //voice
    public static final String GET_VOICE_CALL_INFO = APP_PATH + "voice/watchCall";       //手表获取推送链接参数


    // adm Api
    private static final String ADM_PATH = APP_PATH + "adm/";
    public static final String ADM_ZHENLIAO_PLAN = ADM_PATH + "to-do-list"; // 患者诊疗计划



    /**
     * {@link }
     * 宣教
     */
    private static final String EDUCATION_PATH = APP_PATH + "education/";
    public static final String EDUCATION_LIST = EDUCATION_PATH + "list"; // 宣教数据
    public static final String GET_ZSBK_TYPE =  APP_PATH+ "/knowledge/type";    //知识百科类型，也就是标签 3疾病百科4健康科普5精选专题
    public static final String GET_ZSBK_LIST =  APP_PATH + "/knowledge/list"; //知识百科列表
    public static final String getEducationEvalUrl = APP_PATH+ "/education/eval/url";//获取宣教的评价url





    //------------------------------- 医生护士 模块-------------------------------------//
    //保存的Userid
    public static final String DOCTOR_USER_ID="doctor_userId";
    public static final String NURSE_USER_ID="nurse_userId";
    //人脸识别登录
    public static final String SERVICE_FACE_IDENTIFY="service/faceIdentify";
    //账号密码登录
    public static final String APP_CHECK_BED_USER="app/check/bedUser";
    //获取护士的管床列表  userId
    public static final String APP_BED_NURSE= "app/bed/nurse";
    //获取医生的管床列表  userId
    public static final String BED_DOCTOR= "app/bed/doctor";
    //获取选择护士列表  type locCode
    public static final String ROTA_LOC_NURSES="app/rota/loc-nurses";
    //获取选择医生列表  type locCode
    public static final String ROTA_LOC_DOCTORS="app/rota/loc-doctors";
    //对管床医生和护士进行设置  bedId userId
    public static final String APP_BED_USER_SET="app/bed/user-set";
    //护理事件
    public static final String APP_BED_NURSE_EVENT="app/bed/nurse-event";
    //患者自定义护理事件创建
    public static final String APP_ADM_NURSE_EVENT_EXT_ADD="app/adm/nurse-event-ext/add";
    //患者自定义护理事件删除
    public static final String APP_ADM_NURSE_EVENT_EXT_DEL="app/adm/nurse-event-ext/del";
    //排序自定义护理事件
    public static final String APP_ADM_NURSE_EVENT_EXT_SORT="app/adm/nurse-event-ext/sort";
    //获取就诊患者扩展事件标签
    public static final String APP_ADM_NURSE_EVENT_EXT_TAG="app/adm/nurse-event-ext/tag";
    //护士执行,获取单据类型
    public static final String APP_ORDER_DOC_LIST="app/orderDoc/list";
    //护士执行,获取单据列表
    public static final String APP_ORDER_DOC_INFO="app/orderDoc/info";
    //护士执行医嘱单
    public static final String APP_ORDER_ITEM_EXEC="app/orderItem/exec";

    public static final String APP_GET_TI_ZHENG_CATEGORY =  "app/loc/observation-item";                //根据病区查体征项
    public static final String POST_TI_ZHENG_ITEMS ="app/observation/upload";                    //添加生命体征

    // 医生 诊断记录
    public static final String APP_DIAGNOSE_ADM="app/diagnose/adm";
    //医嘱单子
    public static final String APP_ARC_ITEM_PATIENT_ORDER="app/arc-item/patient/order";
    //检验结果列表
    public static final String APP_ARC_ITEM_PATIENT_SPECIMEN="app/arc-item/patient/specimen";
    //检查结果列表
    public static final String APP_ARC_ITEM_PATIENT_EXAM="app/arc-item/patient/exam";
    //检查结果详情 链接 包含图片 H5
    public static final String APP_ARC_ITEM_RIS_REPORT="app/arc-item/ris-report";
    //查询病历数据
    public static final String ARC_ITEM_PATIENT_EMR="app/arc-item/patient/emr";
    //根据id查找报告结果
    public static final String ARC_ITEM_PATIENT_SPECIMEN_RESULT="app/arc-item/patient/specimen-result";

    //根据患者id和打卡类型，获取患者打卡记录
    public static final String APP_GET_CLOCK_IN_LIST = "app/observation/getClockInList";
    //检查是否有正在的打卡记录
    public static final String APP_GET_CHECK_CLOCK_IN_START = "app/observation/checkClockInStart";
    //更新打卡记录
    public static final String APP_GET_UPDATE_CLOCK = "app/observation/setClockIn";

    //查询护士是否在护理中
    public static final String APP_GET_NURSE_STATUS = "app/nurse-event/nurse/getStatus";
    //检查房间的护理状态
    public static final String APP_POST_CHECK_ROOM_STATUS = "app/nurse-event/nurse/checkStatus";
    //更新值班护士护理状态 护理状态，0:进行中，1:护理完成, 2:复位
    public static final String APP_POST_ROOM_STATUS ="app/nurse-event/nurse/setStatus";
    //获取当前病区增援的护士站主机、门旁、床旁
    public static final String APP_GET_REINFORCEMENTS = "app/bed/reinforcements";

    //消息，检测提醒添加
    public static final String APP_POST_DISCOVER_REMIND =  "app/discoverRemind/add";
    //消息，检测提醒列表
    public static final String APP_GET_DISCOVER_REMIND_LIST =  "app/discoverRemind/list";
    //消息，检测提醒删除
    public static final String APP_DELETE_DISCOVER_REMIND = "app/discoverRemind/delete";

    //医生上传录音
    public static final String APP_POST_AUDIO_UPLOAD = "app/audio/upload";


    //-------------------------------PDA-------------------------------------//
    public static final String GET_STATIC_IMAGE_LOGG =  "static/image/logo_blue.png";  //所有产品动态使用的LOGO图片
    //获取pda配置信息
    public static final String GET_DETAIL_INFO = APP_PATH + "pda/detail";

}
