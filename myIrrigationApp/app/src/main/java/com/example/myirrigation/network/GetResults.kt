package com.example.myirrigation.network

import com.example.myirrigation.Requests.LoginRequest
import com.example.myirrigation.Requests.StartStopJobRequest
import com.example.myirrigation.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class GetResults {

   suspend fun login(uri: String, login: LoginRequest) : User {
       return withContext(Dispatchers.IO) {
           try {
               val response = NetworkUtils.getRetrofitInstance(uri).login(login).execute()

               if (response.isSuccessful) {
                   response.body()!!
               } else {
                   throw Exception("Erro na chamada")
               }
           } catch (e: Exception) {
               throw e
           }
       }
   }

    fun loginsync(uri: String, login: LoginRequest): User {
        return runBlocking {
            login(uri, login)
        }
    }

    suspend fun logout (uri: String, user: User) {
        return withContext(Dispatchers.IO) {
            try {
                val response = NetworkUtils.getRetrofitInstance(uri).logout(user).execute()

                if (response.isSuccessful) {
                    response.body()!!
                } else {
                    throw Exception("Erro ao Deslogar")
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun logoutsync(uri: String, user: User) {
        return runBlocking {
            logout(uri, user)
        }
    }

    suspend fun startStopJob (uri: String, req: StartStopJobRequest ) {
        return withContext(Dispatchers.IO) {
            try {
                val response = NetworkUtils.getRetrofitInstance(uri).startStopJob(req).execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    throw Exception ("Erro ao Iniciar ou Parar o Job")
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun startStopSync(uri: String, req: StartStopJobRequest) {
        return runBlocking {
            startStopJob(uri, req)
        }
    }

}