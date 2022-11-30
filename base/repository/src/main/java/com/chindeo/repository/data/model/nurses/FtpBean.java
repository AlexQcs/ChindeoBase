package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;

public class FtpBean {

        @JSONField(name = "password")
        public String password;
        @JSONField(name = "port")
        public String port;
        @JSONField(name = "host")
        public String host;
        @JSONField(name = "username")
        public String username;
        @JSONField(name = "frequency")
        public int frequency;
}
