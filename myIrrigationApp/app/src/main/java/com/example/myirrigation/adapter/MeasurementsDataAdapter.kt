package com.example.myirrigation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myirrigation.APIEndPoints
import com.example.myirrigation.ConfigurationAcess
import com.example.myirrigation.HomeDataMeasurementResponse
import com.example.myirrigation.R
import com.example.myirrigation.network.NetworkUtils
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MeasurementsDataAdapter(config: ConfigurationAcess) :
    RecyclerView.Adapter<MeasurementsDataAdapter.ViewHolderDatas>() {

    private var configuration: ConfigurationAcess = config
    private var gson: Gson = Gson()

    private val uri = "http://${configuration.ip}:${configuration.port}/"
//    private val datas: HomeDataMeasurementResponse = getDataMeasurement(uri)

    private var titles = arrayListOf(
        "Temperatura do Ar", "Umidade do Ar", "Temperadura do Solo",
        "Umidade do Solo", "Fluxo de Água"
    )
    private var description = arrayListOf(
        "Medida em ºC", "Medida em %", "Medida em ºC",
        "Medida em %", "Medida em L/min"
    )
//    private var data = arrayListOf(
//        datas.airTemp, datas.airUmid, datas.tempGroud, datas.umid,
//        datas.waterFlow
//    )
    private var data = arrayListOf("10", "20", "25", "22", "500")

    inner class ViewHolderDatas(view: View) : RecyclerView.ViewHolder(view) {
        val textTitleItem: TextView = view.findViewById(R.id.textTitleItem)
        val textDescription: TextView = view.findViewById(R.id.textDescription)
        val valueSensor: TextView = view.findViewById(R.id.valueSensor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatas {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.linha_item_home,
            parent, false
        )
        return ViewHolderDatas(view)
    }

    override fun getItemCount(): Int {
        return data.count();
    }

    override fun onBindViewHolder(holder: ViewHolderDatas, position: Int) {
        holder.textTitleItem.text = titles[position]
        holder.textDescription.text = description[position]
        holder.valueSensor.text = data[position]
    }

    private fun getDataMeasurement(uri: String): HomeDataMeasurementResponse {
        var data: HomeDataMeasurementResponse = HomeDataMeasurementResponse()

        NetworkUtils.getRetrofitInstance(uri).getData().enqueue(object :
            Callback<HomeDataMeasurementResponse> {
            override fun onResponse(
                call: Call<HomeDataMeasurementResponse>,
                response: Response<HomeDataMeasurementResponse>
            ) {
                Log.e("RESPONSE", "response: ${gson.toJson(response.body())}")
                data = response.body()!!
            }

            override fun onFailure(call: Call<HomeDataMeasurementResponse>, t: Throwable) {
                Log.e("ERRO", "Erro ao buscar os dados ${t.message}")
            }
        })

        return data
    }
}