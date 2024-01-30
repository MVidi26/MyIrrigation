package com.example.myirrigation.network

import com.example.myirrigation.HomeDataMeasurementResponse
import com.example.myirrigation.Requests.LoginRequest
import com.example.myirrigation.Requests.StartStopJobRequest
import com.example.myirrigation.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("irrigatedLand/ws/acess/login")
    fun login(@Body body: LoginRequest) : Call<User>

    @POST("irrigatedLand/ws/acess/logout")
    fun logout(@Body body: User) : Call<Boolean>

    @POST("irrigatedLand/ws/job/startStopJob")
    fun startStopJob(@Body body: StartStopJobRequest) : Call<Boolean>

    @GET("irrigatedLand/ws/ms/data")
    fun getData() : Call<HomeDataMeasurementResponse>
}