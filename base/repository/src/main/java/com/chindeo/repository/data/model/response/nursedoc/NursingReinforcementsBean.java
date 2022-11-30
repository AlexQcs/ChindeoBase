package com.chindeo.repository.data.model.response.nursedoc;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class NursingReinforcementsBean {

    @JSONField(name = "roomList")
    public List<RoomListDTO> roomList;
    @JSONField(name = "nurseStation")
    public List<ReinforcementsItem> nurseStation;

    public static class RoomListDTO {
        @JSONField(name = "roomId")
        public int roomId;
        @JSONField(name = "roomDesc")
        public String roomDesc;
        @JSONField(name = "locId")
        public int locId;
        @JSONField(name = "sipUsersName")
        public String sipUsersName;
        @JSONField(name = "bedList")
        public List<NursingBedListBean> bedList;
    }

    public static class ReinforcementsItem {
        @JSONField(name = "desc")
        public String desc;
        @JSONField(name = "sipUsersName")
        public String sipUsersName;
    }

    public static class NursingBedListBean {
         @JSONField(name = "id")
         public String id;
         @JSONField(name = "code")
         public String code;
         @JSONField(name = "sipUsersName")
         public String sipUsersName;
         public String roomDesc;
     }
}
