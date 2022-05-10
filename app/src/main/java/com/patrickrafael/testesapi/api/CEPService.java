package com.patrickrafael.testesapi.api;

import com.patrickrafael.testesapi.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CEPService {
//Recuperar dados

    @GET("01310100/json/")
    Call<CEP> recuperarCEP();

}
