package com.example.myirrigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myirrigation.Requests.StartStopJobRequest
import com.example.myirrigation.adapter.MeasurementsDataAdapter
import com.example.myirrigation.mqtt.ConnectionMQTT
import com.example.myirrigation.mqtt.NotificationUtils
import com.example.myirrigation.network.GetResults
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityHome : AppCompatActivity(), View.OnClickListener {
    private var gson: Gson = Gson()
    private var user: User = User()
    private var config: ConfigurationAcess = ConfigurationAcess()
    private var conecctionMqtt: ConnectionMQTT = ConnectionMQTT()
    private lateinit var recyclerMenuHome: RecyclerView
    private lateinit var userNameViewHome: TextView
    private lateinit var buttonMenuHome: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        NotificationUtils.createNotificationChannel(this)
        userNameViewHome = findViewById(R.id.textUserHome)
        recyclerMenuHome = findViewById(R.id.homeRecyclerView)
        buttonMenuHome = findViewById(R.id.buttonMenuHome)

        val intent = intent
        if (intent.hasExtra("user")) {
            user = intent.getSerializableExtra("user") as User
        }
        if (intent.hasExtra("conf")) {
            config = intent.getSerializableExtra("conf") as ConfigurationAcess
        }

        val broker = "tcp://${config.cloud}:${config.portClooud}"

        executeMqtt(broker)


        Log.i("TT", "user: ${user.name}")
        userNameViewHome.text = user.name
        userNameViewHome.setOnClickListener(this)

        recyclerMenuHome.layoutManager = GridLayoutManager(
            this, 1,
            RecyclerView.VERTICAL, false
        )
        recyclerMenuHome.adapter = MeasurementsDataAdapter(config)

        buttonMenuHome.setOnClickListener { buildingPopUpMenu() }
    }

    private fun executeMqtt(broker: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val isConnected = conecctionMqtt.connect(broker, config.deviceName!!)
            if (isConnected) {
                conecctionMqtt.subscribe() { message ->
                    println("Menssagem Recebida: $message")
                    NotificationUtils.showNotification(this@ActivityHome, "Information", message)
                }
    //                conecctionMqtt.subs2(this@ActivityHome)
            }
        }
    }

    private fun buildingPopUpMenu() {
        val popupMenu = PopupMenu(this@ActivityHome, buttonMenuHome)
        popupMenu.menuInflater.inflate(R.menu.home_menu, popupMenu.menu)
        val uri = "http://${config.ip}:${config.port}/"
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                    R.id.menu1 -> {
                        true
                    }

                    R.id.job -> {
                        true
                    }

                    R.id.startJob -> {
                        val start = StartStopJobRequest()
                        start.start = true
                        GetResults().startStopSync(uri, start)
                        true
                    }

                    R.id.stopJob -> {
                        val start = StartStopJobRequest()
                        start.start = false
                        GetResults().startStopSync(uri, start)
                        true
                    }

                    R.id.logout -> {
                        val intent = Intent(this@ActivityHome, MainActivity::class.java)
                        GetResults().logoutsync(uri, user)
                        startActivity(intent)
                        true
                    }
                    else -> {false}
            }
        }
        popupMenu.show()
    }

    override fun onClick(v: View?) {
        return when (v?.id) {
            R.id.textUserHome -> {
                val intent = Intent(this@ActivityHome, ActivityUserInfo::class.java)
                intent.putExtra("user", user)
                intent.putExtra("conf", config)
                startActivity(intent)
            }

            else -> {}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}