package com.enzoapps.viajatour.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.enzoapps.viajatour.util.DBConexao;

//Classe que representa um tipo de pacote de viagem 
public class TipoPacote {
	
	private Long id;
	private String nome;
	private String descricao;
	
	// Construtor com parâmetros
	public TipoPacote(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	// Construtor vazio (necessário para frameworks e JDBC)
	public TipoPacote() {
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

	// Define o nome como representação textual do objeto
	@Override
	public String toString() {
		return nome;
	}
	
	// Compara dois objetos TipoPacote pelo id
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    TipoPacote that = (TipoPacote) obj;
	    return Objects.equals(id, that.id); 
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}
	
	// Insere um novo tipo de pacote no banco de dados
	public boolean insert() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("INSERT INTO tipos_pacote (nome, descricao) VALUES (" +
			          "'" + nome + "', " +
			          "'" + descricao + "' " + 
			          ");");
			DBConexao.fecharConexao(con);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Atualiza os dados de um tipo de pacote existente
	public boolean update() {

		try {

			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("UPDATE tipos_pacote SET "
			        + "nome = '" + nome + "', "
			        + "descricao = '" + descricao + "' "
			        + "WHERE id = " + id + ";");
			DBConexao.fecharConexao(con);

			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	// Exclui um tipo de pacote do banco de dados pelo ID
	public boolean delete() throws SQLException {
		
			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("DELETE FROM tipos_pacote WHERE ID=" + id + ";");
			DBConexao.fecharConexao(con);
			
			return true;
			
	}
	
	// Busca um tipo de pacote pelo ID
	public static TipoPacote findById(Long id) {
		TipoPacote tp = null;

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM tipos_pacote WHERE ID = " + id + ";");

	        if (rs.next()) {
	        	tp = map(rs); // Mapeia o resultado para um objeto TipoPacote
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tp;
	}
	
	// Converte o resultado do banco (ResultSet) em um objeto TipoPacote
	private static TipoPacote map(ResultSet rs) throws SQLException {
		TipoPacote tp;
		tp = new TipoPacote();
		tp.id = rs.getLong("ID");
		tp.nome = rs.getString("nome");
		tp.descricao = rs.getString("descricao");
		return tp;
	}
	
	// Retorna todos os tipos de pacote cadastrados
	public static List<TipoPacote> findAll() {
		List<TipoPacote> list = new ArrayList<TipoPacote>();

	    try {
	        var con = DBConexao.criarConexao();
	        var s = con.createStatement();
	        var rs = s.executeQuery("SELECT * FROM tipos_pacote;");
            
	        while (rs.next()) {
	        	list.add(map(rs)); // Adiciona cada tipo à lista
	        }

	        DBConexao.fecharConexao(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
}