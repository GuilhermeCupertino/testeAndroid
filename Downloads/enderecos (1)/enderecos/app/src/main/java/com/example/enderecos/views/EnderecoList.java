package com.example.enderecos.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.enderecos.R;
import com.example.enderecos.dao.EnderecoDao;
import com.example.enderecos.dao.UsuarioDao;
import com.example.enderecos.database.LocalDatabase;
import com.example.enderecos.databinding.ActivityEnderecoListBinding;
import com.example.enderecos.entities.CidadeEndereco;
import com.example.enderecos.entities.Endereco;
import com.example.enderecos.entities.Usuario;
import java.util.List;

public class EnderecoList extends AppCompatActivity {

    private ActivityEnderecoListBinding binding;
    private LocalDatabase db;
    private ListView listViewEndereco;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnderecoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewEndereco = binding.listEndereco;
        context = this;
        listAllEnd();
        preencheEnderecos();

        binding.btnHomeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAddEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnderecoList.this, EnderecoView.class));
            }
        });
    }

    private void preencheEnderecos() {
        List<CidadeEndereco> cidEnd = db.cidadeEnderecoModel().getAllCidEnd();
        ArrayAdapter<CidadeEndereco> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cidEnd);
        listViewEndereco.setAdapter(adapter);

        listViewEndereco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                CidadeEndereco selectedEndereco = (CidadeEndereco) adapter.getItemAtPosition(position);
                int enderecoID = selectedEndereco.getEnderecoID();
                String descricao = selectedEndereco.getDescricaoEnd();
                saveEnderecoToSharedPreferences(enderecoID, descricao);

                Intent intent = new Intent(context, EnderecoView.class);
                intent.putExtra("idEnd", enderecoID);
                context.startActivity(intent);

                Log.d("TAG", "Saved enderecoId: " + enderecoID);
                Log.d("TAG", "Saved descricao: " + descricao);
            }
        });
    }

    private void saveEnderecoToSharedPreferences(int enderecoID, String descricao) {
        SharedPreferences sharedPreferences = getSharedPreferences("locPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idEnd", enderecoID);
        editor.putString("endMarcado", descricao);
        editor.apply();
    }

    private void listAllEnd() {
        EnderecoDao enderecoDao = db.enderecoModel();
        List<Endereco> enderecos = enderecoDao.getAllEnd();
        for (Endereco endereco : enderecos) {
            Log.d("DB", "Endereco: " + endereco.getDescricao() + ", CidadeID: " + endereco.getCidadeID());
        }
    }


}