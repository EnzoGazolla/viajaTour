package com.enzoapps.viajatour.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import org.h2.tools.Server;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.Contratacao;
import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.ServicoAdicional;
import com.enzoapps.viajatour.db.ServicoContratado;
import com.enzoapps.viajatour.db.TipoCliente;
import com.enzoapps.viajatour.db.TipoPacote;

public class DBCarga {
	
	public void carregar() {
		
try {
			
			var c = new Cliente("Astrobaldo", "45678", "astrobaldo@gmail", TipoCliente.NACIONAL, "23456", "");
			c.insert();
			
			var c1 = new Cliente("Ze", "45678", "ze@gmail", TipoCliente.NACIONAL, "4343", "");
			c1.insert();
			
			var c2 = new Cliente("John", "676952", "john@gmail", TipoCliente.ESTRANGEIRO, "", "122");
			c2.insert();
			
			var c3 = new Cliente("Tim", "87877", "tim@gmail", TipoCliente.ESTRANGEIRO, "", "154");
			c3.insert();
			
			var c4 = new Cliente("Maria", "45678", "maria@gmail", TipoCliente.NACIONAL, "657575", "");
			c4.insert();
			
			var lista = Cliente.findAll();
			for (Cliente cliente : lista) {
				System.out.println(cliente);
			}
			
			//var x = lista.get(0);
			//x.delete();  /// <<<<<<< - NÃO RODOU POR CAUSA DO DELETE !! :-O
			
			var tpc = new TipoPacote("aventura", "aventuras pelo mundo");
			tpc.insert();
			
			var tpc1 = new TipoPacote("luxo", "pacote de luxo");
			tpc1.insert();
			
			var tpc2 = new TipoPacote("cultura", "culturas do mundo");
			tpc2.insert();
			
			var tpacote = TipoPacote.findAll();
			for (TipoPacote tp : tpacote) {
				System.out.println(tp);
			}
			
			var pc = new PacoteViagem("Viagem pro rio quente", "estadia no rio quente resort", "Rio Quente, Go", 15, new BigDecimal(2000), tpacote.get(0).getId());
			pc.insert();		
			
			var pc1 = new PacoteViagem("Viagem pra nova york", "estadia no ymca", "New York, NY",20, new BigDecimal(8000), tpacote.get(0).getId());
			pc1.insert();
			
			var pc2 = new PacoteViagem("Viagem pra toronto", "estadia no hotel", "Toronto, ON",20, new BigDecimal(8000), tpacote.get(0).getId());
			pc2.insert();
			
			var pc3 = new PacoteViagem("Viagem pra Monaco", "estadia no hotel", "Monaco",20, new BigDecimal(8000), tpacote.get(1).getId());
			pc3.insert();
			
			var pc4 = new PacoteViagem("Viagem pra Dubai", "estadia no hotel", "Dubai",20, new BigDecimal(8000), tpacote.get(1).getId());
			pc4.insert();
			
			var pc5 = new PacoteViagem("Viagem pra Paris", "estadia no hotel", "Paris, FR",20, new BigDecimal(8000), tpacote.get(2).getId());
			pc5.insert();
			
			var listapc = PacoteViagem.findAll();
			for (PacoteViagem pacoteViagem : listapc) {
				System.out.println(pacoteViagem);
			}
			
			var clienteId = lista.get(0).getId();
			var pacoteId = listapc.get(0).getId();
			var pacotePreco = listapc.get(0).getPrecoBase();
			var venda = new Contratacao(clienteId, pacoteId, LocalDate.now(), pacotePreco);
			venda.insert();
			
			var clienteId1 = lista.get(1).getId();
			var pacoteId1 = listapc.get(1).getId();
			var pacotePreco1 = listapc.get(1).getPrecoBase();
			var venda1 = new Contratacao(clienteId1, pacoteId1, LocalDate.now(), pacotePreco1);
			venda1.insert();
			
			var listaVenda = Contratacao.findAll();
			for (Contratacao ctr : listaVenda) {
				System.out.println(ctr);
			}
			
			
			var sa1 = new ServicoAdicional("Cafe da manha incluso", "Serviço de cafe diario", new BigDecimal("50.00"));
			sa1.insert();

			var sa2 = new ServicoAdicional("Transporte aeroporto", "Transporte do aeroporto ate o hotel", new BigDecimal("120.00"));
			sa2.insert();
			
			var listaServicos = ServicoAdicional.findAll();
			for (ServicoAdicional sa : listaServicos) {
			    System.out.println(sa);
			}
			
			var vendaId = listaVenda.get(0).getId();
			var servicoId = listaServicos.get(1).getId();
			var servec = new ServicoContratado(vendaId, servicoId);
			servec.insert();
			
			var listaServicoContratado = ServicoContratado.findAll();
			for (ServicoContratado sc : listaServicoContratado) {
				System.out.println(sc);
			}
			 
			
			Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
