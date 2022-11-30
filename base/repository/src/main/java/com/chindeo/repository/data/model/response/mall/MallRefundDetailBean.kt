package com.chindeo.repository.data.model.response.mall

/**
 * 根据id获取到的退款订单详情
 * Created by xiemaohui on 2022/6/1
 */
data class MallRefundDetailBean(
    var adminMark: String,
    var cUserId: Int,
    var createdAt: String,
    var deliveryId: String,
    var deliveryMark: String,
    var deliveryPhone: String,
    var deliveryPics: String,
    var deliveryType: String,
    var failMessage: String,
    var id: Int,
    var isCancel: Int,
    var isSystemDel: Int,
    var mark: String,
    var merDeliveryAddress: String,
    var merDeliveryUser: String,
    var merMark: String,
    var orderId: Int,
    var orderSn: String,
    var phone: String,
    var pics: String,
    var refundMessage: String,
    var refundNum: Int,
    var refundOrderSn: String,
    var refundPrice: Int,
    var refundProduct: List<RefundProduct>,
    var refundStatuses: List<RefundStatuse>,
    var refundType: Int,
    var status: Int,
    var statusTime: String,
    var sysTenancyId: Int,
    var tenancyName: String,
    var updatedAt: String,
    var userNickName: String
)

data class RefundProduct(
    var cartInfo: CartInfo,
    var id: Int,
    var isInvalid: Int,
    var isRefund: Int,
    var isReply: Int,
    var isUp: Int,
    var orderId: Int,
    var orderProductId: Int,
    var productId: Int,
    var productNum: Int,
    var productPrice: Int,
    var productSku: String,
    var productType: Int,
    var refundNum: Int,
    var refundOrderId: Int,
    var unique: String
)

 data class RefundStatuse(
    var changeMessage: String,
    var changeTime: String,
    var changeType: String,
    var refundOrderId: Int
)

data class CartInfo(
    var product: Product,
    var productAttr: ProductAttr
)

data class Product(
    var image: String,
    var storeName: String,
    var temp: Temp
)

data class ProductAttr(
    var price: Int,
    var sku: String
)

data class Temp(
    var appoint: Int,
    var isDefault: Int,
    var name: String,
    var sort: Int,
    var type: Int,
    var undelivery: Int
)