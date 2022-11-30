package com.chindeo.repository.data.model.response.bed;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Objects;

public class AuthorityBean {


    @JSONField(name = "pval")
    public String pval;

    @JSONField(name = "pname")
    public String name;

    @JSONField(name = "leaf")
    public boolean leaf;

    @JSONField(name = "icon")
    public String icon;

    @JSONField(name = "iconRes")
    public int iconRes;

    @JSONField(name = "api")
    public String api;

    @JSONField(name = "children")
    public List<AuthorityBean> children;

    public Boolean isSelected = false;

    public AuthorityBean(String name) {
        this.name = name;
    }

    public AuthorityBean() {
    }

    public AuthorityBean(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityBean that = (AuthorityBean) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AuthorityBean{" +
                "pval='" + pval + '\'' +
                ", name='" + name + '\'' +
                ", leaf=" + leaf +
                ", icon='" + icon + '\'' +
                ", iconRes=" + iconRes +
                ", api='" + api + '\'' +
                ", children=" + children +
                '}';
    }
}
