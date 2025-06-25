package com.enzoapps.viajatour.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enzoapps.viajatour.util.DBConexao;

// Classe do Servico adicional 
public class ServicoAdicional {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	
	// Construtor com parâmetros
	public ServicoAdicional(String nome, String descricao, BigDecimal preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	// Construtor vazio (necessário para mapeamento do banco)
	public ServicoAdicional() {
	}
	
	// Getters e Setters
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
	
	// Representação do objeto como string (útil para exibição no console)
	@Override
	public String toString() {
		return "ServicoAdicional [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + "]";
	}
	
	// Insere um novo serviço adicional no banco
	public boolean insert() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("INSERT INTO servicos_adicionais (nome, descricao, preco) VALUES (" +
			          "'" + nome + "', " +
			          "'" + descricao + "', " + 
			          preco + ");");
			DBConexao.fecharConexao(con);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Atualiza um serviço adicional existente
	public boolean update() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("UPDATE servicos_adicionais SET "
			        + "nome = '" + nome + "', "
			        + "descricao = '" + descricao + "', "
			        + "preco = '" + preco + "' "
			        + "WHERE id = " + id + ";");
			DBConexao.fecharConexao(con);

			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	// Exclui um serviço adicional do banco
	public boolean delete() throws SQLException {
		
			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("DELETE FROM servicos_adicionais WHERE ID=" + id + ";");
			DBConexao.fecharConexao(con);
			
			return true;
	}
	
	// Busca um serviço adicional pelo ID
	public static ServicoAdicional findById(Long id) {
		ServicoAdicional sc = null;

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM servicos_adicionais WHERE ID = " + id + ";");

	        if (rs.next()) {
	            sc = map(rs);  // mapeia o resultado para um objeto Java
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return sc;
	}
	// Mapeia um ResultSet para um objeto ServicoAdicional
	private static ServicoAdicional map(ResultSet rs) throws SQLException {
		ServicoAdicional sc;
		sc = new ServicoAdicional();
		sc.id = rs.getLong("ID");
		sc.nome = rs.getString("nome");
		sc.descricao = rs.getString("descricao");
		sc.preco = rs.getBigDecimal("preco");
		return sc;
	}
	
	// Retorna todos os serviços adicionais cadastrados
	public static List<ServicoAdicional> findAll() {
		List<ServicoAdicional> list = new ArrayList<ServicoAdicional>();

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM servicos_adicionais;");
            
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