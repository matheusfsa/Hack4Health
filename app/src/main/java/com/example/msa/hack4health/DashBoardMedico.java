package com.example.msa.hack4health;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import classes.Paciente;

public class DashBoardMedico extends AppCompatActivity {
    Paciente p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_medico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Paciente p = (Paciente)getIntent().getSerializableExtra("paciente");
        TextView titulo = (TextView)findViewById(R.id.titulo);
        titulo.setText(p.toString());
        ImageView info = (ImageView)findViewById(R.id.info);
        ImageView pron = (ImageView)findViewById(R.id.alterar_pront);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(DashBoardMedico.this, InformacaoPaciente.class);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(DashBoardMedico.this, AlterarProntuario.class);
            }
        });
    }

}
