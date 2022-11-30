package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

public class MallCartInfoBean {
    @JSONField(name = "product")
    public ProductBean product;
    @JSONField(name = "productAttr")
    public ProductAttrBean productAttr;

    public static class ProductBean {
        @JSONField(name = "image")
        public String image;
        @JSONField(name = "storeName")
        public String storeName;
        @JSONField(name = "temp")
        public TempBean temp;

        public static class TempBean {
            @JSONField(name = "name")
            public String name;
            @JSONField(name = "type")
            public int type;
            @JSONField(name = "appoint")
            public int appoint;
            @JSONField(name = "undelivery")
            public int undelivery;
            @JSONField(name = "isDefault")
            public int isDefault;
            @JSONField(name = "sort")
            public int sort;
        }
    }

    public static class ProductAttrBean {
        @JSONField(name = "price")
        public int price;
        @JSONField(name = "sku")
        public String sku;
    }
}
