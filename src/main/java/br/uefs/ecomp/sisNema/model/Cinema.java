package br.uefs.ecomp.sisNema.model;

import br.uefs.ecomp.sisNema.model.Endereco;
import br.uefs.ecomp.sisNema.util.Lista;


/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas

Componente Curricular: MI - Algoritmos II

Concluido em: 

Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum

trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo

de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte

do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.

******************************************************************************************/

/**
 * 
 * @author Alyson
 *Classe para os doadores
 */

public class Cinema {
	
	private static Integer serialId=0;//SerialID � quem vai definir o id de cada doador quando for criado
	private int id;//id do cinema
	private String nome;
	private int QtdSalas=-1;
	private Lista sala;
	private Endereco endereco;//Endere�o do tipo Endere�o para n�o criar tanta String dentro de doador
	private boolean ingressoVendido;
	
	public Cinema(){
		serialId=serialId+1;//Incrementa o SerialID para que n�o se repita
		this.id=serialId;//id da doacao recebe o serialID da cria��o
		this.sala=new Lista();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return o id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome o nome para modificar
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return a qtdSalas
	 */
	public int getQtdSalas() {
		return QtdSalas;
	}

	/**
	 * @param qtdSalas para modificar
	 */
	public void setQtdSalas(int qtdSalas) {
		QtdSalas = qtdSalas;
	}

	/**
	 * @return o endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the sala
	 */
	public Lista getSalas() {
		return sala;
	}

	/**
	 * @param sala the sala to set
	 */
	public void setSala(Lista sala) {
		this.sala = sala;
	}
	/**
	 * @return the ingressoVendido
	 */
	public boolean isIngressoVendido() {
		return ingressoVendido;
	}
	/**
	 * @param ingressoVendido the ingressoVendido to set
	 */
	public void setIngressoVendido(boolean ingressoVendido) {
		this.ingressoVendido = ingressoVendido;
	}

	
}
