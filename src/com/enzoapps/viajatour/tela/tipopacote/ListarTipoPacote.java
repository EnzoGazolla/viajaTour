package com.enzoapps.viajatour.tela.tipopacote;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.enzoapps.viajatour.db.ServicoAdicional;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarTipoPacote extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel tableModel;
	private List<TipoPacote> tipoPacote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new DBBanco().criarTabela();
			new DBCarga().carregar();
			ListarTipoPacote dialog = new ListarTipoPacote();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarTipoPacote() {
		setTitle("Listar Tipos de Pacote");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 22, 436, 210);
			contentPanel.add(scrollPane);
			{
				tableModel = new DefaultTableModel(new Object[] {"ID", "Nome", "Descricao"}, 0) {
					private static final long serialVersionUID = 1L;
				};
						
				table = new JTable(tableModel);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton btnNovo = new JButton("Novo");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new EditarTipoPacote(ListarTipoPacote.this).setVisible(true);
					}
				});
				buttonPane.add(btnNovo);
			}
			{
				JButton btnEditar = new JButton("Editar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				buttonPane.add(btnEditar);
			}
			{
				JButton btnExcluir = new JButton("Excluir");
				btnExcluir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				buttonPane.add(btnExcluir);
			}
		}
		carregarDados();
	}

	public void carregarDados() {
		tableModel.setRowCount(0);
		tipoPacote = TipoPacote.findAll();
		for (TipoPacote tipoPacotes : tipoPacote) {
			tableModel.addRow(new Object[] {tipoPacotes.getId(), tipoPacotes.getNome(), tipoPacotes.getDescricao() });
		}
	}

}
