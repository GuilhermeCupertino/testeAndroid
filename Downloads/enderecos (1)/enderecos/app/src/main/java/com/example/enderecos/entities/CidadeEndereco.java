package com.example.enderecos.entities;

public class CidadeEndereco {

    public int enderecoID;
    public String descricaoEnd;
    public String nomeCidade;

    public void setEnderecoID(int enderecoID) {
        this.enderecoID = enderecoID;
    }

    public String getDescricaoEnd() {
        return descricaoEnd;
    }

    public void setDescricaoEnd(String descricaoEnd) {
        this.descricaoEnd = descricaoEnd;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public int getEnderecoID() { return enderecoID;    }
    @Override
    public String toString() {
        return this. descricaoEnd
                + " Cidade: "+nomeCidade ;
    }

}
