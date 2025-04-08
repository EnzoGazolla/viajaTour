package com.enzoapps.viajatour;

import java.sql.SQLException;
import org.h2.tools.Server;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBConexao;

public class Main {

	public static void main(String[] args) {
		
		new DBBanco().criarTabela();
		
		
		try {
			
			var con = new DBConexao().criarConexao();
			var s = con.createStatement();
			
			s.execute("INSERT INTO clientes (nome, telefone, email, tipo_cliente, cpf, passaporte) values"
					+ "('Astrobaldo', '564567', 'astrobaldp@gmail', 'NACIONAL', '', '');");
			
			Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	
	
}
