package br.uefs.ecomp.sisNema.util;

/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas

Componente Curricular: MI - Algoritmos II

Concluido em: 12/12/2015

Declaro que este código foi elaborado por mim de forma individual e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

******************************************************************************************/
/**
 * 
 * @author Alyson
 * Classe para a celula generica
 */
public class Celula {//Classe para um celula generica
	private Celula proximo;//Referencia para a proxima celula
	private Object objeto;//Objeto generico que vai estar dentro da celula
	/**
	 * Metodo construtor da classe obrigando que venha um objeto quando criar celula
	 * @param o
	 */
	public Celula(Object o){
		this.objeto=o;//objeto recebe object que foi passado
	}
	//Construtores do encapsulamento
	public Celula getProximo() {// para que em outro pacote possa acessar o proximo
		return proximo;//retorna o proximo
	}
	//
	public void setProximo(Celula proximo) {//Para que o ser alterado o proximo por outro pacote
		this.proximo = proximo;//proximo da classe recebe o que foi passado
	}
	//Os dois a baixo tem o mesmo papel que o get e set acima.
	public Object getObjeto() {
		return objeto;
	}
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	//
}
