package com.chindeo.repository.data.model.params.bed;

import com.alibaba.fastjson.annotation.JSONField;

public class BedUpdateParams {

    @JSONField(name = "id")
    public String bedId;
    @JSONField(name = "urgent")
    public String urgent;
    @JSONField(name = "reserved")
    public String reserved;
    @JSONField(name = "observation")
    public String observation;

    // 0否 1是

    public BedUpdateParams() {
    }

    public static BedUpdateParams urgent(String bedId, boolean open) {
        BedUpdateParams params = new BedUpdateParams();
        params.bedId = bedId;
        params.urgent = open ? "1" : "0";
        return params;
    }

    public static BedUpdateParams reserved(String bedId, boolean open) {
        BedUpdateParams params = new BedUpdateParams();
        params.bedId = bedId;
        params.reserved = open ? "1" : "0";
        return params;
    }

    public static BedUpdateParams observation(String bedId, boolean open) {
        BedUpdateParams params = new BedUpdateParams();
        params.bedId = bedId;
        params.observation  = open ? "1" : "0";
        return params;
    }


}
