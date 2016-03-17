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

import br.uefs.ecomp.sisNema.util.*;
import br.uefs.ecomp.sisNema.exceptions.*;
import br.uefs.ecomp.sisNema.model.*;

public class AdministradorController {

	private AdministradorController(){

	}

	private static AdministradorController unicaInstancia;
	Lista cinema = new Lista();//cria a lista de cinemas
	Lista comprador = new Lista();//cria a lista de compradores
	Fila compradorfan = new Fila();//fila de compradores fans

        /**
         * controla o instanciamento de objetos AdministradorController
         * @return unicaInstancia
         */
	public static synchronized AdministradorController getInstance(){
		if(unicaInstancia==null){
			unicaInstancia = new AdministradorController();
		}
		return unicaInstancia;
	}

        /**
         * reseta o objeto AdministradorController ja instanciado
         */
	public static void zerarSingleton (){
		unicaInstancia = null;
	}

        /**
         * retorna toda fila de compradores fans
         * @return compradorfan
         */
	public Fila retornarcompradorfan(){
		return compradorfan;
	}

        /**
         * recebe um objeto de Cinema e insere em uma lista
         * @param cine
         * @return objeto cine ja inserido na lista
         * @throws CinemaNuloException
         * @throws CampoObrigatorioInexistenteException 
         */
	public Cinema cadastrarCinema(Cinema cine) throws CinemaNuloException, CampoObrigatorioInexistenteException {
		if(cine==null){
			throw new CinemaNuloException();
		}else if(cine.getQtdSalas()<1 || cine.getNome().trim().isEmpty() || cine.getEndereco()==null){
			throw new CampoObrigatorioInexistenteException();
		}else{
			Endereco end;
			end=cine.getEndereco();
			if(end.getBairro().trim().isEmpty() || end.getCep().trim().isEmpty() || end.getCidade().trim().isEmpty() || end.getComplemento().trim().isEmpty() || end.getEstado().trim().isEmpty() || end.getNumero()<1 || end.getRua().trim().isEmpty()){
				throw new CampoObrigatorioInexistenteException();
			}
			cinema.inserirInicio(cine);//insere no inicio da lista
			return cine;//retorna o produto cadastrado
		}
	}

        /**
         * verifica se um cinema em especifico existe na lista
         * @param cine
         * @return true ou false
         */
	public boolean cinemaExiste(Cinema cine){
		if(cine==null){
			return false;
		}
		MeuIterador iterador=(MeuIterador)cinema.iterador();
		Cinema auxiliarcine=null;
		while(iterador.temProximo()){
			auxiliarcine=(Cinema)iterador.obterProximo();
			if(auxiliarcine.equals(cine)){
				return true;
			}
		}
		return false;
	}

        /**
         * insere um objeto Sala em uma lista dentro de um determinado objeto Cinema
         * @param cine
         * @param sala
         * @throws CinemaNaoEncontradoException
         * @throws CinemaNuloException
         * @throws CampoObrigatorioInexistenteException
         * @throws SalaNulaException
         * @throws LimiteSalasExcedidoException 
         */
	public void cadastrarSala(Cinema cine, Sala sala)throws CinemaNaoEncontradoException, CinemaNuloException, CampoObrigatorioInexistenteException, SalaNulaException, LimiteSalasExcedidoException {
		boolean cinencontrado=false;//variavel para verificar se cine foi encontrado
		int contadorSalas=0;
		cinencontrado=cinemaExiste(cine);
		if(cinencontrado==true){
			Lista salas;
			salas=cine.getSalas();
			contadorSalas=salas.obterTamanho();
		}
		if(cine == null){
			throw new CinemaNuloException();
		}else if(cine.getQtdSalas()<contadorSalas || cine.getQtdSalas()==contadorSalas){
			throw new LimiteSalasExcedidoException();
		}else if(cinencontrado==false){
			throw new CinemaNaoEncontradoException();
		}else if(sala==null){
			throw new SalaNulaException();
		}else if(sala.getNumeroSala()<1 || sala.getQtdCadeiras()<1){
			throw new CampoObrigatorioInexistenteException(); 
		}else{
			Lista salalista;
			salalista=cine.getSalas();
			salalista.inserirInicio(sala);
		}
	}

