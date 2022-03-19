package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Back.Colaborador;
import Back.Generos;
import Back.Persistencia;
import Back.Salao;
import Back.ValidarEntrada;

public class TelaDeCadastroColaborador extends JFrameBase{

	private static final long serialVersionUID = 1949106005264109267L;

	public TelaDeCadastroColaborador(Salao s) {
		setCentral(s);
		setTitle("Cadastro do colaborador");
	    textos();
		caixaDeTexto();
		separadores();
		imagem();
		sexo();
		cadastrar();
		setVisible(true);
	}
	
	private JTextField TNome, TEmail, TTelefone, TSenha;
	private JLabel label;
	private JSeparator separador;
	private JButton cadastrar, sair;
	private JRadioButton m, f;
	private Generos gen;
	
	public void textos() {

		label = new JLabel("Nome:");
		label.setBounds(50, 150, 50, 50);
		label.setOpaque(false);
		getPainel().add(label);
		
		label = new JLabel("E-mail:");
		label.setBounds(50, 220, 100, 50);
		label.setOpaque(false);
		getPainel().add(label);
		
		label = new JLabel("Telefone:");
		label.setBounds(50, 295, 100, 50);
		label.setOpaque(false);
		getPainel().add(label);
		
		label = new JLabel("Genero:");
		label.setBounds(220, 295, 100, 50);
		label.setOpaque(false);
		getPainel().add(label);
		
		label = new JLabel("senha:");
		label.setBounds(220, 150, 100, 50);
		label.setOpaque(false);
		getPainel().add(label);
		
	}
	
	public void caixaDeTexto() {
		TNome = new JTextField();
		TNome.setBounds(50, 185, 145, 25);
		TNome.setBorder(null);
		TNome.setBackground(Color.decode("#cccccc"));
		getPainel().add(TNome);
		
		TEmail = new JTextField();
		TEmail.setBounds(50, 260, 330, 25);
		TEmail.setBorder(null);
		TEmail.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				cadastrar.setEnabled(ValidarEntrada.validarEmail(TEmail.getText()));
			}
			public void keyPressed(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		
		TEmail.setBackground(Color.decode("#cccccc"));
		getPainel().add(TEmail);
		
		try {
			
			TTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
			
		} catch (ParseException e) {
			new TelaDeAviso("Houve um erro");
		}
		
		TTelefone.setBounds(50, 330, 110, 25);
		TTelefone.setBorder(null);
		TTelefone.setBackground(Color.decode("#cccccc"));
		getPainel().add(TTelefone);
		
		TSenha = new JTextField();
		TSenha.setBounds(220, 183, 145, 25);
		TSenha.setBorder(null);
		TSenha.setBackground(Color.decode("#cccccc"));
		getPainel().add(TSenha);
		
	}
	
	public void imagem() {
		
		label = new JLabel(new ImageIcon(getClass().getResource("/img/colabs.png")));
		label.setBounds(120, 30, 172, 130);
		getPainel().add(label);
		
		
	}
	
	public void separadores() {
		separador = new JSeparator();
		separador.setBounds(50, 210, 145, 3);
		separador.setForeground(Color.decode("#CD7C8D"));
		separador.setBackground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(50, 285, 330, 3);
		separador.setForeground(Color.decode("#CD7C8D"));
		separador.setBackground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(50, 355, 100, 3);
		separador.setForeground(Color.decode("#CD7C8D"));
		separador.setBackground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(220, 210, 155, 3);
		separador.setForeground(Color.decode("#CD7C8D"));
		separador.setBackground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
	}
	
	public void sexo() {
		m = new JRadioButton("M");
		m.setBounds(255, 335, 35, 20);
		m.setBackground(Color.decode("#cccccc"));
		m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gen = Generos.MASCULINO;
			}
		});
		getPainel().add(m);

		f = new JRadioButton("F");
		f.setBounds(305, 335, 35, 20);
		f.setBackground(Color.decode("#cccccc"));
		f.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gen = Generos.FEMININO;
			}
		});
		getPainel().add(f);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(f);
		grupo.add(m);
	}
	
	public void cadastrar() {
		cadastrar = new JButton("Cadastrar");
		cadastrar.setFont(getFontDoButao());
		cadastrar.setBackground(Color.decode("#CD7C8D"));
		cadastrar.setBounds(160, 420, 100, 25);
		cadastrar.setEnabled(false);
		cadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(getCentral().recuperarColaborador(TEmail.getText()) == null) {
					try {
						getCentral().adicionarColaborador(new Colaborador(TNome.getText(), TTelefone.getText(), gen,
								TEmail.getText(), TSenha.getText()));
						
						dispose();
						
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
						
						new TelaDoColaborador(getCentral());
						new TelaDeAviso("Colaborador cadastrado");
						
					} catch (Exception e1) {
					    new TelaDeAviso("Colaborador não cadastrado");
					}
				}
				else {
					new TelaDeAviso("Colaborador já cadastrado");
				}
			}
		});
		getPainel().add(cadastrar);
		
		sair = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		sair.setBounds(380, 445, 30, 30);
		sair.setBorder(null);
		sair.setOpaque(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTitle().equals("Cadastro do colaborador") || getTitle().equals("Editar colaborador") ) {
					dispose();
				    new TelaDoColaborador(getCentral());
				}
			}
		});
		getPainel().add(sair);
		
	}
	
	public JButton getCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(JButton cadastrar) {
		this.cadastrar = cadastrar;
	}
	
	public JButton getSair() {
		return sair;
	}
	public void setSair(JButton sair) {
		this.sair = sair;
	}
	
	public Generos getGen() {
		return gen;
	}
	
	public JTextField getTNome() {
		return TNome;
	}
	public void setTNome(JTextField tNome) {
		TNome = tNome;
	}
	
	public JTextField getTEmail() {
		return TEmail;
	}
	public void setTEmail(JTextField tEmail) {
		TEmail = tEmail;
	}
	
	public JTextField getTTelefone() {
		return TTelefone;
	}
	public void setTTelefone(JTextField tTelefone) {
		TTelefone = tTelefone;
	}
	
	public JTextField getTSenha() {
		return TSenha;
	}
	public void setTSenha(JTextField tSenha) {
		TSenha = tSenha;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JSeparator getSeparador() {
		return separador;
	}

	public void setSeparador(JSeparator separador) {
		this.separador = separador;
	}

	public JRadioButton getM() {
		return m;
	}

	public void setM(JRadioButton m) {
		this.m = m;
	}

	public JRadioButton getF() {
		return f;
	}

	public void setF(JRadioButton f) {
		this.f = f;
	}

	public void setGen(Generos gen) {
		this.gen = gen;
	}
	
}
