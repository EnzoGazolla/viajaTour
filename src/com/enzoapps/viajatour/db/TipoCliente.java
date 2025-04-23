package com.enzoapps.viajatour.db;

public enum TipoCliente  {
	
	NACIONAL("Nacional"),
	ESTRANGEIRO("Estrangeiro");
	
	private final String descricao;

	 TipoCliente(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
	
	

}
