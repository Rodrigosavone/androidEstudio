package br.com.telalogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.telalogin.Dominio.Entidades.Cliente;
import br.com.telalogin.Dominio.Repositorio.ClienteRepositorio;
import br.com.telalogin.database.BancoDeDados;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private BancoDeDados bancoDeDados;
    private RecyclerView lstDados;

    private ClienteAdpter clienteAdpter;
    private ClienteRepositorio clienteRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tLogin = (TextView) findViewById(R.id.tLogin);
                TextView tSenha = (TextView) findViewById(R.id.tSenha);
                String login = tLogin.getText().toString();
                String senha = tSenha.getText().toString();
                if(login.equals("Rodrigo") && senha.equals("12345")){
                    alert("Login Realizado com Sucesso");
                    irParaTelaListaCadastro(view);
                }else {
                    alert("Login ou Senha incorretos");
                }

            }
        });

        //criar uma conexão com o banco de dados
        criarConexao();

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

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    public void irParaTelaCadastro(View view) {
        Intent intent1 = new Intent(getApplicationContext(),TelaCadastro.class);
        startActivity(intent1);
    }

    public void irParaTelaListaCadastro(View view) {
        Intent intent = new Intent(getApplicationContext(),ListaDeCadastros.class);
        startActivity(intent);
    }
}
