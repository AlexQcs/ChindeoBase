    package com.chindeo.repository.contants;

public interface MallApiConstants {

    String BASE_PATH = "v1/";

    String PUBLIC_PATH = BASE_PATH + "public/";
    String API_ACCESS_TOKEN = PUBLIC_PATH + "device/login"; // 床旁登录获取token


    String DEVICE_PATH = BASE_PATH + "device/";
    String API_PRODUCT_CATEGORY = DEVICE_PATH + "productCategory/getProductCategoryList"; //商品分类管理-[床旁端] - 商品分类列表
    String API_PRODUCT_LIST = DEVICE_PATH + "product/getProductList"; // 商品列表
    String API_PRODUCT_DETAIL = DEVICE_PATH + "product/getProductById/"; // 商品详情

    String API_PRODUCT_GET_CART_LIST=DEVICE_PATH+"cart/getCartList"; //获取购物车列表
    String API_PRODUCT_GET_CART_COUNT=DEVICE_PATH+"cart/getProductCount"; //获取购物车商品总量
    String API_PRODUCT_ADD_CART = DEVICE_PATH + "cart/createCart"; // 添加商品到购物车
    String APi_PRODUCT_DELETE_CART=DEVICE_PATH+"cart/deleteCart"; //批量删除购物车
    String Api_PRODUCT_CHANGE_CART_NUM=DEVICE_PATH+"cart/changeCartNum/"; //修改购物车商品数量

   // String API_GET_ORDER_STATUS_TYPE=PUBLIC_PATH+"getOrderStatusType";//订单状态对照
    String API_GET_SEARCH_ORDER_STATUS_TYPE=PUBLIC_PATH+"getSearchOrderStatusType";//订单状态对照

    String API_PRODUCT_ORDER_CHECK = DEVICE_PATH + "order/checkOrder"; // 用户下单数据
    String API_PRODUCT_ORDER_CREATE = DEVICE_PATH + "order/createOrder"; // 用户用户结算订单
    String API_PRODUCT_ORDER_LIST = DEVICE_PATH + "order/getOrderList"; // 我的订单
    String API_PRODUCT_GET_ORDER_DETAIL=DEVICE_PATH+"order/getOrderById/"; //根据id获取订单详情
    String API_PRODUCT_ORDER_REFUND_LIST = DEVICE_PATH + "refundOrder/getRefundOrderList"; // 我的退款订单
    String API_PRODUCT_ORDER_CANCEL = DEVICE_PATH + "order/cancelOrder/"; // 取消订单
    String API_PRODUCT_ORDER_REPAY = DEVICE_PATH + "order/payOrder/"; //  重新获取支付二维码

    String API_PRODUCT_ORDER_CHECK_REFUND = DEVICE_PATH + "order/checkRefundOrder/"; //  申请退款
    String API_PRODUCT_ORDER_REFUND = DEVICE_PATH + "order/refundOrder/"; //  提交退款
    String API_PRODUCT_ORDER_CONFIRM_RECEIVE = DEVICE_PATH + "order/confirmOrder/"; // 确认收货
    String API_PRODUCT_GET_REFUND_STATUS=DEVICE_PATH+"getRefundStatusType"; //获取退款订单状态
    String API_PRODUCT_GET_REFUND_DETAIL=DEVICE_PATH+"refundOrder/getRfundOrderById/"; //根据id获取退款详情

    String ADDRESS_PATH = DEVICE_PATH + "address/";
    String API_ADDRESS_LIST = ADDRESS_PATH + "getAddressList"; // 收货列表
    String API_ADDRESS_UPDATE = ADDRESS_PATH + "updateAddress/"; // 收货地址管理
    String API_ADDRESS_PHONE_UPDATE = ADDRESS_PATH + "updateAddressPhone/"; // 默认手机号修改


}
