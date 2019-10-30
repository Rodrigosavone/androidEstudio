package br.com.telalogin;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.telalogin.Dominio.Entidades.CategoriaLivro;
import br.com.telalogin.Dominio.Repositorio.CategoriaLivroRepositorio;
import br.com.telalogin.database.BancoDeDados;

public class TelaListaCategoriaLivros extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;

    CategoriaLivroRepositorio categoriaLivroRepositorio;
    CategoriaLirvoAdapter categoriaLirvoAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lista_categoria_livros);
        RecyclerView lstDadosCatLivros = (RecyclerView) findViewById(R.id.lstDadosCatLivros);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //criar a conexão com o banco de dados
        criarConexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDadosCatLivros.setLayoutManager(linearLayoutManager);

        categoriaLivroRepositorio = new CategoriaLivroRepositorio(conexao);
        List<CategoriaLivro> dados = categoriaLivroRepositorio.buscar();
        categoriaLirvoAdapter = new CategoriaLirvoAdapter(dados);

        lstDadosCatLivros.setAdapter(categoriaLirvoAdapter);
    }

    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);

            conexao = bancoDeDados.getWritableDatabase();

        }catch(SQLException e){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle((R.string.title_erro));
            dialog.setMessage(e.getMessage());
            dialog.setNeutralButton("Ok",null);
            dialog.show();


        }
    }

    //método para atualizar a lista de leitores cadastrados apos a exlusão de um leitor
    public void atualizarExlusao(View view) {
        Intent intent = new Intent(getApplicationContext(), TelaListaCategoriaLivros.class);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<CategoriaLivro> dados = categoriaLivroRepositorio.buscar();
        categoriaLirvoAdapter = new CategoriaLirvoAdapter(dados);
        RecyclerView lstDadosCatLivros = (RecyclerView) findViewById(R.id.lstDadosCatLivros);
        lstDadosCatLivros.setAdapter(categoriaLirvoAdapter);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
