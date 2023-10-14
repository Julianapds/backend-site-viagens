package br.com.rotasdosol.entidades;

import java.util.Date;

public class Hospedagem {
    private Integer idHospedagem;
    private String nomeHotel;
    private String tipoQuarto;
    private Date dataCheckin;
    private Date dataCheckout;
    private Double valorPernoite;
    private Integer idDestino;

    public Integer getIdHospedagem() {
        return idHospedagem;
    }

    public void setIdHospedagem(Integer idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public String getNomeHotel() {
        return nomeHotel;
    }

    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Date getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(Date dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public Date getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(Date dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Double getValorPernoite() {
        return valorPernoite;
    }

    public void setValorPernoite(Double valorPernoite) {
        this.valorPernoite = valorPernoite;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }
}
