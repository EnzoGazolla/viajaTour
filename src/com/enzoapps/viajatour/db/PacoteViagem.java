package com.enzoapps.viajatour.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enzoapps.viajatour.util.DBConexao;

public class PacoteViagem {
	
	private Long id;
	private String nome;
	private String descricao;
	private String destino;
	private int duracaoDias;
	private BigDecimal precoBase;
	
	public PacoteViagem(String nome, String descricao, String destino, int duracaoDias, BigDecimal precoBase) {
		this.nome = nome;
		this.descricao = descricao;
		this.destino = destino;
		this.duracaoDias = duracaoDias;
		this.precoBase = precoBase;
	}
	
	public PacoteViagem() {
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
	
	public boolean insert() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("INSERT INTO pacotes_viagem (nome, descricao, destino, duracao_dias, preco_base) VALUES ("
			        + "'" + nome + "', "
			        + "'" + descricao + "', "
			        + "'" + destino + "', "
			        + duracaoDias + ", "
			        + precoBase + ");");
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

			s.execute("UPDATE pacotes_viagem SET "
			        + "nome = '" + nome + "', "
			        + "descricao = '" + descricao + "', "
			        + "destino = '" + destino + "', "
			        + "duracao_dias = " + duracaoDias + ", "
			        + "preco_base = " + precoBase + " "
			        + "WHERE id = " + id + ";");
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

			s.execute("DELETE FROM pacotes_viagem WHERE ID=" + id + ";");
			DBConexao.fecharConexao(con);
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static PacoteViagem findById(Long id) {
		PacoteViagem pc = null;

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM pacotes_viagem WHERE ID = " + id + ";");

	        if (rs.next()) {
	            pc = map(rs);
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return pc;
	}

	private static PacoteViagem map(ResultSet rs) throws SQLException {
		PacoteViagem pc;
		pc = new PacoteViagem();
		pc.id = rs.getLong("ID");
		pc.nome = rs.getString("NOME");
		pc.descricao = rs.getString("DESCRICAO");
		pc.destino = rs.getString("DESTINO");
		pc.duracaoDias = rs.getInt("duracao_dias");
		pc.precoBase = rs.getBigDecimal("preco_base");
		return pc;
	}


	public static List<PacoteViagem> findAll() {
		List<PacoteViagem> list = new ArrayList<PacoteViagem>();

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM pacotes_viagem;");
            
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
