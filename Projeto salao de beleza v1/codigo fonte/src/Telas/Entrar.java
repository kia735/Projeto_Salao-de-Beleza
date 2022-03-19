package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import Back.Salao;
import Back.ValidarEntrada;

public class Entrar extends JFrameBase {

	private static final long serialVersionUID = 8716638959202602472L;

	public Entrar(Salao salao) {
		setCentral(salao);
		setTitle("Entrar");
		logo();
		botao();
		caixasDeTextos();
		separador();
		setVisible(true);
	}
	
	private JButton entrar, visivel, esqueceu, sair;
	private JTextField campoEmail;
	private JPasswordField campoSenha;
	private JLabel icone;
	private JSeparator separador;

	

	private void botao() {
		entrar = new JButton("Entrar");
		entrar.setFont(getFontDoButao());
		entrar.setBackground(Color.decode("#CD7C8D"));
		entrar.setBounds(155, 410, 100, 25);
		entrar.setEnabled(false);
		entrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			
				if(getCentral().getProprietario().getEmail().equals(campoEmail.getText()) && 
						getCentral().getProprietario().getSenha().equals(new String(campoSenha.getPassword()))) {
						dispose();
					 new TelaPrincipalAdm(getCentral());
					 new TelaDeAviso("Bem-vindo(a) " + getCentral().getProprietario().getNome());
				}
				
				else {
					boolean t = true;
					for(int i = 0; i< getCentral().getTodosOsColaboradores().size(); i++) {
						if(getCentral().getTodosOsColaboradores().get(i) != null) {
							if(getCentral().getTodosOsColaboradores().get(i).getEmail().equals(campoEmail.getText()) &&
								getCentral().getTodosOsColaboradores().get(i).getSenha().equals(new String(campoSenha.getPassword()))) {
								dispose();
								new TelaPrincipalColaborador(getCentral(), campoEmail.getText());
							    new TelaDeAviso("Bem-vindo(a)");
								t = false;
							}
						}
					}
					if(t) {
						new TelaDeAviso("Dados incorretos");
					}
				}
			}
		});
		getPainel().add(entrar);
		
		visivel = new JButton(new ImageIcon(getClass().getResource("/img/iconEye.png")));
		visivel.setBackground(Color.decode("#cccccc"));
		visivel.setBorder(null);
		visivel.setBounds(282, 323 , 20, 20);
		visivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidarEntrada.configurarVisibilidadeDeSenha(campoSenha);
			}
		});
		getPainel().add(visivel);
		
		esqueceu = new JButton("Esqueceu a senha?");
		esqueceu.setBorder(null);
		esqueceu.setBackground(Color.decode("#cccccc"));
		esqueceu.setForeground(Color.decode("#CD7C8D"));
		esqueceu.setBounds(105, 350, 100, 25);
		esqueceu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new BuscarEmail(getCentral());
			}	
		});
		getPainel().add(esqueceu);
		
		sair = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		sair.setBounds(400, 450, 30, 30);
		sair.setBorder(null);
		sair.setOpaque(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    new Inicio(getCentral());
			}
		});
		getPainel().add(sair);
	}

	private void caixasDeTextos() {
		campoEmail = new JTextField("Digite seu e-mail");
		campoEmail.setBounds(105, 225, 200, 25);
		campoEmail.setBorder(null);
		campoEmail.setBackground(Color.decode("#cccccc"));
		campoEmail.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				campoSenha.setEnabled(ValidarEntrada.validarEmail(campoEmail.getText()));
			}
			public void keyPressed(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		getPainel().add(campoEmail);

		campoSenha = new JPasswordField("Digite sua senha");
		campoSenha.setBounds(105, 320, 200, 25);
		campoSenha.setBorder(null);
		campoSenha.setBackground(Color.decode("#cccccc"));
		campoSenha.setEnabled(false);
		campoSenha.setEchoChar('*');
		campoSenha.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				entrar.setEnabled(ValidarEntrada.validarSenha( new String(campoSenha.getPassword())));
			}
			public void keyPressed(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		getPainel().add(campoSenha);
	}

	private void logo() {

		icone = new JLabel(new ImageIcon(getClass().getResource("/img/IconLoginName.png")));
		icone.setBounds(120, 90, 172, 96);
		getPainel().add(icone);
	}
	
	public void separador() {
		separador = new JSeparator();
		separador.setBounds(105, 345, 200, 3);
		separador.setBackground(Color.decode("#CD7C8D"));
		separador.setForeground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(105, 250, 200, 3);
		separador.setBackground(Color.decode("#CD7C8D"));
		separador.setForeground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
	}
	
	public JButton getEsqueceu() {
		return esqueceu;
	}
	public void setEsqueceu(JButton esqueceu) {
		this.esqueceu = esqueceu;
	}
	public JButton getEntrar() {
		return entrar;
	}
	public void setEntrar(JButton entrar) {
		this.entrar = entrar;
	}
	public JTextField getCampoEmail() {
		return campoEmail;
	}
	public void setCampoEmail(JTextField campoEmail) {
		this.campoEmail = campoEmail;
	}
	public JPasswordField getCampoSenha() {
		return campoSenha;
	}
	public void setCampoSenha(JPasswordField campoSenha) {
		this.campoSenha = campoSenha;
	}
	public JLabel getIcone() {
		return icone;
	}
	public void setIcone(JLabel icone) {
		this.icone = icone;
	}
	public JButton getVisivel() {
		return visivel;
	}
	public void setVisivel(JButton visivel) {
		this.visivel = visivel;
	}

	public JButton getSair() {
		return sair;
	}

	public void setSair(JButton sair) {
		this.sair = sair;
	}

	public JSeparator getSeparador() {
		return separador;
	}

	public void setSeparador(JSeparator separador) {
		this.separador = separador;
	}
}
