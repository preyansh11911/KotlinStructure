package com.support.builders.ApiBuilder.model.registration

import com.google.gson.annotations.SerializedName

data class RegistrationResponseModel(@SerializedName("status")
                                     var status: Int = 0,
                                     @SerializedName("success")
                                     var success: Int = 0,
                                     @SerializedName("message")
                                     var message: String = "")