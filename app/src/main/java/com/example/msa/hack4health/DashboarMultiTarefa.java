package com.example.msa.hack4health;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import classes.Paciente;
import classes.Pessoa;

public class DashboarMultiTarefa extends AppCompatActivity {
    Paciente paciente;
    Pessoa pessoa;
    String observacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboar_multi_tarefa);
        paciente = (Paciente) getIntent().getSerializableExtra("paciente");
        pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");
        observacao = (String)getIntent().getSerializableExtra("observacao");
        ImageView info = (ImageView)findViewById(R.id.informacao_paciente);
        ImageView dispensacao = (ImageView)findViewById(R.id.dispencacao);
        dispensacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboarMultiTarefa.this,DashboardFarmaceutico.class);
                intent.putExtra("paciente",paciente);
                intent.putExtra("pessoa",pessoa);
                intent.putExtra("observacao",observacao);
                startActivity(intent);
            }
        });
    }
}
