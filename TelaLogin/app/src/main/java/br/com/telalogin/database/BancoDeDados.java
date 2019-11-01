package br.com.telalogin.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BancoDeDados extends SQLiteOpenHelper {
    public BancoDeDados(@Nullable Context context) {
        super(context, "Dados", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Script.getCriarTableCliente());
        db.execSQL(Script.getCriarTabelaCategoriaLeitor());
        db.execSQL(Script.getCriarTabelaCategoriaLivro());
        db.execSQL(Script.getCriarTabelaLivro());


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
