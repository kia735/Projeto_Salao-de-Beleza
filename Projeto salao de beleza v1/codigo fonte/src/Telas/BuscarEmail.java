package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import Back.Salao;

public class BuscarEmail extends Verificacao{
	
	private static final long serialVersionUID = 7584568068582719531L;

	public BuscarEmail (Salao salao) {
		super(salao, null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
	
	public  void botao() {
		setVerificar(new JButton("Buscar"));
		getVerificar().setBounds(140, 160, 100, 25);
		getVerificar().setBackground(Color.decode("#CD7C8D"));
		getVerificar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setEmail(verficarEmail(getCodigo().getText()));
				if(getEmail() != null) {
					dispose();
					new Verificacao(getCentral(),getEmail());
					
				}
				else {
				    new TelaDeAviso("Email Invalido");
				}
				
			}
		});
		getPainel().add(getVerificar());
	}
	
	public void adicionarLay() {
		setLabel(new JLabel());
		getLabel().setText("Digite seu email");
		getLabel().setBounds(120, 35, 200, 50);
		getPainel().add(getLabel());
	}

    public String verficarEmail(String e) {
    	if(getCentral().getProprietario().getEmail().equals(e)) {
    		return getCentral().getProprietario().getEmail();
    	}
    	for (int i = 0; i > getCentral().getTodosOsColaboradores().size(); i++) {
			if (getCentral().getTodosOsColaboradores().get(i) != null) {
				if (getCentral().getTodosOsColaboradores().get(i).getEmail().equals(e)) {
					return getCentral().getTodosOsColaboradores().get(i).getEmail();
				}
			}
		}
		return null;
	
     }
}
