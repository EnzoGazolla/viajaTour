package com.enzoapps.viajatour.tela.venda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.Contratacao;
import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.tela.pacoteviagem.EditarPacoteViagem;
import com.enzoapps.viajatour.tela.pacoteviagem.ListarPacoteViagem;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class EditarVenda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtData;
	private JTextField txtPreco;
	private JComboBox<Cliente> cbxCliente;
	private JComboBox<PacoteViagem> cbxPacote;
	private ListarVenda pai;
	public Contratacao venda = new Contratacao();
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Create the dialog.
	 */
	public EditarVenda(ListarVenda pai) {
		this.pai = pai;
		
		setTitle("Editar Vendas");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(20, 46, 68, 13);
		contentPanel.add(lblCliente);
		
		JLabel lblPacoteViagem = new JLabel("PacoteViagem:");
		lblPacoteViagem.setBounds(20, 82, 87, 13);
		contentPanel.add(lblPacoteViagem);
		
		JLabel lblDataContratacao = new JLabel("Data:");
		lblDataContratacao.setBounds(20, 115, 68, 13);
		contentPanel.add(lblDataContratacao);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setBounds(20, 159, 87, 13);
		contentPanel.add(lblValorTotal);
		
		txtData = new JTextField();
		txtData.setBounds(126, 112, 258, 19);
		contentPanel.add(txtData);
		txtData.setColumns(10);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(126, 156, 258, 19);
		contentPanel.add(txtPreco);
		txtPreco.setColumns(10);
		
		cbxPacote = new JComboBox<PacoteViagem>();
		carregarPacote();
		cbxPacote.setBounds(126, 78, 258, 21);
		contentPanel.add(cbxPacote);
		
		cbxCliente = new JComboBox<Cliente>();
		carregarCliente();
		cbxCliente.setBounds(126, 42, 258, 21);
		contentPanel.add(cbxCliente);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						venda.setClienteId(((Cliente)(cbxCliente.getSelectedItem())).getId());
						venda.setPacoteViagemId(((PacoteViagem)(cbxPacote.getSelectedItem())).getId());
						venda.setDataContratacao(LocalDate.parse(txtData.getText(), formatter));
						venda.setValorTotal(new BigDecimal(txtPreco.getText()));
						if (venda.getId() == null) {
							venda.insert();
						} else {
							venda.update();
						}
						JOptionPane.showMessageDialog(pai, "Registro feito com sucesso","Sucesso", JOptionPane.INFORMATION_MESSAGE);
						EditarVenda.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	@Override
	public void dispose() {
		super.dispose();
		pai.carregarDados();
	}
	public void setContratacao(Contratacao venda) {
		this.venda = venda;
		cbxCliente.setSelectedItem(Cliente.findById(venda.getClienteId()));
		cbxPacote.setSelectedItem(PacoteViagem.findById(venda.getPacoteViagemId()));
		txtData.setText(venda.getDataContratacao().format(formatter));
		txtPreco.setText(venda.getValorTotal().toString());
		
	}
	public void carregarCliente() {
		var clientes = Cliente.findAll();
		DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<>();
		for (Cliente cliente : clientes) {
			model.addElement(cliente);
		}
		cbxCliente.setModel(model);
	}
	public void carregarPacote() {
		var pacotes = PacoteViagem.findAll();
		DefaultComboBoxModel<PacoteViagem> model = new DefaultComboBoxModel<>();
		for (PacoteViagem pacote : pacotes) {
			model.addElement(pacote);
		}
		cbxPacote.setModel(model);
	}
}
