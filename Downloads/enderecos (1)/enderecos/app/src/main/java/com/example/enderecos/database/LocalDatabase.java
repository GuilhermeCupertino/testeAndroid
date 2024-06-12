
package com.example.enderecos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.enderecos.dao.CidadeDao;
import com.example.enderecos.dao.CidadeEnderecoDao;
import com.example.enderecos.dao.EnderecoDao;
import com.example.enderecos.dao.UsuarioDao;
import com.example.enderecos.entities.Cidade;
import com.example.enderecos.entities.Endereco;
import com.example.enderecos.entities.Usuario;


@Database(entities = {Cidade.class, Endereco.class, Usuario.class}, version = 3)
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "ControleEndereco").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract EnderecoDao enderecoModel();
    public abstract CidadeDao cidadeModel();
    public abstract CidadeEnderecoDao cidadeEnderecoModel();
    public abstract UsuarioDao usuarioModel();
}
