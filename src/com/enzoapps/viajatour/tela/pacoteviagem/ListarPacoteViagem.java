package com.enzoapps.viajatour.tela.pacoteviagem;

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
import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.tela.cliente.ListarCliente;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarPacoteViagem extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel tableModel;
	private List<PacoteViagem> pacotes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new DBBanco().criarTabela();
			new DBCarga().carregar();
			ListarPacoteViagem dialog = new ListarPacoteViagem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPacoteViagem() {
		setTitle("Listar Pacotes de Viagem");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 26, 404, 196);
		contentPanel.add(scrollPane);

		tableModel = new DefaultTableModel(
				new Object[] { "ID", "Nome", "Descricao", "Destino", "DuracaoDias", "Preco Base", "TipoPacote" }, 0) {
			private static final long serialVersionUID = 1L;
		};

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
		//				new EditarPacoteViagem(ListarPacoteViagem.this).setVisible(true);
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
						    JOptionPane.showMessageDialog(ListarPacoteViagem.this, "Nenhuma opcao selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
						    return;
						}
						
						var selecionado = pacotes.get(linhaSelecionada);
						
						int resposta = JOptionPane.showConfirmDialog(ListarPacoteViagem.this, "Deseja excluir?", "Confirmar exclusao", 
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (resposta == JOptionPane.YES_OPTION) {
							try {
								selecionado.delete();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(ListarPacoteViagem.this, "Esse registro nao pode ser excluido", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
							JOptionPane.showMessageDialog(ListarPacoteViagem.this, "Excluido com sucesso", "Sucesso", 
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
		pacotes = PacoteViagem.findAll();
		for (PacoteViagem pacote : pacotes) {
			tableModel.addRow(new Object[] { pacote.getId(), pacote.getNome(), pacote.getDescricao(),
					pacote.getDestino(), pacote.getDuracaoDias(), pacote.getPrecoBase(),
					TipoPacote.findById(pacote.getTipoPacoteId()).getNome() });
		}
	}
}
