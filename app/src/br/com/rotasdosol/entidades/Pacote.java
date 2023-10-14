package br.com.rotasdosol.entidades;

public class Pacote {
    private Integer idPacote;
    private Double valorPreco;
    private Integer idHospedagem;
    private Integer idVoo;

    public Integer getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    public Double getValorPreco() {
        return valorPreco;
    }

    public void setValorPreco(Double valorPreco) {
        this.valorPreco = valorPreco;
    }

    public Integer getIdHospedagem() {
        return idHospedagem;
    }

    public void setIdHospedagem(Integer idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public Integer getIdVoo() {
        return idVoo;
    }

    public void setIdVoo(Integer idVoo) {
        this.idVoo = idVoo;
    }
}
