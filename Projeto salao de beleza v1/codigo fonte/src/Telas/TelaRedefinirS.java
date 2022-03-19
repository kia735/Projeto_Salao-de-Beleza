package Telas;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Back.Persistencia;
import Back.Salao;

public class TelaRedefinirS extends Verificacao{

	private static final long serialVersionUID = 3357245984668683253L;
	public TelaRedefinirS (Salao salao, String email) {
		super(salao,email);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
	
	public  void botao() {
		setVerificar(new JButton("Redefinir"));
		getVerificar().setBounds(140, 160, 100, 25);
		getVerificar().setBackground(Color.decode("#CD7C8D"));
		getVerificar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				
				if(getCentral().getProprietario().getEmail().equals(getEmail())) {
					getCentral().getProprietario().setSenha(getCodigo().getText());
				}
				else {
					for (int i = 0; i > getCentral().getTodosOsColaboradores().size(); i++) {
						if (getCentral().getTodosOsColaboradores().get(i) != null) {
							if (getCentral().getTodosOsColaboradores().get(i).getEmail().equals(getEmail())) {
								getCentral().getTodosOsColaboradores().get(i).setSenha(getCodigo().getText());
							}
						}
					}
				}
				
				try {
					
					Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					
				} catch (Exception e1) {
					new TelaDeAviso(" erro na classe TRS");
				}
				dispose();
				new Entrar(getCentral());
			}
		});
		getPainel().add(getVerificar());
	}
	public void adicionarLay() {
		setLabel(new JLabel());
		getLabel().setText("Digite a nova senha de 6 digitos ");
		getLabel().setBounds(100, 35, 200, 50);
		getPainel().add(getLabel());
	}
	
}
