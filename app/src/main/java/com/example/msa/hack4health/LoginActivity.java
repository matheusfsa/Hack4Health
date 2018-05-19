package com.example.msa.hack4health;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import classes.Pessoa;

public class LoginActivity extends AppCompatActivity {
    String user;
    String pass;
    ArrayList<Pessoa> usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText usuario = (EditText)findViewById(R.id.usuario);
        final EditText senha = (EditText)findViewById(R.id.senha);
        Button entrar = (Button)findViewById(R.id.entrar);
        usuarios = new ArrayList<>();
        usuarios.add(new Pessoa("a","2",1));
        usuarios.add(new Pessoa("b","231233",2));
        usuarios.add(new Pessoa("c","214313",3));
        usuarios.add(new Pessoa("d","231111",1));
        entrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                user = usuario.getText().toString();
                pass = senha.getText().toString();
                if(user.equals("") | pass.equals("") ) {
                    Toast.makeText(LoginActivity.this,"Preencha todos os campos",Toast.LENGTH_LONG).show();
                }else{
                    Pessoa res = temUser(user);
                    if(res != null) {
                        if(res.getSenha().equals(pass)) {
                            if(res.getFuncao() != 3) {
                                Intent intent = new Intent(LoginActivity.this, ListaPacientes.class);
                                intent.putExtra("Pessoa", res);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(LoginActivity.this, DashboardFarmaceutico.class);
                                intent.putExtra("Pessoa", res);
                                startActivity(intent);
                            }

                        }else {
                            Toast.makeText(LoginActivity.this, "Senha incorreta", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"Usuário não existe",Toast.LENGTH_LONG).show();
                    }


                }

            }
        });

    }
    public Pessoa temUser(String user){
        for (Pessoa e: usuarios) {
            if(e.getUsuario().equals(user)){
                return e;
            }

        }
        return null;
    }


}
