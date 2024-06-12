package com.example.enderecos.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.enderecos.R;
import com.example.enderecos.database.LocalDatabase;
import com.example.enderecos.databinding.ActivityCidadeListBinding;
import com.example.enderecos.databinding.ActivityUsuarioListBinding;
import com.example.enderecos.entities.Cidade;
import com.example.enderecos.entities.Usuario;

import java.util.List;

public class UsuarioList extends AppCompatActivity {

    private ActivityUsuarioListBinding binding;
    private LocalDatabase db;
    private List<Usuario> usuarios;
    private ListView listViewUsuarios;
    private Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade_list);

        binding = ActivityUsuarioListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewUsuarios = binding.listUsuarios;

        binding.btnHomeUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, UsuarioView.class);
        preencheUsuarios();
    }

    private void preencheUsuarios() {
        usuarios = db.usuarioModel().getAll();
        ArrayAdapter<Usuario> usuariosAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, usuarios);
        listViewUsuarios.setAdapter(usuariosAdapter);

        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Usuario usuarioSelecionado = usuarios.get(position);
                edtIntent.putExtra("USUARIO_SELECIONADO_ID",
                        usuarioSelecionado.getUsuarioID());
                startActivity(edtIntent);
            }
        });
    }
}