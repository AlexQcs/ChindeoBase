package com.chindeo.repository.data.api.kt

import com.chindeo.repository.contants.MallApiConstants
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.response.HttpResult
import com.chindeo.repository.data.model.response.mall.*
import retrofit2.http.*

interface MallApi {

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

    // 收获列表
    @GET(MallApiConstants.API_ADDRESS_LIST)
    suspend fun getAddressList(@QueryMap params: RequestContent): HttpResult<MallAddressPageData>

    // 默认手机号修改
    @POST(MallApiConstants.API_ADDRESS_PHONE_UPDATE + "{id}")
    suspend fun addressPhoneUpdate(@Path("id") addressId: Int, @Body params: RequestContent): HttpResult<String>

    // 添加商品到购物车
    @POST(MallApiConstants.API_PRODUCT_ADD_CART)
    suspend fun addCart(@Body params: RequestContent): HttpResult<MallProductAddCartBean?>

    // 用户用户结算订单
    @POST(MallApiConstants.API_PRODUCT_ORDER_CREATE)
    suspend fun createOrder(@Body params: RequestContent): HttpResult<MallProductOrderCreateBean>

    // 根据id获取订单详情
    @GET(MallApiConstants.API_PRODUCT_GET_ORDER_DETAIL+"{id}")
    suspend fun getOrderDetailById(@Path("id")id: String): HttpResult<MallOrderDetailBean>

    //
    @GET(MallApiConstants.API_GET_SEARCH_ORDER_STATUS_TYPE)
    suspend fun getOrderStatusType(): HttpResult<MutableList<MallOrderStatusTypeBean>>

    // 用户获取订单
    @POST(MallApiConstants.API_PRODUCT_ORDER_LIST)
    suspend fun getOrderBillList(@Body params: RequestContent): HttpResult<MallOrderPageData>

    //取消订单
    @GET(MallApiConstants.API_PRODUCT_ORDER_CANCEL+"{id}")
    suspend fun cancelOrder(@Path("id")id: Int): HttpResult<String?>

    //确认收货
    @GET(MallApiConstants.API_PRODUCT_ORDER_CONFIRM_RECEIVE+"{id}")
    suspend fun confirmOrder(@Path("id")id: Int): HttpResult<String>

    //重新获取二维码
    @GET(MallApiConstants.API_PRODUCT_ORDER_REPAY+"{id}")
    suspend fun againPayOrder(@Path("id")id: Int,@Query("orderType")orderType:Int): HttpResult<MallProductOrderCreateBean>
}