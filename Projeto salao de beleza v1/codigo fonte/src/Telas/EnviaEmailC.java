package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Back.Colaborador;
import Back.Mensageiro;
import Back.Salao;

public class EnviaEmailC extends TelaDeSelecaoColaborador {

	private static final long serialVersionUID = 4944180801033101964L;


	public EnviaEmailC (Salao s) {
		super(s);
		setTitle("Enviar Email");
		getPainel().remove(getButaoDeExcluir());
		getPainel().remove(getButaoDeEditar());
		getPainel().remove(getSair());
		botoes();
		setVisible(true);
	}
	
	private JTextArea msg;
	

	@SuppressWarnings("serial")
	public void tabela() {
		
		DefaultTableModel modelo = new DefaultTableModel()
		{
			public boolean isCellEditable(int lin, int colun) {
				return false;
			}
		};
		modelo.addColumn("Selecione o Colaborador");
		
		for(int i = 0; i < getCentral().getTodosOsColaboradores().size(); i++) {
			Colaborador c = getCentral().getTodosOsColaboradores().get(i);
			Object[] linha = new Object[1];
			linha[0] = c.getNome();
			modelo.addRow(linha);
		}
		
	    setTabela(new JTable(modelo));
		getTabela().setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(getTabela());
		scroll.setBounds(80, 30, 250, 180);
		getPainel().add(scroll);
		
		msg = new JTextArea("Digite a mensagem");
		msg.setWrapStyleWord(true);
		msg.setLineWrap(true);
		JScrollPane scroll2 = new JScrollPane(msg);
		scroll2.setBounds(80, 230, 250, 180);
		getPainel().add(scroll2);
		
	}
	
	public void botoes() {
		
		setButaoDeExcluir(new JButton("Enviar"));
		getButaoDeExcluir().setFont(getFontDoButao());
		getButaoDeExcluir().setBackground(Color.decode("#CD7C8D"));
		getButaoDeExcluir().setBounds(170, 430, 100, 25);
		getButaoDeExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum colaborador selecionado");
				}
				else {
					try {
						Mensageiro ms = new Mensageiro();
						ms.enviaremail(getCentral().getTodosOsColaboradores()
						.get(getTabela().getSelectedRow()).getEmail(), msg.getText());
						
						 new TelaDeAviso("Email enviado");
					}catch(Exception e1) {
						new TelaDeAviso("Erro ao enviar email!");
					}
				}
				
			}
		});
		getPainel().add(getButaoDeExcluir());
		
		setSair(new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png"))));
		getSair().setBounds(380, 465, 30, 30);
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
	

	public JTextArea getMsg() {
		return msg;
	}

	public void setMsg(JTextArea msg) {
		this.msg = msg;
	}
}
