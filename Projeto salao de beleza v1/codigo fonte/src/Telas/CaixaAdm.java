package Telas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Back.Persistencia;
import Back.Salao;
import Back.ValidarEntrada;

public class CaixaAdm extends TelaPrincipalBase {
	
	private static final long serialVersionUID = 1444130095640123221L;

	public CaixaAdm(Salao s) {
		setCentral(s);
		setTitle("Caixa");
		getPainelLateral().remove(getImagemCentral());
		getPainel().remove(getSair());
		adicionandoImagem();
		label();
		text();
		botoes();
		setVisible(true);
	}
	
	private JTextField valor;
	private JTextField senha;
	
	public void adicionandoImagem() {
		setImagemCentral(new JLabel(new ImageIcon(getClass().getResource("/img/caixa-registradora.png"))));
		getImagemCentral().setBounds(25, 90, 229, 267);
		getPainelLateral().add(getImagemCentral());
	}
	
	public void label() {
		
		JLabel total = new JLabel();
		total.setText("Total : " + Float.toString (getCentral().getCaixa().getSaldo())+ " R$");
		total.setFont(new Font("Times", Font.PLAIN, 19));
		total.setBounds(75, 20, 200, 50);
		getPainelLateral().add(total);
	}
	
	
	public void botoes() {
		
		JButton b1 = new JButton("Sacar");
		b1.setBackground(Color.decode("#cccccc"));
		b1.setBounds(95, 420, 80, 25);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidarEntrada validar = new ValidarEntrada(getCentral());
				if(validar.souAdmin(getCentral().getProprietario().getEmail(),senha.getText())) {
					try {
						float p = Float.parseFloat(valor.getText());
						if(getCentral().getCaixa().sacar(p)) {
							Persistencia.salvarCentral(getCentral(), "arquivo-Central");
							dispose();
							new CaixaAdm(getCentral());
							new TelaDeAviso("Saque concluido");
						}
						else {
							new TelaDeAviso("Saldo insuficiene");
						}
						
					}catch(Exception e7) {
						new TelaDeAviso("Formato passado nao aceito. R$ 8.50");
				    }
				}
				else {
					new TelaDeAviso("Senha incorreta");
				}
			}
		});
		getPainelLateral().add(b1);
		
		JButton saques = new JButton(new ImageIcon(getClass().getResource("/img/iconSaque.png")));
		saques.setBounds(160, 87, 60, 60);
		saques.setBorder(null);
		saques.setOpaque(false);
		saques.setContentAreaFilled(false);
		saques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaDeRelatorioSaque(getCentral());
			}
		});
		getPainel().add(saques);
		
		JButton depositos = new JButton(new ImageIcon(getClass().getResource("/img/iconDeposito.png")));
		depositos.setBounds(160, 190, 60, 70);
		depositos.setBorder(null);
		depositos.setOpaque(false);
		depositos.setContentAreaFilled(false);
		depositos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaDePagamentoDeClientes(getCentral());
			}
		});
		getPainel().add(depositos);
		
		JButton adiantamento = new JButton(new ImageIcon(getClass().getResource("/img/iconAdiantamento.png")));
		adiantamento.setBounds(165, 310, 60, 70);
		adiantamento.setBorder(null);
		adiantamento.setOpaque(false);
		adiantamento.setContentAreaFilled(false);
		adiantamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaDePagamentosC(getCentral());
			}
		});
		getPainel().add(adiantamento);
		
		setSair( new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png"))));
		getSair().setBounds(5, 430, 16, 16);
		getSair().setBorder(null);
		getSair().setOpaque(false);
		getSair().setContentAreaFilled(false);
		getSair().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaPrincipalAdm(getCentral());
			}
		});
		getPainel().add(getSair());
		
		
	}
	
	public void text() {
		valor = new JTextField("0.00");
		valor.setBounds(40, 380, 90, 25);
		getPainelLateral().add(valor);
		
		senha = new JTextField("Digite sua senha");
		senha.setBounds(140, 380, 110, 25);
		getPainelLateral().add(senha);
	}

}
