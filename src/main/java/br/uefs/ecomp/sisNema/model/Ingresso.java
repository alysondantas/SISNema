
package br.uefs.ecomp.sisNema.model;

public class Ingresso {
    private Cinema cinema;
    private Sessao sessao;
    private Sala sala;
    private Comprador comprador;

    public Ingresso (Cinema cinema, Sessao sessao, Sala sala, Comprador comprador) {
        this.cinema = cinema;
        this.sala = sala;
        this.sessao = sessao;
        this.comprador = comprador;
    }
    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    
}
