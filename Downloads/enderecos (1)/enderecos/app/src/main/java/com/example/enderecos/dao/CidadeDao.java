package com.example.enderecos.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.enderecos.entities.Cidade;

import java.util.List;

@Dao
public interface CidadeDao {

    @Query("SELECT * FROM Cidade WHERE cidadeID = :id LIMIT 1")
    Cidade getCidade(int id);

    @Query("SELECT * FROM Cidade")
    List<Cidade> getAll();

    @Insert
    void insertAll(Cidade... cidade);

    @Update
    void update(Cidade cidades);

    @Delete
    void delete(Cidade cidades);


}