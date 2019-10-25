package br.com.telalogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import br.com.telalogin.Dominio.Entidades.Cliente;
import br.com.telalogin.Dominio.Entidades.Leitor;
import br.com.telalogin.Dominio.Repositorio.ClienteRepositorio;
import br.com.telalogin.Dominio.Repositorio.LeitorRepositorio;
import br.com.telalogin.database.BancoDeDados;

public class TelaListaLeitores extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;

    LeitorRepositorio leitorRepositorio;
    LeitorAdpter leitorAdpter;
    Leitor leitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lista_leitores);
        RecyclerView lstDadosLeitor = (RecyclerView) findViewById(R.id.lstDadosLeitor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        criarConexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDadosLeitor.setLayoutManager(linearLayoutManager);

        leitorRepositorio = new LeitorRepositorio(conexao);
        List<Leitor> dados = leitorRepositorio.buscar();
        leitorAdpter = new LeitorAdpter(dados);

        lstDadosLeitor.setAdapter(leitorAdpter);
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
        Intent intent = new Intent(getApplicationContext(), TelaListaLeitores.class);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<Leitor> dados = leitorRepositorio.buscar();
        leitorAdpter = new LeitorAdpter(dados);
        RecyclerView lstDadosLeitor = (RecyclerView) findViewById(R.id.lstDadosLeitor);
        lstDadosLeitor.setAdapter(leitorAdpter);

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
