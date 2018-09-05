package com.support.builders.ApiBuilder

import com.support.POJOModel
import io.reactivex.Observable
import retrofit2.http.GET

interface WebServices {

    @GET("")
    fun <T : POJOModel> sampleWebService(): Observable<T>

    enum class ApiNames {
        sample
    }
}