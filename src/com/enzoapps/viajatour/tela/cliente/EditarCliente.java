package com.enzoapps.viajatour.tela.cliente;

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

		txtPassaporte = new JTextField();
		txtPassaporte.setBounds(109, 146, 277, 19);
		contentPanel.add(txtPassaporte);
		txtPassaporte.setColumns(10);

		cbxTipoCliente = new JComboBox<>(TipoCliente.values());
		cbxTipoCliente.setBounds(109, 95, 277, 21);
		contentPanel.add(cbxTipoCliente);
		
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
						c.setNome(txtNome.getText());
						c.setTelefone(txtTelefone.getText());
						c.setEmail(txtEmail.getText());
						c.setTipo((TipoCliente)cbxTipoCliente.getSelectedItem());
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
}
