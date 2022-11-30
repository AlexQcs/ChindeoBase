package com.chindeo.repository.data.model.params.business;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.data.model.nurses.PatientAuthority;

public class ShieldPatientParams {

    @JSONField(name = "bindId")
    public String bindId;
    @JSONField(name = "flag")
    public boolean open;
    @JSONField(name = "use")
    public int authorityCode;
    @JSONField(name = "type")
    public int type;

    /**
     * @param bindId    绑定对象Id
     * @param authority
     * @param open
     */
    public ShieldPatientParams(String bindId, PatientAuthority authority, boolean isLocConfig, boolean open) {
        this.bindId = isLocConfig ? "1" : bindId; // 病区传1
        this.authorityCode = authority.code;
        this.open = open;
        this.type = isLocConfig ? 1 : 3;
    }
}
