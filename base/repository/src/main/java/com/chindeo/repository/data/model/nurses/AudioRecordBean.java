package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class AudioRecordBean {

    @JSONField(name = "admId")
    public String admId;//295,
    @JSONField(name = "bedCode")
    public String bedCode;//"17",
    @JSONField(name = "audioDate")
    public String audioDate;//"09:41:28",
    @JSONField(name = "audioId")
    public String audioId;//2,
    @JSONField(name = "name")
    public String name;//"陈实体",
    @JSONField(name = "fileUrl")
    public String fileUrl;//"http://10.0.0.23/upload/file/audio/1600393288235.mp4"
    //1为播放中的状态 0 未播放
    @JSONField(name = "type")
    public int  type = 0;
    @JSONField(name = "isRead")
    public boolean isRead = false; // 本地数据

    public String formatBed() {
        return bedCode.replace("床", "") + "床";
    }
}
