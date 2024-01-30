package com.example.myirrigation.firestore

import com.example.myirrigation.ConfigurationAcess
import com.google.firebase.firestore.FirebaseFirestore

class Operation {
    val db = FirebaseFirestore.getInstance();
    var collection: String = Operation.collection

    fun persistDataOnFireStore(conf: ConfigurationAcess, db: FirebaseFirestore) {
        val configurationCon = hashMapOf(
            "deviceName" to conf.deviceName,
            "ip" to conf.ip,
            "port" to conf.port,
            "cloud" to conf.cloud,
            "portCloud" to conf.portClooud
        )

        db.collection(collection)
            .document(conf.deviceName.toString())
            .set(configurationCon)
            .addOnSuccessListener {
                println("Documento adicionado com ID: ${conf.deviceName}")
            }
            .addOnFailureListener { e ->
                println("Erro ao adicionar documento: $e")
            }
    }
    
    companion object {
        const val collection = "device"
    }

}