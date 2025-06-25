package com.enzoapps.viajatour.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enzoapps.viajatour.util.DBConexao;

// Classe Servico Contratado
public class ServicoContratado {
	
	private Long id;
	private Long contratacaoId;
	private Long ServicoId;
	
	// Construtor com parâmetros
	public ServicoContratado(Long contrtacaoId, Long servicoId) {
		this.contratacaoId = contrtacaoId;
		this.ServicoId = servicoId;
	}

	// Construtor vazio (necessário para métodos como map())
	public Long getId() {
		return id;
	}

	// Getters e Setters
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
	
	// Representação do objeto como string
	@Override
	public String toString() {
		
		var c = Contratacao.findById(contratacaoId);
		var sl = Cliente.findById(c.getClienteId());
		var p = PacoteViagem.findById(c.getPacoteViagemId());
		var s = ServicoAdicional.findById(ServicoId);
		
		//return "ServicoContratado [contratacaoId =" + contratacaoId + ", ServicoId=" + ServicoId + "]";
		return "ServicoContratado [nome = " + sl.getNome() + ", comprou o pacote= " + p.getDescricao() + "e contratou= " + s.getNome()+ "]";
		
	}
	
	// Insere um novo registro no banco
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
	
	// Atualiza um registro existente
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
	
	// Deleta um registro do banco
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
	
	// Busca um registro pelo ID
	public static ServicoContratado findById(Long id) {
		ServicoContratado sc = null;

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM contratacao_servicos WHERE ID = " + id + ";");

	        if (rs.next()) {
	            sc = map(rs); // converte ResultSet em objeto ServicoContratado
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return sc;
	}
	
	// Converte um ResultSet em um objeto ServicoContratado
	private static ServicoContratado map(ResultSet rs) throws SQLException {
		ServicoContratado sc;
		sc = new ServicoContratado();
		sc.id = rs.getLong("ID");
		sc.contratacaoId = rs.getLong("contratacao_id");
		sc.ServicoId = rs.getLong("servico_id");
		return sc;
	}
	
	// Retorna todos os registros da tabela contratacao_servicos
	public static List<ServicoContratado> findAll() {
		List<ServicoContratado> list = new ArrayList<ServicoContratado>();

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM contratacao_servicos;");
            
	        while (rs.next()) {
	        	list.add(map(rs)); // adiciona cada registro mapeado na lista
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
}
