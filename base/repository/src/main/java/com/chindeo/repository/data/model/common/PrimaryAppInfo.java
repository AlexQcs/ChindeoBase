package com.chindeo.repository.data.model.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.data.model.response.upgrade.PluginBean;

import java.util.ArrayList;
import java.util.List;

public class PrimaryAppInfo {

    @JSONField(name = "primaryAppPackage")
    public String primaryAppPackageName;

    @JSONField(name = "pluginStringList")
    public List<String> pluginPackageStringList;

    @JSONField(name = "pluginList")
    public List<PluginBean> pluginPackageList;

    @JSONField(name = "env")
    public String env = "";
}
