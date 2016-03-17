package br.uefs.ecomp.sisNema.model;

import br.uefs.ecomp.sisNema.model.Endereco;
import br.uefs.ecomp.sisNema.util.Lista;


/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas

Componente Curricular: MI - Algoritmos II

Concluido em: 

Declaro que este código foi elaborado por mim de forma individual e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

******************************************************************************************/

/**
 * 
 * @author Alyson
 *Classe para os doadores
 */

public class Cinema {
	
	private static Integer serialId=0;//SerialID é quem vai definir o id de cada doador quando for criado
	private int id;//id do cinema
	private String nome;
	private int QtdSalas=-1;
	private Lista sala;
	private Endereco endereco;//Endereço do tipo Endereço para não criar tanta String dentro de doador
	private boolean ingressoVendido;
	
	public Cinema(){
		serialId=serialId+1;//Incrementa o SerialID para que não se repita
		this.id=serialId;//id da doacao recebe o serialID da criação
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
