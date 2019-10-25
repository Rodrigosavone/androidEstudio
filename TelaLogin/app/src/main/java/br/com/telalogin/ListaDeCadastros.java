package br.com.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListaDeCadastros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_cadastros);
    }

    public void irParaTelaCadastro(View view) {
        Intent intent = new Intent(getApplicationContext(),TelaCadastro.class);
        startActivityForResult(intent, 0);
    }

    public void irParaTelaCategoriaLeitor(View view) {
        Intent intent = new Intent(getApplicationContext(),TelaCategoriaLeitor.class);
        startActivityForResult(intent, 0);
    }

    public void irParaTelaCategoriaLivro(View view) {
        Intent intent = new Intent(getApplicationContext(),TelaCategoriaLivro.class);
        startActivityForResult(intent, 0);
    }
}
