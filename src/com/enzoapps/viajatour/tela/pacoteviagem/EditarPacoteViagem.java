package com.enzoapps.viajatour.tela.pacoteviagem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.enzoapps.viajatour.db.PacoteViagem;
import com.enzoapps.viajatour.db.TipoPacote;
import com.enzoapps.viajatour.util.DBBanco;
import com.enzoapps.viajatour.util.DBCarga;

public class EditarPacoteViagem extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNome;
	private JTextField textDescricao;
	private JTextField textDestino;
	private JTextField textDuracaoDias;
	private JTextField textPrecoBase;
	private JComboBox<TipoPacote> cbxTipoPacote;
	private ListarPacoteViagem pai;
	public PacoteViagem pv = new PacoteViagem();
	

	/**
	 * Create the dialog.
	 */
	public EditarPacoteViagem(ListarPacoteViagem pai) {
		this.pai = pai;
		
		setTitle("Editar Pacote de Viagem");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNome = new JLabel("Nome:");
			lblNome.setBounds(10, 42, 80, 13);
			contentPanel.add(lblNome);
		}
		{
			JLabel lblDescricao = new JLabel("Descrição:");
			lblDescricao.setBounds(10, 65, 80, 13);
			contentPanel.add(lblDescricao);
		}
		{
			JLabel lblDestino = new JLabel("Destino:");
			lblDestino.setBounds(10, 88, 80, 13);
			contentPanel.add(lblDestino);
		}
		{
			JLabel lblDuracaoDias = new JLabel("Duração de Dias:");
			lblDuracaoDias.setBounds(10, 111, 99, 13);
			contentPanel.add(lblDuracaoDias);
		}
		{
			JLabel lblPrecoBase = new JLabel("Preço Base:");
			lblPrecoBase.setBounds(10, 134, 92, 13);
			contentPanel.add(lblPrecoBase);
		}
		{
			JLabel lblTipoPacote = new JLabel("Tipo de Pacote:");
			lblTipoPacote.setBounds(10, 157, 92, 13);
			contentPanel.add(lblTipoPacote);
		}
		{
			textNome = new JTextField();
			textNome.setBounds(111, 39, 296, 19);
			contentPanel.add(textNome);
			textNome.setColumns(10);
		}
		{
			textDescricao = new JTextField();
			textDescricao.setBounds(111, 62, 296, 19);
			contentPanel.add(textDescricao);
			textDescricao.setColumns(10);
		}
		{
			textDestino = new JTextField();
			textDestino.setBounds(111, 85, 296, 19);
			contentPanel.add(textDestino);
			textDestino.setColumns(10);
		}
		{
			textDuracaoDias = new JTextField();
			textDuracaoDias.setBounds(111, 108, 294, 19);
			contentPanel.add(textDuracaoDias);
			textDuracaoDias.setColumns(10);
		}
		{
			textPrecoBase = new JTextField();
			textPrecoBase.setBounds(111, 131, 296, 19);
			contentPanel.add(textPrecoBase);
			textPrecoBase.setColumns(10);
		}
		
		cbxTipoPacote = new JComboBox<TipoPacote>();
		carregarTipoPacote();
		cbxTipoPacote.setBounds(112, 153, 295, 21);
		contentPanel.add(cbxTipoPacote);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						pv.setNome(textNome.getText());
						pv.setDescricao(textDescricao.getText());
						pv.setDestino(textDescricao.getText());
						pv.setDuracaoDias(Integer.parseInt(textDuracaoDias.getText()));
						pv.setPrecoBase(new BigDecimal(textPrecoBase.getText()));
						pv.setTipoPacoteId(((TipoPacote)(cbxTipoPacote.getSelectedItem())).getId());
						if (pv.getId() == null) {
							pv.insert();
						} else {
							pv.update();
						}
						JOptionPane.showMessageDialog(pai, "Registro feito com sucesso","Sucesso", JOptionPane.INFORMATION_MESSAGE);
						EditarPacoteViagem.this.dispose();
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
	public void setPacoteViagem(PacoteViagem pv) {
		this.pv = pv;
		textNome.setText(pv.getNome());
		textDescricao.setText(pv.getDescricao());
		textDestino.setText(pv.getDestino());
		textDuracaoDias.setText(String.valueOf(pv.getDuracaoDias()));
		textPrecoBase.setText(pv.getPrecoBase().toString());
		cbxTipoPacote.setSelectedItem(TipoPacote.findById(pv.getTipoPacoteId()));
	}
	public void carregarTipoPacote() {
		var tipos = TipoPacote.findAll();
		DefaultComboBoxModel<TipoPacote> model = new DefaultComboBoxModel<>();
		for (TipoPacote tipoPacote : tipos) {
			model.addElement(tipoPacote);
		}
		cbxTipoPacote.setModel(model);
	}
}
