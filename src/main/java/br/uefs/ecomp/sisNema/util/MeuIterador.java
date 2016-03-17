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

import br.uefs.ecomp.sisNema.util.Celula;//importo a classe celula generica
import br.uefs.ecomp.sisNema.util.Iterador;//importo a intefacer interador
/**
 * 
 * @author Alyson
 * Meu iterador para trabalhar dentro das listas
 */
public class MeuIterador implements Iterador {//Meu iterador implementa a interface Iterador
	private Celula auxiliar;//crio uma celula auxiliar para trabalhar dentro do iterador
	private Celula primeira;//crio um celula para sempre ser a primeira celula da lista encadeada
	/**
	 * Construtor para receber primeira celula
	 * @param primeira
	 */
	public MeuIterador(Object primeira){//construtor do meu iterador recebe a primeira celula da lista como objeto
		auxiliar=(Celula)primeira;//auxiliar recebe a primeira celula
		this.primeira=(Celula)primeira;//primeira recebe a primeira celula
	}

	@Override
	/**
	 * Metodo para verificar se existe proxima celula ou não
	 */
	public boolean temProximo() {
		return auxiliar!=null;//se auxiliar for diferente de null é porque existe outra celula
	}

	@Override
	/**
	 * Metodo para retornar celula atuala e passar pra proxima
	 */
	public Object obterProximo() {
		Object objetoRecebido=auxiliar.getObjeto();//objeto recebido pega o objeto da celula atual
		auxiliar=auxiliar.getProximo();//celula atual passa pra proxima
		return objetoRecebido;//retorna o objeto que foi recebido
	}
	/**
	 * metodo para fazer com que o iterador retorne para o inicio da lista
	 */
	 public void reiniciar() {
		 auxiliar = primeira;//auxiliar recebe a primeira
	 }
}