package com.example.myirrigation

import com.google.gson.annotations.SerializedName

class HomeDataMeasurementResponse {

    @SerializedName("airTemp")
    var airTemp: String? = null

    @SerializedName("airUmid")
    var airUmid: String? = null

    @SerializedName("tempGround")
    var tempGroud: String? = null

    @SerializedName("umid")
    var umid: String? = null

    @SerializedName("waterFlow")
    var waterFlow: String? = null
}