package com.enzoapps.viajatour.tela.servicoadicional;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.enzoapps.viajatour.db.ServicoAdicional;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarServicoAdicional extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel tableModel;
	private List<ServicoAdicional> servicos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new DBBanco().criarTabela();
			new DBCarga().carregar();
			ListarServicoAdicional dialog = new ListarServicoAdicional();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarServicoAdicional() {
		setTitle("Listar Serviços Adicionais");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 436, 212);
		contentPanel.add(scrollPane);

		tableModel = new DefaultTableModel(new Object[] { "ID", "Nome", "Descricao", "Preco" }, 0) {
			private static final long serialVersionUID = 1L;
		};

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);

			JButton btnNovo = new JButton("Novo");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// new EditarServicoAdicional(ListarServicoAdicional.this).setVisible(true);
				}
			});
			buttonPane.add(btnNovo);

			JButton btnEditar = new JButton("Editar");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			buttonPane.add(btnEditar);

			JButton btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			buttonPane.add(btnExcluir);
		}
		carregarDados();

	}

	public void carregarDados() {
		tableModel.setRowCount(0);
		servicos = ServicoAdicional.findAll();
		for (ServicoAdicional servico : servicos) {
			tableModel.addRow(
					new Object[] { servico.getId(), servico.getNome(), servico.getDescricao(), servico.getPreco() });
		}
	}
}
