package br.senai.sp.daumhelp.service;

import java.util.List;

import br.senai.sp.daumhelp.model.Subcategoria;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SubcategoriaService {

    @GET("subcategorias")
    Call<List<Subcategoria>> buscarSubcategorias();

}
