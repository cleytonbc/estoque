package br.gov.rj.faeterj.estoque.model;

public enum Sabor {

	DOCE("Doce"),
	SUAVE("Suave"),
	SECO("Seco"),
	SEMISECO("Semi-Seco");
	
	private String descricao;
	
	Sabor(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
