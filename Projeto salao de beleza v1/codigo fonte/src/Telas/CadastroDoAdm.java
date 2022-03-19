package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Back.Salao;
import Back.ValidarEntrada;
import Back.CadastroAdm;
import Back.Persistencia;


public class CadastroDoAdm extends Entrar{

	private static final long serialVersionUID = -4666890897017277485L;

	public CadastroDoAdm(Salao salao){
		super(salao);
		getEsqueceu().setVisible(false);
		getSeparador().setBounds(105, 285, 200, 25);
		setCentral(salao);
		setTitle("Cadastrar");
		getPainel().remove(getIcone());
		getPainel().remove(getEntrar());
		botao();
		logo();
		separador();
		caixasDeTextos();
		setVisible(true);
		
	}
	
	private JLabel novoIcone;
	private JButton butaoDeCadastro;
	private JTextField nome;
	private JSeparator separador;

	public void botao() {
		
		butaoDeCadastro = new JButton("Cadastrar");
		butaoDeCadastro.setFont(getFontDoButao());
		butaoDeCadastro.setBackground(Color.decode("#CD7C8D"));
		butaoDeCadastro.setBounds(155, 410, 100, 25);
		butaoDeCadastro.setEnabled(false);
		butaoDeCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					CadastroAdm.cadastrandoAdm(getCentral(), nome.getText(), getCampoEmail().getText(), 
					new String(getCampoSenha().getPassword()));
					Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					dispose();
					 
					new Entrar(getCentral());
					 
				} catch (Exception e1) {
					new TelaDeAviso("Houve algum erro");
				}
				
			}
		});
		getPainel().add(butaoDeCadastro);
	}
	
	public void caixasDeTextos() {
		nome = new JTextField("Digite seu nome");
		nome.setBounds(105, 210, 200, 25);
		nome.setBorder(null);
		nome.setBackground(Color.decode("#cccccc"));
		getPainel().add(nome);
		
		getCampoEmail().setText("Cadastre um email");
		getCampoEmail().setBounds(105, 265, 200, 25);	
		getCampoEmail().addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
				getCampoSenha().setEnabled(ValidarEntrada.validarEmail(getCampoEmail().getText()));
				
			}
			public void keyPressed(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		getPainel().add(getCampoEmail());
		
		getCampoSenha().setBounds(105, 320, 200, 25);
		getCampoSenha().addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				
				butaoDeCadastro.setEnabled(ValidarEntrada.validarSenha( new String(getCampoSenha().getPassword())));
				
			}
			public void keyPressed(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		getPainel().add(getCampoSenha());
	}
	
	public void logo() {
		ImageIcon icone = new ImageIcon(getClass().getResource("/img/cadastro.png"));
		setNovoIcone(new JLabel(icone));
		getNovoIcone().setBounds(125, 40, 172, 130);
		getPainel().add(getNovoIcone());
	}
	
	public void separador() {
		separador = new JSeparator();
		separador.setBounds(105, 235, 200, 3);
		separador.setBackground(Color.decode("#CD7C8D"));
		separador.setForeground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(105, 345, 200, 3);
		separador.setBackground(Color.decode("#CD7C8D"));
		separador.setForeground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
	}
	
	public JTextField getNome() {
		return nome;
	}
	public void setNome(JTextField nome) {
		this.nome = nome;
	}
	public JSeparator getSeparador() {
		return separador;
	}

	public void setSeparador(JSeparator separador) {
		this.separador = separador;
	}

	public JButton getButaoDeCadastro() {
		return butaoDeCadastro;
	}
	public void setButaoDeCadastro(JButton butaoDeCadastro) {
		this.butaoDeCadastro = butaoDeCadastro;
	}
	public JLabel getNovoIcone() {
		return novoIcone;
	}
	public void setNovoIcone(JLabel novoIcone) {
		this.novoIcone = novoIcone;
	}

}
