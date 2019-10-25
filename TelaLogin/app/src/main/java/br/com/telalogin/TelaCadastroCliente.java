package br.com.telalogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.telalogin.Dominio.Entidades.Cliente;
import br.com.telalogin.Dominio.Repositorio.ClienteRepositorio;
import br.com.telalogin.database.BancoDeDados;

public class TelaCadastroCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtCpf;
    private EditText edtDataNascimento;
    private EditText edtCategoriaLeitor;

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;

    private ClienteRepositorio clienteRepositorio;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtCpf = (EditText)findViewById(R.id.edtCpf);
        edtDataNascimento = (EditText)findViewById(R.id.edtDataNascimento);
        edtCategoriaLeitor = (EditText)findViewById(R.id.edtCategoriaLeitor);

        criarConexao();

        //recuperar a informação por parametro
        verificaParametro();


    }
    //recupera as informações a hora de alterar o cadastro do cliente
    private void verificaParametro(){
        Bundle bundle = getIntent().getExtras();

        cliente = new Cliente();

        if((bundle != null) && (bundle.containsKey("CLIENTE"))){
            cliente = (Cliente) bundle.getSerializable("CLIENTE");
            edtNome.setText(cliente.nome);

            edtEndereco.setText(cliente.endereco);
            edtEmail.setText(cliente.email);
            edtTelefone.setText(cliente.telefone);
            edtCpf.setText(cliente.cpf);
            edtDataNascimento.setText(cliente.dtNascimento);
            edtCategoriaLeitor.setText(cliente.cdLeitor);
        }
    }
    private void criarConexao(){
        try{
            bancoDeDados = new BancoDeDados(this);

            conexao = bancoDeDados.getWritableDatabase();

            clienteRepositorio = new ClienteRepositorio(conexao);



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

                if (cliente.codigo == 0) {
                    clienteRepositorio.inserir(cliente);
                }else {
                    clienteRepositorio.alterar(cliente);
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

    //Validar os campos digitados no cadatro de clientes
    public boolean validarCampos(){

        boolean resposta = false;

        String nome = edtNome.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String email = edtEmail.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String cpf = edtCpf.getText().toString();
        String dtNascimento = edtDataNascimento.getText().toString();
        String cdLeitor = edtCategoriaLeitor.getText().toString();

        //Populando o objeto cliente
        cliente.nome = nome;
        cliente.endereco = endereco;
        cliente.email = email;
        cliente.telefone = telefone;
        cliente.cpf = cpf;
        cliente.dtNascimento = dtNascimento;
        cliente.cdLeitor = cdLeitor;


        if(isCampoVazio(nome)){
            edtNome.requestFocus();
            resposta = true;
        }else if(isCampoVazio(endereco)){
            edtEndereco.requestFocus();
            resposta = true;
        }else if(!isEmailValido(email)){
            edtEmail.requestFocus();
            resposta = true;
        }else if(isCampoVazio(telefone)){
            edtTelefone.requestFocus();
            resposta = true;
        }else if(isCampoVazio(cpf)){
            edtCpf.requestFocus();
            resposta = true;
        }else if(isCampoVazio(dtNascimento)){
            edtDataNascimento.requestFocus();
            resposta = true;
        }else if(isCampoVazio(cdLeitor)){
            edtCategoriaLeitor.requestFocus();
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

    public boolean isEmailValido(String email){
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_cliente, menu);
        return super.onCreateOptionsMenu(menu);
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

               clienteRepositorio.excluir(cliente.codigo);
               finish();

        }

        return super.onOptionsItemSelected(item);
    }

}
