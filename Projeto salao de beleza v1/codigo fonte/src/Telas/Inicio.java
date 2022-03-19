package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import Back.Salao;

public class Inicio extends JFrameBase{

	private static final long serialVersionUID = -5022863157379327656L;

	public Inicio(Salao salao) {
		setCentral(salao);
		setTitle("Inicio");
		adicionandoIcon();
		configurandoJButton();
		setVisible(true);
	}
	
	private JButton butaoTenhoCadastro, butaoSouCliente;
	private JLabel label;

	public void configurandoJButton() {
		
		butaoTenhoCadastro = new JButton("Tenho Cadastro");
		butaoTenhoCadastro.setBounds(250, 250, 140, 40);
		butaoTenhoCadastro.setFont(getFontDoButao());
		butaoTenhoCadastro.setBackground(Color.decode("#CD7C8D"));
		butaoTenhoCadastro.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
					dispose();
					new Entrar(getCentral());
						
			}
		  }
		);
		getPainel().add(butaoTenhoCadastro);
		
		
		
		butaoSouCliente = new JButton("Sou Cliente");
		butaoSouCliente.setBounds(70, 250, 140, 40);
		butaoSouCliente.setFont(getFontDoButao());
		butaoSouCliente.setBackground(Color.decode("#CD7C8D"));
		butaoSouCliente.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
					dispose();
				    new TelaPrincipalCliente(getCentral());
			}
		  }
		);
		getPainel().add(butaoSouCliente);
	}
	
	public void adicionandoIcon() {
		
		label = new JLabel(new ImageIcon(getClass().getResource("/img/IconCliente.png")));
		label.setBounds(90, 150, 100, 100);
		getPainel().add(label);
		
		label = new JLabel(new ImageIcon(getClass().getResource("/img/IconColaborador.png")));
		label.setBounds(260, 150, 100, 100);
		getPainel().add(label);
		
	}
	
	public JButton getButaoTenhoCadastro() {
		return butaoTenhoCadastro;
	}
	public void setButaoTenhoCadastro(JButton butaoTenhoCadastro) {
		this.butaoTenhoCadastro = butaoTenhoCadastro;
	}
	
	public JButton getButaoSouCliente() {
		return butaoSouCliente;
	}
	public void setButaoSouCliente(JButton butaoSouCliente) {
		this.butaoSouCliente = butaoSouCliente;
	}
	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
	
}
