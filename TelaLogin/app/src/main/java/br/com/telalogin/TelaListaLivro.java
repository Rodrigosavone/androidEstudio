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

import br.com.telalogin.Dominio.Entidades.Leitor;
import br.com.telalogin.Dominio.Entidades.Livro;
import br.com.telalogin.Dominio.Repositorio.LeitorRepositorio;
import br.com.telalogin.Dominio.Repositorio.LivroRepositorio;
import br.com.telalogin.database.BancoDeDados;

public class TelaListaLivro extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;

    LivroRepositorio livroRepositorio;
    LivroAdapter livroAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lista_livros);
        RecyclerView lstlivros = (RecyclerView) findViewById(R.id.lstlivros);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        criarConexao();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstlivros.setLayoutManager(linearLayoutManager);

        livroRepositorio = new LivroRepositorio(conexao);
        List<Livro> dados = livroRepositorio.buscar();
        livroAdapter = new LivroAdapter(dados);

        lstlivros.setAdapter(livroAdapter);

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
        Intent intent = new Intent(getApplicationContext(), TelaLivro.class);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<Livro> dados = livroRepositorio.buscar();
        livroAdapter = new LivroAdapter(dados);
        RecyclerView lstlivros = (RecyclerView) findViewById(R.id.lstlivros);
        lstlivros.setAdapter(livroAdapter);

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
