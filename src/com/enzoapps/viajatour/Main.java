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
import com.enzoapps.viajatour.tela.Menu;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;
import com.enzoapps.viajatour.util.DBConexao;

/* Classe principal da aplicação ViajaTour.
  Responsável por iniciar o sistema:
  - Criar as tabelas do banco de dados (caso não existam)
  - Carregar dados iniciais de exemplo
  - Abrir a interface gráfica principal (menu)*/
public class Main {

	public static void main(String[] args) {
		
		// Cria as tabelas do banco de dados (estrutura)
		new DBBanco().criarTabela();
		
		// Insere dados de exemplo no banco (carga inicial)
		new DBCarga().carregar();
		
		// Abre a interface gráfica principal da aplicação
		new Menu().setVisible(true);
		
	}
}