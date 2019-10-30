package br.com.telalogin.Dominio.Entidades;

import java.io.Serializable;

public class CategoriaLivro implements Serializable {

    public int codigo;
    public String cdCategoria;
    public String dsCategoria;
    public String numMaxDias;
    public String taxaMulta;



    public CategoriaLivro(){
        codigo = 0;
    }
}
