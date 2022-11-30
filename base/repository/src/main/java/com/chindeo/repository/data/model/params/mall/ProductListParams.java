package com.chindeo.repository.data.model.params.mall;

import com.alibaba.fastjson.annotation.JSONField;

public class ProductListParams {

    @JSONField(name = "tenancyCategoryId")
    public int tenancyCategoryId;

    @JSONField(name = "type")
    public String type;

    public ProductListParams(int tenancyCategoryId) {
        this.tenancyCategoryId = tenancyCategoryId;
        this.type = "1";
    }
}
