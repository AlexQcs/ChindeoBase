package com.chindeo.repository.data.model.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

public class Flavor {

    @JSONField(name = "name")
    public String name;
    @JSONField(name = "text")
    public String text;
    @JSONField(name = "jsonUrl")
    public String jsonUrl;

    public Flavor() {
        this("");
    }

    public Flavor(String name) {
        this.name = name;
        this.text = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flavor flavor = (Flavor) o;
        return Objects.equals(name, flavor.name);
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
