package com.enzoapps.viajatour.tela.pacoteviagem;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditarPacoteViagem extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNome;
	private JTextField textDescricao;
	private JTextField textDestino;
	private JTextField textDuracaoDias;
	private JTextField textPrecoBase;
	private JTextField textTipoPacote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarPacoteViagem dialog = new EditarPacoteViagem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarPacoteViagem() {
		setTitle("Editar Pacote de Viagem");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNome = new JLabel("Nome:");
			lblNome.setBounds(10, 42, 45, 13);
			contentPanel.add(lblNome);
		}
		{
			JLabel lblDescricao = new JLabel("Descrição:");
			lblDescricao.setBounds(10, 65, 56, 13);
			contentPanel.add(lblDescricao);
		}
		{
			JLabel lblDestino = new JLabel("Destino:");
			lblDestino.setBounds(10, 88, 45, 13);
			contentPanel.add(lblDestino);
		}
		{
			JLabel lblDuracaoDias = new JLabel("Duração de Dias:");
			lblDuracaoDias.setBounds(10, 111, 80, 13);
			contentPanel.add(lblDuracaoDias);
		}
		{
			JLabel lblPrecoBase = new JLabel("Preço Base:");
			lblPrecoBase.setBounds(10, 134, 64, 13);
			contentPanel.add(lblPrecoBase);
		}
		{
			JLabel lblTipoPacote = new JLabel("Tipo de Pacote:");
			lblTipoPacote.setBounds(10, 157, 80, 13);
			contentPanel.add(lblTipoPacote);
		}
		{
			textNome = new JTextField();
			textNome.setBounds(44, 39, 266, 19);
			contentPanel.add(textNome);
			textNome.setColumns(10);
		}
		{
			textDescricao = new JTextField();
			textDescricao.setBounds(65, 62, 245, 19);
			contentPanel.add(textDescricao);
			textDescricao.setColumns(10);
		}
		{
			textDestino = new JTextField();
			textDestino.setBounds(65, 85, 245, 19);
			contentPanel.add(textDestino);
			textDestino.setColumns(10);
		}
		{
			textDuracaoDias = new JTextField();
			textDuracaoDias.setBounds(89, 108, 221, 19);
			contentPanel.add(textDuracaoDias);
			textDuracaoDias.setColumns(10);
		}
		{
			textPrecoBase = new JTextField();
			textPrecoBase.setBounds(65, 131, 245, 19);
			contentPanel.add(textPrecoBase);
			textPrecoBase.setColumns(10);
		}
		{
			textTipoPacote = new JTextField();
			textTipoPacote.setBounds(89, 154, 221, 19);
			contentPanel.add(textTipoPacote);
			textTipoPacote.setColumns(10);
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
