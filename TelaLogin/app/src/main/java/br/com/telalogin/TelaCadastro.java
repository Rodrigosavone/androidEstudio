package br.com.telalogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class TelaCadastro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void irParaTelaCadastroCliente(View view) {
        Intent intent = new Intent(getApplicationContext(), TelaCadastroCliente.class);
        startActivityForResult(intent, 0);
    }

    public void irParaTelaListaCliente(View view) {
        Intent intent = new Intent(getApplicationContext(),TelaListaClientes.class);
        startActivity(intent);
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
