package com.enzoapps.viajatour;

import java.sql.SQLException;
import org.h2.tools.Server;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.TipoCliente;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBConexao;

public class Main {

	public static void main(String[] args) {
		
		new DBBanco().criarTabela();
		
		
		try {
			
			var c = new Cliente("Astrobaldo", "45678", "astrobaldo@gmail", TipoCliente.NACIONAL, "23456", "223");
			c.save();
			
			var c1 = new Cliente("Ze", "45678", "ze@gmail", TipoCliente.NACIONAL, "4343", "113");
			c1.save();
			
			var lista = Cliente.findAll();
			for (Cliente cliente : lista) {
				System.out.println(cliente);
			}
			
			var x = lista.get(0);
			x.delete();
			
			Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	
	
}
