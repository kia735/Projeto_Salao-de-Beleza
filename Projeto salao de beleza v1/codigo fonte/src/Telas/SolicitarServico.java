package Telas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;


import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Back.BuscarColaboradores;
import Back.Salao;
import Back.ValidarAgendamento;

public class SolicitarServico extends JFrameBase {
	
	
	private static final long serialVersionUID = -581381436269360472L;

	boolean passe = true;
	
	public SolicitarServico(Salao salao) throws Exception  {
		setCentral(salao);
		imagens();
		text();
		separador();
		botao();
		label();
		comboBox();
		getBarraPadraoinferior().setBounds(0, 450, 300, 13);
		setTitle("Agendar");
		setSize(300,500);
		setLocationRelativeTo(null);
		setVisible(true);
		if(!passe) {
			new TelaDeAviso("Sem colaboradores ativos no momento!");
		}
	}
	
	private JLabel label;
	@SuppressWarnings("rawtypes")
	private JComboBox listDeServicos, listaDeColaboradores;
	private JTextField emailText, nomeText, dataText, horaText, descricaoText = null;
	private JButton voltar, solicitar;
	private JSeparator separador;
	private int vezUm = 0,vezDois = 0;
	private String[] osServicos;
	private String[] osColaboradores;
	
	public void imagens() {

		label = new JLabel(new ImageIcon(getClass().getResource("/img/iconsTime.png")));
		label.setBounds(70, 20, 128, 128);
		getPainel().add(label);
		
	}
	
	public void text() throws ParseException {
		
		nomeText = new JTextField();
		nomeText.setBounds(70, 160, 130, 20);
		nomeText.setBackground(Color.decode("#cccccc"));
		nomeText.setBorder(null);
		nomeText.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if(vezUm == 0) {
					nomeText.setText("");
					vezUm++;
				}
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		
		getPainel().add(nomeText);
		
		emailText = new JTextField();
		emailText.setBounds(70, 200, 130, 20);
		emailText.setBorder(null);
		emailText.setBackground(Color.decode("#cccccc"));
		emailText.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if(vezDois == 0) {
					emailText.setText("");
					vezDois++;
				}
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		
		getPainel().add(emailText);
		
		dataText  = new JFormattedTextField(new MaskFormatter("##/##/####"));
		dataText.setBackground(Color.decode("#cccccc"));
		dataText.setBounds(70, 240, 130, 20);
		dataText.setBorder(null);
		getPainel().add(dataText);
		
		horaText  = new JFormattedTextField(new MaskFormatter("##:##"));
		horaText.setBackground(Color.decode("#cccccc"));
		horaText.setBounds(70, 280, 50, 20);
		horaText.setBorder(null);
		getPainel().add(horaText);
		
		descricaoText= new JTextField();
		descricaoText.setBounds(70, 320, 130, 20);
		descricaoText.setBorder(null);
		descricaoText.setBackground(Color.decode("#cccccc"));
		getPainel().add(descricaoText);

	}
	
