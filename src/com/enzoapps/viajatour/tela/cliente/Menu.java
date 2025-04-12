package com.enzoapps.viajatour.tela.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setTitle("ViajaTour");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClientes = new JButton("<html><head></head><body>Cadastro <br> Clientes</body></html>");
		btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClientes.setBounds(10, 22, 172, 172);
		contentPane.add(btnClientes);
		
		JButton btncadastroPacotes = new JButton("<html><head></head><body>Cadastro <br> Pacotes</body></html>");
		btncadastroPacotes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btncadastroPacotes.setBounds(210, 22, 172, 172);
		contentPane.add(btncadastroPacotes);
		
		JButton btncadastroServiosAdicionais = new JButton("<html><head></head><body>Cadastro <br> Serviços <br> Adicionais</body></html>");
		btncadastroServiosAdicionais.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btncadastroServiosAdicionais.setBounds(404, 22, 172, 172);
		contentPane.add(btncadastroServiosAdicionais);
		
		JButton btnCadastroTipoPacote = new JButton("<html><head></head><body><center> Cadastro <br> Tipo <br> Pacote </center></body></html>");
		btnCadastroTipoPacote.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCadastroTipoPacote.setBounds(10, 208, 172, 172);
		contentPane.add(btnCadastroTipoPacote);
		
		JButton btnVendaPacote = new JButton("<html><head></head><body><center> Venda <br> Pacote </center></body></html>");
		btnVendaPacote.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVendaPacote.setBounds(210, 208, 172, 172);
		contentPane.add(btnVendaPacote);
		
		JButton btnSobre = new JButton("<html><head></head><body><center> Sobre </center></body></html>");
		btnSobre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSobre.setBounds(404, 208, 172, 172);
		contentPane.add(btnSobre);
	}
}
