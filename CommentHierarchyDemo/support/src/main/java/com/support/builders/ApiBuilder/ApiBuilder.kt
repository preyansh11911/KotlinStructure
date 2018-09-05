package com.support.builders.ApiBuilder

import com.example.parth.kotlinpractice_2.support.CoreActivity
import com.google.gson.GsonBuilder
import com.support.POJOModel
import com.support.builders.ApiBuilder.WebServices.ApiNames
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun <T : POJOModel> CoreActivity<*, *, *>.callApi(apiName: ApiNames, api: (Retrofit) -> Observable<T>, builder: ApiBuilder<T>.() -> Unit) = ApiBuilder(this, apiName, api).apply(builder)
fun setBaseURL(baseURL: String) {
    ApiBuilder.BASE_URL = baseURL
}

class ApiBuilder<T : POJOModel>(mActivity: CoreActivity<*, *, *>, apiName: ApiNames, api: (Retrofit) -> Observable<T>) {

    companion object {
        val OKHTTP_TIMEOUT = 30.toLong()
        val TAG = "ApiClient"
        var BASE_URL = ""
    }

    var retrofit: Retrofit? = null
    lateinit var observableApi: Observable<T>

    init {
        val gson = with(GsonBuilder()) {
            setLenient()
            setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            create()
        }

        try {
            if (BASE_URL.isNotBlank())
                retrofit = with(Retrofit.Builder()) {
                    baseUrl("")
                    client(okHttpClient())
                    addConverterFactory(GsonConverterFactory.create(gson))
                    addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    build()
                }
            else
                throw IllegalArgumentException("Base URL can not be empty")
        } catch (e: IllegalArgumentException) {
            print(e.localizedMessage)
        }

        observableApi = api.invoke(retrofit!!)
    }

    fun okHttpClient(): OkHttpClient {
        var client: OkHttpClient? = null
        if (client == null) {
            val clientBuilder = with(OkHttpClient.Builder()) {
                retryOnConnectionFailure(true)
                connectTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
            }

            clientBuilder.addInterceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                chain.proceed(requestBuilder.build())
            }

            client = clientBuilder.build()
        }
        return client!!
    }

    fun subscribeToSingle()
}