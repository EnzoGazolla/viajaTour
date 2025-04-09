package com.enzoapps.viajatour;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;

import org.h2.tools.Server;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.TipoCliente;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBConexao;

public class Main {

	public static void main(String[] args) {
		
		new DBBanco().criarTabela();
		
		
		try {
			
			var c = new Cliente("Astrobaldo", "45678", "astrobaldo@gmail", TipoCliente.NACIONAL, "23456", "223");
			c.insert();
			
			var c1 = new Cliente("Ze", "45678", "ze@gmail", TipoCliente.NACIONAL, "4343", "113");
			c1.insert();
			
			var lista = Cliente.findAll();
			for (Cliente cliente : lista) {
				System.out.println(cliente);
			}
			
			var x = lista.get(0);
			x.delete();
			
			
			var pc = new PacoteViagem("Viagem pro rio quente", "estadia no rio quente resort", "Rio Quente, Go", 15, new BigDecimal(2000));
			pc.insert();		
			
			var pc1 = new PacoteViagem("Viagem pra nova york", "estadia no ymca", "New York, NY",20, new BigDecimal(8000));
			pc1.insert();
			
			var listapc = PacoteViagem.findAll();
			for (PacoteViagem pacoteViagem : listapc) {
				System.out.println(pacoteViagem);
			}
			
			
			Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	
	
}
