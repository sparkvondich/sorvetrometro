package com.example.phillipe.sorvetrometro;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;
import com.example.phillipe.sorvetrometro.model.Sorvete;
import com.example.phillipe.sorvetrometro.model.Sorveteria;
import com.example.phillipe.sorvetrometro.dao.SorveteDAO;
import com.example.phillipe.sorvetrometro.dao.SorveteriaDAO;


import com.example.phillipe.sorvetrometro.R;

public class NovoSorveteActivity extends AppCompatActivity {

    public final static int CODE_NOVO_SORVETE = 1002;
    private TextInputLayout tilNomeSorvete;
    private Spinner spSorveteria;
    private List<Sorveteria> sorveterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_sorvete);
        tilNomeSorvete = (TextInputLayout) findViewById(R.id.tilNomeSorvete);
        spSorveteria = (Spinner) findViewById(R.id.spSorveteria);
        SorveteriaDAO sorveteriaDAO = new SorveteriaDAO(this);
        sorveterias = sorveteriaDAO.getAll();
        ArrayAdapter<Sorveteria> adapter = new ArrayAdapter<Sorveteria>(getApplicationContext(), R.layout.sorveteria_spinner_item, sorveterias);
        adapter.setDropDownViewResource(R.layout.sorveteria_spinner_item);
        spSorveteria.setAdapter(adapter);
    }

    public void cadastrar(View v) {
        SorveteDAO sorveteDAO = new SorveteDAO(this);
        Sorvete sorvete = new Sorvete();
        sorvete.setNome(tilNomeSorvete.getEditText().getText().toString());
        sorvete.setSorveteria((Sorveteria) spSorveteria.getSelectedItem());
        sorveteDAO.add(sorvete);
        retornaParaTelaAnterior();
    }

    public void retornaParaTelaAnterior() {
        Intent intentMessage = new Intent();
        setResult(CODE_NOVO_SORVETE, intentMessage);
        finish();
    }
}