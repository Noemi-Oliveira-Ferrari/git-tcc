package br.senai.sp.daumhelp.configretrofit;

import br.senai.sp.daumhelp.model.Profissional;
import br.senai.sp.daumhelp.service.CategoriaService;
import br.senai.sp.daumhelp.service.EnderecoService;
import br.senai.sp.daumhelp.service.ProfissionalService;
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

    public EnderecoService getEnderecoService(){
        return this.retroFit.create(EnderecoService.class);
    }

    public ProfissionalService getProfissionalService(){
        return this.retroFit.create(ProfissionalService.class);
    }


}
