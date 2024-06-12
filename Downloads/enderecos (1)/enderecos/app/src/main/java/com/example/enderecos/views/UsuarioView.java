package com.example.enderecos.views;

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
import com.example.enderecos.databinding.ActivityUsuarioViewBinding;
import com.example.enderecos.entities.Cidade;
import com.example.enderecos.entities.Usuario;

public class UsuarioView extends AppCompatActivity {

    private LocalDatabase db;
    private ActivityUsuarioViewBinding binding;
    private int dbUsuarioID;
    private Usuario dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade_view);
        binding = ActivityUsuarioViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbUsuarioID = getIntent().getIntExtra(
                "USUARIO_SELECIONADO_ID", -1);


    }

    protected void onResume() {
        super.onResume();
        if(dbUsuarioID >= 0) {
            getDBUsuario();
        } else {
            binding.btnExcluirUsuario.setVisibility(View.GONE);
        }
    }

    private void getDBUsuario() {
        dbUsuario = db.usuarioModel().getUsuario(dbUsuarioID);
        binding.edtNome.setText(dbUsuario.getNome());
        binding.edtEmail.setText(dbUsuario.getEmail());
    }

    public void salvarUsuario(View view) {
        String nomeUsuario = binding.edtNome.getText().toString();
        String emailUsuario = binding.edtEmail.getText().toString();
        if (nomeUsuario.equals("")) {
            Toast.makeText(this, "Adicione um nome.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailUsuario.equals("")) {
            Toast.makeText(this, "Adicione um email.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario thisUsuario = new Usuario(nomeUsuario, emailUsuario);

        if (dbUsuario != null) {
            thisUsuario.setUsuarioID(dbUsuarioID);
            db.usuarioModel().update(thisUsuario);
            Toast.makeText(this, "Usuário atualizada com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.usuarioModel().insertAll(thisUsuario);
            Toast.makeText(this, "Usuário criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void voltar(View view) {
        finish();
    }
}