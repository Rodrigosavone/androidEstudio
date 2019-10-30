package br.com.telalogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.telalogin.Dominio.Entidades.CategoriaLivro;
import br.com.telalogin.Dominio.Entidades.Leitor;
import br.com.telalogin.Dominio.Repositorio.CategoriaLivroRepositorio;
import br.com.telalogin.Dominio.Repositorio.ClienteRepositorio;
import br.com.telalogin.Dominio.Repositorio.LeitorRepositorio;
import br.com.telalogin.database.BancoDeDados;

public class TelaCadastroCatLivro extends AppCompatActivity {

    private EditText edtCdCategoria;
    private EditText edtDsCategoria;
    private EditText edtNumMaxDias;
    private EditText edtTaxaMulta;
    private CategoriaLivro categoriaLivro;
    private CategoriaLivroRepositorio categoriaLivroRepositorio;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_cat_livro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtCdCategoria = (EditText)findViewById(R.id.edtCdCategoria);
        edtDsCategoria = (EditText)findViewById(R.id.edtDsCategoria);
        edtNumMaxDias = (EditText)findViewById(R.id.edtNumMaxDias);
        edtTaxaMulta = (EditText)findViewById(R.id.edtTaxaMulta);

        //cria uma conexão com o banco de dados
        criarConexao();



        //recuperar a informação por parametro
        verificaParametro();
    }
    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);

            conexao = bancoDeDados.getWritableDatabase();

            categoriaLivroRepositorio = new CategoriaLivroRepositorio(conexao);

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

        String cdCategoria =  edtCdCategoria.getText().toString();
        String dsCategoria = edtDsCategoria.getText().toString();
        String numMaxDias = edtNumMaxDias.getText().toString();
        String taxaMulta = edtTaxaMulta.getText().toString();


        //Populando o obejto categoriaLivro
        categoriaLivro.cdCategoria = cdCategoria;
        categoriaLivro.dsCategoria = dsCategoria;
        categoriaLivro.numMaxDias = numMaxDias;
        categoriaLivro.taxaMulta = taxaMulta;



        if(isCampoVazio(String.valueOf(cdCategoria))){
            edtCdCategoria.requestFocus();
            resposta = true;
        }else if(isCampoVazio(dsCategoria)){
            edtDsCategoria.requestFocus();
            resposta = true;
        }else if(isCampoVazio(numMaxDias)){
            edtNumMaxDias.requestFocus();
            resposta = true;
        }else if(isCampoVazio(taxaMulta)){
            edtTaxaMulta.requestFocus();
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

                categoriaLivroRepositorio.excluir(categoriaLivro.codigo);
                finish();

        }

        return super.onOptionsItemSelected(item);
    }
    public void confirmar() {

        if (validarCampos() == false) {
            try {

                if(categoriaLivro.codigo == 0) {
                    categoriaLivroRepositorio.inserir(categoriaLivro);
                    alert("Dados inseridos na Base");
                }else {
                    categoriaLivroRepositorio.alterar(categoriaLivro);
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

    //recupera as informações a hora de alterar o cadastro da categoria
    private void verificaParametro(){
        Bundle bundle = getIntent().getExtras();

        categoriaLivro = new CategoriaLivro();

        if((bundle != null) && (bundle.containsKey("CATEGORIALIVRO"))){
            categoriaLivro = (CategoriaLivro) bundle.getSerializable("CATEGORIALIVRO");

            edtCdCategoria.setText(categoriaLivro.cdCategoria);
            edtDsCategoria.setText(categoriaLivro.dsCategoria);
            edtNumMaxDias.setText(categoriaLivro.numMaxDias);
            edtTaxaMulta.setText(categoriaLivro.taxaMulta);
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
