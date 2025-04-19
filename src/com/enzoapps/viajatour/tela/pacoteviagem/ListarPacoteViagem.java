package com.enzoapps.viajatour.tela.pacoteviagem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		
		tableModel = new DefaultTableModel (new Object[] {"ID", "Nome", "Descricao", "Destino", "DuracaoDias", "Preco Base", "TipoPacote"}, 0 ){
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
		
		pacotes = PacoteViagem.findAll();
		for (PacoteViagem pacote : pacotes) {
			tableModel.addRow(new Object[] {pacote.getId(), pacote.getNome(), pacote.getDescricao(), pacote.getDestino(), pacote.getDuracaoDias(), 
					pacote.getPrecoBase(), TipoPacote.findById(pacote.getTipoPacoteId()).getNome()});
		}
	}
}
