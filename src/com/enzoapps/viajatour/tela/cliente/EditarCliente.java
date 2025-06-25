package com.enzoapps.viajatour.tela.cliente;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.TipoCliente;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.EditarTipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.ListarTipoPacote;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtCPF;
	private JTextField txtPassaporte;
	private JComboBox<TipoCliente> cbxTipoCliente;
	private ListarCliente pai;
	public Cliente c = new Cliente();

	/**
	 * Create the dialog.
	 */
	public EditarCliente(ListarCliente pai) {
		this.pai = pai;

		setTitle("Editar Clientes");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		txtNome = new JTextField();
		txtNome.setBounds(109, 24, 277, 19);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 53, 73, 13);
		contentPanel.add(lblTelefone);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 76, 73, 13);
		contentPanel.add(lblEmail);

		JLabel lblTipoCliente = new JLabel("Tipo do Cliente:");
		lblTipoCliente.setBounds(10, 99, 89, 13);
		contentPanel.add(lblTipoCliente);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(10, 126, 45, 13);
		contentPanel.add(lblCPF);

		JLabel lblPassaporte = new JLabel("Passaporte:");
		lblPassaporte.setBounds(10, 149, 73, 13);
		contentPanel.add(lblPassaporte);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(109, 50, 277, 19);
		contentPanel.add(txtTelefone);
		txtTelefone.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(109, 73, 277, 19);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);

		txtCPF = new JTextField();
		txtCPF.setBounds(109, 123, 277, 19);
		contentPanel.add(txtCPF);
		txtCPF.setColumns(10);
		txtCPF.setDisabledTextColor(Color.DARK_GRAY);
		txtCPF.setEnabled(true);

		txtPassaporte = new JTextField();
		txtPassaporte.setBounds(109, 146, 277, 19);
		contentPanel.add(txtPassaporte);
		txtPassaporte.setColumns(10);
		txtPassaporte.setDisabledTextColor(Color.DARK_GRAY);
		txtPassaporte.setEnabled(false);

		cbxTipoCliente = new JComboBox<>(TipoCliente.values());
		cbxTipoCliente.setBounds(109, 95, 277, 21);
		contentPanel.add(cbxTipoCliente);
		cbxTipoCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbxTipoCliente.getSelectedItem().equals(TipoCliente.NACIONAL)) {
					
					txtPassaporte.setEnabled(false);
					txtCPF.setEnabled(true);
				} else {
					txtPassaporte.setEnabled(true);
					txtCPF.setEnabled(false);
				}
			}
		});

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 27, 73, 13);
		contentPanel.add(lblNome);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (txtNome.getText().isEmpty() || txtNome.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "O nome esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (txtTelefone.getText().isEmpty() || txtTelefone.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "O telefone esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (txtEmail.getText().isEmpty() || txtEmail.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "O email esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if (cbxTipoCliente.getSelectedItem().equals(TipoCliente.NACIONAL)) {
							if (txtCPF.getText().isEmpty() || txtCPF.getText().isBlank()) {
								JOptionPane.showMessageDialog(pai, "O CPF esta em branco", "Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							} 
						}
						if (cbxTipoCliente.getSelectedItem().equals(TipoCliente.ESTRANGEIRO)) {
							if (txtPassaporte.getText().isEmpty() || txtPassaporte.getText().isBlank()) {
								JOptionPane.showMessageDialog(pai, "O passaporte esta em branco", "Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							} 
						}
						
						
						/* Validação*/
						
						if (cbxTipoCliente.getSelectedItem().equals(TipoCliente.NACIONAL)) {
							if (!validarCPF(txtCPF.getText())) {
								JOptionPane.showMessageDialog(pai, "Este CPF invalido", "Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							} 
						}
						if (!validarEmail(txtEmail.getText())) {
							JOptionPane.showMessageDialog(pai, "Este Email invalido", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						c.setNome(txtNome.getText());
						c.setTelefone(txtTelefone.getText());
						c.setEmail(txtEmail.getText());
						c.setTipo((TipoCliente) cbxTipoCliente.getSelectedItem());
						c.setCpf(txtCPF.getText());
						c.setPassaporte(txtPassaporte.getText());
						if (c.getId() == null) {
							c.insert();
						} else {
							c.update();
						}
						JOptionPane.showMessageDialog(pai, "Cliente Cadastrado com Sucesso", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						EditarCliente.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		pai.carregarDados();
	}

	public void setCliente(Cliente c) {
		this.c = c;
		txtNome.setText(c.getNome());
		txtTelefone.setText(c.getTelefone());
		txtEmail.setText(c.getEmail());
		txtCPF.setText(c.getCpf());
		txtPassaporte.setText(c.getPassaporte());
		cbxTipoCliente.setSelectedItem(c.getTipo());
	}

	/**
	 * Método para validar CPF brasileiro Verifica se o CPF possui 11 dígitos e se
	 * os dígitos verificadores estão corretos
	 * 
	 * @param cpf String contendo o CPF a ser validado (pode conter pontos e hífen)
	 * @return true se o CPF for válido, false caso contrário
	 */
	private boolean validarCPF(String cpf) {
		// Remove caracteres não numéricos (pontos e hífen)
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verifica se o CPF tem 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Verifica se todos os dígitos são iguais (casos inválidos como 111.111.111-11)
		if (cpf.matches("(\\d)\\1{10}")) {
			return false;
		}

		// Calcula o primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		int primeiroDigito = 11 - (soma % 11);
		if (primeiroDigito >= 10) {
			primeiroDigito = 0;
		}

		// Verifica se o primeiro dígito verificador está correto
		if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
			return false;
		}

		// Calcula o segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		int segundoDigito = 11 - (soma % 11);
		if (segundoDigito >= 10) {
			segundoDigito = 0;
		}

		// Verifica se o segundo dígito verificador está correto
		return Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
	}
	
	/*
	 * Método para validar endereço de email
	 * Verifica se o email possui formato válido usando regex
	 * @param email String contendo o email a ser validado
	 * @return true se o email for válido, false caso contrário
	 */
	private boolean validarEmail(String email) {
	    // Verifica se o email não é nulo ou vazio
	    if (email == null || email.trim().isEmpty()) {
	        return false;
	    }
	    
	    // Regex para validação de email
	    // Aceita letras, números, pontos, hífens e underscores antes do @
	    // Exige pelo menos um ponto após o @ para o domínio
	    String regexEmail = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    
	    // Verifica se o email corresponde ao padrão regex
	    return email.matches(regexEmail);
	}
}
