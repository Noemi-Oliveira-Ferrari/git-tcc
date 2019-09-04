package br.senai.sp.daumhelp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Subcategoria {

    @JsonAlias({"idSubcategoria"})
    private Long idSubcategoria;
    @JsonAlias({"subcategoria"})
    private String subcategoria;


    @Override
    public String toString() {
        return subcategoria;
    }
}