        /**
         * verifica se uma determinada sala existe na lista
         * @param cine
         * @param sala
         * @return true ou false
         */
	public boolean salaExiste(Cinema cine, Sala sala){
		if(cine==null || sala==null){
			return false;
		}
		Lista salas=cine.getSalas();
		MeuIterador iterador=(MeuIterador)salas.iterador();
		Sala auxiliarsalas=null;
		while(iterador.temProximo()){
			auxiliarsalas=(Sala)iterador.obterProximo();
			if(auxiliarsalas.equals(sala)){
				return true;
			}
		}
		return false;
	}

        /**
         * verifica se o horario de uma sessao conflita com alguma ja cadastrada
         * @param sala
         * @param sessao
         * @return true ou false
         */
	public boolean sessaoConflita(Sala sala, Sessao sessao){
		if(sessao==null || sala==null){
			return false;
		}
		Lista sessoes=sala.getSessoes();
		Sessao auxiliar;
		int h1;
		int h2;

		MeuIterador iterador=(MeuIterador)sessoes.iterador();
		while(iterador.temProximo()){
			auxiliar=(Sessao) iterador.obterProximo();
			h1=auxiliar.getHoraInicio();
			h2=sessao.getHoraInicio();
			if(h1>21 && h2<3){
				h2=h2+24;
			}else if(h2>21 && h1<3){
				h1=h1+24;
			}
			if(h1-h2<3 && h1-h2>0){
				return true;
			}else if(h2-h1<3 && h2-h1>0){
				return true;
			}
		}
		return false;
	}

        /**
         * cadastra uma sessao numa lista encadeada, em um objeto sala
         * @param cine
         * @param sala
         * @param sessao
         * @throws SessaoNulaException
         * @throws CampoObrigatorioInexistenteException
         * @throws IntervaloMinimoInsuficienteException
         * @throws CinemaNaoEncontradoException
         * @throws SalaNaoEncontradaException
         * @throws CinemaNuloException
         * @throws SalaNulaException 
         */
	public void cadastrarSessao(Cinema cine,Sala sala,Sessao sessao) throws SessaoNulaException, CampoObrigatorioInexistenteException, IntervaloMinimoInsuficienteException, CinemaNaoEncontradoException, SalaNaoEncontradaException, CinemaNuloException, SalaNulaException{
		boolean cinencontrado=false;//variavel para verificar se cine foi encontrado
		cinencontrado=cinemaExiste(cine);
		boolean salaencontrada=salaExiste(cine, sala);
		boolean conflitosessao=sessaoConflita(sala, sessao);
		if(sessao==null){
			throw new SessaoNulaException();
		}else if(sessao.getHoraFim()==-1 || sessao.getHoraInicio()==-1){
			throw new CampoObrigatorioInexistenteException();
		}else if(sala==null){
			throw new SalaNulaException();
		}else if(cine==null){
			throw new CinemaNuloException();
		}else if(cinencontrado==false){
			throw new CinemaNaoEncontradoException();
		}else if(salaencontrada==false){
			throw new SalaNaoEncontradaException();
		}else if(conflitosessao==true){
			throw new IntervaloMinimoInsuficienteException();
		}else{
			Lista sessaolista;
			sessaolista=sala.getSessoes();
			sessaolista.inserirInicio(sessao);
			sessao.setQtdCadeiras(sala.getQtdCadeiras());
		}
	}

        /**
         * encontra e retorna um objeto cine que esteja cadastrado no sistema, baseado em seu id
         * @param idCinema
         * @return cine
         * @throws CinemaNaoEncontradoException 
         */
	public Cinema recuperarCinema (int idCinema) throws CinemaNaoEncontradoException {
		Cinema cine;
		MeuIterador iterador=(MeuIterador)cinema.iterador();
		while (iterador.temProximo()) {
			cine = (Cinema)iterador.obterProximo();
			if (cine.getId() == idCinema) {
				return cine;
			}
		}
		throw new CinemaNaoEncontradoException();
	}

