package br.com.telalogin.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.telalogin.Dominio.Entidades.Cliente;

public class ClienteRepositorio {

    private SQLiteDatabase conexao;

    public ClienteRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",cliente.nome);
        contentValues.put("ENDERECO",cliente.endereco);
        contentValues.put("EMAIL",cliente.email);
        contentValues.put("TELEFONE",cliente.telefone);
        contentValues.put("CPF",cliente.cpf);
        contentValues.put("DTNASCIMENTO",cliente.dtNascimento);
        contentValues.put("CDLEITOR",cliente.cdLeitor);

        conexao.insertOrThrow("CLIENTE",null,contentValues);



    }

    public void excluir(int codigo){
        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexao.delete("CLIENTE","CODIGO = ?",paramentros);

    }

    public void alterar(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",cliente.nome);
        contentValues.put("ENDERECO",cliente.endereco);
        contentValues.put("EMAIL",cliente.email);
        contentValues.put("TELEFONE",cliente.telefone);
        contentValues.put("CPF",cliente.cpf);
        contentValues.put("DTNASCIMENTO",cliente.dtNascimento);
        contentValues.put("CDLEITOR",cliente.cdLeitor);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(cliente.codigo);

        conexao.update("CLIENTE", contentValues, "CODIGO = ?",paramentros);

    }

    public List<Cliente>buscar(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO, NOME, ENDERECO, EMAIL, TELEFONE, CPF, DTNASCIMENTO, CDLEITOR");
        sql.append("   FROM CLIENTE");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Cliente cliente = new Cliente();
                cliente.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                cliente.nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                cliente.endereco = resultado.getString(resultado.getColumnIndexOrThrow("ENDERECO"));
                cliente.email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
                cliente.telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
                cliente.cpf = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));
                cliente.dtNascimento = resultado.getString(resultado.getColumnIndexOrThrow("DTNASCIMENTO"));
                cliente.cdLeitor = resultado.getString(resultado.getColumnIndexOrThrow("CDLEITOR"));

                clientes.add(cliente);
            }while (resultado.moveToNext());
        }

        return clientes;
    }
}
