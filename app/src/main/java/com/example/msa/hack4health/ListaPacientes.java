package com.example.msa.hack4health;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import classes.Medico;
import classes.Paciente;
import classes.Pessoa;

public class ListaPacientes extends AppCompatActivity {
    ArrayList<Paciente> lista;
    ArrayList<Paciente> lista2;
    ListView list;
    Pessoa p;
    MaterialSearchView materialSearchView;
    ArrayAdapter<Paciente> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);
        list = (ListView) findViewById(R.id.listview);

        p = (Pessoa)getIntent().getSerializableExtra("Pessoa");

        lista = new ArrayList<>();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ArrayList<Paciente> banco = new ArrayList<>();
        banco.add(new Paciente(new Medico("a"),"a","c"));
        banco.add(new Paciente(new Medico("a"),"d","e"));
        banco.add(new Paciente(new Medico("b"),"f","g"));
        banco.add(new Paciente(new Medico("c"),"h","i"));
        for (Paciente pa: banco) {
            if(pa.getMedico().equals(p.getUsuario())){
                lista.add(pa);

            }
        }
        adapter = new ArrayAdapter<Paciente>(ListaPacientes.this, android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        materialSearchView = (MaterialSearchView)findViewById(R.id.material);
        materialSearchView.closeSearch();
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lista2 = new ArrayList<>();
                for (Paciente p: lista) {
                    lista2.add(p);
                }
                //Toast.makeText(ListaPacientes.this,query,Toast.LENGTH_LONG).show();
                for (Paciente p: lista2) {
                    if(!p.toString().contains(query)){
                        lista2.remove(p);

                    }
                    adapter = new ArrayAdapter<>(ListaPacientes.this, android.R.layout.simple_list_item_1, lista2);
                    list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Seu codigo aqui
                //Toast.makeText(ListarNoticias.this, lista.get(position).toString(),Toast.LENGTH_LONG).show();
                if(p.getFuncao() == 1) {
                    Intent intent = new Intent(ListaPacientes.this, Dashboard.class);
                    intent.putExtra("paciente", lista.get(position));
                    startActivity(intent);
                }

            }


        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        materialSearchView.setMenuItem(item);
        return true;
    }


}
