package com.enzoapps.viajatour.util;

import java.sql.SQLException;

public class DBBanco {

	public void criarTabela() {

		try {
			var con = DBConexao.criarConexao();
			var s = con.createStatement();

			s.execute("CREATE TABLE IF NOT EXISTS clientes (" 
					+ "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
					+ "nome VARCHAR(255) NOT NULL," 
					+ "telefone VARCHAR(20)," 
					+ "email VARCHAR(100) UNIQUE,"
					+ "tipo_cliente VARCHAR(20) NOT NULL," // 'NACIONAL' ou 'ESTRANGEIRO'
					+ "cpf VARCHAR(14) ,"
					+ "passaporte VARCHAR(50)" + ");");

			s.execute("CREATE TABLE IF NOT EXISTS tipos_pacote ("
	                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
	                + "nome VARCHAR(100) NOT NULL,"
	                + "descricao TEXT"
	                + ");");
			
			s.execute("CREATE TABLE IF NOT EXISTS pacotes_viagem ("
	                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
	                + "nome VARCHAR(255) NOT NULL,"
	                + "descricao VARCHAR(255) NOT NULL,"
	                + "destino VARCHAR(255) NOT NULL,"
	                + "duracao_dias INT NOT NULL,"
	                + "preco_base DECIMAL(10, 2) NOT NULL,"
	                + "id_tipos_pacote BIGINT NOT NULL,"
	                + "FOREIGN KEY (id_tipos_pacote) REFERENCES tipos_pacote(id)"
	                + ");");
			
			s.execute("CREATE TABLE IF NOT EXISTS servicos_adicionais ("
	                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
	                + "nome VARCHAR(100) NOT NULL,"
	                + "descricao TEXT,"
	                + "preco DECIMAL(10, 2) NOT NULL"
	                + ");");
			
			s.execute("CREATE TABLE IF NOT EXISTS contratacoes ("
	                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
	                + "id_cliente BIGINT NOT NULL,"
	                + "id_pacote_viagem BIGINT NOT NULL,"
	                + "data_contratacao DATETIME DEFAULT CURRENT_TIMESTAMP,"
	                + "valor_total DECIMAL(10, 2) NOT NULL,"
	                + "FOREIGN KEY (id_cliente) REFERENCES clientes(id),"
	                + "FOREIGN KEY (id_pacote_viagem) REFERENCES pacotes_viagem(id)"
	                + ");");
			
			s.execute("CREATE TABLE IF NOT EXISTS contratacao_servicos ("
					+ "id BIGINT PRIMARY KEY AUTO_INCREMENT, "
	                + "contratacao_id BIGINT NOT NULL,"
	                + "servico_id BIGINT NOT NULL,"
	                + "FOREIGN KEY (contratacao_id) REFERENCES contratacoes(id) ON DELETE CASCADE," 
	                + "FOREIGN KEY (servico_id) REFERENCES servicos_adicionais(id));");
	 	
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
