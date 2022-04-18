package com.infotrack.capacitacionandroidit.datos;

import com.infotrack.capacitacionandroidit.traversales.enumeradores.Constantes;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrigenDatosRetrofit {

    public Retrofit obtenerConexionRetrofit() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder cliente = new OkHttpClient().newBuilder();
        cliente.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder().baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente.build()).build();
    }

}
