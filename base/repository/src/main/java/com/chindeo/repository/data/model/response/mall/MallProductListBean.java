package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


//商品列表
public class MallProductListBean {


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
    public String isShow;
    /**
     * status
     */
    @JSONField(name = "status")
    public String status;
    /**
     * unitName
     */
    @JSONField(name = "unitName")
    public String unitName;
    /**
     * sort
     */
    @JSONField(name = "sort")
    public String sort;
    /**
     * rank
     */
    @JSONField(name = "rank")
    public String rank;
    /**
     * sales
     */
    @JSONField(name = "sales")
    public String sales;
    /**
     * price
     */
    @JSONField(name = "price")
    public String price;
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
    public String isHot;
    /**
     * isBenefit
     */
    @JSONField(name = "isBenefit")
    public String isBenefit;
    /**
     * isBest
     */
    @JSONField(name = "isBest")
    public String isBest;
    /**
     * isNew
     */
    @JSONField(name = "isNew")
    public String isNew;
    /**
     * isGood
     */
    @JSONField(name = "isGood")
    public String isGood;
    /**
     * productType
     */
    @JSONField(name = "productType")
    public String productType;
    /**
     * ficti
     */
    @JSONField(name = "ficti")
    public String ficti;
    /**
     * browse
     */
    @JSONField(name = "browse")
    public String browse;
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
    public String specType;
    /**
     * refusal
     */
    @JSONField(name = "refusal")
    public String refusal;
    /**
     * rate
     */
    @JSONField(name = "rate")
    public String rate;
    /**
     * replyCount
     */
    @JSONField(name = "replyCount")
    public String replyCount;
    /**
     * careCount
     */
    @JSONField(name = "careCount")
    public String careCount;
    /**
     * image
     */
    @JSONField(name = "image")
    public String image;
    /**
     * oldId
     */
    @JSONField(name = "oldId")
    public String oldId;
    /**
     * tempId
     */
    @JSONField(name = "tempId")
    public String tempId;
    /**
     * sysTenancyId
     */
    @JSONField(name = "sysTenancyId")
    public String sysTenancyId;
    /**
     * sysBrandId
     */
    @JSONField(name = "sysBrandId")
    public String sysBrandId;
    /**
     * productCategoryId
     */
    @JSONField(name = "productCategoryId")
    public String productCategoryId;
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
     * 购买数量/购物车数量
     */
    @JSONField(name = "buyCount") //自定义的字段
    public int buyCount=0;
    /**
     * 购物车id
     */
    @JSONField(name = "cartId") //自定义的字段
    public int cartId=-1;
    /**
     * productCates
     */
    @JSONField(name = "productCates")
    public List<ProductCatesBean> productCates;

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

}
