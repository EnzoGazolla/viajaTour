package com.enzoapps.viajatour.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexao {
	
	public Connection criarConexao() {
		
		Connection con = null;
		
		try {
			Class.forName("org.h2.Driver");
			 con = DriverManager.getConnection("jdbc:h2:mem:viajaTour;DB_CLOSE_DELAY=-1", "sa", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void fecharConexao(Connection con) {
		
		try {
			if (con != null || !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
