package com.enzoapps.viajatour.tela.venda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.db.Cliente;
import com.enzoapps.viajatour.db.Contratacao;
import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.ServicoAdicional;
import com.enzoapps.viajatour.db.ServicoContratado;

public class EditarVendaComAbas extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    
    // Componentes da aba Pacote
    private JTextField txtData;
    private JTextField txtPreco;
    private JComboBox<Cliente> cbxCliente;
    private JComboBox<PacoteViagem> cbxPacote;
    private ListarVenda pai; // Referência para a tela pai (lista)
    public Contratacao venda = new Contratacao(); // Objeto da venda a ser editado
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    // Componentes da aba Dual List
    private JList<ServicoAdicional> listDisponivel;
    private JList<ServicoAdicional> listSelecionado;
    private DefaultListModel<ServicoAdicional> modelDisponivel;
    private DefaultListModel<ServicoAdicional> modelSelecionado;
    private JButton btnIncluirTodos;
    private JButton btnRemoverTodos;
    private JButton btnIncluir;
    private JButton btnRemover;

    
    /**
     * Create the dialog.
     */
    public EditarVendaComAbas(ListarVenda pai) {
        this.pai = pai;
        initializeComponent();
    }
    
    private void initializeComponent() {
        setTitle("Editar Vendas - Completo");
        setModal(true);
        setBounds(100, 100, 650, 450);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        // Criar o JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPanel.add(tabbedPane, BorderLayout.CENTER);
        
        // Inicializar as abas
        initializePacoteTab(tabbedPane);
        initializeDualListTab(tabbedPane);
        
        // Panel dos botões principais
        initializeButtonPanel();
        
        // Carregar dados iniciais
        if (pai != null) {
            carregarCliente();
            carregarPacote();
            carregarServicoContratado();
        }
        popularDadosExemplo();
    }
    
    private void carregarServicoContratado() {
    	var idPacote = venda.getId();
    	var servicos  = ServicoContratado.findByPacoteViagem(idPacote);
		for (ServicoContratado servico : servicos) {
			var sa = ServicoAdicional.findById(servico.getServicoId());
			modelSelecionado.addElement(sa);
		}
		
	}

	private void initializePacoteTab(JTabbedPane tabbedPane) {
        JPanel panelPacote = new JPanel();
        tabbedPane.addTab("Pacote", null, panelPacote, null);
        panelPacote.setLayout(null);
        
        // Rótulos
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(20, 46, 68, 13);
        panelPacote.add(lblCliente);
        
        JLabel lblPacoteViagem = new JLabel("PacoteViagem:");
        lblPacoteViagem.setBounds(20, 82, 87, 13);
        panelPacote.add(lblPacoteViagem);
        
        JLabel lblDataContratacao = new JLabel("Data:");
        lblDataContratacao.setBounds(20, 115, 68, 13);
        panelPacote.add(lblDataContratacao);
        
        JLabel lblValorTotal = new JLabel("Valor Total:");
        lblValorTotal.setBounds(20, 159, 87, 13);
        panelPacote.add(lblValorTotal);
        
        // Campo de Data
        txtData = new JTextField();
        txtData.setBounds(126, 112, 258, 19);
        panelPacote.add(txtData);
        txtData.setColumns(10);
        
        // Campo de Preco
        txtPreco = new JTextField();
        txtPreco.setBounds(126, 156, 258, 19);
        panelPacote.add(txtPreco);
        txtPreco.setColumns(10);
        
        // ComboBox de Pacotes
        cbxPacote = new JComboBox<PacoteViagem>();
        cbxPacote.setBounds(126, 78, 258, 21);
        panelPacote.add(cbxPacote);
        
        // ComboBox de Clientes
        cbxCliente = new JComboBox<Cliente>();
        cbxCliente.setBounds(126, 42, 258, 21);
        panelPacote.add(cbxCliente);
    }
    
    private void initializeDualListTab(JTabbedPane tabbedPane) {
        JPanel panelDualList = new JPanel();
        tabbedPane.addTab("Seleção de Itens", null, panelDualList, null);
        
        // Inicializar os modelos das listas
        modelDisponivel = new DefaultListModel<>();
        modelSelecionado = new DefaultListModel<>();
        
        
        GridBagLayout gbl_panelDualList = new GridBagLayout();
        gbl_panelDualList.columnWidths = new int[]{200, 80, 200, 0};
        gbl_panelDualList.rowHeights = new int[]{30, 250, 0};
        gbl_panelDualList.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panelDualList.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        panelDualList.setLayout(gbl_panelDualList);
        
        // Label para lista disponível
        JLabel lblDisponivel = new JLabel("Itens Disponíveis:");
        GridBagConstraints gbc_lblDisponivel = new GridBagConstraints();
        gbc_lblDisponivel.anchor = GridBagConstraints.WEST;
        gbc_lblDisponivel.insets = new Insets(0, 0, 5, 5);
        gbc_lblDisponivel.gridx = 0;
        gbc_lblDisponivel.gridy = 0;
        panelDualList.add(lblDisponivel, gbc_lblDisponivel);
        
        // Label para lista selecionada
        JLabel lblSelecionado = new JLabel("Itens Selecionados:");
        GridBagConstraints gbc_lblSelecionado = new GridBagConstraints();
        gbc_lblSelecionado.anchor = GridBagConstraints.WEST;
        gbc_lblSelecionado.insets = new Insets(0, 0, 5, 0);
        gbc_lblSelecionado.gridx = 2;
        gbc_lblSelecionado.gridy = 0;
        panelDualList.add(lblSelecionado, gbc_lblSelecionado);
        
        // Lista disponível com scroll
        listDisponivel = new JList<>(modelDisponivel);
        listDisponivel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPaneDisponivel = new JScrollPane(listDisponivel);
        GridBagConstraints gbc_scrollPaneDisponivel = new GridBagConstraints();
        gbc_scrollPaneDisponivel.fill = GridBagConstraints.BOTH;
        gbc_scrollPaneDisponivel.insets = new Insets(0, 0, 0, 5);
        gbc_scrollPaneDisponivel.gridx = 0;
        gbc_scrollPaneDisponivel.gridy = 1;
        panelDualList.add(scrollPaneDisponivel, gbc_scrollPaneDisponivel);
        
        // Panel com botões centrais
        JPanel panelBotoes = new JPanel();
        GridBagConstraints gbc_panelBotoes = new GridBagConstraints();
        gbc_panelBotoes.fill = GridBagConstraints.VERTICAL;
        gbc_panelBotoes.insets = new Insets(0, 0, 0, 5);
        gbc_panelBotoes.gridx = 1;
        gbc_panelBotoes.gridy = 1;
        panelDualList.add(panelBotoes, gbc_panelBotoes);
        
        GridBagLayout gbl_panelBotoes = new GridBagLayout();
        gbl_panelBotoes.columnWidths = new int[]{80, 0};
        gbl_panelBotoes.rowHeights = new int[]{30, 30, 30, 30, 0};
        gbl_panelBotoes.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panelBotoes.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelBotoes.setLayout(gbl_panelBotoes);
        
        // Botão incluir todos
        btnIncluirTodos = new JButton(">>");
        btnIncluirTodos.setToolTipText("Incluir Todos");
        GridBagConstraints gbc_btnIncluirTodos = new GridBagConstraints();
        gbc_btnIncluirTodos.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnIncluirTodos.insets = new Insets(0, 0, 5, 0);
        gbc_btnIncluirTodos.gridx = 0;
        gbc_btnIncluirTodos.gridy = 0;
        panelBotoes.add(btnIncluirTodos, gbc_btnIncluirTodos);
        
        // Botão incluir selecionado
        btnIncluir = new JButton(">");
        btnIncluir.setToolTipText("Incluir Selecionado");
        GridBagConstraints gbc_btnIncluir = new GridBagConstraints();
        gbc_btnIncluir.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnIncluir.insets = new Insets(0, 0, 5, 0);
        gbc_btnIncluir.gridx = 0;
        gbc_btnIncluir.gridy = 1;
        panelBotoes.add(btnIncluir, gbc_btnIncluir);
        
        // Botão remover selecionado
        btnRemover = new JButton("<");
        btnRemover.setToolTipText("Remover Selecionado");
        GridBagConstraints gbc_btnRemover = new GridBagConstraints();
        gbc_btnRemover.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnRemover.insets = new Insets(0, 0, 5, 0);
        gbc_btnRemover.gridx = 0;
        gbc_btnRemover.gridy = 2;
        panelBotoes.add(btnRemover, gbc_btnRemover);
        
        // Botão remover todos
        btnRemoverTodos = new JButton("<<");
        btnRemoverTodos.setToolTipText("Remover Todos");
        GridBagConstraints gbc_btnRemoverTodos = new GridBagConstraints();
        gbc_btnRemoverTodos.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnRemoverTodos.gridx = 0;
        gbc_btnRemoverTodos.gridy = 3;
        panelBotoes.add(btnRemoverTodos, gbc_btnRemoverTodos);
        
        // Lista selecionada com scroll
        listSelecionado = new JList<>(modelSelecionado);
        listSelecionado.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPaneSelecionado = new JScrollPane(listSelecionado);
        GridBagConstraints gbc_scrollPaneSelecionado = new GridBagConstraints();
        gbc_scrollPaneSelecionado.fill = GridBagConstraints.BOTH;
        gbc_scrollPaneSelecionado.gridx = 2;
        gbc_scrollPaneSelecionado.gridy = 1;
        panelDualList.add(scrollPaneSelecionado, gbc_scrollPaneSelecionado);
        
        // Configurar eventos da dual list
        setupDualListEventListeners();
    }
    
    private void initializeButtonPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarDados();
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }
    
    private void setupDualListEventListeners() {
        // Incluir todos os itens
        btnIncluirTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                incluirTodos();
            }
        });
        
        // Incluir itens selecionados
        btnIncluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                incluirSelecionados();
            }
        });
        
        // Remover itens selecionados
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerSelecionados();
            }
        });
        
        // Remover todos os itens
        btnRemoverTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerTodos();
            }
        });
    }
    
    private void salvarDados() {
        if (pai == null) {
            // Se pai é null (modo teste), apenas mostra os dados selecionados
            List<ServicoAdicional> selecionados = getItensSelecionados();
            JOptionPane.showMessageDialog(this, 
                "Itens selecionados: " + selecionados.toString(), 
                "Dados", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Validação se estiverem em Branco
        if (txtData.getText().isEmpty() || txtData.getText().isBlank()) {
            JOptionPane.showMessageDialog(pai, "O campo de data esta em branco", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validacao
        if (!validarData(txtData.getText())) {
            JOptionPane.showMessageDialog(pai, "A Data esta invalida", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validação se estiverem em Branco
        if (txtPreco.getText().isEmpty() || txtPreco.getText().isBlank()) {
            JOptionPane.showMessageDialog(pai, "O valor esta em branco", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validacao
        var valor = validaValor(txtPreco.getText());
        if (valor.compareTo(BigDecimal.ZERO) == 0) {
            JOptionPane.showMessageDialog(pai, "O valor esta incorreto", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        var id = 0L;
        // Preencher o objeto com os dados do formulário
        venda.setClienteId(((Cliente)(cbxCliente.getSelectedItem())).getId());
        venda.setPacoteViagemId(((PacoteViagem)(cbxPacote.getSelectedItem())).getId());
        venda.setDataContratacao(LocalDate.parse(txtData.getText(), formatter));
        venda.setValorTotal(valor);
        if (venda.getId() == null) {
        	
            id = venda.insert();
        } else {
            venda.update();
        }
        
        if (!modelSelecionado.isEmpty()) {
			for (ServicoAdicional sa : java.util.Collections.list(modelSelecionado.elements())) {
				
				var sc = new ServicoContratado(venda.getId() == null ? id : venda.getId(), sa.getId());
				sc.insert();
			}
		}
        JOptionPane.showMessageDialog(pai, "Registro feito com sucesso","Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    
    // Fecha a janela e recarrega os dados da lista
    @Override
    public void dispose() {
        super.dispose();
        if (pai != null) {
            pai.carregarDados();
        }
    }
    
    // Preenche os campos com os dados da venda selecionada
    public void setContratacao(Contratacao venda) {
        this.venda = venda;
        if (pai != null) {
            cbxCliente.setSelectedItem(Cliente.findById(venda.getClienteId()));
            cbxPacote.setSelectedItem(PacoteViagem.findById(venda.getPacoteViagemId()));
            txtData.setText(venda.getDataContratacao().format(formatter));
            txtPreco.setText(venda.getValorTotal().toString());
        }
    }
    
    // Carrega todos os clientes para o ComboBox
    public void carregarCliente() {
        var clientes = Cliente.findAll();
        DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<>();
        for (Cliente cliente : clientes) {
            model.addElement(cliente);
        }
        cbxCliente.setModel(model);
    }
    
    // Carrega todos os pacotes para o ComboBox
    public void carregarPacote() {
        var pacotes = PacoteViagem.findAll();
        DefaultComboBoxModel<PacoteViagem> model = new DefaultComboBoxModel<>();
        for (PacoteViagem pacote : pacotes) {
            model.addElement(pacote);
        }
        cbxPacote.setModel(model);
    }
    
    // Valida o valor informado (retorna 0 se inválido)
    public boolean validarData(String dataStr) {
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
     */
    private BigDecimal validaValor(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return new java.math.BigDecimal(0);
        }
        
        valor = valor.trim();
        valor = valor.replace(",", ".");
        
        if (valor.contains(".")) {
            int ultimoPonto = valor.lastIndexOf(".");
            if (valor.indexOf(".") != ultimoPonto) {
                String parteInteira = valor.substring(0, ultimoPonto).replace(".", "");
                String parteDecimal = valor.substring(ultimoPonto);
                valor = parteInteira + parteDecimal;
            }
        }
        
        try {
            java.math.BigDecimal resultado = new java.math.BigDecimal(valor);
            
            if (resultado.compareTo(java.math.BigDecimal.ZERO) < 0) {
                return new java.math.BigDecimal(0);
            }
            
            return resultado;
            
        } catch (NumberFormatException e) {
            return new java.math.BigDecimal(0);
        }
    }
    
    // Métodos da Dual List
    private void popularDadosExemplo() {
       // modelDisponivel.addElement("Serviço 1 - Transporte");
    	var servicos  = ServicoAdicional.findAll();
		for (ServicoAdicional servico : servicos) {
			modelDisponivel.addElement(servico);
		}
        
    }
    
    private void incluirTodos() {
        while (modelDisponivel.getSize() > 0) {
            ServicoAdicional item = modelDisponivel.getElementAt(0);
            modelSelecionado.addElement(item);
            modelDisponivel.removeElementAt(0);
            
        }
    }
    
    private void incluirSelecionados() {
        List<ServicoAdicional> selectedValues = listDisponivel.getSelectedValuesList();
        for (ServicoAdicional item : selectedValues) {
            modelSelecionado.addElement(item);
            modelDisponivel.removeElement(item);
        }
        listDisponivel.clearSelection();
    }
    
    private void removerSelecionados() {
        List<ServicoAdicional> selectedValues = listSelecionado.getSelectedValuesList();
        for (ServicoAdicional item : selectedValues) {
            modelDisponivel.addElement(item);
            modelSelecionado.removeElement(item);
        }
        listSelecionado.clearSelection();
    }
    
    private void removerTodos() {
        while (modelSelecionado.getSize() > 0) {
        	ServicoAdicional item = modelSelecionado.getElementAt(0);
            modelDisponivel.addElement(item);
            modelSelecionado.removeElementAt(0);
        }
    }
    
    // Métodos públicos para manipular os dados da dual list
    public void setItensDisponiveis(List<ServicoAdicional> itens) {
        modelDisponivel.clear();
        for (ServicoAdicional item : itens) {
            modelDisponivel.addElement(item);
        }
    }
    
    public List<ServicoAdicional> getItensSelecionados() {
        List<ServicoAdicional> selecionados = new ArrayList<>();
        for (int i = 0; i < modelSelecionado.getSize(); i++) {
            selecionados.add(modelSelecionado.getElementAt(i));
        }
        return selecionados;
    }
    
    public void setItensSelecionados(List<ServicoAdicional> itens) {
        modelSelecionado.clear();
        for (ServicoAdicional item : itens) {
            modelSelecionado.addElement(item);
        }
    }
}