package com.enzoapps.viajatour.tela.cliente;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.TipoCliente;
import com.enzoapps.viajatour.tela.tipopacote.EditarTipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.ListarTipoPacote;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private List<Cliente> clientes;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new DBBanco().criarTabela();
			new DBCarga().carregar();
			ListarCliente dialog = new ListarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarCliente() {
		setTitle("Listar Clientes");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 436, 0 };
		gbl_contentPanel.rowHeights = new int[] { 222, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		tableModel = new DefaultTableModel(
				new Object[] { "ID", "Nome", "Telefone", "Email", "Tipo", "CPF", "Passaporte" }, 0) {
			private static final long serialVersionUID = 1L;
		};

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPanel.add(scrollPane, gbc_scrollPane);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton btnNovo = new JButton("Novo");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new EditarCliente(ListarCliente.this).setVisible(true);
					}
				});
				buttonPane.add(btnNovo);
			}
			{
				JButton btnEditar = new JButton("Editar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int linhaSelecionada = table.getSelectedRow();

						if (linhaSelecionada == -1) {
						    JOptionPane.showMessageDialog(ListarCliente.this, "Nenhum cliente selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
						    return;
						}
						
						var selecionado = clientes.get(linhaSelecionada);
						var editar = new EditarCliente(ListarCliente.this);
						editar.setCliente(selecionado);
						editar.setVisible(true);
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
						    JOptionPane.showMessageDialog(ListarCliente.this, "Nenhuma opcao selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
						    return;
						}
						
						var selecionado = clientes.get(linhaSelecionada);
						
						int resposta = JOptionPane.showConfirmDialog(ListarCliente.this, "Deseja excluir?", "Confirmar exclusao", 
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (resposta == JOptionPane.YES_OPTION) {
							try {
								selecionado.delete();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(ListarCliente.this, "Esse registro nao pode ser excluido", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
							JOptionPane.showMessageDialog(ListarCliente.this, "Excluido com sucesso", "Sucesso", 
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
		System.out.println("Lista Cliente");
		tableModel.setRowCount(0);
		clientes = Cliente.findAll();
		for (Cliente cliente : clientes) {
			tableModel.addRow(new Object[] { cliente.getId(), cliente.getNome(), cliente.getTelefone(),
					cliente.getEmail(), cliente.getTipo().getDescricao(), cliente.getCpf(), cliente.getPassaporte() });
			System.out.println(cliente);
		}
	}
}
