package com.example.myirrigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myirrigation.Requests.LoginRequest
import com.example.myirrigation.network.GetResults
import com.example.myirrigation.network.NetworkUtils
import com.google.android.gms.common.api.internal.LifecycleActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity(), View.OnClickListener, Serializable {
    private lateinit var userN: EditText;
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var configuration: ImageButton
    private var gson: Gson = Gson()
    private var conf: ConfigurationAcess = ConfigurationAcess()
    var user: User = User()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        if (intent.hasExtra("conf")) {
            conf = intent.getSerializableExtra("conf") as ConfigurationAcess
        }

        userN = findViewById(R.id.inserUser)
        password = findViewById(R.id.insertPass)
        login = findViewById(R.id.login)
        configuration = findViewById(R.id.buttonLoginConfig)

        configuration.setOnClickListener(this)
        login.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        val username = userN.text.toString()
        val password = password.text.toString()

        when (view?.id) {
            R.id.login -> {
                /*ao clicar deve buscar o usuario e chamar a tela principar passando o usuario*/
                val intent = Intent(this@MainActivity, ActivityHome::class.java)

                val config = conf
                val user = connection(username, password, config)
                if (user.name.isNullOrBlank()) {
                    return
                }
                /*passar user e senha para a função connection*/
                intent.putExtra("user", user)
                intent.putExtra("conf", config)
                startActivity(intent)
            }

            R.id.buttonLoginConfig -> {
                val intentConfig = Intent(this@MainActivity, ActivityLoginConfig::class.java)
                startActivity(intentConfig)
            }
        }
    }

    private fun connection(username: String, password: String, config: ConfigurationAcess?): User {

        val login: LoginRequest = LoginRequest()
        var uri: String = String()
        var cloud: String = String()
        val gson: Gson = Gson()

        if (config != null) {
            if (config.cloud != null) {
                cloud = "tcp://${config.cloud}:${config.portClooud}"
            }
            if (config.ip != null) {
                uri = "http://${config.ip}:${config.port}/"
            }
            login.deviceName = config.deviceName
        }
        Log.e("TT", "URI: $uri")
        login.user = username
        login.password = password

        Log.i("RQ", "request: $login")
        user = GetResults().loginsync(uri, login)
        return user
    }
}
