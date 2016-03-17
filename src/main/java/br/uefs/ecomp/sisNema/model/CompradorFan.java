
package br.uefs.ecomp.sisNema.model;

public class CompradorFan extends Comprador{
    private int registro;
    
    public CompradorFan(String nome, String telefone, String email, int documento, Endereco endereco){
		super(nome, telefone, email, documento, endereco);
	}
    
    public CompradorFan() {
	}
    
    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }
}
