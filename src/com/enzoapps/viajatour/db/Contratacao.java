package com.enzoapps.viajatour.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.enzoapps.viajatour.util.DBConexao;

public class Contratacao {
	
	private Long id;
	private Long clienteId;
	private Long pacoteViagemId;
	private LocalDate dataContratacao;
	private BigDecimal valorTotal;
	
	public Contratacao(Long clienteId, Long pacoteViagemId, LocalDate dataContratacao, BigDecimal valorTotal) {
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
	
	public boolean insert() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("INSERT INTO contratacoes (id_cliente, id_pacote_viagem, data_contratacao, valor_total) VALUES (" +
			          "'" + clienteId + "', " +
			          "'" + pacoteViagemId + "', " +
			          "'" + dataContratacao + "', " +  
			          valorTotal + ");");
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

			s.execute("UPDATE contratacoes SET "
			        + "id_cliente = '" + clienteId + "', "
			        + "id_pacote_viagem = '" + pacoteViagemId + "', "
			        + "data_contratacao = '" + dataContratacao + "', "
			        + "valor_total = " + valorTotal + " "
			        + "WHERE id = " + id + ";");
			DBConexao.fecharConexao(con);

			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean delete() throws SQLException {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("DELETE FROM contratacoes WHERE ID=" + id + ";");
			DBConexao.fecharConexao(con);
			
			return true;
	}

	public static Contratacao findById(Long id) {
		Contratacao c = null;

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM contratacoes WHERE ID = " + id + ";");

	        if (rs.next()) {
	            c = map(rs);
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return c;
	}

	private static Contratacao map(ResultSet rs) throws SQLException {
		Contratacao c;
		c = new Contratacao();
		c.id = rs.getLong("ID");
		c.clienteId = rs.getLong("id_cliente");
		c.pacoteViagemId = rs.getLong("id_pacote_viagem");
		c.dataContratacao = rs.getDate("data_contratacao").toLocalDate();
		c.valorTotal = rs.getBigDecimal("valor_total");
		return c;
	}

	public static List<Contratacao> findAll() {
		List<Contratacao> list = new ArrayList<Contratacao>();

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM contratacoes;");
            
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