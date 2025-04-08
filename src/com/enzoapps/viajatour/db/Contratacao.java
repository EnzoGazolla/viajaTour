package com.enzoapps.viajatour.db;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contratacao {
	
	private Long id;
	private Long clienteId;
	private Long pacoteViagemId;
	private LocalDate dataContratacao;
	private BigDecimal valorTotal;
	
	public Contratacao(Long id, Long clienteId, Long pacoteViagemId, LocalDate dataContratacao, BigDecimal valorTotal) {
		this.id = id;
		this.clienteId = clienteId;
		this.pacoteViagemId = pacoteViagemId;
		this.dataContratacao = dataContratacao;
		this.valorTotal = valorTotal;
	}

	public Contratacao() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getPacoteViagemId() {
		return pacoteViagemId;
	}

	public void setPacoteViagemId(Long pacoteViagemId) {
		this.pacoteViagemId = pacoteViagemId;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "Contratacao [id=" + id + ", clienteId=" + clienteId + ", pacoteViagemId=" + pacoteViagemId
				+ ", dataContratacao=" + dataContratacao + ", valorTotal=" + valorTotal + "]";
	}
	
	public boolean save() {
		return true;
	}
	public boolean delete() {
		return true;
	}
	public static Contratacao findById(Long id) {
		return new Contratacao();
	}
	public static List<Contratacao> findAll() {
		return new ArrayList<Contratacao>();
	}
	 private static Contratacao mapResultSetToCliente( ) {
		 return new Contratacao();
	 }
	 
	 
	
}
