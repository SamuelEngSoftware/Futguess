package br.com.fsamuel.futguess.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FutebolApi {
    @GET("jogadores")
    suspend fun buscarJogadores(): List<Jogador>
}

object RetrofitClient {
     private const val BASE_URL = "https://my-json-server.typicode.com/renansuaris/futguess-api/"

    val api: FutebolApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FutebolApi::class.java)
    }
}