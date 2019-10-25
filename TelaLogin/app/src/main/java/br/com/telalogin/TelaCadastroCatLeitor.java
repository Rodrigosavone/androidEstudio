package br.com.telalogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.telalogin.Dominio.Entidades.Cliente;
import br.com.telalogin.Dominio.Entidades.Leitor;
import br.com.telalogin.Dominio.Repositorio.ClienteRepositorio;
import br.com.telalogin.Dominio.Repositorio.LeitorRepositorio;
import br.com.telalogin.database.BancoDeDados;

public class TelaCadastroCatLeitor extends AppCompatActivity {


    private EditText edtCdCategoria;
    private EditText edtDsCategoria;
    private EditText edtNumMaxDias;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;

    private LeitorRepositorio leitorRepositorio;
    private Leitor leitor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_cat_leitor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtCdCategoria = (EditText)findViewById(R.id.edtCdCategoria);
        edtDsCategoria = (EditText)findViewById(R.id.edtDsCategoria);
        edtNumMaxDias = (EditText)findViewById(R.id.edtNumMaxDias);

        //cria uma conexão com o banco de dados
        criarConexao();



        //recuperar a informação por parametro
        verificaParametro();
    }

    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);

            conexao = bancoDeDados.getWritableDatabase();

            leitorRepositorio = new LeitorRepositorio(conexao);

            alert("Conexão Criada");


        }catch(SQLException e){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle((R.string.title_erro));
            dialog.setMessage(e.getMessage());
            dialog.setNeutralButton("Ok",null);
            dialog.show();


        }
    }
    // Método para Validar o Campos preenchidos na tela
    public boolean validarCampos(){

        boolean resposta = false;

        String cdCategoria = edtCdCategoria.getText().toString();
        String dsCategoria = edtDsCategoria.getText().toString();
        String numMaxDias = edtNumMaxDias.getText().toString();

        //Populando o obejto leitor
        leitor.cdCategoria = cdCategoria;
        leitor.dsCategoria = dsCategoria;
        leitor.numMaxDias = numMaxDias;



        if(isCampoVazio(cdCategoria)){
            edtCdCategoria.requestFocus();
            resposta = true;
        }else if(isCampoVazio(dsCategoria)){
            edtDsCategoria.requestFocus();
            resposta = true;
        }else if(isCampoVazio(numMaxDias)){
            edtNumMaxDias.requestFocus();
            resposta = true;
        }

        if (resposta){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle(R.string.title_aviso);
            dialogo.setMessage(R.string.message_campo_invalido);
            dialogo.setNeutralButton("Ok",null);
            dialogo.show();
        }


        return resposta;
    }

    public boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){

            case android.R.id.home:
                finish();

            case R.id.action_ok:
                confirmar();
                break;

            case R.id.action_excluir:

                leitorRepositorio.excluir(leitor.codigo);
                finish();

        }

        return super.onOptionsItemSelected(item);
    }

    public void confirmar() {

        if (validarCampos() == false) {
            try {

                if (leitor.codigo == 0) {
                    leitorRepositorio.inserir(leitor);
                    alert("Dados inseridos na Base");
                }else {
                    leitorRepositorio.alterar(leitor);
                }

                finish();

            } catch (SQLException e) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle((R.string.title_erro));
                dialog.setMessage(e.getMessage());
                dialog.setNeutralButton("Ok", null);
                dialog.show();
            }

        }
    }

    //recupera as informações a hora de alterar o cadastro do cliente
    private void verificaParametro(){
        Bundle bundle = getIntent().getExtras();

        leitor = new Leitor();

        if((bundle != null) && (bundle.containsKey("LEITOR"))){
            leitor = (Leitor) bundle.getSerializable("LEITOR");

            edtCdCategoria.setText(leitor.cdCategoria);
            edtDsCategoria.setText(leitor.dsCategoria);
            edtNumMaxDias.setText(leitor.numMaxDias);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
