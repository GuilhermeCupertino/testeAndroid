package com.example.enderecos.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.enderecos.R;
import com.example.enderecos.database.LocalDatabase;
import com.example.enderecos.databinding.ActivityEnderecoViewBinding;
import com.example.enderecos.entities.Cidade;
import com.example.enderecos.entities.Endereco;
import java.util.List;

public class EnderecoView extends AppCompatActivity {

    private ActivityEnderecoViewBinding binding;
    private LocalDatabase db;
    private int dbEnderecoID;
    private List<Cidade> cidades;
    private Spinner spnCidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnderecoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbEnderecoID = getIntent().getIntExtra("idEnd", -1);
        spnCidades = binding.spnCidades;
        preencheCidades();

        binding.btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(EnderecoView.this, MapsActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbEnderecoID >= 0) {
            preencherDadosEndereco();
        } else {
            binding.btnExcluirModelo.setVisibility(View.GONE);
            binding.btnMapa.setVisibility(View.GONE);
        }
    }

    private void preencheCidades() {
        cidades = db.cidadeModel().getAll();
        ArrayAdapter<Cidade> cidadesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cidades);
        spnCidades.setAdapter(cidadesAdapter);
    }

    private void preencherDadosEndereco() {
        SharedPreferences sharedPreferences = getSharedPreferences("locPref", MODE_PRIVATE);
        dbEnderecoID = sharedPreferences.getInt("idEnd", -1);
        Endereco endereco = db.enderecoModel().getEndereco(dbEnderecoID);

        if (endereco != null) {
            binding.edtDescricao.setText(endereco.getDescricao());
            binding.edtLat.setText(String.valueOf(endereco.getLongitude()));
            binding.edtLong.setText(String.valueOf(endereco.getLatitude()));
        } else {
            Toast.makeText(this, "Erro: Endereço não encontrado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void salvarEndereco(View view) {
        Endereco endereco = db.enderecoModel().getEndereco(dbEnderecoID);
        String descricao = binding.edtDescricao.getText().toString();
        String novaCidade = "";


        if(spnCidades.getSelectedItem() != null){
            novaCidade = spnCidades.getSelectedItem().toString();
        }
        if(descricao.equals("")){
            Toast.makeText(this, "A descrição é obrigatória", Toast.LENGTH_SHORT).show();
            return; 
        }
        if(novaCidade.equals("")) {
            Toast.makeText(this, "Entre com uma Cidade.", Toast.LENGTH_SHORT).show();
            return;
        }

        Endereco novoEndereco = new Endereco();
        novoEndereco.setDescricao(descricao);
        novoEndereco.setLongitude(-20.452302);
        novoEndereco.setLatitude(-54.619325);
        //Log.d("cheguei", "ceg " + novoEndereco.getLatitude());
        novoEndereco.setCidadeID(cidades.get(
                spnCidades.getSelectedItemPosition()).getCidadeID());
        if(endereco != null){
            novoEndereco.setEnderecoID(dbEnderecoID);
            db.enderecoModel().update(novoEndereco);
            Toast.makeText(this, "Endereço atualizado com sucesso.",
                    Toast.LENGTH_SHORT).show();
        } else {
            db.enderecoModel().insertAll(novoEndereco);
            Toast.makeText(this, "Endereço cadastrado com sucesso.",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirEndereco(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Endereço")
                .setMessage("Deseja excluir esse endereço?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void excluir() {
        Endereco endereco = db.enderecoModel().getEndereco(dbEnderecoID);
        db.enderecoModel().delete(endereco);
        Toast.makeText(this, "Endereço excluído com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void voltar(View view) {
        finish();
    }
}