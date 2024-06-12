package com.example.enderecos.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Cidade.class,
        parentColumns = "cidadeID", childColumns = "cidadeID",
        onDelete = ForeignKey.CASCADE))
public class Endereco {
    @PrimaryKey(autoGenerate = true)
    private int enderecoID;
    private String descricao;
    private double latitude;
    private double longitude;
    private int cidadeID;

    public Endereco(){
    }
    public Endereco(String descricao, int cidadeID, double latitude, double longitude) {
        this.descricao = descricao;
        this.cidadeID = cidadeID;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getEnderecoID() { return enderecoID; }

    public void setEnderecoID(int enderecoID) {
        this.enderecoID = enderecoID;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCidadeID() {
        return cidadeID;
    }

    public void setCidadeID(int cidadeID) {
        this.cidadeID = cidadeID;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "enderecoID=" + enderecoID +
                ", descricao='" + descricao + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cidadeID=" + cidadeID +
                '}';
    }
}
