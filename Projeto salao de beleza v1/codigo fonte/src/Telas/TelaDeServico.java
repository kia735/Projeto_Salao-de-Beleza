package Telas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Back.Salao;

public class TelaDeServico extends TelaDoColaborador{
	
	private static final long serialVersionUID = 3872644134295421963L;

	public TelaDeServico(Salao s) {
		super(s);
		setTitle("Opcões");
		getPainel().remove(getAdicionar());
		getPainel().remove(getEditar());
		botoes();
		setVisible(true);
	}
	private JButton sair;
	
	public JButton getSair() {
		return sair;
	}

	public void setSair(JButton sair) {
		this.sair = sair;
	}

	public void addImagens() {
		
		setIconEdit(new ImageIcon(getClass().getResource("/img/iconEditarS.png")));
		setIconAdd(new ImageIcon(getClass().getResource("/img/iconAddS.png")));
		
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
					new TelaDeCadastroServico(getCentral());
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
			    new TelaDeSelecaoServico(getCentral());		
			}
		  }
		);
		getPainel().add(getEditar());
		
		sair = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		sair.setBounds(380, 465, 30, 30);
		sair.setBorder(null);
		sair.setOpaque(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaPrincipalAdm(getCentral());
			}
		});
		getPainel().add(sair);
	}
	
}
