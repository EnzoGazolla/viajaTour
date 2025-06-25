package com.enzoapps.viajatour.tela.servicoadicional;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.db.ServicoAdicional;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.EditarTipoPacote;
import com.enzoapps.viajatour.tela.tipopacote.ListarTipoPacote;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class EditarServicoAdicional extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNome;
	private JTextField textDescricao;
	private JTextField textPreco;
	private ListarServicoAdicional pai;
	public ServicoAdicional sa = new ServicoAdicional();

	/**
	 * Create the dialog.
	 */
	 // Construtor do diálogo de edição/criação de serviço adicional
	public EditarServicoAdicional(ListarServicoAdicional pai) {
		this.pai = pai;
		
		setTitle("Editar Serviços Adicionais");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
			// Label para campo Nome
		{
			JLabel lblNome = new JLabel("Nome:");
			lblNome.setBounds(20, 65, 61, 13);
			contentPanel.add(lblNome);
		}
			// Label para campo Descrição
		{
			JLabel lblDescricao = new JLabel("Descrição:");
			lblDescricao.setBounds(20, 106, 76, 13);
			contentPanel.add(lblDescricao);
		}
			// Label para campo Preço
		{
			JLabel lblPreco = new JLabel("Preço:");
			lblPreco.setBounds(20, 148, 61, 13);
			contentPanel.add(lblPreco);
		}
			// Campo de texto para Nome
		{
			textNome = new JTextField();
			textNome.setBounds(117, 62, 288, 19);
			contentPanel.add(textNome);
			textNome.setColumns(10);
		}
		 	// Campo de texto para Descrição
		{
			textDescricao = new JTextField();
			textDescricao.setBounds(117, 103, 288, 19);
			contentPanel.add(textDescricao);
			textDescricao.setColumns(10);
		}
			// Campo de texto para Preço
		{
			textPreco = new JTextField();
			textPreco.setBounds(117, 145, 288, 19);
			contentPanel.add(textPreco);
			textPreco.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// Valida se o campo Descrição não está vazio
						if (textNome.getText().isEmpty() || textNome.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "O Nome esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if (textDescricao.getText().isEmpty() || textDescricao.getText().isBlank()) {
							JOptionPane.showMessageDialog(pai, "A Descricao esta em branco", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						// Valida o valor do preço
						var valor = validaValor(textPreco.getText());
						if (valor.compareTo(BigDecimal.ZERO) == 0) {
							JOptionPane.showMessageDialog(pai, "O valor do preco esta incorreto", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						// Atualiza os dados do serviço adicional
						sa.setNome(textNome.getText());
						sa.setDescricao(textDescricao.getText());
						sa.setPreco(valor);
						if (sa.getId() == null) {
							sa.insert();
						} else {
							sa.update();
						}
						JOptionPane.showMessageDialog(pai, "Registro feito com sucesso","Sucesso", JOptionPane.INFORMATION_MESSAGE);
						EditarServicoAdicional.this.dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	// Ao fechar o diálogo, recarrega os dados na tela pai
	@Override
	public void dispose() {
		super.dispose();
		pai.carregarDados();
	}
	// Método para preencher os campos do formulário com os dados do serviço adicional selecionado
	public void setServicoAdicional(ServicoAdicional sa) {
		this.sa = sa;
		textNome.setText(sa.getNome());
		textDescricao.setText(sa.getDescricao());
		textPreco.setText(sa.getPreco().toString());
	}
	
	/* Método para validar se uma String pode ser convertida para BigDecimal
	  Verifica se a string possui formato numérico válido
	  Aceita formatos com vírgula ou ponto como separador decimal
	  @param valor String contendo o valor a ser validado
	  @return true se a string for um número válido, false caso contrário */
	
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
