package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

public class MallProductCheckOrderBean {


    @JSONField(name = "sysTenancyId")
    public int sysTenancyId;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "Avatar")
    public String avatar;
    @JSONField(name = "products")
    public List<ProductsBean> products;
    @JSONField(name = "totalPrice")
    public String totalPrice;
    @JSONField(name = "totalOtPrice")
    public String totalOtPrice;
    @JSONField(name = "orderPrice")
    public String orderPrice;
    @JSONField(name = "orderOtPrice")
    public String orderOtPrice;
    @JSONField(name = "postagePrice")
    public String postagePrice;
    @JSONField(name = "downPrice")
    public String downPrice;
    @JSONField(name = "totalNum")
    public int totalNum;
    @JSONField(name = "orderType")
    public int orderType;


    public static class ProductsBean {
        @JSONField(name = "id")
        public int id;
        @JSONField(name = "sysTenancyId")
        public int sysTenancyId;
        @JSONField(name = "specType")
        public int specType;
        @JSONField(name = "productId")
        public int productId;
        @JSONField(name = "storeName")
        public String storeName;
        @JSONField(name = "image")
        public String image;
        @JSONField(name = "cartNum")
        public int cartNum;
        @JSONField(name = "isFail")
        public int isFail;
        @JSONField(name = "productAttrUnique")
        public String productAttrUnique;
        @JSONField(name = "attrValue")
        public AttrValueBean attrValue;
        @JSONField(name = "attr")
        public AttrBean attr;

        public static class AttrValueBean {
            @JSONField(name = "productId")
            public int productId;
            @JSONField(name = "sku")
            public String sku;
            @JSONField(name = "stock")
            public int stock;
            @JSONField(name = "sales")
            public int sales;
            @JSONField(name = "image")
            public String image;
            @JSONField(name = "barCode")
            public String barCode;
            @JSONField(name = "cost")
            public int cost;
            @JSONField(name = "otPrice")
            public int otPrice;
            @JSONField(name = "price")
            public int price;
            @JSONField(name = "volume")
            public int volume;
            @JSONField(name = "weight")
            public int weight;
            @JSONField(name = "unique")
            public String unique;
            @JSONField(name = "detail")
            public Map detailMap;
            @JSONField(name = "value")
            public List<String> value;

        }

        public static class AttrBean {
            @JSONField(name = "detail")
            public String detail;
            @JSONField(name = "value")
            public String value;
        }
    }
}
