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

import br.uefs.ecomp.sisNema.util.ILista;//importo a interface da lista
import br.uefs.ecomp.sisNema.util.Iterador;//importo a interface do iterador

/**
 * 
 * @author Alyson
 *Classe generica para todas as listas
 */
public class Lista implements ILista {//minha lista é implementada pela interface
	private Celula primeira;//crio a primeira celula


	@Override
	/**
	 * Metodo para verificar se a lista é vazia
	 */
	public boolean estaVazia() {
		return primeira==null;//retorna true se a primeira celula for nula
	}

	@Override
	/**
	 * Metodo para obter o tamanho da lista(contando com 0)(como um vetor)
	 */
	public int obterTamanho() {
		int numeroCelulas=0;//variavel para guardar o tamanho da lista
		Celula auxiliar=primeira;//Auxiliar recebe a primeira
		if(auxiliar!=null){
			numeroCelulas++;
		}else if(auxiliar==null){
			return 0;
		}
		while(auxiliar.getProximo()!=null){//enquanto a proxima de auxiliar for diferente de nula
			numeroCelulas=numeroCelulas+1;;//numero de celulas rebe ela mais um
			auxiliar=auxiliar.getProximo();//auxiliar passa pra proxima
		}
		return numeroCelulas;//quando sair do laço retorna o tamanho da lista
	}

	@Override
	/**
	 * Metodo para inserir no inicio da lista
	 * @param Objetct
	 */
	public void inserirInicio(Object o) {
		Celula celula=new Celula(o);//celula é instanciada com o objeto que foi passsado
		if(primeira==null){//se não tiver nenhum objeto na lista
			primeira=celula;//a primeira se torna a celula que foi instanciada
		}
		else{//se não
			Celula auxiliar=primeira;//cria uma celula auxiliar que guarda a primeira
			primeira=celula;//orimeira se torna a celula nova
			primeira.setProximo(auxiliar);//e o proximo da primeira pega a auxiliar
		}
	}

	@Override
	/**
	 * inserir no final(não foi utilizado)
	 * @deprecated inserirInicio
	 * @param Object
	 */
	public void inserirFinal(Object o) {
		Celula novaCelula = new Celula(0);//celula nova é instancia com o objeto que foi passado
		Celula ultimaCelula=primeira;//uma variavel é criada para encontrar o ultimo elemento e a inicio recebe a primeira
		if(primeira==null){//se ja existir lista
			primeira=novaCelula;
		}else{
			while(ultimaCelula.getProximo()!=null){//enquanto o proximo da celula não for nulo
				ultimaCelula=ultimaCelula.getProximo();//ela passa pra proxima
			}
			ultimaCelula.setProximo(novaCelula);//quando sair do laço o proximo do ultimo se torna a nova celula
		}
	}

	@Override
	/**
	 * Metodo para remover no inicio
	 */
	public Object removerInicio() {
		Celula auxiliar=primeira;//auxiliar recebe a primeira
		if(primeira!=null){//se existir a primeira
			primeira=primeira.getProximo();//primeira passa pra seu proximo
		}
		return auxiliar.getObjeto();//retorna o objeto removido
		//não fiz uso desse retorno, eu podia colocar null mas por verificação se for diferente de nulo foi removido
	}

	@Override
	/**
	 * Metodo para remover no final
	 * @deprecated remover
	 */
	public Object removerFinal() {
		//não fiz uso desse metodo ja que era pra uma lista com duas referencias uma pra inicio e uma pro final
		//o metodo de remover no meio serve para remover no final se não existir as duas referencias
		if(primeira!=null){//se existir lista
			Celula ultimaCelula=primeira;//tulma recebe a primeira
			Celula penultimaCelula=primeira;//penultima recebe a primeira
			while(ultimaCelula.getProximo()!=null){//enquanto o proximo de ultimo for diferente de nulo
				penultimaCelula=ultimaCelula;//penultima recebe a ultima
				ultimaCelula=ultimaCelula.getProximo();//ultima passa pra proxima
			}
			penultimaCelula.setProximo(null);//penultima aponta pra nulo
			ultimaCelula.setProximo(null);//ultima é desnecessaria
		}
		return null;
	}

	@Override
	/**
	 * remove objeto atravez do index(indice onde ele esta)
	 * @param index
	 */
	public Object remover(int index) {
		Celula auxiliar=primeira;//auxiliar recebe a primeira
		Celula antes=primeira;//anterio recebe a primeira
		Object remove;//objeto a ser removido
		if(index==0){//se index for igual a 0 ele é o primeiro elemnto
			remove=removerInicio();//chamo remoção no inicio
		}else{//se não
			for(int contador=index;contador>0;contador--){//eu instancio um contador do tamanho do index e vou decrementar ele enquando for maior que zero
				antes=auxiliar;//antes recebe auxiliar
				auxiliar=auxiliar.getProximo();//auxiliar passa pro proximo
			}
			//quando sair do "for" auxiliar sera o elemnto que desejo remover e antes sera um elemento atras de auxiliar
			remove=auxiliar.getObjeto();//remove recebe o objeto a ser removido
			antes.setProximo(auxiliar.getProximo());//antes aponta pro proximo de auxiliar
		}
		return remove;//retorno o objeto a ser removido se diferente de null ele foi removido
	}

	@Override
	/**
	 * Metodo para encontrar objeto atravez do index(indice)
	 * @param index
	 */
	public Object recuperar(int index) {
		Celula auxiliar=primeira;//auxiliar recebe a primeira
		int contador;//crio um contador
		for(contador=index;contador>0;contador--){//dou ao contador o index e decremento ele enquanto for maior que zero
			auxiliar=auxiliar.getProximo();//auxiliar passa pro proximo
		}
		//quando sair do "for" o auxiliar é o elemento desejado
		return auxiliar.getObjeto();//retorno objeto encontrado
	}

	@Override
	/**
	 * Metodo pra retornar um iterador com o primeiro elemnto da lista
	 */
	public Iterador iterador() {
		MeuIterador iterador=new MeuIterador(primeira);//instancio o iterador com a primeira celula por paramentro
		return iterador;//retorno o iterador
	}


	/**
	 * Metodo para trocar dois objetos de posição como um BubbleSort
	 * @param index1
	 * @param index2
	 */
	public void bubbleSort(int index1, int index2) {
		Celula celula1 = primeira;//celula1 recebe primeira celula
		Celula celula2 = primeira;//celula2 recebe primeira celula
		int contador;//contador é instanciado
		Object objeto;//objeto a ser trocado é instanciado
		//nesse caso por recorrencia de bugs eu não decremento eu incremento
		for(contador =0; contador <index1; contador++){//contador recebe 0 e entando ele for menor que o primeiro index ele é incrementado
			celula1 = celula1.getProximo();//celula1 passa ao proximo dela
		}//quando sair do laço ela esta no elemnto desejado
		for(contador =0; contador <index2; contador++){//contador recebe 0 e entando ele for menor que o primeiro index ele é incrementado
			celula2 = celula2.getProximo();//celula2 passa ao proximo dela
		}//quando sair do laço ela esta no elemento desejado
		objeto = celula1.getObjeto();//objeto recebe o objeto da celula1
		celula1.setObjeto(celula2.getObjeto());//celula1 recebe o objeto da celula2
		celula2.setObjeto(objeto);//celula2 recebe o objeto da celula1 anterio
		//metodo foi baseado no BubbleSort
	}
}