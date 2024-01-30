package com.example.myirrigation.Requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequest {
    var user: String? = null
    var password: String? = null
    var deviceName: String? = null
}