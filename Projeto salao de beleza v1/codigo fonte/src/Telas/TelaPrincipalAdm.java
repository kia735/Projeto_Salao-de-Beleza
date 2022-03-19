package Telas;
/**
 * @author Ezequias Soares
 * @see TelaPrincipalBase
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import Back.Salao;

public class TelaPrincipalAdm extends TelaPrincipalBase{
	
	private static final long serialVersionUID = -2548412123354271821L;
	
	/**
	 * Chama as configurações da tela que estar herdando, e chama os outros metodos
	 * para a criação dos botoes e imagens.
	 * @param s
	 */
	
	public TelaPrincipalAdm (Salao s) {
		setCentral(s);
		circulos();
		setVisible(true);
	}
	
	/**
	 * iram armazenar os dados e seus metodos gets e sets para que possa ser reutilizados 
	 * esses atributos em outras classes caso seja necessario.
	 */
	
	private JComponent circulo;
	private JLabel label;

	private JButton bUm, bDois,  bTres, bQuatro;
	private JButton biconEditoAdm, bCaixa, bMsg, servico;
	
	/**
	 * Cria os circulos que modelam o design da tela e os quatros botoes.
	 */
	
	public void circulos() {
		circulo = new CirculoJComponent();
		circulo.setBounds(145, 70,200, 200);
		circulo.add( new JButton("Sou Cliente"));
		getPainel().add(circulo);

		circulo = new CirculoJComponent();
		circulo.setBounds(145, 305,200, 200);
		getPainel().add(circulo);
		
		circulo = new CirculoJComponent();
		circulo.setBounds(35, 190,200, 200);
		getPainel().add(circulo);
		
		circulo = new CirculoJComponent();
		circulo.setBounds(260, 190,200, 200);
		getPainel().add(circulo);
	}
	
	/**
	 * criam os botoes que seram utilizados para chamar as outras classes.
	 */
	
	public void adicionandoJButton() {
		
		bUm = new JButton("Serviços");
		bUm.setBounds(157, 80, 80, 80);
		bUm.setBorder(null);
		bUm.setOpaque(false);
		bUm.setContentAreaFilled(false);
		bUm.setBackground(Color.decode("#CD7C8D"));
		bUm.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
						dispose();
				 try {
					 new TelaDeServico(getCentral());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
						
			}
		  }
		);
		getPainel().add(bUm);
		
		bDois = new JButton("Associações");
		bDois.setBounds(157, 315, 80, 80);
		bDois.setBorder(null);
		bDois.setOpaque(false);
		bDois.setContentAreaFilled(false);
		bDois.setBackground(Color.decode("#CD7C8D"));
		bDois.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
						dispose();
				 new TelaDeAssociacao(getCentral());
						
			}
		  }
		);
		getPainel().add(bDois);
		
		bTres = new JButton("Colaboradores");
		bTres.setBounds(45, 200, 80, 80);
		bTres.setBorder(null);
		bTres.setOpaque(false);
		bTres.setContentAreaFilled(false);

		bTres.setBackground(Color.decode("#CD7C8D"));
		bTres.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
						dispose();
				 new TelaDoColaborador(getCentral());
						
			}
		  }
		);
		
		getPainel().add(bTres);
		
		bQuatro = new JButton("Agenda");
		bQuatro.setBounds(270, 200, 80, 80);
		bQuatro.setBorder(null);
		bQuatro.setOpaque(false);
		bQuatro.setContentAreaFilled(false);
		bQuatro.setBackground(Color.decode("#CD7C8D"));
		bQuatro.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
						dispose();
				 new AgendaAdm(getCentral());
						
			}
		  }
		);
		getPainel().add(bQuatro);
		
		bCaixa = new JButton(new ImageIcon(getClass().getResource("/img/IconCaixaRegistradora.png")));
		bCaixa.setBounds(10, 350, 50, 50);
		bCaixa.setBorder(null);
		bCaixa.setBackground(Color.decode("#cccccc"));
		bCaixa.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				 new CaixaAdm(getCentral());
			}
		});
		getPainel().add(bCaixa);
		
		biconEditoAdm = new JButton(new ImageIcon(getClass().getResource("/img/iconAdm.png")));
		biconEditoAdm.setBounds(15, 25, 65, 65);
		biconEditoAdm.setBorder(null);
		biconEditoAdm.setBackground(Color.decode("#cccccc"));
		biconEditoAdm.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new EditarAdm(getCentral());
			}
		});
		getPainel().add(biconEditoAdm);
		
		setSair(new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png"))));
		getSair().setBounds(345, 415, 30, 30);
		getSair().setBorder(null);
		getSair().setBackground(Color.decode("#cccccc"));
		getSair().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Inicio(getCentral());
			}
		});
		getPainel().add(getSair());
		
		bMsg = new JButton(new ImageIcon(getClass().getResource("/img/iconEmail.png")));
		bMsg.setBounds(10, 400, 50, 50);
		bMsg.setBorder(null);
		bMsg.setBackground(Color.decode("#cccccc"));
		bMsg.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				 new EnviaEmailC(getCentral());
			}
		});
		getPainel().add(bMsg);
	}
	
	/**
	 * cria as imagens a serem adicionada na tela lateal.
	 */
	
	public void adicionandoImagem() {
		ImagemCentral();
	}
	
	/**
	 * cria as imagens a serem adicionada principal.
	 */
	
	public void ImagemCentral() {
		label = new JLabel(new ImageIcon(getClass().getResource("/img/iconComputador.png")));
		label.setBounds(145, 185, 115, 115);
		getPainel().add(label);
		 
		label = new JLabel(new ImageIcon(getClass().getResource("/img/IconModelosUm.png")));
		label.setBounds(25, 80, 229, 267);
		getPainelLateral().add(label);

	}

	public JComponent getCirculo() {
		return circulo;
	}

	public void setCirculo(JComponent circulo) {
		this.circulo = circulo;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JButton getbUm() {
		return bUm;
	}

	public void setbUm(JButton bUm) {
		this.bUm = bUm;
	}

	public JButton getbDois() {
		return bDois;
	}

	public void setbDois(JButton bDois) {
		this.bDois = bDois;
	}

	public JButton getbTres() {
		return bTres;
	}

	public void setbTres(JButton bTres) {
		this.bTres = bTres;
	}

	public JButton getbQuatro() {
		return bQuatro;
	}

	public void setbQuatro(JButton bQuatro) {
		this.bQuatro = bQuatro;
	}

	public JButton getBiconEditoAdm() {
		return biconEditoAdm;
	}

	public void setBiconEditoAdm(JButton biconEditoAdm) {
		this.biconEditoAdm = biconEditoAdm;
	}

	public JButton getbCaixa() {
		return bCaixa;
	}

	public void setbCaixa(JButton bCaixa) {
		this.bCaixa = bCaixa;
	}

	public JButton getbMsg() {
		return bMsg;
	}

	public void setbMsg(JButton bMsg) {
		this.bMsg = bMsg;
	}

	public JButton getServico() {
		return servico;
	}

	public void setServico(JButton servico) {
		this.servico = servico;
	}

}
