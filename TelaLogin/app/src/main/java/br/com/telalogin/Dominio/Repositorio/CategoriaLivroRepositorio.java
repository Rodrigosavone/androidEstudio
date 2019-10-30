package br.com.telalogin.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.telalogin.Dominio.Entidades.CategoriaLivro;

public class CategoriaLivroRepositorio {

    private SQLiteDatabase conexao;


    public CategoriaLivroRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    //Metodo para inserir informações na base de dados
    public void inserir(CategoriaLivro categoriaLivro){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CDCATEGORIA",categoriaLivro.cdCategoria);
        contentValues.put("DSCATEGORIA",categoriaLivro.dsCategoria);
        contentValues.put("NUMMAXDIAS",categoriaLivro.numMaxDias);
        contentValues.put("TXMULTA",categoriaLivro.taxaMulta);


        conexao.insertOrThrow("CATEGORIALIVRO",null,contentValues);
    }


    //Metodo para exluir as informações da base de dados
    public void excluir(int codigo){
        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexao.delete("CATEGORIALIVRO","CDCATEGORIA = ?",paramentros);


    }


    //Metdo para alterar as informações na base de dados
    public void alterar(CategoriaLivro categoriaLivro){

        ContentValues contentValues = new ContentValues();
        contentValues.put("CDCATEGORIA", categoriaLivro.cdCategoria);
        contentValues.put("DSCATEGORIA",categoriaLivro.dsCategoria);
        contentValues.put("NUMMAXDIAS",categoriaLivro.numMaxDias);
        contentValues.put("TXMULTA",categoriaLivro.taxaMulta);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(categoriaLivro.cdCategoria);

        conexao.update("CATEGORIALIVRO", contentValues, "CDCATEGORIA = ?",paramentros);

    }

    //Metodo para consultar A lista de categorias de livros cadastrados
    public List<CategoriaLivro> buscar(){

        List<CategoriaLivro> categoriaLivros = new ArrayList<CategoriaLivro>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CDCATEGORIA, DSCATEGORIA, NUMMAXDIAS, TXMULTA");
        sql.append("   FROM CATEGORIALIVRO");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                CategoriaLivro categoriaLivro = new CategoriaLivro();

                categoriaLivro.cdCategoria = resultado.getInt(resultado.getColumnIndexOrThrow("CDCATEGORIA"));
                categoriaLivro.dsCategoria = resultado.getString(resultado.getColumnIndexOrThrow("DSCATEGORIA"));
                categoriaLivro.numMaxDias = resultado.getString(resultado.getColumnIndexOrThrow("NUMMAXDIAS"));
                categoriaLivro.taxaMulta = resultado.getString(resultado.getColumnIndexOrThrow("TXMULTA"));
                categoriaLivros.add(categoriaLivro);
            }while (resultado.moveToNext());
        }

        return categoriaLivros;
    }



}
