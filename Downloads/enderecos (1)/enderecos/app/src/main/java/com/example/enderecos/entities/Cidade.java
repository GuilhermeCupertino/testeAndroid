package com.example.enderecos.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cidade {
    @PrimaryKey(autoGenerate = true)
    private int cidadeID;
    private String cidade;
    private String estado;

    public Cidade() {}

    public Cidade(String cidade, String estado) {
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getCidadeID() {
        return cidadeID;
    }

    public void setCidadeID(int cidadeID) {
        this.cidadeID = cidadeID;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return cidade + ": " + estado ;
    }
}