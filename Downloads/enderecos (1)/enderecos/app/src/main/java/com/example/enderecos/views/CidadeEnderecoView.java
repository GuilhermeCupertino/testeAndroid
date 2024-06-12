package com.example.enderecos.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.enderecos.R;
import com.example.enderecos.databinding.ActivityCidadeEnderecoViewBinding;
import com.example.enderecos.databinding.ActivityMainBinding;

public class CidadeEnderecoView extends AppCompatActivity {

    private ActivityCidadeEnderecoViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCidadeEnderecoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CidadeEnderecoView.this,UsuarioList.class);
                startActivity(it);
            }
        });

        binding.btnCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CidadeEnderecoView.this,CidadeList.class);
                startActivity(it);
            }
        });

        binding.btnEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CidadeEnderecoView.this,EnderecoList.class);
                startActivity(it);
            }
        });
    }
}