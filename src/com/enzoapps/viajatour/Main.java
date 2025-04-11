package com.enzoapps.viajatour;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;

import org.h2.tools.Server;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.Contratacao;
import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.ServicoAdicional;
import com.enzoapps.viajatour.db.ServicoContratado;
import com.enzoapps.viajatour.db.TipoCliente;
import com.enzoapps.viajatour.db.TipoPacote;
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
			
			var pc1 = new PacoteViagem("Viagem pra nova york", "estadia no ymca", "New York, NY",20, new BigDecimal(8000), tpacote.get(2).getId());
			pc1.insert();
			
			var listapc = PacoteViagem.findAll();
			for (PacoteViagem pacoteViagem : listapc) {
				System.out.println(pacoteViagem);
			}
			
			var clienteId = lista.get(0).getId();
			var pacoteId = listapc.get(0).getId();
			var pacotePreco = listapc.get(0).getPrecoBase();
			var venda = new Contratacao(clienteId, pacoteId, LocalDate.now(), pacotePreco);
			venda.insert();
			
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