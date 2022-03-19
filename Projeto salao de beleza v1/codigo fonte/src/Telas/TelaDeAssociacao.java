package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import Back.Salao;

public class TelaDeAssociacao extends TelaDeServico{
	
	private static final long serialVersionUID = 8992180118465022293L;

	public TelaDeAssociacao(Salao s) {
		super(s);
		getPainel().remove(getAdicionar());
		getPainel().remove(getEditar());
		getPainel().remove(getSair());
		botoes();
		setVisible(true);
	}


	public void addImagens() {
	
	setIconEdit(new ImageIcon(getClass().getResource("/img/organizacao.png")));
	setIconAdd(new ImageIcon(getClass().getResource("/img/associacoes.png")));
	
	}

	public void botoes() {

		setAdicionar(new JButton(getIconAdd()));
		getAdicionar().setBounds(160, 90, 100, 100);
		getAdicionar().setBorder(null);
		getAdicionar().setOpaque(false);
		getAdicionar().setContentAreaFilled(false);
		getAdicionar().setBackground(Color.decode("#CD7C8D"));
		getAdicionar().addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				dispose();
			    try {
					new  AssociadorSAC(getCentral());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
						
			}
		  }
		);
		getPainel().add(getAdicionar());
	
		setEditar(new JButton(getIconEdit()));
		getEditar().setBounds(160, 285, 100, 100);
		getEditar().setBorder(null);
		getEditar().setOpaque(false);
		getEditar().setContentAreaFilled(false);
		getEditar().setBackground(Color.decode("#CD7C8D"));
		getEditar().addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
			   dispose();
			   new EditarAssociacao(getCentral());
			}
		  }
		);
		getPainel().add(getEditar());
		
		setSair(new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png"))));
		getSair().setBounds(380, 465, 30, 30);
		getSair().setBorder(null);
		getSair().setOpaque(false);
		getSair().setContentAreaFilled(false);
		getSair().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaPrincipalAdm(getCentral());
			}
		});
		
		getPainel().add(getSair());
	}

}
