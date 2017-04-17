package com.example.phillipe.sorvetrometro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import com.example.phillipe.sorvetrometro.model.Sorveteria;
import com.example.phillipe.sorvetrometro.model.Sorvete;

public class SorveteDAO {

    private SQLiteDatabase db;
    private DBOpenHelper banco;

    public SorveteDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    private static final String TABELA_SORVETE = "sorvete";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_SORVETERIA_ID = "sorveteria_id";

    private static final String[] COLUMNS = {COLUNA_ID, COLUNA_NOME, COLUNA_SORVETERIA_ID};
    public String add(Sorvete sorvete) {
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, sorvete.getNome());
        values.put(COLUNA_SORVETERIA_ID, sorvete.getSorveteria().getId());
        resultado = db.insert(TABELA_SORVETE, null, values);
        db.close();
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public List<Sorvete> getAll() {
        List<Sorvete> sorvetes = new LinkedList<>();
        String rawQuery = "SELECT t.*, c.nome FROM " + SorveteDAO.TABELA_SORVETE+ " t INNER JOIN "
                + SorveteriaDAO.TABELA_SORVETERIA +
                " c ON t." + SorveteDAO.COLUNA_SORVETERIA_ID +
                " = c." + SorveteriaDAO.COLUNA_ID + " ORDER BY " + SorveteDAO.COLUNA_NOME + " ASC";
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Sorvete sorvete = null;
        if (cursor.moveToFirst()) {
            do {
                sorvete = new Sorvete();
                sorvete.setId(cursor.getInt(0));
                sorvete.setNome(cursor.getString(2));
                sorvete.setSorveteria(new Sorveteria(cursor.getInt(1), cursor.getString(3)));
                sorvetes.add(sorvete);
            } while (cursor.moveToNext());
        } return sorvetes;
    }
}
