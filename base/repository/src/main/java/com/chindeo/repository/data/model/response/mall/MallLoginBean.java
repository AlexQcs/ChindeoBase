package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

public class MallLoginBean {

    @JSONField(name = "AccessToken")
    public String accessToken;
    @JSONField(name = "user")
    public User user;

    public class User {

        @JSONField(name = "nickname")
        public String nickname;
        @JSONField(name = "id")
        public String id;
        @JSONField(name = "hospitalNo")
        public String hospitalNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MallLoginBean that = (MallLoginBean) o;
        return Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken);
    }
}
