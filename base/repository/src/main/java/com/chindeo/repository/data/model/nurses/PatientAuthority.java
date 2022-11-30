package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public enum PatientAuthority implements MultiItemEntity {

    SHIELD_EXAM(1, "隐藏检查结果", "exam"),
    SHIELD_INSPECT(2, "隐藏检验结果", "inspect"),
    SHIELD_DIAGNOSIS(3, "隐藏诊断结果", "diagnosis");

    public final int code;
    public final String desc;
    public final String resp;

    PatientAuthority(int code, String desc, String resp) {
        this.code = code;
        this.desc = desc;
        this.resp = resp;
    }

    /**
     *
     * @param bean
     * @return
     */
    public static List<PatientAuthority> authorityList(PatientAuthorityBean bean) {
        TreeMap<String, Boolean> map = JSON.parseObject(JSON.toJSONString(bean),
                new TypeReference<TreeMap<String, Boolean>>() {
                });
        PatientAuthority[] values = values();
        List<PatientAuthority> list = new ArrayList<>();

        for (PatientAuthority value : values) {
            if (!map.get(value.resp)) {
                list.add(value);
            }
        }
        return list;
    }

    @Override
    public int getItemType() {
        return 1;
    }


}
