package br.com.telalogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class TelaLivro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_livro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void irParaTelaCadastrarLivro(View view) {
        Intent intent = new Intent(getApplicationContext(), TelaCadastroLivro.class);
        startActivityForResult(intent, 0);
    }

    public void irParaTelaListaLivros(View view) {
        Intent intent = new Intent(getApplicationContext(), TelaListaCategoriaLivros.class);
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
