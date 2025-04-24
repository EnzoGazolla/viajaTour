package com.enzoapps.viajatour.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.tela.cliente.ListarCliente;
import com.enzoapps.viajatour.tela.pacoteviagem.ListarPacoteViagem;
import com.enzoapps.viajatour.tela.servicoadicional.ListarServicoAdicional;
import com.enzoapps.viajatour.tela.tipopacote.ListarTipoPacote;
import com.enzoapps.viajatour.tela.venda.ListarVenda;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListarCliente().setVisible(true);
			}
		});
		btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClientes.setBounds(10, 22, 172, 172);
		contentPane.add(btnClientes);
		
		JButton btncadastroPacotes = new JButton("<html><head></head><body>Cadastro <br> Pacotes</body></html>");
		btncadastroPacotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListarPacoteViagem().setVisible(true);
			}
		});
		btncadastroPacotes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btncadastroPacotes.setBounds(210, 22, 172, 172);
		contentPane.add(btncadastroPacotes);
		
		JButton btncadastroServiosAdicionais = new JButton("<html><head></head><body>Cadastro <br> Serviços <br> Adicionais</body></html>");
		btncadastroServiosAdicionais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListarServicoAdicional().setVisible(true);
			}
		});
		btncadastroServiosAdicionais.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btncadastroServiosAdicionais.setBounds(404, 22, 172, 172);
		contentPane.add(btncadastroServiosAdicionais);
		
		JButton btnCadastroTipoPacote = new JButton("<html><head></head><body><center> Cadastro <br> Tipo <br> Pacote </center></body></html>");
		btnCadastroTipoPacote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListarTipoPacote().setVisible(true);
			}
		});
		btnCadastroTipoPacote.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCadastroTipoPacote.setBounds(10, 208, 172, 172);
		contentPane.add(btnCadastroTipoPacote);
		
		JButton btnVendaPacote = new JButton("<html><head></head><body><center> Venda <br> Pacote </center></body></html>");
		btnVendaPacote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListarVenda().setVisible(true);
			}
		});
		btnVendaPacote.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVendaPacote.setBounds(210, 208, 172, 172);
		contentPane.add(btnVendaPacote);
		
		JButton btnSobre = new JButton("<html><head></head><body><center> Sobre </center></body></html>");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Sobre().setVisible(true);
			}
		});
		btnSobre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSobre.setBounds(404, 208, 172, 172);
		contentPane.add(btnSobre);
	}
}
