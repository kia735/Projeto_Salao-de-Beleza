package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Back.Agenda;
import Back.BuscarHistorico;
import Back.Persistencia;
import Back.Salao;
import Back.ValidarEntrada;

public class HistoricoDoCliente extends JFrameBase {

	private static final long serialVersionUID = -2491955820055329246L;

	public HistoricoDoCliente(Salao salao) {
		
		botao(salao);
		text();
		separador();
		setTitle("Em Aberto");
		bSair();
		setVisible(true);
	}
	
	private JTextField email;
	private JLabel notificar;
	private JButton lupaBotao, cancelar,sair;
	private JSeparator separador;
	private JTable tabelaDeHistorico;
	private Object[] linha = null; 
	private String[] colunas;
	private DefaultTableModel modelo;
	private JScrollPane painelTabela;
	private int vezUm = 0;
	
	public void text() {
		
		email = new JTextField("Adicione seu email");
		email.setBounds(80, 50, 240, 20);
		email.setBackground(Color.decode("#cccccc"));
		email.setBorder(null);
		email.addKeyListener(new KeyListener() {
	
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if(vezUm == 0) {
					email.setText("");
					vezUm++;
				}
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		getPainel().add(email);
		
	}
	
	public void separador() {
		
		separador = new JSeparator();
		separador.setBounds(80, 70, 270, 5);
		separador.setForeground(Color.decode("#CD7C8D"));
		separador.setBackground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
	}
	
	public void botao(Salao salao) {
		
		lupaBotao = new JButton(new ImageIcon(getClass().getResource("/img/iconLupa.png")));
		lupaBotao.setBackground(Color.decode("#cccccc"));
		lupaBotao.setBounds(330, 50, 20, 20);
		lupaBotao.setBorder(null);
		lupaBotao.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				if(ValidarEntrada.validarEmail(email.getText())) {
					try {
						if(BuscarHistorico.buscar(salao, email.getText()) != null){
							
							tabela(salao);
							lupaBotao.setEnabled(false);
						}
						else {
							new TelaDeAviso("cliente não encontrado");
						}
					}catch(Exception e2){
						new TelaDeAviso("Historico não encontrado");
					}
				}
				else {
					new TelaDeAviso("Insira um email válido");
				}
				repaint();
				validate();
				
			}
		});
		getPainel().add(lupaBotao);
	}
	
	public void tabela(Salao salao) {
		
		modelo = new DefaultTableModel();
		
		modelo.addColumn("Data");
		modelo.addColumn("Nome");
		modelo.addColumn("Status");
		
		for(Agenda a : BuscarHistorico.getAgenda()) {
			Object[] linha = new Object[3];
			linha[0] = a.getData();
			linha[1] = a.getNome();
			linha[2] = a.getStatus();
			modelo.addRow(linha);
		}
		
		tabelaDeHistorico = new JTable(modelo);
		tabelaDeHistorico.setDefaultEditor(Object.class, null);
		tabelaDeHistorico.setBackground(Color.decode("#cccccc"));
		painelTabela = new JScrollPane(tabelaDeHistorico);
		painelTabela.setBounds(80, 100, 270, 270);
		getPainel().add(painelTabela);
		
		botaoCancelar(salao);
	}
	
	public void botaoCancelar(Salao salao) {
		
		cancelar = new JButton(new ImageIcon(getClass().getResource("/img/iconCancelar.png")));
		cancelar.setText("Cancelar Agendamento");
		cancelar.setBackground(Color.decode("#cccccc"));
		cancelar.setBorder(null);
		cancelar.setBounds(120, 400, 200, 20);
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaDeHistorico.getSelectedRow() < 0) {
					new TelaDeAviso("Nenhum indice selecionado");
				}
				else {
				    salao.adicionarAosRegistros(tabelaDeHistorico.getSelectedRow());
				    salao.removerHistorico(tabelaDeHistorico.getSelectedRow());
				    dispose();
				    new HistoricoDoCliente(getCentral());
				    try {
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					} catch (Exception e1) {
						new TelaDeAviso("Houve um erro");
					}
				}
			}
		});
		getPainel().add(cancelar);
	}
	
	public void bSair() {
		sair = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		sair.setBounds(380, 445, 30, 30);
		sair.setBorder(null);
		sair.setOpaque(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    new TelaPrincipalCliente(getCentral());
			}
		});
		getPainel().add(sair);
		
	}
	
	public JTextField getEmail() {
		return email;
	}
	
	public void setEmail(JTextField email) {
		this.email = email;
	}
	
	public JLabel getNotificar() {
		return notificar;
	}
	
	public void setNotificar(JLabel notificar) {
		this.notificar = notificar;
	}
	
	public JButton getLupaBotao() {
		return lupaBotao;
	}
	
	public void setLupaBotao(JButton lupaBotao) {
		this.lupaBotao = lupaBotao;
	}
	
	public JButton getCancelar() {
		return cancelar;
	}
	
	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
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
	
	public JTable getTabelaDeHistorico() {
		return tabelaDeHistorico;
	}
	
	public void setTabelaDeHistorico(JTable tabelaDeHistorico) {
		this.tabelaDeHistorico = tabelaDeHistorico;
	}
	
	public Object[] getLinha() {
		return linha;
	}
	
	public void setLinha(Object[] linha) {
		this.linha = linha;
	}
	
	public String[] getColunas() {
		return colunas;
	}
	
	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}
	
	public DefaultTableModel getModelo() {
		return modelo;
	}
	
	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}
	
	public JScrollPane getPainelTabela() {
		return painelTabela;
	}
	
	public void setPainelTabela(JScrollPane painelTabela) {
		this.painelTabela = painelTabela;
	}
	
	public int getVezUm() {
		return vezUm;
	}
	
	public void setVezUm(int vezUm) {
		this.vezUm = vezUm;
	}
	
	}


