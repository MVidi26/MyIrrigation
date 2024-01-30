package com.example.myirrigation

import java.io.Serializable

class User : Serializable {

    var name: String? = null
    var city: String? = null
    var state: String? = null
    var country: String? = null
    var email: String? = null
    var phone: String? = null
    var cep: String? = null
    var password: String? = null

    override fun toString(): String {
        return "User(name=$name, " +
                "city=$city, " +
                "state=$state, " +
                "country=$country, " +
                "email=$email, " +
                "phone=$phone, " +
                "cep=$cep, " +
                "password=$password)"
    }


}