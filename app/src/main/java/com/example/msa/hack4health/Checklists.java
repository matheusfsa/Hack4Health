package com.example.msa.hack4health;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import classes.Paciente;
import classes.Pessoa;
import classes.Remedio;

public class Checklists extends AppCompatActivity {
    int i;
    final String NOMEFANTASIA = "Nome Fantasia: ";
    final String NOME = "Nome da Droga: ";
    final String EFEITO = "Efeito: ";
    final String VIADEADMINISTRACAO = "Via de administração: ";
    final String EFEITOSCOLATERAIS = "Efeitos Colaterais: ";
    TextView nomeSocial;
    TextView nome;
    TextView efeito;
    TextView viaAdministrativa;
    TextView efeitosColaterais;
    TextView info;
    Remedio[] rm;
    Pessoa p;
    Paciente pe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        i = 0;
        super.onCreate(savedInstanceState);
        rm = new Remedio[4];
        rm[0] = new Remedio("Rocefin", "Ceftriaxona", "Antibiótico","1g EV 12/12hs-06hs e 18hs", "Eosinofilia","");
        rm[1] = new Remedio("Pantozol", "Pantoprazol", "Reduz a acidez estomacal", "20 mg VO em jejum - 06hs","Dor de cabeça","");
        rm[2] = new Remedio("Capotem", "Captopril", "Hipotensor", "25mg - 1/2 comprimido VO 6/6hs - 06hs, 12hs, 18hs, 00hs","Tosse seca e persistente","");
        rm[3] = new Remedio("Novalgina", "Dipirona", "Analgésico", "1g EV SN 6/6hs","Reação alérgica","");
        setContentView(R.layout.activity_checklists);
        p = (Pessoa)getIntent().getSerializableExtra("Pessoa");
        pe = (Paciente)getIntent().getSerializableExtra("Paciente");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nomeSocial = (TextView)findViewById(R.id.textView17);
        nome = (TextView)findViewById(R.id.textView18);
        efeito = (TextView)findViewById(R.id.textView19);
        viaAdministrativa = (TextView)findViewById(R.id.textView20);
        efeitosColaterais = (TextView)findViewById(R.id.textView21);
        info = (TextView)findViewById(R.id.textView22);
        nomeSocial.setText(NOMEFANTASIA +"Hemofol");
        nome.setText(NOME + "Heparina");
        efeito.setText(EFEITO+"Anticoagulante");
        viaAdministrativa.setText(VIADEADMINISTRACAO + "Subcutânea, Dose usual 5000 UI 12/12 hrs ou 8/8 hrs");
        efeitosColaterais.setText(EFEITOSCOLATERAIS + "Sangramento");
        info.setText("Este paciente é alérgico à este medicamento.");
        Button btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i <= 4){
                    nomeSocial.setText(NOMEFANTASIA + rm[i].getNome());
                    nome.setText(NOME + rm[i].getNomeDroga());
                    efeito.setText(EFEITO + rm[i].getEfeito());
                    viaAdministrativa.setText(VIADEADMINISTRACAO + rm[i].getViaDeAdministracao());
                    efeitosColaterais.setText(EFEITOSCOLATERAIS + rm[i].getEfeitoColateral());

                    info.setText(rm[i].getInfo());
                    i++;
            }else{
                    Intent intent = new Intent(Checklists.this,ListaPacientes.class);
                    intent.putExtra("Pessoa",p);
                    intent.putExtra("Paciente",pe);
                    startActivity(intent);
                }
            }
        });
    }

}
