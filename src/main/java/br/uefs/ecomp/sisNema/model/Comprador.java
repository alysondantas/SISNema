
package br.uefs.ecomp.sisNema.model;

public class Comprador {
    protected String nome;
    protected Endereco endereco;
    protected String telefone;
    protected String email;
    protected int documento;
    private boolean comprou;

    public Comprador(){
		
	}
    
    public Comprador(String nome, String telefone, String email, int documento, Endereco endereco){
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
	}
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    } 
    
	/**
	 * @return the comprou
	 */
	public boolean isComprou() {
		return comprou;
	}

	/**
	 * @param comprou the comprou to set
	 */
	public void setComprou(boolean comprou) {
		this.comprou = comprou;
	}
}
