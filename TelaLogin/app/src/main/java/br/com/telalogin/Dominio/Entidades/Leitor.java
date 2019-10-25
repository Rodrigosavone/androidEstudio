package br.com.telalogin.Dominio.Entidades;

import java.io.Serializable;

public class Leitor implements Serializable {

    public int codigo;
    public String cdCategoria;
    public String dsCategoria;
    public String numMaxDias;


    public Leitor(){
        codigo = 0;
    }

}
