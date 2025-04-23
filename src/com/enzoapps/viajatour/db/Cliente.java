package com.enzoapps.viajatour.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Server;

import com.enzoapps.viajatour.util.DBConexao;

public class Cliente {
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private TipoCliente tipo;
	private String cpf;
	private String passaporte;

	public Cliente(String nome, String telefone, String email, TipoCliente tipo, String cpf,
			String passaporte) {

		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.tipo = tipo;
		this.cpf = cpf;
		this.passaporte = passaporte;
	}

	public Cliente() {
	
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

	public boolean insert() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("INSERT INTO clientes (nome, telefone, email, tipo_cliente, cpf, passaporte) values"
					+ "('" + nome + "', '" + telefone + "',' " + email + "',' " + tipo
					+ "',' " + cpf + "',' " + passaporte + "');");
			DBConexao.fecharConexao(con);
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean update() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("UPDATE clientes SET nome = '" + nome + 
			          "', telefone = '" + telefone + 
			          "', email = '" + email + 
			          "', tipo_cliente = '" + tipo + 
			          "', passaporte = '" + passaporte + 
			          "', cpf = '" + cpf + "' WHERE id = " + id + ";");
			DBConexao.fecharConexao(con);
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean delete() {
		
		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("DELETE FROM clientes WHERE ID=" + id + ";");
			DBConexao.fecharConexao(con);
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Cliente findById(Long id) {
	    Cliente c = null;

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM clientes WHERE ID = " + id + ";");

	        if (rs.next()) {
	            c = map(rs);
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return c;
	}

	private static Cliente map(ResultSet rs) throws SQLException {
		Cliente c;
		c = new Cliente();
		c.id = rs.getLong("ID");
		c.nome = rs.getString("NOME");
		c.telefone = rs.getString("TELEFONE");
		c.email = rs.getString("EMAIL");
		String tipoBanco = rs.getString("TIPO_CLIENTE").toUpperCase().trim();
	    if ("NACIONAL".equals(tipoBanco)) {
	        c.tipo = TipoCliente.NACIONAL;
	    } else if ("ESTRANGEIRO".equals(tipoBanco)) {
	        c.tipo = TipoCliente.ESTRANGEIRO; 
	    } else {
	        throw new SQLException("Tipo de cliente inválido: " + tipoBanco);
	    }
		c.cpf = rs.getString("CPF");
		c.passaporte = rs.getString("PASSAPORTE");
		return c;
	}


	public static List<Cliente> findAll() {
		List<Cliente> list = new ArrayList<Cliente>();

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM clientes;");
            
	        while (rs.next()) {
	        	list.add(map(rs));
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}

}
