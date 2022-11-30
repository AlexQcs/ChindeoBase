package com.chindeo.repository.data.model.response.bed;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author chindeo_zcg
 * @Date 2022/7/8-9:49
 * @Email chrisSpringSmell@gmail.com
 */
public class BedVolumeDeleteBean {


    @JSONField(name = "id")
    public int id;
    @JSONField(name = "type")//0全部  1 删除1 2删除2
    public int type;

    public BedVolumeDeleteBean() {
    }

    public BedVolumeDeleteBean(int id, int type) {
        this.id = id;
        this.type = type;
    }
}
