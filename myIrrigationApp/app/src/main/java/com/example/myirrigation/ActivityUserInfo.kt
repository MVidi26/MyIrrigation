package com.example.myirrigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class ActivityUserInfo : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonReturnUserInfo: Button
    private lateinit var userName: TextView
    private lateinit var city: TextView
    private lateinit var state: TextView
    private lateinit var country: TextView
    private lateinit var cep: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var gson: Gson
    private var user: User = User()
    private var conf: ConfigurationAcess = ConfigurationAcess()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_infos)
        userName = findViewById(R.id.userNameUserInfoId)
        state = findViewById(R.id.stateValueUserInfo)
        country = findViewById(R.id.countryValueUserInfo)
        city = findViewById(R.id.cityValueUserInfo)
        cep = findViewById(R.id.cepValueUserInfo)
        email = findViewById(R.id.emailUserInfoValue)
        phone = findViewById(R.id.phoneUserInfoValue)

        val intent = intent

        if (intent.hasExtra("user")) {
            user = intent.getSerializableExtra("user") as User
        }

        if (intent.hasExtra("conf")) {
            conf = intent.getSerializableExtra("conf") as ConfigurationAcess
        }

        userName.text = user.name
        state.text = user.state
        country.text = user.country
        city.text = user.city
        cep.text = user.cep
        email.text = user.email
        phone.text = user.phone

        buttonReturnUserInfo = findViewById(R.id.buttonReturnUserInfo)
        buttonReturnUserInfo.setOnClickListener(this)
    }

    override fun onClick(p: View?) {
        return when (p?.id) {
            R.id.buttonReturnUserInfo -> {
                val intent = Intent(this@ActivityUserInfo, ActivityHome::class.java)

                intent.putExtra("user", user)
                intent.putExtra("conf", conf)
                startActivity(intent)
            }

            else -> {}
        }
    }

}