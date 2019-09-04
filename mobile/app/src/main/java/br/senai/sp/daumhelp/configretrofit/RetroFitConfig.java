package br.senai.sp.daumhelp.configretrofit;

import br.senai.sp.daumhelp.service.CategoriaService;
import br.senai.sp.daumhelp.service.SubcategoriaService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetroFitConfig {

    private Retrofit retroFit;

    public RetroFitConfig(){
        retroFit = new Retrofit.Builder()
                .baseUrl("http://10.107.144.24:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


    public CategoriaService getCategoriaService(){
        return this.retroFit.create(CategoriaService.class);
    }

    public SubcategoriaService getSubcategoriaService(){
        return this.retroFit.create(SubcategoriaService.class);
    }


}
