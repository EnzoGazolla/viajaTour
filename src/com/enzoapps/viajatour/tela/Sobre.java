package com.enzoapps.viajatour.tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("CEUB");
			lblNewLabel.setBounds(24, 10, 388, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Professor: Felippe Pires Ferreira");
			lblNewLabel_1.setBounds(24, 33, 388, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Aluno: Enzo Gazolla Nagano — Matrícula: 22401710");
			lblNewLabel_2.setBounds(24, 56, 388, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Aluno: Flavio Bonifacio Felix — Matrícula: 22401803");
			lblNewLabel_3.setBounds(24, 72, 388, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("<html>Projeto desenvolvido na disciplina Linguagem e Técnicas de Programação II <br> do curso de Ciência da Computação (UniCEUB). <br> O sistema simula a gestão de uma agência de viagens, <br> permitindo o cadastro e gerenciamento de clientes nacionais e estrangeiros, <br> pacotes de viagens, e serviços adicionais como passeios e translado.</html>");
			lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_4.setBounds(10, 95, 402, 127);
			contentPanel.add(lblNewLabel_4);
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
						Sobre.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
