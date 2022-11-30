package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class PageData<T> {

    @JSONField(name = "page")
    public int page;
    @JSONField(name = "pageSize")
    public int pageSize;
    @JSONField(name = "isLastPage")
    public boolean isLastPage;
    @JSONField(name = "total")
    public int total;
    @JSONField(name = "list")
    public List<T> list;

    public boolean hasNext() {
        return total > page;
    }
}
