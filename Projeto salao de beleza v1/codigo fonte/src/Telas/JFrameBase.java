package Telas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;


import javax.swing.JFrame;
import javax.swing.JPanel;


import Back.Salao;

public class JFrameBase extends JFrame{

	private static final long serialVersionUID = 6623917201389031615L;

	public void configurandoTela() {
		setSize(450, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		configurandoPainel();
		configurandoIconDoJFrame();

	}
	
	public JFrameBase() {
		configurandoTela();
	}
	
	private Salao central;
	private JPanel painel, barraPadraoSuperio, barraPadraoinferior;
	private Font fontDoButao = new Font("Roboto", Font.BOLD, 9);

	public void configurandoPainel() {

		painel = new JPanel();
		painel.setLayout(null);
		painel.setSize(450, 550);
		painel.setBackground(Color.decode("#cccccc"));
		add(painel);

		barraPadraoSuperio = new JPanel();
		barraPadraoSuperio.setSize(450, 13);
		barraPadraoSuperio.setBackground(Color.decode("#CD7C8D"));
		painel.add(barraPadraoSuperio);
		
		barraPadraoinferior = new JPanel();
		barraPadraoinferior.setBounds(0, 500, 450, 13);
		barraPadraoinferior.setBackground(Color.decode("#CD7C8D"));
		getPainel().add(barraPadraoinferior);
	}

	public void configurandoIconDoJFrame() {

		URL url = this.getClass().getResource("/img/iconAdm.png");
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);

	}
	
	public Salao getCentral() {
		return central;
	}
	public void setCentral(Salao central) {
		this.central = central;
	}

	public JPanel getPainel() {
		return painel;
	}
	public void setPainel(JPanel painel) {
		this.painel = painel;
	}
	
	public JPanel getBarraPadraoSuperio() {
		return barraPadraoSuperio;
	}
	public void setBarraPadraoSuperio(JPanel barraPadraoSuperio) {
		this.barraPadraoSuperio = barraPadraoSuperio;
	}

	public JPanel getBarraPadraoinferior() {
		return barraPadraoinferior;
	}
	public void setBarraPadraoinferior(JPanel barraPadraoinferior) {
		this.barraPadraoinferior = barraPadraoinferior;
	}
	
	public Font getFontDoButao() {
		return fontDoButao;
	}
	public void setFontDoButao(Font fontDoButao) {
		this.fontDoButao = fontDoButao;
	}
}
