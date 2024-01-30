package com.example.myirrigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myirrigation.firestore.Operation
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.io.Serializable


class ActivityLoginConfig : AppCompatActivity(), View.OnClickListener, Serializable {

    private lateinit var returnConf: ImageButton
    private lateinit var inputDeviceName: EditText
    private lateinit var inputIpConection: EditText
    private lateinit var inputPortConection: EditText
    private lateinit var awsInputConection: EditText
    private lateinit var awsInputPortConection: EditText
    private var conf: ConfigurationAcess = ConfigurationAcess()
    private var gson: Gson = Gson()
    private var op: Operation = Operation()
    private val db = FirebaseFirestore.getInstance();
    var notExistsDoc = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuration_acess)
        FirebaseApp.initializeApp(this)

        returnConf = findViewById(R.id.confAcessReturn)
        inputDeviceName = findViewById(R.id.inputDeviceName)
        inputIpConection = findViewById(R.id.inputIpConection)
        inputPortConection = findViewById(R.id.inputPortConection)
        awsInputConection = findViewById(R.id.awsInputConection)
        awsInputPortConection = findViewById(R.id.awsInputPortConection)

        returnConf.setOnClickListener(this)

        inputDeviceName.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) { }
            else {
                val deviceName = inputDeviceName.text.toString().trim()
                if (!deviceName.isEmpty()) {
                    db.collection("device").document(deviceName).get()
                        .addOnCompleteListener {
                        task ->

                        if (task.isSuccessful) {
                            val documento = task.result
                            if (documento != null && documento.exists()) {
                                val dados = documento.data
                                val key = documento.id

                                notExistsDoc = true

                                conf.deviceName = dados?.get("deviceName").toString()
                                conf.ip = dados?.get("ip").toString()
                                conf.port = dados?.get("port").toString()
                                conf.cloud = dados?.get("cloud").toString()
                                conf.portClooud = dados?.get("portCloud").toString()

                                inputDeviceName.setText(conf.deviceName)
                                inputIpConection.setText(conf.ip)
                                inputPortConection.setText(conf.port)
                                awsInputConection.setText(conf.cloud)
                                awsInputPortConection.setText(conf.portClooud)

                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.confAcessReturn -> {
                if (!notExistsDoc) {

                conf.deviceName = inputDeviceName.text.toString()
                conf.ip = inputIpConection.text.toString()
                conf.port = inputPortConection.text.toString()
                conf.cloud = awsInputConection.text.toString()
                conf.portClooud = awsInputPortConection.text.toString()

                    Log.e("Antes do Persist", "conf ${conf.toString()}")
                    op.persistDataOnFireStore(conf, db)
                }
                val intent = Intent(this@ActivityLoginConfig, MainActivity::class.java)
                intent.putExtra("conf", conf)
                startActivity(intent)
            }

            else -> {}
        }
    }
}

