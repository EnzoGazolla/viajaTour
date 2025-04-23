package com.enzoapps.viajatour.tela.venda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.Contratacao;
import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.tela.cliente.ListarCliente;
import com.enzoapps.viajatour.tela.tipopacote.EditarTipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.ListarTipoPacote;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarVenda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel tableModel;
	private List<Contratacao> vendas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new DBBanco().criarTabela();
			new DBCarga().carregar();
			ListarVenda dialog = new ListarVenda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarVenda() {
		setTitle("Listar Vendas");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 10, 436, 222);
			contentPanel.add(scrollPane);
			{

				tableModel = new DefaultTableModel(
						new Object[] { "ID", "ClienteID", "PacoteViagemID", "DataContratacao", "ValorTotal" }, 0) {
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
	//					new EditarVenda(ListarVenda.this).setVisible(true);
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
						int linhaSelecionada = table.getSelectedRow();

						if (linhaSelecionada == -1) {
						    JOptionPane.showMessageDialog(ListarVenda.this, "Nenhuma opcao selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
						    return;
						}
						
						var selecionado = vendas.get(linhaSelecionada);
						
						int resposta = JOptionPane.showConfirmDialog(ListarVenda.this, "Deseja excluir?", "Confirmar exclusao", 
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (resposta == JOptionPane.YES_OPTION) {
							try {
								selecionado.delete();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(ListarVenda.this, "Esse registro nao pode ser excluido", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
							JOptionPane.showMessageDialog(ListarVenda.this, "Excluido com sucesso", "Sucesso", 
									JOptionPane.INFORMATION_MESSAGE);
							carregarDados();
						}
					}
				});
				buttonPane.add(btnExcluir);
			}
		}
		carregarDados();
	}

	public void carregarDados() {
		tableModel.setRowCount(0);
		vendas = Contratacao.findAll();
		for (Contratacao contratacao : vendas) {
			tableModel
					.addRow(new Object[] { contratacao.getId(), Cliente.findById(contratacao.getClienteId()).getNome(),
							PacoteViagem.findById(contratacao.getPacoteViagemId()).getNome(),
							contratacao.getDataContratacao(), contratacao.getValorTotal() });
		}

	}

}
