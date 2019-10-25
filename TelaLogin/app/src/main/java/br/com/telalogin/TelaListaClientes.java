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

import br.com.telalogin.Dominio.Entidades.Cliente;
import br.com.telalogin.Dominio.Repositorio.ClienteRepositorio;
import br.com.telalogin.database.BancoDeDados;



public class TelaListaClientes extends AppCompatActivity {

    private ClienteRepositorio clienteRepositorio;
    private Cliente cliente;
    private ClienteAdpter clienteAdpter;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lista_clientes);
        RecyclerView lstDados = (RecyclerView) findViewById(R.id.lstDados);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        criarConexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);

        clienteRepositorio = new ClienteRepositorio(conexao);
        List<Cliente> dados = clienteRepositorio.buscar();
        clienteAdpter = new ClienteAdpter(dados);

        lstDados.setAdapter(clienteAdpter);

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

    //método para atualizar a lista de clientes cadastrados apos a exlusão de um cliente
    public void atualizarExlusao(View view) {
        Intent intent = new Intent(getApplicationContext(),TelaListaClientes.class);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<Cliente> dados = clienteRepositorio.buscar();
        clienteAdpter = new ClienteAdpter(dados);
        RecyclerView lstDados = (RecyclerView) findViewById(R.id.lstDados);
        lstDados.setAdapter(clienteAdpter);

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
