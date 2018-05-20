package com.example.msa.hack4health;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuEnfermeiro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_enfermeiro);
        ImageView atender = (ImageView)findViewById(R.id.atender_paciente);
        atender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEnfermeiro.this, DashboardFarmaceutico.class);
                intent.putExtra("Opcao",2);
                startActivity(intent);
            }
        });
    }

}
