package com.chindeo.repository.data.model.common;

import com.alibaba.fastjson.annotation.JSONField;

public class PageLimit implements IPage {

    @JSONField(name = "page")
    public int page;
    @JSONField(name = "pageSize")
    public int limit;

    public PageLimit() {
        setPage(1);
        setLimit(20);
    }

    public PageLimit(int page) {
        setPage(page);
        setLimit(20);
    }

    public PageLimit(int page, int pageSize) {
        setPage(page);
        setLimit(pageSize);
    }

    @Override
    public IPage setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public IPage setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public PageLimit nextPage() {
        page++;
        return this;
    }

    @Override
    public IPage revertPage() {
        if (page > 1) {
            page--;
        }
        return this;
    }

}
