package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 购物车商品详情
 * Created by xiemaohui on 2022/5/23
 */
public  class MallCartProductBean {
        @JSONField(name = "sysTenancyId") //商户id
        public int sysTenancyId;
        @JSONField(name = "name") //宝安中心人民医院
        public String name;
        @JSONField(name = "Avatar") //商户图片
        public String Avatar;
        @JSONField(name = "products") //购物车商品
        public List<ProductBean> products;


        public static class ProductBean {
            /**
             * 购物车id
             */
            @JSONField(name = "id")
            public int id;
            /**
             * 商品id
             */
            @JSONField(name = "productId") // 商品id
            public int productId;
            @JSONField(name = "image") //商品图片
            public String image;
            @JSONField(name = "storeName") //商品名
            public String storeName;
            @JSONField(name = "cartNum") //商品数量
            public int cartNum;
            @JSONField(name = "productAttrUnique") //商品规格唯一标识
            public String productAttrUnique;
            @JSONField(name = "attrValue")
            public AttrValueBean attrValue;
            /**
             * 是否选中
             */
            @JSONField(name = "isSelect")
            public Boolean isSelect=false;

            public static class AttrValueBean {
                /**
                 *商品id
                 */
                @JSONField(name = "productId")
                public int productId;
                /**
                 * sku
                 */
                @JSONField(name = "sku")
                public String sku;
                /**
                 * 库存
                 */
                @JSONField(name = "stock")
                public int stock;
                /**
                 * 销量
                 */
                @JSONField(name = "sales")
                public int sales;
                /**
                 * 图片
                 */
                @JSONField(name = "image")
                public String image;
                /**
                 * barCode
                 */
                @JSONField(name = "barCode")
                public String barCode;
                /**
                 * 成本
                 */
                @JSONField(name = "cost")
                public int cost;
                /**
                 * 原价
                 */
                @JSONField(name = "otPrice")
                public int otPrice;
                /**
                 * 价格
                 */
                @JSONField(name = "price")
                public Double price;
                /**
                 * volume
                 */
                @JSONField(name = "volume")
                public int volume;
                /**
                 * 重量
                 */
                @JSONField(name = "weight")
                public String weight;
                /**
                 * 商品规格唯一标识
                 */
                @JSONField(name = "unique")
                public String unique;
                /**
                 * 详情
                 */
                @JSONField(name = "detail")
                public DetailBean detail;
                /**
                 * value
                 */
                @JSONField(name = "value")
                public List<String> value;

                public static class DetailBean {

                }
            }
        }
}
