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
	 * Create the dialog.
	 */
	// Construtor do diálogo
	public EditarTipoPacote(ListarTipoPacote pai) {
		this.pai = pai;
		
		// Configura painel de conteúdo
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		// Rótulo e campo de texto para o nome
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 29, 45, 13);
		contentPanel.add(lblNome);
		
		// Rótulo e campo de texto para a descrição
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 81, 45, 13);
		contentPanel.add(lblDescricao);
		
		// Painel dos botões
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
						

						// Validação dos campos
						if (txtNome.getText().isEmpty() || txtNome.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "O nome esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if (txtDescricao.getText().isEmpty() || txtDescricao.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "A descrição esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						tp.setNome(txtNome.getText());
						tp.setDescricao(txtDescricao.getText());
						if (tp.getId() == null) {
							tp.insert();
						} else {
							tp.update();
						}
						// Mensagem de sucesso e fecha o diálogo
						JOptionPane.showMessageDialog(pai, "Registro feito com sucesso","Sucesso", JOptionPane.INFORMATION_MESSAGE);
						EditarTipoPacote.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	// Fecha a janela e recarrega os dados na tela principal
	@Override
	public void dispose() {
		super.dispose();
		pai.carregarDados();
	}
	// Método para preencher os campos com dados de um TipoPacote existente
	public void setTipoPacote(TipoPacote tp) {
		this.tp = tp;
		txtNome.setText(tp.getNome());
		txtDescricao.setText(tp.getDescricao());
	}
}
