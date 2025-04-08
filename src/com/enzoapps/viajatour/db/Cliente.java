package com.enzoapps.viajatour.db;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private TipoCliente tipo;
	private String cpf;
	private String passaporte;
	
	public Cliente(Long id, String nome, String telefone, String email, TipoCliente tipo, String cpf,
			String passaporte) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.tipo = tipo;
		this.cpf = cpf;
		this.passaporte = passaporte;
	}

	public Cliente() {
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

	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", tipo=" + tipo
				+ ", cpf=" + cpf + ", passaporte=" + passaporte + "]";
	}
	
	public boolean save() {
		return true;
	}
	public boolean delete() {
		return true;
	}
	public static Cliente findById(Long id) {
		return new Cliente();
	}
	public static List<Cliente> findAll() {
		return new ArrayList<Cliente>();
	}
	 private static Cliente mapResultSetToCliente( ) {
		 return new Cliente();
	 }
}
