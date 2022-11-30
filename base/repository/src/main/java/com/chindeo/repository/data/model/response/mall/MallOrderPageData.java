package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MallOrderPageData{

    @JSONField(name = "stat")
    public List<StatBean> stat;
    @JSONField(name = "page")
    public int page;
    @JSONField(name = "pageSize")
    public int pageSize;
    @JSONField(name = "total")
    public int total;
    @JSONField(name = "list")
    public List<MallOrderListBean> list;

    public static class StatBean {
        @JSONField(name = "className")
        public String className;
        @JSONField(name = "count")
        public int count;
        @JSONField(name = "field")
        public String field;
        @JSONField(name = "name")
        public String name;
    }
}
