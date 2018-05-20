package com.example.msa.hack4health;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import classes.Paciente;
import classes.Pessoa;
import classes.Remedio;

public class DashboardFarmaceutico extends AppCompatActivity {
    ArrayList<Remedio> lista;
    ListView list;
    ArrayAdapter<Remedio> adapter;
    String res;
    Paciente paciente;
    Pessoa pessoa;
    double dosagem;
    int opcao;
    int posicao;
    String observacao;
    String valor;
    String cod_barras;
    AlertDialog alerta;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opcao = getIntent().getIntExtra("Opcao",-1);
        paciente = (Paciente) getIntent().getSerializableExtra("paciente");
        pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");
        observacao = (String)getIntent().getSerializableExtra("observacao");
        setContentView(R.layout.activity_dashboard_farmaceutico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Activity activity = this;
        list = (ListView) findViewById(R.id.listview2);
        lista = new ArrayList<>();

        lista.add(new Remedio("Hidroclorotiazida",25,"7896862918583"));
        lista.add(new Remedio("Captropio",12.5,"7896472508143"));
        lista.add(new Remedio("Rocefin",1000,"7896226500591"));
        lista.add(new Remedio("Pantoprazol",20,"7896676415445"));
        lista.add(new Remedio("Hemofol",0.25,"7896676415445"));
        lista.add(new Remedio("Dipirona",1000,"7896714207551"));

        adapter = new ArrayAdapter<Remedio>(DashboardFarmaceutico.this, android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Remedio item  = lista.get(position);
                valor = item.getNome();
                dosagem = item.getDosagem();
                cod_barras = item.getCod();
                posicao = position;
                IntentIntegrator integrator = new IntentIntegrator(DashboardFarmaceutico.this);
                integrator.initiateScan();

            }
        });
        Button concluir = (Button)findViewById(R.id.Concluir);
        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lista.size() == 0){
                    if(observacao != null){
                        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardFarmaceutico.this);
                        builder.setTitle("Observação do Médico");
                        builder.setPositiveButton(observacao, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                    }
                    if(opcao == 1){
                        Intent intent = new Intent(DashboardFarmaceutico.this,ListaPacientes.class);
                        intent.putExtra("Pessoa",pessoa);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(DashboardFarmaceutico.this,ConfereQr.class);
                        intent.putExtra("Pessoa",pessoa);
                        intent.putExtra("Paciente",paciente);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            // handle scan result
            String str = scanResult.getContents();
            Remedio rm = getRemedio(str);
            int pos = contem(rm.getNome());

            if(cod_barras.equals(rm.getCod())){
                lista.remove(pos);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Atenção!");
                if(pos!= -1){
                    if(lista.get(pos).getNome().toUpperCase().equals(rm.getNome().toUpperCase())) {
                        builder.setMessage("Os valores de dosagem não conferem");
                        builder.setPositiveButton("Tentar Novamente", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        alerta = builder.create();
                        alerta.show();
                    }
                }else{
                    builder.setMessage("Código de barras não confere");
                    builder.setPositiveButton("Tentar Novamente", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    alerta = builder.create();
                    alerta.show();
                }

            }
            alert(scanResult.getContents());
        }
    }// onactivityresult

    private void  alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
    private int contem(String rm){
        int i = 0;
        for(Remedio e: lista){
            if(e.getNome().equals(rm)){
                return i;
            }
            i++;
        }
        return -1;
    }
    public void scanBar(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog

        }
    }
    private Remedio getRemedio(String cod_barra){
        if(cod_barra.equals("7894164001439")){
            return new Remedio("Captropio",25,cod_barra);
        }else if(cod_barra.equals("7896862918583")){
            return new Remedio("Hidroclorotiazida",25,cod_barra);

        }else if(cod_barra.equals("7896472508143")){
            return new Remedio("Captropio",12.5,cod_barra);
        }else if(cod_barra.equals("7896226500591")){
            return new Remedio("Rocefin",1000,cod_barra);
        }else if(cod_barra.equals("7896004705972")){
            return new Remedio("Pantoprazol",20,cod_barra);
        }
        else if(cod_barra.equals("7896676415445")){
            return new Remedio("Hemofol",0.25,cod_barra);
        }
        else{
            return new Remedio("Dipirona",1000,cod_barra);
        }

    }
}
