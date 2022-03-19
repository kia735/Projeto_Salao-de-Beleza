package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import Back.Colaborador;
import Back.Salao;

public class TelaPrincipalColaborador extends TelaPrincipalBase{
	
	
	private static final long serialVersionUID = -3967755521848191863L;
	private JButton perfil, historico, pendentes;
	private JLabel label;
	private Font font;
	
	public TelaPrincipalColaborador(Salao salao, String email) {
		setCentral(salao);
		label(email);
		botao(email);
		setTitle("Colaborador");
		setVisible(true);
	}
	
	public void label(String email) {
		
		font = new Font("Courier New", Font.BOLD, 15);
		
		label = new JLabel("Prévia Salarial: R$ " + Colaborador.previaSalarial(getCentral(), email));
		label.setBounds(50, 5, 200, 20);
		getPainelLateral().add(label);
		
		label = new JLabel("Perfil");
		label.setBounds(175, 170, 70, 30);
		label.setFont(font);
		getPainel().add(label);
		
		label = new JLabel("Historico");
		label.setBounds(80, 310, 100, 30);
		label.setFont(font);
		getPainel().add(label);

		label = new JLabel("Pendentes");
		label.setBounds(245, 310, 100, 30);
		label.setFont(font);
		getPainel().add(label);

		
	}
	public void botao(String email) {
	
		perfil = new JButton(new ImageIcon(getClass().getResource("/img/iconUser.png")));
		perfil.setBounds(150, 70, 100, 100);
		perfil.setBorder(null);
		perfil.setBackground(Color.decode("#cccccc"));
		perfil.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				for(int i = 0; i < getCentral().getTodosOsColaboradores().size(); i++) {
					
					if(getCentral().getTodosOsColaboradores().get(i) != null) {
						if(getCentral().getTodosOsColaboradores().get(i).getEmail().equals(email)) {
							dispose();
							new InformacoesDoColaborador(getCentral(), i,email);
						}
					}
				}
			}
		});
		getPainel().add(perfil);
		
		historico = new JButton(new ImageIcon(getClass().getResource("/img/iconClock.png")));
		historico.setForeground(Color.decode("#F44336"));
		historico.setBounds(70, 210, 100, 100);
		historico.setBorder(null);
		historico.setBackground(Color.decode("#cccccc"));
		historico.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HistoricoDoColaborador(getCentral(), email);
			}
		});
		getPainel().add(historico);
		
		pendentes = new JButton(new ImageIcon(getClass().getResource("/img/iconQuestions.png")));
		pendentes.setForeground(Color.decode("#8D6E63"));
		pendentes.setBounds(230, 210, 100, 100);
		pendentes.setBorder(null);
		pendentes.setBackground(Color.decode("#cccccc"));
		pendentes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new AgendamentosPendentes(getCentral(), email);
				
			}
		});
		getPainel().add(pendentes);
		
	}

	public JButton getPerfil() {
		return perfil;
	}

	public void setPerfil(JButton perfil) {
		this.perfil = perfil;
	}

	public JButton getHistorico() {
		return historico;
	}

	public void setHistorico(JButton historico) {
		this.historico = historico;
	}

	public JButton getPendentes() {
		return pendentes;
	}

	public void setPendentes(JButton pendentes) {
		this.pendentes = pendentes;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

}
