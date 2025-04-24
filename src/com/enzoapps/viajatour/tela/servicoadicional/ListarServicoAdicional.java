package com.enzoapps.viajatour.tela.servicoadicional;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.enzoapps.viajatour.db.ServicoAdicional;
import com.enzoapps.viajatour.tela.cliente.ListarCliente;
import com.enzoapps.viajatour.tela.tipopacote.EditarTipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.ListarTipoPacote;
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
					 new EditarServicoAdicional(ListarServicoAdicional.this).setVisible(true);
				}
			});
			buttonPane.add(btnNovo);

			JButton btnEditar = new JButton("Editar");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int linhaSelecionada = table.getSelectedRow();

					if (linhaSelecionada == -1) {
					    JOptionPane.showMessageDialog(ListarServicoAdicional.this, "Nenhum serviço selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
					    return;
					}
					
					var selecionado = servicos.get(linhaSelecionada);
					var editar = new EditarServicoAdicional(ListarServicoAdicional.this);
					editar.setServicoAdicional(selecionado);
					editar.setVisible(true);
				}
			});
			buttonPane.add(btnEditar);

			JButton btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int linhaSelecionada = table.getSelectedRow();

					if (linhaSelecionada == -1) {
					    JOptionPane.showMessageDialog(ListarServicoAdicional.this, "Nenhuma opcao selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
					    return;
					}
					
					var selecionado = servicos.get(linhaSelecionada);
					
					int resposta = JOptionPane.showConfirmDialog(ListarServicoAdicional.this, "Deseja excluir?", "Confirmar exclusao", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						try {
							selecionado.delete();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(ListarServicoAdicional.this, "Esse registro nao pode ser excluido", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						}
						JOptionPane.showMessageDialog(ListarServicoAdicional.this, "Excluido com sucesso", "Sucesso", 
								JOptionPane.INFORMATION_MESSAGE);
						carregarDados();
					}
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
