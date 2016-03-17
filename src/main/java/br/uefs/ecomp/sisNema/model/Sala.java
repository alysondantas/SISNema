package br.uefs.ecomp.sisNema.model;

import br.uefs.ecomp.sisNema.util.Lista;

public class Sala {
	private int numeroSala=-1;
	private int qtdCadeiras=-1;
	private Lista sessoes;
	
	public Sala(){
		this.setSessoes(new Lista());
	}
	
	/**
	 * @return the numeroSala
	 */
	public int getNumeroSala() {
		return numeroSala;
	}
	/**
	 * @param numeroSala the numeroSala to set
	 */
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}
	/**
	 * @return the qtdCadeiras
	 */
	public int getQtdCadeiras() {
		return qtdCadeiras;
	}
	/**
	 * @param qtdCadeiras the qtdCadeiras to set
	 */
	public void setQtdCadeiras(int qtdCadeiras) {
		this.qtdCadeiras = qtdCadeiras;
	}

	/**
	 * @return the sessoes
	 */
	public Lista getSessoes() {
		return sessoes;
	}

	/**
	 * @param sessoes the sessoes to set
	 */
	public void setSessoes(Lista sessoes) {
		this.sessoes = sessoes;
	}
	
}
