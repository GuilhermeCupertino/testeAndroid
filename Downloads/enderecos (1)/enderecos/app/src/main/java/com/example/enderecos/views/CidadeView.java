package com.example.enderecos.views;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.enderecos.R;
import com.example.enderecos.database.LocalDatabase;
import com.example.enderecos.databinding.ActivityCidadeViewBinding;
import com.example.enderecos.entities.Cidade;

public class CidadeView extends AppCompatActivity {

    private LocalDatabase db;
    private ActivityCidadeViewBinding binding;
    private int dbCidadeID;
    private Cidade dbCidade;
    private Spinner spnEstados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade_view);
        binding = ActivityCidadeViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbCidadeID = getIntent().getIntExtra(
                "CIDADE_SELECIONADA_ID", -1);

        spnEstados = binding.spnEstados;

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,
                R.array.estadosBrasil, android.R.layout.simple_dropdown_item_1line);
        binding.spnEstados.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        if(dbCidadeID >= 0) {
            getDBCidade();
        } else {
            binding.btnExcluirCidade.setVisibility(View.GONE);
        }
    }

    private void getDBCidade() {
        dbCidade = db.cidadeModel().getCidade(dbCidadeID);
        binding.edtCidade.setText(dbCidade.getCidade());
    }

    public void salvarCidade(View view) {
        String nomeCidade = binding.edtCidade.getText().toString();
        String nomeEstado = binding.spnEstados.getSelectedItem().toString();
        if (nomeCidade.equals("")) {
            Toast.makeText(this, "Adicione uma cidade.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (nomeEstado.equals("")) {
            Toast.makeText(this, "Adicione um estado.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Cidade thisCidade = new Cidade(nomeCidade, nomeEstado);

        if (dbCidade != null) {
            thisCidade.setCidadeID(dbCidadeID);
            db.cidadeModel().update(thisCidade);
            Toast.makeText(this, "Cidade atualizada com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.cidadeModel().insertAll(thisCidade);
            Toast.makeText(this, "Cidade criada com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirCidade(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Cidade")
                .setMessage("Deseja excluir essa cidade?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        if(db.enderecoModel().getEndByCid(dbCidadeID)!=null){
            Toast.makeText(this, "Impossível excluir cidade com endereços cadastrados", Toast.LENGTH_SHORT).show();
        }else {
            db.cidadeModel().delete(dbCidade);
            Toast.makeText(this, "Cidade excluída com sucesso", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void voltar(View view) {
        finish();
    }
}