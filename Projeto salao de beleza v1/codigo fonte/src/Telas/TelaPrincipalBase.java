package Telas;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaPrincipalBase extends JFrameBase{
	
	private static final long serialVersionUID = -1477073226505738030L;



	public TelaPrincipalBase() {
		
		setTitle("Principal");
		getPainel().remove(getBarraPadraoSuperio());
		getPainel().remove(getBarraPadraoinferior());
		getPainel().setSize(400,700);
		configurandoJFrame();
		adicionandoPainel();
		adicionandoImagem();
		adicionandoJButton();
		setVisible(true);
	}
	
	private JPanel painelLateral;
	private JLabel imagemCentral;
	private JButton sair;
	
	

	public void configurandoJFrame() {
		
		setSize(700,500);
		setLocationRelativeTo(null);	
	}
	
	public void adicionandoPainel() {
		painelLateral = new JPanel();
		painelLateral.setBounds(400,0,400,700);
		painelLateral.setBackground(Color.decode("#CD7C8D"));
		painelLateral.setLayout(null);
		add(painelLateral);
		
	}
	
	public void adicionandoImagem() {
		
		imagemCentral = new JLabel(new ImageIcon(getClass().getResource("/img/imagePrincipal.png")));
		imagemCentral.setBounds(25, 70, 229, 267);
		painelLateral.add(imagemCentral);
		
	}
	
	public void adicionandoJButton() {
		sair = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		sair.setBounds(5, 430, 16, 16);
		sair.setBorder(null);
		sair.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			    new Inicio(getCentral());
			}
		});
		getPainel().add(sair);
	}
	
	public JPanel getPainelLateral() {
		return painelLateral;
	}
	public void setPainelLateral(JPanel painelLateral) {
		this.painelLateral = painelLateral;
	}

	public JLabel getImagemCentral() {
		return imagemCentral;
	}
	public void setImagemCentral(JLabel imagemCentral) {
		this.imagemCentral = imagemCentral;
	}

	public JButton getSair() {
		return sair;
	}
	public void setSair(JButton sair) {
		this.sair = sair;
	}
	
}



