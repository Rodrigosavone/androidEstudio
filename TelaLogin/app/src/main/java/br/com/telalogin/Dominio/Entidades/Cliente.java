package br.com.telalogin.Dominio.Entidades;

import java.io.Serializable;

public class Cliente implements Serializable {

    public int codigo;
    public String nome;
    public String endereco;
    public String email;
    public String telefone;
    public String cpf;
    public String dtNascimento;
    public String cdLeitor;

    public Cliente(){
        codigo = 0;
    }

}
