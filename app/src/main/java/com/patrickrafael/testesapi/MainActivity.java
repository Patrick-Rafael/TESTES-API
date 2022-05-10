package com.patrickrafael.testesapi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.patrickrafael.testesapi.api.CEPService;
import com.patrickrafael.testesapi.model.CEP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private Button buttonAcao;
    private TextView textResultado;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ids();

        configuracaoBasicaRetrofit();

        buttonAcao.setOnClickListener(view -> {

            recuperarCEPRetrofit();



        });

    }

    private void recuperarCEPRetrofit(){

        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP();

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {

                if(response.isSuccessful()){
                    CEP cep = response.body();
                    textResultado.setText( cep.getLogradouro());
                }

            }
            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });



    }

    public void ids(){

        buttonAcao = findViewById(R.id.buttonAcao);
        textResultado = findViewById(R.id.textViewResultado);

    }

    private void configuracaoBasicaRetrofit(){
        //Configuração Basica do Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}