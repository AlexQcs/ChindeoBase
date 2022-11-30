package com.chindeo.repository.resources.x

import com.chindeo.repository.data.api.kt.MallApi
import com.chindeo.repository.data.model.common.PageLimit
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.params.mall.*
import com.chindeo.repository.data.model.response.mall.PageData
import com.chindeo.repository.util.http.RetrofitManager

/**
 * 护工Repository
 */
class MallRepo : BaseRepository() {

    private val mService by lazy { RetrofitManager.instance.create(MallApi::class.java) }

    suspend fun login(params: MallLoginParamsKt) = mService.login(RequestContent.create(params))

    suspend fun getCategory() = mService.getCategory()

    suspend fun getProductList(params: ProductListParams, page: PageLimit) = mService.getProductList(RequestContent.createRepositoryParams(params, page))

    suspend fun getProductDetail(productId: String) = mService.getProductDetail(productId)

    suspend fun getAddressList(page: PageLimit) = mService.getAddressList(RequestContent.createRepositoryParams(page))

    suspend fun addressPhoneUpdate(addressId: Int, phone: MallAddressPhoneUpdateParams) = mService.addressPhoneUpdate(addressId, RequestContent.createRepositoryParams(phone))

    suspend fun addCart(params: MallAddCartParams) = mService.addCart(RequestContent.createRepositoryParams(params))

    suspend fun createOrder(params: MallCreateOrderParams) = mService.createOrder(RequestContent.createRepositoryParams(params))

    suspend fun getOrderDetailById(id :String)=mService.getOrderDetailById(id)

    suspend fun getOrderStatusType()=mService.getOrderStatusType()

    suspend fun getOrderBillList(params: MallOrderBillListParams, page: PageLimit)=mService.getOrderBillList(RequestContent.createRepositoryParams(params, page))

    suspend fun cancelOrder(id:Int)=mService.cancelOrder(id)

    suspend fun confirmOrder(id:Int)=mService.confirmOrder(id)

    suspend fun againPayOrder(id:Int,orderType:Int)=mService.againPayOrder(id,orderType)

    companion object {
        @Volatile
        private var netWork: MallRepo? = null

        fun getInstance() = netWork ?: synchronized(this) {
            netWork ?: MallRepo().also { netWork = it }
        }
    }


}