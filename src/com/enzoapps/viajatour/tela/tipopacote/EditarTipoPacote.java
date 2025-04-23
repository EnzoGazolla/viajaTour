package com.enzoapps.viajatour.tela.tipopacote;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class EditarTipoPacote extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtDescricao;
	private ListarTipoPacote pai;
	public TipoPacote tp = new TipoPacote();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new DBBanco().criarTabela();
			new DBCarga().carregar();
		//	EditarTipoPacote dialog = new EditarTipoPacote();
		//	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//	dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarTipoPacote(ListarTipoPacote pai) {
		this.pai = pai;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 29, 45, 13);
		contentPanel.add(lblNome);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 81, 45, 13);
		contentPanel.add(lblDescricao);
		
		txtNome = new JTextField();
		txtNome.setBounds(65, 26, 350, 19);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(65, 78, 350, 19);
		contentPanel.add(txtDescricao);
		txtDescricao.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						tp.setNome(txtNome.getText());
						tp.setDescricao(txtDescricao.getText());
						if (tp.getId() == null) {
							tp.insert();
						} else {
							tp.update();
						}
						JOptionPane.showMessageDialog(pai, "Registro feito com sucesso","Sucesso", JOptionPane.INFORMATION_MESSAGE);
						EditarTipoPacote.this.dispose();
					}
				});
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
	@Override
	public void dispose() {
		super.dispose();
		pai.carregarDados();
	}
	public void setTipoPacote(TipoPacote tp) {
		this.tp = tp;
		txtNome.setText(tp.getNome());
		txtDescricao.setText(tp.getDescricao());
	}
}
