package com.chindeo.repository.data.api.kt

import com.alibaba.fastjson.annotation.JSONField
import com.chindeo.repository.contants.MallApiConstants
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.response.HttpResult
import com.chindeo.repository.data.model.response.mall.*
import retrofit2.http.*

/**
 * 营养点餐API
 * Created by xiemaohui on 2022/5/17
 */

interface OrderApi {

    // 床旁登录获取token
    @POST(MallApiConstants.API_ACCESS_TOKEN)
    suspend fun login(@Body params: RequestContent): HttpResult<MallLoginBean>

    //商品分类管理-[床旁端] - 商品分类列表
    @GET(MallApiConstants.API_PRODUCT_CATEGORY)
    suspend fun getCategory(): HttpResult<List<MallProductCategoryBean>>

    // 商品列表
    @POST(MallApiConstants.API_PRODUCT_LIST)
    suspend fun getProductList(@Body params: RequestContent): HttpResult<PageData<MallProductListBean>>

    // 商品详情
    @GET(MallApiConstants.API_PRODUCT_DETAIL + "{id}")
    suspend fun getProductDetail(@Path("id") id: String): HttpResult<MallProductBean>

    // 收货列表
    @GET(MallApiConstants.API_ADDRESS_LIST)
    suspend fun getAddressList(@QueryMap params: RequestContent): HttpResult<MallAddressPageData>

    // 默认手机号修改
    @POST(MallApiConstants.API_ADDRESS_PHONE_UPDATE + "{id}")
    suspend fun addressPhoneUpdate(@Path("id") addressId: Int, @Body params: RequestContent): HttpResult<String>

    // 添加商品到购物车
    @POST(MallApiConstants.API_PRODUCT_ADD_CART)
    suspend fun addCart(@Body params: RequestContent): HttpResult<MallProductAddCartBean?>

    // 获取购物车列表
    @GET(MallApiConstants.API_PRODUCT_GET_CART_LIST)
    suspend fun getCartList(): HttpResult<MallCartListBean?>

    // 获取购物车商品总数
    @GET(MallApiConstants.API_PRODUCT_GET_CART_COUNT)
    suspend fun getCartNum(): HttpResult<MallCartNumBean>

    //修改购物车商品数量
    @POST(MallApiConstants.Api_PRODUCT_CHANGE_CART_NUM+"{id}")
    suspend fun changeCartNum(@Path("id")id: Int, @Body params: RequestContent):HttpResult<String>

    //批量删除购物车
    @POST(MallApiConstants.APi_PRODUCT_DELETE_CART)
    suspend fun deleteCart(@Body params: RequestContent):HttpResult<String> //请求参数 商品id数组

    // 用户用户结算订单
    @POST(MallApiConstants.API_PRODUCT_ORDER_CREATE)
    suspend fun createOrder(@Body params: RequestContent): HttpResult<MallProductOrderCreateBean>

    // 用户获取订单
    @POST(MallApiConstants.API_PRODUCT_ORDER_LIST)
    suspend fun getOrderBillList(@Body params: RequestContent): HttpResult<MallOrderPageData>

    // 根据id获取订单详情
    @GET(MallApiConstants.API_PRODUCT_GET_ORDER_DETAIL+"{id}")
    suspend fun getOrderDetailById(@Path("id")id: String): HttpResult<MallOrderDetailBean>

    // 获取退款状态
    @GET(MallApiConstants.API_PRODUCT_GET_REFUND_STATUS)
    suspend fun getRefundStatus(): HttpResult<MallRefundStatusBean>

    //获取退款
    @GET(MallApiConstants.API_PRODUCT_ORDER_REFUND_LIST)
    suspend fun getRefundList(): HttpResult<MallRefundListBean>

    //根据id获取退款详情
    @POST(MallApiConstants.API_PRODUCT_GET_REFUND_DETAIL+"{id}")
    suspend fun getRefundById(@Path("id")id: Int): HttpResult<MallRefundDetailBean>

    //取消订单
    @GET(MallApiConstants.API_PRODUCT_ORDER_CANCEL+"{id}")
    suspend fun cancelOrder(@Path("id")id: Int): HttpResult<String>

    //确认收货
    @GET(MallApiConstants.API_PRODUCT_ORDER_CONFIRM_RECEIVE+"{id}")
    suspend fun confirmOrder(@Path("id")id: Int): HttpResult<String>

    //重新获取二维码
    @GET(MallApiConstants.API_PRODUCT_ORDER_REPAY+"{id}")
    suspend fun againPayOrder(@Path("id")id: Int,@Query("orderType")orderType:Int): HttpResult<MallProductOrderCreateBean>
}