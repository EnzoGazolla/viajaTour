package com.enzoapps.viajatour.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.enzoapps.viajatour.util.DBConexao;

//Classe que representa uma contratação (venda de pacote) no sistema
public class Contratacao {
	
	private Long id;
	private Long clienteId;
	private Long pacoteViagemId;
	private LocalDate dataContratacao;
	private BigDecimal valorTotal;
	
	// Construtor com parâmetros
	public Contratacao(Long clienteId, Long pacoteViagemId, LocalDate dataContratacao, BigDecimal valorTotal) {
		this.clienteId = clienteId;
		this.pacoteViagemId = pacoteViagemId;
		this.dataContratacao = dataContratacao;
		this.valorTotal = valorTotal;
	}
	
	// Construtor vazio (necessário para mapeamento e frameworks)
	public Contratacao() {
		// TODO Auto-generated constructor stub
	}
	
	// Getters e Setters
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
	
	// Representação textual do objeto (útil para debug e exibição)
	@Override
	public String toString() {
		return "Contratacao [id=" + id + ", clienteId=" + clienteId + ", pacoteViagemId=" + pacoteViagemId
				+ ", dataContratacao=" + dataContratacao + ", valorTotal=" + valorTotal + "]";
	}
	
	// Insere uma nova contratação no banco de dados
	public Long insert() {
	    try {
	        var con = DBConexao.criarConexao();
	        var sql = "INSERT INTO contratacoes (id_cliente, id_pacote_viagem, data_contratacao, valor_total) VALUES (?, ?, ?, ?)";
	        
	        // Prepara o statement indicando que queremos as chaves geradas
	        var ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        // Define os parâmetros usando PreparedStatement (mais seguro)
	        ps.setLong(1, clienteId);
	        ps.setLong(2, pacoteViagemId);
	        ps.setDate(3, java.sql.Date.valueOf(dataContratacao));
	        ps.setBigDecimal(4, valorTotal);
	        
	        // Executa a inserção
	        int affectedRows = ps.executeUpdate();
	        
	        if (affectedRows == 0) {
	            throw new SQLException("Falha na inserção, nenhuma linha foi afetada.");
	        }
	        
	        // Recupera as chaves geradas
	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                Long generatedId = generatedKeys.getLong(1);
	                this.id = generatedId; // Atualiza o ID do objeto atual
	                return generatedId;
	            } else {
	                throw new SQLException("Falha na inserção, nenhum ID foi gerado.");
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    } 
	}
	
	// Atualiza os dados de uma contratação existente
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
	
	// Deleta uma contratação com base no ID
	public boolean delete() throws SQLException {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("DELETE FROM contratacoes WHERE ID=" + id + ";");
			DBConexao.fecharConexao(con);
			
			return true;
	}
	// Busca uma contratação pelo ID
	public static Contratacao findById(Long id) {
		Contratacao c = null;

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM contratacoes WHERE ID = " + id + ";");

	        if (rs.next()) {
	            c = map(rs); // Mapeia resultado do banco para objeto Contratacao
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return c;
	}
	
	// Converte o resultado do banco (ResultSet) em um objeto Contratacao
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
	
	// Retorna todas as contratações cadastradas
	public static List<Contratacao> findAll() {
		List<Contratacao> list = new ArrayList<Contratacao>();

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM contratacoes;");
            
	        while (rs.next()) {
	        	list.add(map(rs)); // Adiciona cada contratação à lista
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
}