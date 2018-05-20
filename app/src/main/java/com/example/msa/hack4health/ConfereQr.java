package com.example.msa.hack4health;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import classes.Paciente;

public class ConfereQr extends AppCompatActivity {
    Paciente p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = (Paciente)getIntent().getSerializableExtra("Paciente");
        setContentView(R.layout.activity_confere_qr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btn = (Button) findViewById(R.id.verificar);

        final Activity activity = this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Camera Scan");
                integrator.setCameraId(0);
                integrator.setOrientationLocked(true);
                integrator.initiateScan();
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            String s = result.getContents();
            if(s!=null){

                if(s.equals(p.getNome())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Alerta");
                    builder.setMessage("Verificação feita com sucesso!");
                    builder.setPositiveButton("Avançar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(ConfereQr.this, Checklists.class);
                            intent.putExtra("Pessoa",p.getMedico());
                            intent.putExtra("Paciente",p);
                            startActivity(intent);

                        }
                    });
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Alerta");
                    builder.setMessage("O QR Code não corresponde ao paciente");
                    builder.setPositiveButton("Tentar novamente", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(ConfereQr.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }// onactivityresult

    private void  alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

}
