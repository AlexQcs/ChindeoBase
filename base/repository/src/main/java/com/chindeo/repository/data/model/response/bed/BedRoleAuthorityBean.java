package com.chindeo.repository.data.model.response.bed;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class BedRoleAuthorityBean {


    /**
     * 病人
     */
    @JSONField(name = "tourist")
    public ModuleCategoryBean patient;
    /**
     * doctor
     */
    @JSONField(name = "doctor")
    public ModuleCategoryBean doctor;
    /**
     * nurse
     */
    @JSONField(name = "nurse")
    public ModuleCategoryBean nurse;

    public static class ModuleCategoryBean {
        @JSONField(name = "fixed")
        public List<AuthorityBean> fixed;
        @JSONField(name = "dynamic")
        public List<AuthorityBean> dynamic;
    }
}
