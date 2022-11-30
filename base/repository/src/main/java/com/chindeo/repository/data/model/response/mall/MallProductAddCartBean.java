package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

public class MallProductAddCartBean {


    @JSONField(name = "id")
    public int id;
    @JSONField(name = "createdAt")
    public String createdAt;
    @JSONField(name = "updatedAt")
    public String updatedAt;
    @JSONField(name = "productType")
    public int productType;
    @JSONField(name = "productAttrUnique")
    public String productAttrUnique;
    @JSONField(name = "cartNum")
    public int cartNum;
    @JSONField(name = "source")
    public int source;
    @JSONField(name = "sourceId")
    public int sourceId;
    @JSONField(name = "isPay")
    public int isPay;
    @JSONField(name = "isDel")
    public int isDel;
    @JSONField(name = "isNew")
    public int isNew;
    @JSONField(name = "isFail")
    public int isFail;
    @JSONField(name = "productId")
    public int productId;
    @JSONField(name = "sysUserId")
    public int sysUserId;
    @JSONField(name = "sysTenancyId")
    public int sysTenancyId;

    @Override
    public String toString() {
        return "MallProductAddCartBean{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", productType=" + productType +
                ", productAttrUnique='" + productAttrUnique + '\'' +
                ", cartNum=" + cartNum +
                ", source=" + source +
                ", sourceId=" + sourceId +
                ", isPay=" + isPay +
                ", isDel=" + isDel +
                ", isNew=" + isNew +
                ", isFail=" + isFail +
                ", productId=" + productId +
                ", sysUserId=" + sysUserId +
                ", sysTenancyId=" + sysTenancyId +
                '}';
    }
}
