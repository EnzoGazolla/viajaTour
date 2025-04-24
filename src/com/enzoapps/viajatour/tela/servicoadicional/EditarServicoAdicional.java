package com.enzoapps.viajatour.tela.servicoadicional;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.db.ServicoAdicional;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.EditarTipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.ListarTipoPacote;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class EditarServicoAdicional extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNome;
	private JTextField textDescricao;
	private JTextField textPreco;
	private ListarServicoAdicional pai;
	public ServicoAdicional sa = new ServicoAdicional();

	/**
	 * Create the dialog.
	 */
	public EditarServicoAdicional(ListarServicoAdicional pai) {
		this.pai = pai;
		
		setTitle("Editar Serviços Adicionais");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNome = new JLabel("Nome:");
			lblNome.setBounds(20, 65, 61, 13);
			contentPanel.add(lblNome);
		}
		{
			JLabel lblDescricao = new JLabel("Descrição:");
			lblDescricao.setBounds(20, 106, 76, 13);
			contentPanel.add(lblDescricao);
		}
		{
			JLabel lblPreco = new JLabel("Preço:");
			lblPreco.setBounds(20, 148, 61, 13);
			contentPanel.add(lblPreco);
		}
		{
			textNome = new JTextField();
			textNome.setBounds(117, 62, 288, 19);
			contentPanel.add(textNome);
			textNome.setColumns(10);
		}
		{
			textDescricao = new JTextField();
			textDescricao.setBounds(117, 103, 288, 19);
			contentPanel.add(textDescricao);
			textDescricao.setColumns(10);
		}
		{
			textPreco = new JTextField();
			textPreco.setBounds(117, 145, 288, 19);
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						sa.setNome(textNome.getText());
						sa.setDescricao(textDescricao.getText());
						sa.setPreco(new BigDecimal(textPreco.getText()));
						if (sa.getId() == null) {
							sa.insert();
						} else {
							sa.update();
						}
						JOptionPane.showMessageDialog(pai, "Registro feito com sucesso","Sucesso", JOptionPane.INFORMATION_MESSAGE);
						EditarServicoAdicional.this.dispose();
						
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
	public void setServicoAdicional(ServicoAdicional sa) {
		this.sa = sa;
		textNome.setText(sa.getNome());
		textDescricao.setText(sa.getDescricao());
		textPreco.setText(sa.getPreco().toString());
	}

}
