package com.enzoapps.viajatour.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PacoteViagem {
	
	private Long id;
	private String nome;
	private String descricao;
	private String destino;
	private int duracaoDias;
	private BigDecimal precoBase;
	
	public PacoteViagem(Long id, String nome, String descricao, String destino, int duracaoDias, BigDecimal precoBase) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.destino = destino;
		this.duracaoDias = duracaoDias;
		this.precoBase = precoBase;
	}
	

	public PacoteViagem() {
	
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

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getDuracaoDias() {
		return duracaoDias;
	}

	public void setDuracaoDias(int duracaoDias) {
		this.duracaoDias = duracaoDias;
	}

	public BigDecimal getPrecoBase() {
		return precoBase;
	}

	public void setPrecoBase(BigDecimal precoBase) {
		this.precoBase = precoBase;
	}

	@Override
	public String toString() {
		return "PacoteViagem [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", destino=" + destino
				+ ", duracaoDias=" + duracaoDias + ", precoBase=" + precoBase + "]";
	}
	
	public boolean save() {
		return true;
	}
	public boolean delete() {
		return true;
	}
	public static PacoteViagem  findById(Long id) {
		return new PacoteViagem ();
	}
	public static List<PacoteViagem > findAll() {
		return new ArrayList<PacoteViagem >();
	}
	 private static PacoteViagem  mapResultSetToCliente( ) {
		 return new PacoteViagem ();
	 }
	
	

}
