package br.com.telalogin.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.telalogin.Dominio.Entidades.Leitor;

public class LeitorRepositorio {

        private SQLiteDatabase conexao;

    public LeitorRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    //Metodo para inserir informações na base de dados
    public void inserir(Leitor leitor){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CDCATEGORIA",leitor.cdCategoria);
        contentValues.put("DSCATEGORIA",leitor.dsCategoria);
        contentValues.put("NUMMAXDIAS",leitor.numMaxDias);

        conexao.insertOrThrow("LEITOR",null,contentValues);
    }


    //Metodo para exluir as informações da base de dados
        public void excluir(int codigo){
        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexao.delete("LEITOR","CODIGO = ?",paramentros);


    }


    //Metdo para alterar as informações na base de dados
        public void alterar(Leitor leitor){

        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGO",leitor.codigo);
        contentValues.put("CDCATEGORIA",leitor.cdCategoria);
        contentValues.put("DSCATEGORIA",leitor.dsCategoria);
        contentValues.put("NUMMAXDIAS",leitor.numMaxDias);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(leitor.codigo);

        conexao.update("LEITOR", contentValues, "CODIGO = ?",paramentros);

    }

    //Metodo para consultar A lista de leitores cadastrados
        public List<Leitor> buscar(){

        List<Leitor> leitores = new ArrayList<Leitor>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO, CDCATEGORIA, DSCATEGORIA, NUMMAXDIAS");
        sql.append("   FROM LEITOR");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Leitor leitor = new Leitor();

                leitor.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                leitor.cdCategoria = resultado.getString(resultado.getColumnIndexOrThrow("CDCATEGORIA"));
                leitor.dsCategoria = resultado.getString(resultado.getColumnIndexOrThrow("DSCATEGORIA"));
                leitor.numMaxDias = resultado.getString(resultado.getColumnIndexOrThrow("NUMMAXDIAS"));
                leitores.add(leitor);
            }while (resultado.moveToNext());
        }

        return leitores;
    }

}
