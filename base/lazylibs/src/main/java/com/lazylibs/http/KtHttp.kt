//package com.lazylibs.http
//
//import com.lazylibs.http.data.converter.FastJsonConverterFactory
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//
//class KtHttp {
//}
//
//object ServiceCreator {
//    private const val BASE_URL = "填入URL"
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(FastJsonConverterFactory.create())
//        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//
//    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
//    inline fun <reified T> create(): T = create(T::class.java)
//}
//
//
//suspend fun <T> Call<T>.await(): T {
//    return suspendCoroutine { continuation ->
//        enqueue(object : Callback<T> {
//            override fun onResponse(call: Call<T>, response: Response<T>) {
//                val body = response.body()
//                if (body != null) {
//                    // 请求成功，继续
//                    continuation.resume(body)
//                } else {
//                    // 请求成功但无结果，继续
//                    continuation.resumeWithException(RuntimeException("response body is null"))
//                }
//            }
//
//            override fun onFailure(call: Call<T>, t: Throwable) {
//                // 请求失败，返回错误信息
//                continuation.resumeWithException(t)
//            }
//        })
//    }
//}