        /**
         * cadastra e insere um novo comprador numa lista
         * @param pessoa
         * @throws CompradorNuloException
         * @throws CampoObrigatorioInexistenteException 
         */
	public void cadastrarComprador (Comprador pessoa) throws CompradorNuloException, CampoObrigatorioInexistenteException {
		if (pessoa == null) {
			throw new CompradorNuloException();
		}else if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty() || pessoa.getEmail() == null || pessoa.getEmail().trim().isEmpty() || pessoa.getTelefone() == null || pessoa.getTelefone().trim().isEmpty() || pessoa.getDocumento() == 0 ||  pessoa.getEndereco() == null) {
			throw new CampoObrigatorioInexistenteException();
		} else {
			Endereco end;
			end=pessoa.getEndereco();
			if(end.getBairro().trim().isEmpty() || end.getCep().trim().isEmpty() || end.getCidade().trim().isEmpty() || end.getComplemento().trim().isEmpty() || end.getEstado().trim().isEmpty() || end.getNumero()<1 || end.getRua().trim().isEmpty()){
				throw new CampoObrigatorioInexistenteException();
			}
			comprador.inserirInicio(pessoa);
		}
	}

        /**
         * cadastra e insere um novo comprador fan na lista
         * @param pessoa2
         * @throws CompradorNuloException
         * @throws CampoObrigatorioInexistenteException 
         */
	public void cadastrarComprador (CompradorFan pessoa2) throws CompradorNuloException, CampoObrigatorioInexistenteException {
		if(pessoa2 == null){
			throw new CompradorNuloException();
		}else if( pessoa2.getNome() == null  || pessoa2.getNome().trim().isEmpty() || pessoa2.getEmail() == null || pessoa2.getEmail().trim().isEmpty() || pessoa2.getTelefone() == null || pessoa2.getTelefone().trim().isEmpty() || pessoa2.getDocumento() == 0 || pessoa2.getEndereco() == null || pessoa2.getRegistro() == 0){
			throw new CampoObrigatorioInexistenteException();
		}
		Endereco end;
		end=pessoa2.getEndereco();
		if(end.getBairro().trim().isEmpty() || end.getCep().trim().isEmpty() || end.getCidade().trim().isEmpty() || end.getComplemento().trim().isEmpty() || end.getEstado().trim().isEmpty() || end.getNumero()<1 || end.getRua().trim().isEmpty()){
			throw new CampoObrigatorioInexistenteException();
		}
		comprador.inserirInicio(pessoa2);

	}

        /**
         * encontra e retorna um objeto comprador no sistema, baseado em seu documento
         * @param documento
         * @return pessoa
         * @throws CompradorNaoEncontradoException 
         */
	public Comprador recuperarComprador (int documento) throws CompradorNaoEncontradoException {
		Comprador pessoa=null;
		MeuIterador iterador=(MeuIterador)comprador.iterador();
		while (iterador.temProximo()) {
			pessoa = (Comprador)iterador.obterProximo();
			if (pessoa.getDocumento() == documento) {
				if(pessoa instanceof CompradorFan){
					return (CompradorFan) pessoa;
				}
				return pessoa;
			}
		}
		throw new CompradorNaoEncontradoException();
	}

        /**
         * encontra e retorna um objeto sala do sistema, baseado em seu numero, e o id do cinema ao qual pertence
         * @param idCinema
         * @param numSala
         * @return sala
         * @throws CinemaNaoEncontradoException
         * @throws SalaNaoEncontradaException 
         */
	public Sala recuperarSala(int idCinema,int numSala) throws CinemaNaoEncontradoException, SalaNaoEncontradaException {
		Cinema cine=null;
		Lista salas;
		MeuIterador iterador=(MeuIterador)cinema.iterador();
		while (iterador.temProximo()) {
			cine = (Cinema)iterador.obterProximo();
			if (cine.getId() == idCinema) {
				break;
			}
		}if(cine==null){
			throw new CinemaNaoEncontradoException();
		}else{
			salas=cine.getSalas();
			Sala sala;
			iterador=(MeuIterador)salas.iterador();
			while (iterador.temProximo()) {
				sala = (Sala)iterador.obterProximo();
				if (sala.getNumeroSala() == numSala) {
					return sala;
				}
			}
		}
		throw new SalaNaoEncontradaException();
	}

        /**
         * encontra e recupera no sistema um objeto sessao, baseado na sua hora de inicio, numero da sala, e id do cinema
         * @param idCinema
         * @param numSala
         * @param horaInicio
         * @return sessao
         * @throws CinemaNaoEncontradoException
         * @throws SalaNaoEncontradaException
         * @throws SessaoNaoEncontradaException 
         */
	public Sessao recuperarSessao(int idCinema,int numSala, int horaInicio) throws CinemaNaoEncontradoException, SalaNaoEncontradaException, SessaoNaoEncontradaException {
		Cinema cine=null;
		Sala sala=null;
		Lista lista;
		MeuIterador iterador=(MeuIterador)cinema.iterador();
		while (iterador.temProximo()) {
			cine = (Cinema)iterador.obterProximo();
			if (cine.getId() == idCinema) {
				break;
			}
		}if(cine==null){
			throw new CinemaNaoEncontradoException();
		}else{
			lista=cine.getSalas();
			iterador=(MeuIterador)lista.iterador();
			while (iterador.temProximo()) {
				sala = (Sala)iterador.obterProximo();
				if (sala.getNumeroSala() == numSala) {
					break;
				}
			}
		}if(sala==null){
			throw new SalaNaoEncontradaException();
		}else{
			Sessao sessao;
			lista=sala.getSessoes();
			iterador=(MeuIterador)lista.iterador();
			while (iterador.temProximo()) {
				sessao = (Sessao)iterador.obterProximo();
				if (sessao.getHoraInicio() == horaInicio) {
					return sessao;
				}
			}
		}
		throw new SessaoNaoEncontradaException();
	}

        /**
         * retorna toda lista de cinemas cadastrados
         * @return 
         */
	public Lista listarCinemas(){
		return cinema;
	}

        /**
         * altera informações de um objeto cinema que ja foi cadastrado
         * @param cine
         * @throws CinemaNuloException
         * @throws CampoObrigatorioInexistenteException
         * @throws CinemaNaoEncontradoException 
         */
	public void alterarCinema(Cinema cine) throws CinemaNuloException, CampoObrigatorioInexistenteException, CinemaNaoEncontradoException{
		Cinema cine2;
		if(cine==null){
			throw new CinemaNuloException();
		}else{
			cine2=recuperarCinema(cine.getId());
			if(cine2==null){
				throw new CinemaNaoEncontradoException();
			}else if(cine.getQtdSalas()<1 || cine.getNome().trim().isEmpty() || cine.getEndereco()==null){
				throw new CampoObrigatorioInexistenteException();
			}
			else{
				cine2.setEndereco(cine.getEndereco());
				cine2.setIngressoVendido(cine.isIngressoVendido());
				cine2.setNome(cine.getNome());
				cine2.setQtdSalas(cine.getQtdSalas());
				cine2.setSala(cine.getSalas());
			}
		}
	}

        /**
         * altera informações de um objeto sala que ja foi cadastrado
         * @param cine
         * @param sala
         * @throws CinemaNuloException
         * @throws CampoObrigatorioInexistenteException
         * @throws CinemaNaoEncontradoException
         * @throws SalaNulaException
         * @throws SalaNaoEncontradaException 
         */
	public void alterarSala(Cinema cine, Sala sala) throws CinemaNuloException, CampoObrigatorioInexistenteException,CinemaNaoEncontradoException,SalaNulaException,SalaNaoEncontradaException{
		Sala sala2;
		Cinema cine2;
		if(cine==null){
			throw new CinemaNuloException();
		}else{
			cine2=recuperarCinema(cine.getId());
			if(cine2==null){
				throw new CinemaNaoEncontradoException();
			}else if(sala==null){
				throw new SalaNulaException();
				
			}else if(sala.getNumeroSala()<1 || sala.getQtdCadeiras()<1){
				throw new CampoObrigatorioInexistenteException();
			}else{
				sala2=recuperarSala(cine.getId(), sala.getNumeroSala());
				if(sala2==null){
					throw new SalaNaoEncontradaException();
				}else if(sala2.getNumeroSala()<1 || sala2.getQtdCadeiras()<1){
					throw new CampoObrigatorioInexistenteException();
				} else{
					sala2.setNumeroSala(sala.getNumeroSala());
					sala2.setQtdCadeiras(sala.getQtdCadeiras());
					sala2.setSessoes(sala.getSessoes());
				}
			}
		}
	}

        /**
         * retorna a lista de salas contida no objeto cine recebido
         * @param cine
         * @return salas;
         */
	public Lista listarSalas(Cinema cine){
		Lista salas;
		salas=cine.getSalas();
		return salas;
	}

        /**
         * altera informações de um objeto sessao ja cadastrado no sistema
         * @param cine
         * @param salaTeste
         * @param horaInicioAntiga
         * @param horaFimAntiga
         * @param horaInicioNova
         * @param horaFimNova
         * @throws CinemaNuloException
         * @throws CinemaNaoEncontradoException
         * @throws SalaNulaException
         * @throws SalaNaoEncontradaException
         * @throws SessaoNaoEncontradaException
         * @throws IntervaloMinimoInsuficienteException
         * @throws SessaoNulaException 
         */
	public void alterarSessao(Cinema cine,Sala salaTeste,int horaInicioAntiga,int horaFimAntiga,int horaInicioNova,int horaFimNova) throws CinemaNuloException, CinemaNaoEncontradoException, SalaNulaException, SalaNaoEncontradaException, SessaoNaoEncontradaException, IntervaloMinimoInsuficienteException, SessaoNulaException{
		boolean cinencontrado=cinemaExiste(cine);//variavel para verificar se cine foi encontrado
		boolean salaencontrada=salaExiste(cine, salaTeste);
		if(horaFimNova==0 || horaInicioNova==0){
			throw new SessaoNulaException();
		}else if(cine==null){
			throw new CinemaNuloException();
		}else if(cinencontrado==false){
			throw new CinemaNaoEncontradoException();
		}else if(salaTeste==null){
			throw new SalaNulaException();
		}else if(salaencontrada==false){
			throw new SalaNaoEncontradaException();
		}else{
			Sessao sessao;
			sessao=recuperarSessao(cine.getId(), salaTeste.getNumeroSala(), horaInicioAntiga);
			if(sessao==null){
				throw new SessaoNaoEncontradaException();
			}else{
				sessao.setHoraInicio(horaInicioNova);
				sessao.setHoraFim(horaFimNova);
				boolean conflitosessao=sessaoConflita(salaTeste, sessao);
				if(conflitosessao==true){
					sessao.setHoraInicio(horaInicioAntiga);
					sessao.setHoraFim(horaFimAntiga);
					throw new IntervaloMinimoInsuficienteException();
				}
			}
		}
	}

        /**
         * retorna lista de sessoes cadastradas, de acordo com o cinema e sala recebidos
         * @param cine
         * @param salaTeste
         * @return sessoes
         * @throws CinemaNuloException
         * @throws SalaNulaException
         * @throws SalaNaoEncontradaException
         * @throws CinemaNaoEncontradoException 
         */
	public Lista listarSessoes(Cinema cine,Sala salaTeste) throws CinemaNuloException, SalaNulaException, SalaNaoEncontradaException,CinemaNaoEncontradoException{
		Lista sessoes;
		if(cine==null){
			throw new CinemaNuloException();
		} else if(salaTeste==null){
			throw new SalaNulaException();
		}else{
			boolean salaexiste=salaExiste(cine, salaTeste);
			boolean cinemaexiste=cinemaExiste(cine);
			if(cinemaexiste!=true){
				throw new CinemaNaoEncontradoException();
			}
			if(salaexiste!=true){
				throw new SalaNaoEncontradaException();
			}
		}
		sessoes=salaTeste.getSessoes();
		return sessoes;
	}
        
        /**
         * altera informacoes de um objeto comprador cadastrado no sistema
         * @param pessoa
         * @throws CompradorNuloException
         * @throws CampoObrigatorioInexistenteException
         * @throws CompradorNaoEncontradoException 
         */
	public void alterarComprador(Comprador pessoa) throws CompradorNuloException, CampoObrigatorioInexistenteException, CompradorNaoEncontradoException{
		Comprador pessoa2;
		if (pessoa == null) {
			throw new CompradorNuloException();
		}else if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty() || pessoa.getEmail() == null || pessoa.getEmail().trim().isEmpty() || pessoa.getTelefone() == null || pessoa.getTelefone().trim().isEmpty() || pessoa.getDocumento() == 0 ||  pessoa.getEndereco() == null) {
			throw new CampoObrigatorioInexistenteException();
		}else{
			pessoa2=recuperarComprador(pessoa.getDocumento());
			if(pessoa2==null){
				throw new CompradorNaoEncontradoException();
			} else{
				pessoa2.setComprou(pessoa.isComprou());
				pessoa2.setDocumento(pessoa.getDocumento());
				pessoa2.setEmail(pessoa.getEmail());
				pessoa2.setEndereco(pessoa.getEndereco());
				pessoa2.setNome(pessoa.getNome());
				pessoa2.setTelefone(pessoa.getTelefone());
			}
		}
	}

        /**
         * verifica se um determinado comprador existe na lista cadastrada
         * @param pessoa
         * @return true ou false
         */
	public boolean compradorExiste(Comprador pessoa){
		if(pessoa==null){
			return false;
		}
		MeuIterador iterador=(MeuIterador)comprador.iterador();
		Comprador auxiliarcomprador=null;
		while(iterador.temProximo()){
			auxiliarcomprador=(Comprador)iterador.obterProximo();
			if(auxiliarcomprador.equals(pessoa)){
				return true;
			}
		}
		return false;
	}

        /**
         * altera informacoes de um comprador fan cadastrado no sistema
         * @param pessoa
         * @throws CompradorNuloException
         * @throws CompradorNaoEncontradoException
         * @throws CampoObrigatorioInexistenteException 
         */
	public void alterarComprador(CompradorFan pessoa) throws CompradorNuloException, CompradorNaoEncontradoException, CampoObrigatorioInexistenteException{
		CompradorFan pessoa2;
		if (pessoa == null) {
			throw new CompradorNuloException();
		}else if(pessoa.getNome() == null  || pessoa.getNome().trim().isEmpty() || pessoa.getEmail() == null || pessoa.getEmail().trim().isEmpty() || pessoa.getTelefone() == null || pessoa.getTelefone().trim().isEmpty() || pessoa.getDocumento() == 0 || pessoa.getEndereco() == null || pessoa.getRegistro() == 0){
			throw new CampoObrigatorioInexistenteException();
		}else{
			pessoa2=(CompradorFan) recuperarComprador(pessoa.getDocumento());
			if(pessoa2==null){
				throw new CompradorNaoEncontradoException();
			}else{
				pessoa2.setComprou(pessoa.isComprou());
				pessoa2.setDocumento(pessoa.getDocumento());
				pessoa2.setEmail(pessoa.getEmail());
				pessoa2.setEndereco(pessoa.getEndereco());
				pessoa2.setNome(pessoa.getNome());
				pessoa2.setTelefone(pessoa.getTelefone());
				pessoa2.setRegistro(pessoa.getRegistro());
			}
		}
	}

        /**
         * retorna fila com todos fans cadastrados
         * @return compradorfan
         */
	public Fila recuperarFansHabilitados(){
		return compradorfan;
	}
        
        /**
         * retorna lista ordenada de todos compradores cadastrados
         * @return comprador
         */
	public Lista listarCompradores(){
		OrdenaComprador();
		return comprador;
	}

        /**
         * remove um comprador cadastrado que nao tenha efetuado nenhuma compra
         * @param documento
         * @return pessoa
         * @throws RemocaoNaoPermitidaException
         * @throws CompradorNaoEncontradoException 
         */
	public Comprador removerComprador(int documento)throws RemocaoNaoPermitidaException, CompradorNaoEncontradoException{
		Comprador pessoa=null;
		Lista ingressos;
		Ingresso ingresso;
		Comprador pessoa2;
		CompradorController controllerComprador = CompradorController.getInstance();
		ingressos=controllerComprador.retornarlistaingresso();
		MeuIterador iterador=(MeuIterador)comprador.iterador();
		MeuIterador iteradoringresso=(MeuIterador)ingressos.iterador();
		int index=0;
		if(!comprador.estaVazia()){
			while(iterador.temProximo()){
				pessoa=(Comprador) iterador.obterProximo();
				if(pessoa.getDocumento() == documento){
					while(iteradoringresso.temProximo()){
						ingresso=(Ingresso) iteradoringresso.obterProximo();
						pessoa2=ingresso.getComprador();
						if(pessoa2.equals(pessoa)){
							throw new RemocaoNaoPermitidaException();
						}
					}
					pessoa = (Comprador) comprador.remover(index);
					return pessoa;
				}
				index++;
			}
		}
		throw new CompradorNaoEncontradoException();
	}

        /**
         * remove um cinema cadastrado
         * @param id
         * @return true ou false
         * @throws RemocaoNaoPermitidaException
         * @throws CinemaNaoEncontradoException 
         */
	public boolean removerCinema(int id)throws RemocaoNaoPermitidaException, CinemaNaoEncontradoException{
		Cinema cine;
		Lista ingressos;
		Ingresso ingresso;
		CompradorController controllerComprador = CompradorController.getInstance();
		ingressos=controllerComprador.retornarlistaingresso();
		MeuIterador iterador=(MeuIterador)cinema.iterador();
		MeuIterador iteradoringresso=(MeuIterador)ingressos.iterador();
		int index=0;
		if(!cinema.estaVazia()){
			while(iterador.temProximo()){
				cine=(Cinema) iterador.obterProximo();
				if(cine.getId() == id){
					while(iteradoringresso.temProximo()){
						ingresso=(Ingresso) iteradoringresso.obterProximo();
						if(ingresso.getCinema()==cine){
							throw new RemocaoNaoPermitidaException();
						}
					}	
					return cinema.remover(index)!=null;
				}
				index++;
			}
		}
		throw new CinemaNaoEncontradoException();
	}

        /**
         * distribui uma quantidade predefinida de camisetas, para os compradores que estiverem na fila de fans
         * @param qtd
         * @throws FanHabilitadoInexistenteException 
         */
	public void distribuirCamisas(int qtd) throws FanHabilitadoInexistenteException{
		Comprador pessoa;
		int contador=0;
		if(compradorfan==null){
			throw new FanHabilitadoInexistenteException();
		}
		while(contador<qtd){
			pessoa=(Comprador) compradorfan.removerInicio();
			if(pessoa!=null){
				break;
			}
			contador++;
		}

	}

        /**
         * Ordena a lista de compradores em ordem alfabetica
         */
	public void OrdenaComprador(){
		int tamanho;
		tamanho=comprador.obterTamanho();
		Comprador array[]=new Comprador[tamanho];
		MeuIterador iterador = (MeuIterador) comprador.iterador();
		int cont=0;
		while(cont<tamanho || iterador.temProximo()){
			array[cont]=(Comprador) iterador.obterProximo();
			cont++;
		}
			comprador=new Lista();
		QuickSort.quickSort(array,0, tamanho-1);
		cont=0;
		while(cont<tamanho){
			comprador.inserirInicio(array[cont]);
			cont++;
		}
	}
}
