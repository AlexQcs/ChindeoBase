package com.chindeo.constants;

public class UpgradeActionConstants {

    private static final String ACTION_TYPE = "upgrade_";

    public static final String ACTION_FINISH = ACTION_TYPE + "finish";
    public static final String EXTRA_BOOLEAN = ACTION_TYPE + "extra_boolean";
    public static final String ACTION_DES = ACTION_TYPE + "action_des";
    public static final String EXTRA_DES = ACTION_TYPE + "extra_des";

    public static final String ACTION_START_DOWNLOAD = ACTION_TYPE + "action_download";
    public static final String EXTRA_PACKAGE_NAME = ACTION_TYPE + "extra_package";
    public static final String EXTRA_DOWNLOAD_ID = ACTION_TYPE + "extra_download_id";

    public static final String ACTION_DOWNLOAD_PROGRESS = ACTION_TYPE + "action_download_progress";
    public static final String EXTRA_DOWNLOAD_PROGRESS = ACTION_TYPE + "extra_download_progress";

    public static final String ACTION_INSTALL_FINISH = ACTION_TYPE + "action_install";

    // 更新列表
    public static final String ACTION_STATUS_LIST = ACTION_TYPE + "action_status_list";
    public static final String EXTRA_STATUS_LIST = ACTION_TYPE + "extra_status_list";
}
