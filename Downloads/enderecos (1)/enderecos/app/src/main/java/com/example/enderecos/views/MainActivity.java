package com.example.enderecos.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.enderecos.R;
import com.example.enderecos.dao.UsuarioDao;
import com.example.enderecos.database.LocalDatabase;
import com.example.enderecos.entities.Usuario;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.enderecos.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LocalDatabase db;
    private EditText edtEmail, edtSenha;
    private int dbUsuarioId;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=LocalDatabase.getDatabase(getApplicationContext());
        edtEmail=findViewById(R.id.edtEmail);
        edtSenha=findViewById(R.id.edtSenha);
        dbUsuarioId=getIntent().getIntExtra("usuario_id",-1);

        listAllUsers();

        binding.btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                if (isValidEmail(email)) {
                    if (isValidLogin(email, senha)) {
                        Intent it = new Intent(MainActivity.this, CidadeEnderecoView.class);
                        startActivity(it);
                    } else {
                        Toast.makeText(MainActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Email inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent it=new Intent(MainActivity.this, TelaCadastro.class);
                startActivity(it);
            }
        });

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private boolean isValidLogin(String email, String senha) {
        UsuarioDao usuarioDao = db.usuarioModel();
        Usuario usuario = usuarioDao.getUsuarioByEmailAndPassword(email, senha);
        if (usuario != null) {
            Log.d("Login", "Usuário encontrado: " + usuario.getEmail());
            return true;
        } else {
            Log.d("Login", "Nenhum usuário encontrado com email: " + email);
            return false;
        }
    }

    private void listAllUsers() {
        UsuarioDao usuarioDao = db.usuarioModel();
        List<Usuario> usuarios = usuarioDao.getAll();
        for (Usuario usuario : usuarios) {
            Log.d("DB", "Usuario: " + usuario.getEmail() + ", Senha: " + usuario.getSenha());
        }
    }

}