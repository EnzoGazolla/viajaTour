package com.enzoapps.viajatour.tela.servicoadicional;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EditarServicoAdicional extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNome;
	private JTextField textDescricao;
	private JTextField textPreco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarServicoAdicional dialog = new EditarServicoAdicional();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarServicoAdicional() {
		setTitle("Editar Serviços Adicionais");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNome = new JLabel("Nome:");
			lblNome.setBounds(20, 65, 45, 13);
			contentPanel.add(lblNome);
		}
		{
			JLabel lblDescricao = new JLabel("Descrição:");
			lblDescricao.setBounds(20, 106, 64, 13);
			contentPanel.add(lblDescricao);
		}
		{
			JLabel lblPreco = new JLabel("Preço:");
			lblPreco.setBounds(20, 148, 45, 13);
			contentPanel.add(lblPreco);
		}
		{
			textNome = new JTextField();
			textNome.setBounds(52, 62, 166, 19);
			contentPanel.add(textNome);
			textNome.setColumns(10);
		}
		{
			textDescricao = new JTextField();
			textDescricao.setBounds(70, 103, 148, 19);
			contentPanel.add(textDescricao);
			textDescricao.setColumns(10);
		}
		{
			textPreco = new JTextField();
			textPreco.setBounds(52, 145, 166, 19);
			contentPanel.add(textPreco);
			textPreco.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
