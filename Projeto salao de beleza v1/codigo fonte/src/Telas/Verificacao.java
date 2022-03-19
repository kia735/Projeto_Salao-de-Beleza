package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Back.Salao;
import Back.Validador;

public class Verificacao extends JFrameBase{

	private static final long serialVersionUID = -2112544555734089183L;
	public Verificacao(Salao salao, String email) {
		setCentral(salao);
		setEmail(email);
		getPainel().setSize(400,250);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getBarraPadraoSuperio().setSize(400, 13);
		getBarraPadraoinferior().setBounds(0, 200, 400, 13);
		setSize(400,250);
		
		setLocationRelativeTo(null);
		inicializar();
		setVisible(true);
	}
	
	private JButton verificar;
	private JTextField codigo;
	private JLabel label;
	private String email;
	private Validador va;

	
	private class ouvinte implements KeyListener{
		public void keyTyped(KeyEvent e) {
			char v = e.getKeyChar();
			if(!Character.isDigit(v)) {
				e.consume();
			}
		}
		
		public void keyPressed(KeyEvent e) {
		}
		
		public void keyReleased(KeyEvent e) {
		}
		
	}
	
	public void campo() {
		codigo = new JTextField();
		codigo.setBounds(90, 110, 200, 25);
		ouvinte ver = new ouvinte();
		if(verificar.getText().equals("Verificar")) {
			codigo.addKeyListener(ver);
		}
		getPainel().add(codigo);
		
	}
	
	public void inicializar() {
		adicionarLay();
		botao();
		campo();
	}
	
	public  void botao() {
		verificar = new JButton("Verificar");
		verificar.setBounds(140, 160, 100, 25);
		verificar.setBackground(Color.decode("#CD7C8D"));
		if(verificar.getText().equals("Verificar")) {
			va = new Validador(getCentral().getProprietario().getEmail());
			verificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				if(getCodigo().getText().equals(Integer.toString(va.getCodigo()))) {
					dispose();
					new TelaRedefinirS(getCentral(), getEmail());
				}
				else {
					new TelaDeAviso("Codigo Incorreto");
					}
				}
			});
		}
		getPainel().add(verificar);

	}

	public void adicionarLay() {
		label = new JLabel("Por favor, digite o codigo que");
		label.setBounds(100, 25, 200, 50);
		getPainel().add(label);
		
		label = new JLabel("enviamos agora para seu e-mail: ");
		label.setBounds(95, 40, 200, 50);
		getPainel().add(label);
	}
	
	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Validador getVa() {
		return va;
	}
	public void setVa(Validador va) {
		this.va = va;
	}
	
	public JButton getVerificar() {
		return verificar;
	}
	public void setVerificar(JButton entrar) {
		this.verificar = entrar;
	}
	
	public JTextField getCodigo() {
		return codigo;
	}
	public void setCodigo(JTextField codigo) {
		this.codigo = codigo;
	}
}
