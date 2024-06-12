package com.example.enderecos.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enderecos.R;
import com.example.enderecos.database.LocalDatabase;
import com.example.enderecos.databinding.ActivityMainBinding;
import com.example.enderecos.databinding.ActivityTelaCadastroBinding;
import com.example.enderecos.entities.Usuario;

public class TelaCadastro extends AppCompatActivity {

    private ActivityTelaCadastroBinding binding;
    private LocalDatabase db;
    private EditText edtNome, edtEmail, edtSenha;
    private int dbUsuarioId;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=LocalDatabase.getDatabase(getApplicationContext());
        edtNome=findViewById(R.id.edtNome);
        edtEmail=findViewById(R.id.edtEmail);
        edtSenha=findViewById(R.id.edtSenha);
        dbUsuarioId=getIntent().getIntExtra("usuario_id",-1);

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(TelaCadastro.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    public void salvarUsuario(View view) {
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        if (!nome.equals("") && !email.equals("") && !senha.equals("")) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(nome);
            novoUsuario.setEmail(email);
            novoUsuario.setSenha(senha);
            if (dbUsuarioId != -1) {
                usuario = db.usuarioModel().getUsuario(dbUsuarioId);
                usuario.setUsuarioID(dbUsuarioId);
                db.usuarioModel().update(usuario);
            } else {
                db.usuarioModel().insertAll(novoUsuario);
                Toast.makeText(this, "Usuário Inserido",
                        Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}