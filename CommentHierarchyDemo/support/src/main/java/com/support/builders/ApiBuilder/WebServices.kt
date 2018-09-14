package com.support.builders.ApiBuilder

import com.support.POJOModel
import com.support.builders.ApiBuilder.model.MoviesItem
import com.support.builders.ApiBuilder.model.registration.RegistrationResponseModel
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WebServices {

    @GET("")
    fun <T : POJOModel> sampleWebService(): Observable<T>

    @GET("demos/marvel/")
    fun movieList(): Observable<ArrayList<MoviesItem>>

    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(@Field("firstname") firstname: String,
                     @Field("lastname") lastname: String,
                     @Field("adhdarcard") adhdarcard: String,
                     @Field("email") email: String,
                     @Field("password") password: String,
                     @Field("refralid") refralid: String,
                     @Field("deviceid") deviceid: String): Observable<RegistrationResponseModel>

    enum class ApiNames {
        sample, movieList, registration
    }
}