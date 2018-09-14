package com.support.builders.ApiBuilder.model.registration

data class RegistrationRequestModel(
        var firstname: String,
        var lastname: String,
        var adhdarcard: String,
        var email: String,
        var password: String,
        var refralid: String,
        var deviceid: String
)