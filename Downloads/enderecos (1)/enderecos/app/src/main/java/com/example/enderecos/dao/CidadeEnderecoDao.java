package com.example.enderecos.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.enderecos.entities.CidadeEndereco;

import java.util.List;

@Dao
public interface CidadeEnderecoDao {
    @Query("SELECT Endereco.enderecoID AS enderecoID, Endereco.descricao " +
            "AS descricaoEnd, Cidade.cidade AS nomeCidade " +
            "FROM Endereco INNER JOIN Cidade ON Endereco.cidadeID = Cidade.cidadeID")
    List<CidadeEndereco> getAllCidEnd();

   /* @Query("SELECT * FROM Celular WHERE celularID = :id LIMIT 1")
    CelularMarca getCelId(int id);*/
}
