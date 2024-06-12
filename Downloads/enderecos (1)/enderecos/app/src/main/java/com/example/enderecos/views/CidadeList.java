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
import com.example.enderecos.databinding.ActivityTelaCadastroBinding;
import com.example.enderecos.entities.Cidade;

import java.util.List;

public class CidadeList extends AppCompatActivity {

    private ActivityCidadeListBinding binding;
    private LocalDatabase db;
    private List<Cidade> cidades;
    private ListView listViewCidades;
    private Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade_list);

        binding = ActivityCidadeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewCidades = binding.listCidades;

        binding.btnHomeCid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAddCid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CidadeList.this, CidadeView.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, CidadeView.class);
        preencheCidades();
    }

    private void preencheCidades() {
        cidades = db.cidadeModel().getAll();
        ArrayAdapter<Cidade> cidadesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cidades);
        listViewCidades.setAdapter(cidadesAdapter);

        listViewCidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Cidade cidadeSelecionada = cidades.get(position);
                edtIntent.putExtra("CIDADE_SELECIONADA_ID",
                        cidadeSelecionada.getCidadeID());
                startActivity(edtIntent);
            }
        });
    }

}