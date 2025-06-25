package com.enzoapps.viajatour.db;

/* Enum TipoCliente
 * 
 * Representa os tipos de cliente que podem existir no sistema de pacotes de viagem:
 * - Nacional: clientes que residem no Brasil
 * - Estrangeiro: clientes de outros países 
 * Cada valor possui uma descrição legível (por exemplo, "Nacional" ou "Estrangeiro"),
 * útil para exibição em interfaces ou relatórios. */

public enum TipoCliente  {
	
	// Constantes do enum com descrições associadas
	NACIONAL("Nacional"),
	ESTRANGEIRO("Estrangeiro");
	
	private final String descricao;
	
	// Atributo para armazenar a descrição do tipo de cliente
	 TipoCliente(String descricao) {
		this.descricao = descricao;
	}
	 
	// Retorna a descrição associada ao tipo de cliente
	public String getDescricao() {
		return descricao;
	}
	
	// Sobrescreve toString para exibir a descrição ao invés do nome técnico do enum
	@Override
	public String toString() {
		return descricao;
	}
	
	

}
