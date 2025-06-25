package com.enzoapps.viajatour.tela.venda;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.Contratacao;
import com.enzoapps.viajatour.db.PacoteViagem;

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
						
						if (txtData.getText().isEmpty() || txtData.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "O campo de data esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if (!validarData(txtData.getText())) {
							JOptionPane.showMessageDialog(pai, "A Data esta invalida", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if (txtPreco.getText().isEmpty() || txtPreco.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "O valor esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						var valor = validaValor(txtPreco.getText());
						if (valor.compareTo(BigDecimal.ZERO) == 0) {
							JOptionPane.showMessageDialog(pai, "O valor esta incorreto", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						venda.setClienteId(((Cliente)(cbxCliente.getSelectedItem())).getId());
						venda.setPacoteViagemId(((PacoteViagem)(cbxPacote.getSelectedItem())).getId());
						venda.setDataContratacao(LocalDate.parse(txtData.getText(), formatter));
						venda.setValorTotal(valor);
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
	public  boolean validarData(String dataStr) {
		DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalDate data = LocalDate.parse(dataStr, formatter);
            return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Método para validar se uma String pode ser convertida para BigDecimal
	 * Verifica se a string possui formato numérico válido
	 * Aceita formatos com vírgula ou ponto como separador decimal
	 * @param valor String contendo o valor a ser validado
	 * @return true se a string for um número válido, false caso contrário
	 */
	private BigDecimal validaValor(String valor) {
	    // Verifica se o valor não é nulo ou vazio
	    if (valor == null || valor.trim().isEmpty()) {
	        return new java.math.BigDecimal(0);
	    }
	    
	    // Remove espaços em branco
	    valor = valor.trim();
	    
	    // Substitui vírgula por ponto (padrão brasileiro para internacional)
	    valor = valor.replace(",", ".");
	    
	    // Remove pontos que não sejam o separador decimal
	    // Exemplo: "1.234.567,89" vira "1234567.89"
	    if (valor.contains(".")) {
	        int ultimoPonto = valor.lastIndexOf(".");
	        // Se há mais de um ponto, remove os pontos que não são o último (separadores de milhares)
	        if (valor.indexOf(".") != ultimoPonto) {
	            String parteInteira = valor.substring(0, ultimoPonto).replace(".", "");
	            String parteDecimal = valor.substring(ultimoPonto);
	            valor = parteInteira + parteDecimal;
	        }
	    }
	    
	    try {
	        // Tenta converter para BigDecimal para verificar se é válido
	        java.math.BigDecimal resultado = new java.math.BigDecimal(valor);
	        
	        // Verifica se o valor é negativo (opcional - remova se valores negativos forem permitidos)
	        if (resultado.compareTo(java.math.BigDecimal.ZERO) < 0) {
	            return new java.math.BigDecimal(0);
	        }
	        
	        return resultado;
	        
	    } catch (NumberFormatException e) {
	        // Se não conseguir converter, retorna false
	        return new java.math.BigDecimal(0);
	    }
	}
}
