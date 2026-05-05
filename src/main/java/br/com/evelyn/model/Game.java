package br.com.evelyn.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tbl_games")
public class Game {
    @Id
    private Long id;
    private String titulo;
    @Column(name = "dt_lancamento")
    private LocalDate dataLancamento;
    private Double valor;
    private String produtora;
    private Boolean finalizado;
    private String categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ""
                + "\nTITULO: " + this.titulo + ""
                + "\nPRODUTORA: " + this.produtora + ""
                + "\nCATEGORIA: " + this.categoria + ""
                + "\nLANÇAMENTO: " + this.dataLancamento + ""
                + "\nFINALIZADO: " + this.finalizado + ""
                + "\nVALOR: " + this.valor + "\n------------------------";
    }
}
