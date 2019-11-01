package br.com.telalogin.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.telalogin.Dominio.Entidades.Leitor;
import br.com.telalogin.Dominio.Entidades.Livro;

public class LivroRepositorio {

    private SQLiteDatabase conexao;

    public LivroRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    //Metodo para inserir informações na base de dados
    public void inserir(Livro livro){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGOLIVRO",livro.codigoLivro);
        contentValues.put("ISBN",livro.ISBN);
        contentValues.put("CATEGORIA",livro.categoria);
        contentValues.put("AUTOR",livro.autor);
        contentValues.put("PALAVRACHAVE",livro.palavraChave);
        contentValues.put("DTPUBLICACAO",livro.dtPublicacao);
        contentValues.put("NUMEDICAO",livro.numEdicao);
        contentValues.put("EDITORA",livro.editora);
        contentValues.put("NUMPAGINA",livro.numPagina);

        conexao.insertOrThrow("LIVRO",null,contentValues);
    }

    //Metodo para exluir as informações da base de dados
    public void excluir(int codigo){
        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexao.delete("LIVRO","CODIGO = ?",paramentros);
    }

    //Metdo para alterar as informações na base de dados
    public void alterar(Livro livro){

        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGOLIVRO",livro.codigoLivro);
        contentValues.put("ISBN",livro.ISBN);
        contentValues.put("CATEGORIA",livro.categoria);
        contentValues.put("AUTOR",livro.autor);
        contentValues.put("PALAVRACHAVE",livro.palavraChave);
        contentValues.put("DTPUBLICACAO",livro.dtPublicacao);
        contentValues.put("NUMEDICAO",livro.numEdicao);
        contentValues.put("EDITORA",livro.editora);
        contentValues.put("NUMPAGINA",livro.numPagina);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(livro.codigo);

        conexao.update("LIVRO", contentValues, "CODIGO = ?",paramentros);

    }

    //Metodo para consultar A lista de Livros cadastrados
    public List<Livro> buscar(){

        List<Livro> livros = new ArrayList<Livro>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO, CODIGOLIVRO, ISBN, CATEGORIA,");
        sql.append("       AUTOR, PALAVRACHAVE, DTPUBLICACAO, NUMEDICAO, EDITORA, NUMPAGINA");
        sql.append("  FROM LIVRO");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Livro livro = new Livro();

                livro.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                livro.codigoLivro = resultado.getString(resultado.getColumnIndexOrThrow("CODIGOLIVRO"));
                livro.ISBN = resultado.getString(resultado.getColumnIndexOrThrow("ISBN"));
                livro.categoria = resultado.getString(resultado.getColumnIndexOrThrow("CATEGORIA"));
                livro.autor = resultado.getString(resultado.getColumnIndexOrThrow("AUTOR"));
                livro.palavraChave = resultado.getString(resultado.getColumnIndexOrThrow("PALAVRACHAVE"));
                livro.dtPublicacao = resultado.getString(resultado.getColumnIndexOrThrow("DTPUBLICACAO"));
                livro.numEdicao = resultado.getString(resultado.getColumnIndexOrThrow("NUMEDICAO"));
                livro.editora = resultado.getString(resultado.getColumnIndexOrThrow("EDITORA"));
                livro.numPagina = resultado.getString(resultado.getColumnIndexOrThrow("NUMPAGINA"));
                livros.add(livro);
            }while (resultado.moveToNext());
        }

        return livros;
    }
}
