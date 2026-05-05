package br.com.evelyn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_produtora")
public class Produtora {

    @Id
    private Long id;

    @Column(name = "nome_produtora")
    private String nomeProdutora;

    @Column(name = "cidade_sede")
    private String cidadeSede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProdutora() {
        return nomeProdutora;
    }

    public void setNomeProdutora(String nomeProdutora) {
        this.nomeProdutora = nomeProdutora;
    }

    public String getCidadeSede() {
        return cidadeSede;
    }

    public void setCidadeSede(String cidadeSede) {
        this.cidadeSede = cidadeSede;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "\nPRODUTORA: " + nomeProdutora
                + "\nCIDADE-SEDE: " + cidadeSede;
    }
}
