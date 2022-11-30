package com.chindeo.repository.data.model.common;

import com.alibaba.fastjson.annotation.JSONField;

public interface IPage {

    @JSONField(serialize = false, deserialize = false)
    IPage setPage(int page);

    @JSONField(serialize = false, deserialize = false)
    IPage setLimit(int limit);

    @JSONField(serialize = false, deserialize = false)
    IPage nextPage();

    @JSONField(serialize = false, deserialize = false)
    IPage revertPage();
}
