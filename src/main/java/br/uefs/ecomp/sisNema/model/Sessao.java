package br.uefs.ecomp.sisNema.model;

public class Sessao {
	private int horaInicio=-1;
	private int horaFim=-1;
        private int qtdCadeiras;
	
	/**
	 * @return the horaInicio
	 */
	public int getHoraInicio() {
		return horaInicio;
	}
	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
	/**
	 * @return the horaFim
	 */
	public int getHoraFim() {
		return horaFim;
	}
	/**
	 * @param horaFim the horaFim to set
	 */
	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
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
}
