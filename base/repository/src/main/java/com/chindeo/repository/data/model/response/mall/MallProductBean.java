package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Objects;


//商品详情
public class MallProductBean {


    /**
     * id
     */
    @JSONField(name = "id")
    public int id;
    /**
     * createdAt
     */
    @JSONField(name = "createdAt")
    public String createdAt;
    /**
     * updatedAt
     */
    @JSONField(name = "updatedAt")
    public String updatedAt;
    /**
     * storeName
     */
    @JSONField(name = "storeName")
    public String storeName;
    /**
     * storeInfo
     */
    @JSONField(name = "storeInfo")
    public String storeInfo;
    /**
     * keyword
     */
    @JSONField(name = "keyword")
    public String keyword;
    /**
     * barCode
     */
    @JSONField(name = "barCode")
    public String barCode;
    /**
     * isShow
     */
    @JSONField(name = "isShow")
    public int isShow;
    /**
     * status
     */
    @JSONField(name = "status")
    public int status;
    /**
     * unitName
     */
    @JSONField(name = "unitName")
    public String unitName;
    /**
     * sort
     */
    @JSONField(name = "sort")
    public int sort;
    /**
     * rank
     */
    @JSONField(name = "rank")
    public int rank;
    /**
     * sales
     */
    @JSONField(name = "sales")
    public String sales;
    /**
     * price
     */
    @JSONField(name = "price")
    public String price="";
    /**
     * cost
     */
    @JSONField(name = "cost")
    public String cost;
    /**
     * otPrice
     */
    @JSONField(name = "otPrice")
    public String otPrice;
    /**
     * stock
     */
    @JSONField(name = "stock")
    public int stock;
    /**
     * isHot
     */
    @JSONField(name = "isHot")
    public int isHot;
    /**
     * isBenefit
     */
    @JSONField(name = "isBenefit")
    public int isBenefit;
    /**
     * isBest
     */
    @JSONField(name = "isBest")
    public int isBest;
    /**
     * isNew
     */
    @JSONField(name = "isNew")
    public int isNew;
    /**
     * isGood
     */
    @JSONField(name = "isGood")
    public int isGood;
    /**
     * productType
     */
    @JSONField(name = "productType")
    public int productType;
    /**
     * ficti
     */
    @JSONField(name = "ficti")
    public String ficti;
    /**
     * browse
     */
    @JSONField(name = "browse")
    public int browse;
    /**
     * codePath
     */
    @JSONField(name = "codePath")
    public String codePath;
    /**
     * videoLink
     */
    @JSONField(name = "videoLink")
    public String videoLink;
    /**
     * specType
     */
    @JSONField(name = "specType")
    public int specType;
    /**
     * refusal
     */
    @JSONField(name = "refusal")
    public String refusal;
    /**
     * rate
     */
    @JSONField(name = "rate")
    public int rate;
    /**
     * replyCount
     */
    @JSONField(name = "replyCount")
    public int replyCount;
    /**
     * careCount
     */
    @JSONField(name = "careCount")
    public int careCount;
    /**
     * image
     */
    @JSONField(name = "image")
    public String image;
    /**
     * oldId
     */
    @JSONField(name = "oldId")
    public int oldId;
    /**
     * tempId
     */
    @JSONField(name = "tempId")
    public int tempId;
    /**
     * sysTenancyId
     */
    @JSONField(name = "sysTenancyId")
    public int sysTenancyId;
    /**
     * sysBrandId
     */
    @JSONField(name = "sysBrandId")
    public int sysBrandId;
    /**
     * productCategoryId
     */
    @JSONField(name = "productCategoryId")
    public int productCategoryId;
    /**
     * sysTenancyName
     */
    @JSONField(name = "sysTenancyName")
    public String sysTenancyName;
    /**
     * cateName
     */
    @JSONField(name = "cateName")
    public String cateName;
    /**
     * brandName
     */
    @JSONField(name = "brandName")
    public String brandName;
    /**
     * tempName
     */
    @JSONField(name = "tempName")
    public String tempName;
    /**
     * content
     */
    @JSONField(name = "content")
    public String content;
    /**
     * sliderImage
     */
    @JSONField(name = "sliderImage")
    public String sliderImage;
    /**
     * sliderImages
     */
    @JSONField(name = "sliderImages")
    public List<String> sliderImages;
    /**
     * attrValue
     */
    @JSONField(name = "attrValue")
    public List<AttrValueBean> attrValue;
    /**
     * attr
     */
    @JSONField(name = "attr")
    public List<AttrBean> attr;
    /**
     * cateId
     */
    @JSONField(name = "cateId")
    public int cateId;
    /**
     * tenancyCategoryId
     */
    @JSONField(name = "tenancyCategoryId")
    public List<Integer> tenancyCategoryId;
    /**
     * productCates
     */
    @JSONField(name = "productCates")
    public List<ProductCatesBean> productCates;

    public static class AttrValueBean {
        /**
         * productId
         */
        @JSONField(name = "productId")
        public int productId;
        /**
         * sku
         */
        @JSONField(name = "sku")
        public String sku;
        /**
         * stock
         */
        @JSONField(name = "stock")
        public int stock;
        /**
         * sales
         */
        @JSONField(name = "sales")
        public int sales;
        /**
         * image
         */
        @JSONField(name = "image")
        public String image;
        /**
         * barCode
         */
        @JSONField(name = "barCode")
        public String barCode;
        /**
         * cost
         */
        @JSONField(name = "cost")
        public int cost;
        /**
         * otPrice
         */
        @JSONField(name = "otPrice")
        public int otPrice;
        /**
         * price
         */
        @JSONField(name = "price")
        public String price;
        /**
         * volume
         */
        @JSONField(name = "volume")
        public int volume;
        /**
         * weight
         */
        @JSONField(name = "weight")
        public String weight;
        /**
         * unique
         */
        @JSONField(name = "unique")
        public String unique;
        /**
         * detail
         */
        @JSONField(name = "detail")
        public DetailBean detail;
        /**
         * value
         */
        @JSONField(name = "value")
        public List<String> value;

        public static class DetailBean {
            /**
             * 护理需求
             */
            @JSONField(name = "护理需求")
            public String 护理需求;
            /**
             * 服务时长
             */
            @JSONField(name = "服务时长")
            public String 服务时长;
        }
    }

    public static class AttrBean {
        /**
         * detail
         */
        @JSONField(name = "detail")
        public List<String> detail;
        /**
         * value
         */
        @JSONField(name = "value")
        public String value;

        public AttrChooseBean chooseBean;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AttrBean attrBean = (AttrBean) o;
            return Objects.equals(value, attrBean.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class ProductCatesBean {
        /**
         * id
         */
        @JSONField(name = "id")
        public int id;
        /**
         * cateName
         */
        @JSONField(name = "cateName")
        public String cateName;
    }

    /**
     * 本地构建数据
     */
    public static class AttrChooseBean{

        @JSONField(name = "hasStock")
        public boolean hasStock = true;
        @JSONField(name = "value")
        public String value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AttrChooseBean attrBean = (AttrChooseBean) o;
            return Objects.equals(value, attrBean.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
