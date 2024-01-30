package com.example.myirrigation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ConfigurationAcess: Serializable {
    @SerializedName("deviceName")
    @Expose
    var deviceName: String? = null

    @SerializedName("ip")
    @Expose
    var ip: String? = null

    @SerializedName("port")
    @Expose
    var port: String? = null

    @SerializedName("cloud")
    @Expose
    var cloud: String? = null

    @SerializedName("portCloud")
    @Expose
    var portClooud: String? = null

    override fun toString(): String {
        return "ConfigurationAcess(" +
                "deviceName=$deviceName, " +
                "ip=$ip, port=$port, " +
                "cloud=$cloud, " +
                "portClooud=$portClooud)"
    }


}