	public void separador() {
		
		separador = new JSeparator();
		separador.setBounds(70, 180, 130, 3);
		separador.setForeground(Color.decode("#6EC7B0"));
		separador.setBackground(Color.decode("#6EC7B0"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(70, 220, 130, 3);
		separador.setForeground(Color.decode("#6EC7B0"));
		separador.setBackground(Color.decode("#6EC7B0"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(70, 260, 130, 3);
		separador.setForeground(Color.decode("#6EC7B0"));
		separador.setBackground(Color.decode("#6EC7B0"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(70, 300, 50, 3);
		separador.setForeground(Color.decode("#6EC7B0"));
		separador.setBackground(Color.decode("#6EC7B0"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(70, 340, 130, 3);
		separador.setForeground(Color.decode("#6EC7B0"));
		separador.setBackground(Color.decode("#6EC7B0"));
		getPainel().add(separador);
	}
	
	public void botao() {
		
		solicitar = new JButton("Solicitar");
		solicitar.setBackground(Color.decode("#6EC7B0"));
		solicitar.setBorder(null);
		
		solicitar.setBounds(105, 400, 70, 20);
		
		solicitar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(ValidarAgendamento.verificandoAgendamento(getCentral(),emailText,nomeText,dataText,horaText,
						osServicos,osColaboradores,listDeServicos,listaDeColaboradores)) {
					dispose();
					try {
						
						new SolicitarServico(getCentral());
						new TelaDeAviso("Agendamento concluido");
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}	
		});
		getPainel().add(solicitar);
		
		voltar = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		voltar.setBorder(null);
		voltar.setBounds(3, 430, 16, 16);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    new TelaPrincipalCliente(getCentral());
			}	
		});
		getPainel().add(voltar);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void comboBox() {

		BuscarColaboradores b = new BuscarColaboradores(getCentral());
		try {
		osServicos = b.listaDeServicos();
		osColaboradores =b.listaDeColaboradores();
		}catch(Exception e) {
			dispose();
		}
		
		listaDeColaboradores = new JComboBox();
		listaDeColaboradores.setBackground(Color.decode("#6EC7B0"));
		listaDeColaboradores.setBounds(150, 355, 130, 20);
		getPainel().add(listaDeColaboradores);
		
		listDeServicos = new JComboBox();
		
		try {
			listDeServicos.setModel(new DefaultComboBoxModel<String>(osServicos));
		}catch(Exception e) {
			passe = false;
		}
		listDeServicos.setBackground(Color.decode("#6EC7B0"));
		listDeServicos.setBounds(10, 355, 130, 20);
		listDeServicos.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				if (listDeServicos.getSelectedItem().equals(osServicos[listDeServicos.getSelectedIndex()])) {
					
					listaDeColaboradores.setModel(new DefaultComboBoxModel<String>(osColaboradores));
					repaint();
					validate();
				}
			}
		});
		getPainel().add(listDeServicos);

	}
	
	public void label() {
		
		label = new JLabel("*Nome");
		label.setForeground(Color.gray);
		label.setBounds(210, 160, 75, 20);
		getPainel().add(label);
		
		label = new JLabel("*Email");
		label.setForeground(Color.gray);
		label.setBounds(210, 200, 75, 20);
		getPainel().add(label);
		
		label = new JLabel("*Descreva");
		label.setForeground(Color.gray);
		label.setBounds(210, 320, 75, 20);
		getPainel().add(label);
		
	}

	public JButton getVoltar() {
		return voltar;
	}

	public void setVoltar(JButton voltar) {
		this.voltar = voltar;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getListDeServicos() {
		return listDeServicos;
	}

	@SuppressWarnings("rawtypes")
	public void setListDeServicos(JComboBox listDeServicos) {
		this.listDeServicos = listDeServicos;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getListaDeColaboradores() {
		return listaDeColaboradores;
	}

	@SuppressWarnings("rawtypes")
	public void setListaDeColaboradores(JComboBox listaDeColaboradores) {
		this.listaDeColaboradores = listaDeColaboradores;
	}

	public JTextField getEmailText() {
		return emailText;
	}

	public void setEmailText(JTextField emailText) {
		this.emailText = emailText;
	}

	public JTextField getNomeText() {
		return nomeText;
	}

	public void setNomeText(JTextField nomeText) {
		this.nomeText = nomeText;
	}

	public JTextField getDataText() {
		return dataText;
	}

	public void setDataText(JTextField dataText) {
		this.dataText = dataText;
	}

	public JTextField getHoraText() {
		return horaText;
	}

	public void setHoraText(JTextField horaText) {
		this.horaText = horaText;
	}

	public JTextField getDescricaoText() {
		return descricaoText;
	}

	public void setDescricaoText(JTextField descricaoText) {
		this.descricaoText = descricaoText;
	}

	public JButton getSolicitar() {
		return solicitar;
	}

	public void setSolicitar(JButton solicitar) {
		this.solicitar = solicitar;
	}

	public JSeparator getSeparador() {
		return separador;
	}

	public void setSeparador(JSeparator separador) {
		this.separador = separador;
	}

	public String[] getOsServicos() {
		return osServicos;
	}

	public void setOsServicos(String[] osServicos) {
		this.osServicos = osServicos;
	}

	public String[] getOsColaboradores() {
		return osColaboradores;
	}

	public void setOsColaboradores(String[] osColaboradores) {
		this.osColaboradores = osColaboradores;
	}
	
}