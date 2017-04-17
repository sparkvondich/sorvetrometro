package com.example.phillipe.sorvetrometro.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.phillipe.sorvetrometro.model.Sorveteria;

import java.util.LinkedList;
import java.util.List;

public class SorveteriaDAO {

    private DBOpenHelper banco;

    public SorveteriaDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_SORVETERIA = "sorveteria";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";

    public List<Sorveteria> getAll() {
        List<Sorveteria> sorveterias = new LinkedList<>();
        String query = "SELECT * FROM " + TABELA_SORVETERIA;
        SQLiteDatabase db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Sorveteria sorveteria = null;

        if (cursor.moveToFirst()) {
            do {
                sorveteria = new Sorveteria();
                sorveteria.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                sorveteria.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
                sorveterias.add(sorveteria);
            } while (cursor.moveToNext());
        }
        return sorveterias;
    }

    public Sorveteria getBy(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = {COLUNA_ID, COLUNA_NOME};
        String where = "id = " + id;
        Cursor cursor = db.query(true, TABELA_SORVETERIA, colunas, where, null, null, null, null, null);
        Sorveteria sorveteria = null;
        if (cursor != null) {
            cursor.moveToFirst();
            sorveteria = new Sorveteria();
            sorveteria.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
            sorveteria.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
        }
        return sorveteria;
    }
}


