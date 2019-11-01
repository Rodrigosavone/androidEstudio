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

import br.com.telalogin.Dominio.Entidades.Cliente;
import br.com.telalogin.Dominio.Entidades.Livro;
import br.com.telalogin.Dominio.Repositorio.ClienteRepositorio;
import br.com.telalogin.Dominio.Repositorio.LivroRepositorio;
import br.com.telalogin.database.BancoDeDados;

public class TelaCadastroLivro extends AppCompatActivity {

    private EditText edtCodigoLivro;
    private EditText edtISBN;
    private EditText edtTitulo;
    private EditText edtCategoria;
    private EditText edtAutores;
    private EditText edtPalavraChave;
    private EditText edtDtPublicao;
    private EditText edtNumEdicao;
    private EditText edtEditora;
    private EditText edtNumPagina;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;

    private LivroRepositorio livroRepositorio;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_livro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtCodigoLivro = (EditText)findViewById(R.id.edtCodigoLivro);
        edtISBN = (EditText)findViewById(R.id.edtISBN);
        edtTitulo = (EditText)findViewById(R.id.edtTitulo);
        edtCategoria = (EditText)findViewById(R.id.edtCategoria);
        edtAutores = (EditText)findViewById(R.id.edtAutores);
        edtPalavraChave = (EditText)findViewById(R.id.edtPalavraChave);
        edtDtPublicao = (EditText)findViewById(R.id.edtDtPublicao);
        edtNumEdicao = (EditText)findViewById(R.id.edtNumEdicao);
        edtEditora = (EditText)findViewById(R.id.edtEditora);
        edtNumPagina = (EditText)findViewById(R.id.edtNumPagina);

        criarConexao();

        //recuperar a informação por parametro
        verificaParametro();

    }

    //recupera as informações a hora de alterar o cadastro do cliente
    private void verificaParametro(){
        Bundle bundle = getIntent().getExtras();

        livro = new Livro();

        if((bundle != null) && (bundle.containsKey("LIVRO"))){
            livro = (Livro) bundle.getSerializable("LIVRO");

            edtCodigoLivro.setText(livro.codigoLivro);
            edtISBN.setText(livro.ISBN);
            edtTitulo.setText(livro.titulo);
            edtCategoria.setText(livro.categoria);
            edtAutores.setText(livro.autor);
            edtPalavraChave.setText(livro.palavraChave);
            edtDtPublicao.setText(livro.dtPublicacao);
            edtNumEdicao.setText(livro.numEdicao);
            edtEditora.setText(livro.editora);
            edtNumPagina.setText(livro.numPagina);
        }
    }

    // Método para Validar o Campos preenchidos na tela
    public boolean validarCampos(){

        boolean resposta = false;

        String codigoLivro =  edtCodigoLivro.getText().toString();
        String ISBN = edtISBN.getText().toString();
        String titulo = edtTitulo.getText().toString();
        String categoria = edtCategoria.getText().toString();
        String autor = edtAutores.getText().toString();
        String palavraChave = edtPalavraChave.getText().toString();
        String dtPublicacao = edtDtPublicao.getText().toString();
        String numEdicao = edtNumEdicao.getText().toString();
        String editora = edtEditora.getText().toString();
        String numPagina = edtNumPagina.getText().toString();



//        //Populando o obejto livro
        livro.codigoLivro = codigoLivro;
        livro.ISBN = ISBN;
        livro.titulo = titulo;
        livro.categoria = categoria;
        livro.autor = autor;
        livro.palavraChave = palavraChave;
        livro.dtPublicacao = dtPublicacao;
        livro.numEdicao = numEdicao;
        livro.editora = editora;
        livro.numPagina = numPagina;


        if(isCampoVazio(codigoLivro)){
            edtCodigoLivro.requestFocus();
            resposta = true;
        }else if(isCampoVazio(ISBN)){
            edtISBN.requestFocus();
            resposta = true;
        }else if(isCampoVazio(titulo)){
            edtTitulo.requestFocus();
            resposta = true;
        }else if(isCampoVazio(categoria)){
            edtCategoria.requestFocus();
            resposta = true;
        }else if(isCampoVazio(autor)) {
            edtAutores.requestFocus();
            resposta = true;
        }else if(isCampoVazio(palavraChave)) {
            edtPalavraChave.requestFocus();
            resposta = true;
        }else if(isCampoVazio(dtPublicacao)) {
            edtDtPublicao.requestFocus();
            resposta = true;
        }else if(isCampoVazio(numEdicao)) {
            edtNumEdicao.requestFocus();
            resposta = true;
        }else if(isCampoVazio(editora)) {
            edtEditora.requestFocus();
            resposta = true;
        }else if(isCampoVazio(numPagina)) {
            edtNumPagina.requestFocus();
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



    //Metodo para criar Conexão com a Base de Dados
    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);

            conexao = bancoDeDados.getWritableDatabase();

            livroRepositorio = new LivroRepositorio(conexao);

            alert("Conexão Criada");


        }catch(SQLException e){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle((R.string.title_erro));
            dialog.setMessage(e.getMessage());
            dialog.setNeutralButton("Ok",null);
            dialog.show();


        }
    }
    public void confirmar() {

        if (validarCampos() == false) {
            try {

                if (livro.codigo == 0) {
                    livroRepositorio.inserir(livro);
                }else {
                    livroRepositorio.alterar(livro);
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

                livroRepositorio.excluir(livro.codigo);
                finish();

        }

        return super.onOptionsItemSelected(item);
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
