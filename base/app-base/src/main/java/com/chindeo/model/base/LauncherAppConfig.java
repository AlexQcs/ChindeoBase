package com.chindeo.model.base;

import com.chindeo.repository.data.model.common.ConfigInterface;

import java.util.ArrayList;
import java.util.List;

public class LauncherAppConfig implements ConfigInterface {


    @Override
    public String configSimpleName() {
        return LauncherAppConfig.class.getSimpleName();
    }


    public List<String> ignoreUpdateList() {
        return new ArrayList<>();
    }

}
