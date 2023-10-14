package br.com.rotasdosol.entidades;

import java.util.Date;

public class Voo {
    private Integer idVoo;
    private String companhiaAerea;
    private Date dataPartida;
    private Date dataChegada;
    private Double valorPreco;
    private Integer idDestino;

    public void setIdVoo(Integer idVoo) {
        this.idVoo = idVoo;
    }
    public void setCompanhiaAerea(String companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public void setValorPreco(Double valorPreco) {
        this.valorPreco = valorPreco;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public Integer getIdVoo() {
        return idVoo;
    }

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public Double getValorPreco() {
        return valorPreco;
    }

    public Integer getIdDestino() {
        return idDestino;
    }
}
