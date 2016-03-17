
package br.uefs.ecomp.sisNema.util;

public class Fila implements IFila{

    private Celula primeiro;
    private Celula ultimo;
    private int tamanho = 0 ;
    
    public Fila(){
		primeiro = null;
		ultimo = null;
	}
    
    @Override
    public boolean estaVazia() {
    	if(primeiro == null){
    		return true;
    	}
        return false;
    }

    @Override
    public int obterTamanho() {
        return tamanho;
    }

    @Override
    public void inserirFinal(Object o) {
    	Celula celulaNova=new Celula(o);
        if (primeiro==null) {
            primeiro=celulaNova;
            ultimo=primeiro;
        }
        else {
            ultimo.setProximo(celulaNova);
            ultimo=celulaNova;
        }
        tamanho ++;
    }

    @Override
    public Object removerInicio() {
        Celula auxiliar = null;
        if(primeiro!=null) {
            auxiliar = primeiro;
            if(primeiro == ultimo) {
            	auxiliar=primeiro;
                primeiro = null;
                ultimo = null;
            } else {
            	auxiliar=primeiro;
                primeiro = primeiro.getProximo();
            }
            tamanho--;
        }
        return auxiliar.getObjeto();
    }

    @Override
    public Object recuperarInicio() {
        return primeiro.getObjeto();
    }

    @Override
    public Iterador iterador() {
        MeuIterador iterador = new MeuIterador(primeiro);
        return iterador;
    }
    
}
