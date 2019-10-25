package br.com.telalogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class TelaCategoriaLeitor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_categoria_leitor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void irParaTelaCadastroCatLeitor(View view) {
        Intent intent = new Intent(getApplicationContext(),TelaCadastroCatLeitor.class);
        startActivityForResult(intent, 0);
    }

    public void irParaTelaListaLeitor(View view) {
        Intent intent = new Intent(getApplicationContext(),TelaListaLeitores.class);
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
