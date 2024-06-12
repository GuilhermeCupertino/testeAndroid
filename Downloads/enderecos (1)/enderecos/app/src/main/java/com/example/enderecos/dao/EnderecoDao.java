package com.example.enderecos.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.enderecos.entities.Cidade;
import com.example.enderecos.entities.Endereco;
import com.example.enderecos.entities.Usuario;

import java.util.List;

@Dao
public interface EnderecoDao {

    @Query("SELECT * FROM Endereco WHERE enderecoID = :id LIMIT 1")
    Endereco getEndereco(int id);

    @Insert
    void insertAll(Endereco endereco);

    @Update
    void update(Endereco endereco);

    @Delete
    void delete(Endereco endereco);

    @Query("SELECT * FROM Endereco WHERE cidadeID = :idCidade LIMIT 1")
    Endereco getEndByCid(int idCidade);

    @Query("SELECT * FROM Endereco")
    List<Endereco> getAllEnd();
}
