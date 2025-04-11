package com.enzoapps.viajatour.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enzoapps.viajatour.util.DBConexao;

public class ServicoContratado {
	
	private Long id;
	private Long contratacaoId;
	private Long ServicoId;
	
	public ServicoContratado(Long contrtacaoId, Long servicoId) {
		this.contratacaoId = contrtacaoId;
		this.ServicoId = servicoId;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServicoContratado() {
	}

	public Long getContrtacaoId() {
		return contratacaoId;
	}

	public void setContrtacaoId(Long contrtacaoId) {
		this.contratacaoId = contrtacaoId;
	}

	public Long getServicoId() {
		return ServicoId;
	}

	public void setServicoId(Long servicoId) {
		ServicoId = servicoId;
	}

	@Override
	public String toString() {
		
		var c = Contratacao.findById(contratacaoId);
		var sl = Cliente.findById(c.getClienteId());
		var p = PacoteViagem.findById(c.getPacoteViagemId());
		var s = ServicoAdicional.findById(ServicoId);
		
		//return "ServicoContratado [contratacaoId =" + contratacaoId + ", ServicoId=" + ServicoId + "]";
		return "ServicoContratado [nome = " + sl.getNome() + ", comprou o pacote= " + p.getDescricao() + "e contratou= " + s.getNome()+ "]";
		
	}
	
	public boolean insert() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("INSERT INTO contratacao_servicos (contratacao_id, servico_id) VALUES (" +
			          "'" + contratacaoId + "', " +
			          "'" + ServicoId + "' " 
			          + ");");
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

			s.execute("UPDATE contratacao_servicos SET "
			        + "contratacao_id = '" + contratacaoId + "', "
			        + "servico_id = '" + ServicoId + "' "
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

			s.execute("DELETE FROM contratacao_servicos WHERE ID=" + id + ";");
			DBConexao.fecharConexao(con);
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ServicoContratado findById(Long id) {
		ServicoContratado sc = null;

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM contratacao_servicos WHERE ID = " + id + ";");

	        if (rs.next()) {
	            sc = map(rs);
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return sc;
	}

	private static ServicoContratado map(ResultSet rs) throws SQLException {
		ServicoContratado sc;
		sc = new ServicoContratado();
		sc.id = rs.getLong("ID");
		sc.contratacaoId = rs.getLong("contratacao_id");
		sc.ServicoId = rs.getLong("servico_id");
		return sc;
	}

	public static List<ServicoContratado> findAll() {
		List<ServicoContratado> list = new ArrayList<ServicoContratado>();

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM contratacao_servicos;");
            
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
