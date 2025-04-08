package com.enzoapps.viajatour.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServicoAdicional {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	
	public ServicoAdicional(Long id, String nome, String descricao, BigDecimal preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	public ServicoAdicional() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "ServicoAdicional [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + "]";
	}
	
	public boolean save() {
		return true;
	}
	public boolean delete() {
		return true;
	}
	public static ServicoAdicional findById(Long id) {
		return new ServicoAdicional();
	}
	public static List<ServicoAdicional> findAll() {
		return new ArrayList<ServicoAdicional>();
	}
	 private static ServicoAdicional mapResultSetToCliente( ) {
		 return new ServicoAdicional();
	 }
	
	
}
