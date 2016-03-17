/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Lokisley Oliveira Pedreira

Componente Curricular: MI - Algoritmos II

Concluido em: 07/03/2016

Declaro que este codigo foi elaborado por mim de forma individual e nao contem nenhum

trecho de codigo de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e paginas ou documentos eletronicos da Internet. Qualquer trecho de codigo

de outra autoria que nao a minha esta destacado com uma citacao para o autor e a fonte

do codigo, e estou ciente que estes trechos nao serao considerados para fins de avaliacao.

 ******************************************************************************************/

package br.uefs.ecomp.sisNema.controller;

import br.uefs.ecomp.sisNema.exceptions.CinemaNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CompradorNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.LimiteIngressosExcedidoException;
import br.uefs.ecomp.sisNema.exceptions.SalaNaoEncontradaException;
import br.uefs.ecomp.sisNema.exceptions.SessaoNaoEncontradaException;
import br.uefs.ecomp.sisNema.model.*;
import br.uefs.ecomp.sisNema.model.Ingresso;
import br.uefs.ecomp.sisNema.util.*;

public class CompradorController {
    
    private static CompradorController unicaInstancia;
    Lista listaIngresso = new Lista();
    AdministradorController controllerAdm = AdministradorController.getInstance();
    
    private CompradorController () {
        AdministradorController.zerarSingleton();
    }
    
    /**
     * controla o instanciamento de objetos do tipo CompradorController
     * @return unicaInstancia
     */
    public static synchronized CompradorController getInstance(){
        if(unicaInstancia==null){
                unicaInstancia = new CompradorController();
        }
        return unicaInstancia;
    }
    
    /**
     * retorna toda a lista de Ingressos cadastrados
     * @return listaIngresso
     */
    public Lista retornarlistaingresso(){
    	return listaIngresso;
    }
    
    /**
     * limpa a instancia do objeto CompradorController
     */
    public static void zerarSingleton () {
        unicaInstancia = null;
    }
    
    /**
     * insere um novo objeto Ingresso na lista
     * @param documento
     * @param idCine
     * @param numSala
     * @param inicioSessao
     * @param qtdIngresso
     * @param valorPago
     * @throws CompradorNaoEncontradoException
     * @throws CinemaNaoEncontradoException
     * @throws SalaNaoEncontradaException
     * @throws SessaoNaoEncontradaException
     * @throws LimiteIngressosExcedidoException 
     */
    public void comprarIngresso (int documento, int idCine, int numSala, int inicioSessao, int qtdIngresso, double valorPago) throws CompradorNaoEncontradoException, CinemaNaoEncontradoException, SalaNaoEncontradaException, SessaoNaoEncontradaException, LimiteIngressosExcedidoException{
        Comprador pessoa=controllerAdm.recuperarComprador(documento);
        Cinema cine=controllerAdm.recuperarCinema(idCine);
    	if (pessoa == null) {
            throw new CompradorNaoEncontradoException();
        } else if (cine == null) {
            throw new CinemaNaoEncontradoException();
        } else if (controllerAdm.recuperarSala(idCine, numSala) == null){
            throw new SalaNaoEncontradaException();
        } else if (controllerAdm.recuperarSessao(idCine, numSala, inicioSessao) == null){
            throw new SessaoNaoEncontradaException();
        } else if ((controllerAdm.recuperarSessao(idCine, numSala, inicioSessao).getQtdCadeiras() - qtdIngresso) < 0) {
            throw new LimiteIngressosExcedidoException();
        } else {
            controllerAdm.recuperarSessao(idCine, numSala, inicioSessao).setQtdCadeiras(controllerAdm.recuperarSessao(idCine, numSala, inicioSessao).getQtdCadeiras() - qtdIngresso);
            int i=0;
            while(i<qtdIngresso){
            	Ingresso ingresso = new Ingresso(controllerAdm.recuperarCinema(idCine), controllerAdm.recuperarSessao(idCine, numSala, inicioSessao), controllerAdm.recuperarSala(idCine, numSala), controllerAdm.recuperarComprador(documento));
                listaIngresso.inserirInicio(ingresso);
                i++;
            }
            pessoa.setComprou(true);
            Fila compradorfan;
        	compradorfan=controllerAdm.recuperarFansHabilitados();
        	if(pessoa instanceof CompradorFan){
        		compradorfan.inserirFinal(pessoa);
        	}
        } 
    }
    
    /**
     * retorna lista de ingressos correspondentes a um dado comprador
     * @param comprador
     * @return ingresso2
     */
    public Lista recuperarIngressos (Comprador comprador) {
        Ingresso procuraIngresso;
        Lista ingresso2 = new Lista();
        MeuIterador iterador = (MeuIterador)listaIngresso.iterador();
        while(iterador.temProximo()) {
            procuraIngresso = (Ingresso)iterador.obterProximo();
            if (procuraIngresso.getComprador().equals(comprador)) {
                ingresso2.inserirInicio(procuraIngresso);
            }
        }
        return ingresso2;
    }
}
