package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Back.Colaborador;
import Back.Mensageiro;
import Back.Persistencia;
import Back.Salao;
import Back.Status;

public class TelaDeSelecaoColaborador extends JFrameBase{

	private static final long serialVersionUID = 1722227702791331666L;

	public TelaDeSelecaoColaborador(Salao s) {
		setCentral(s);
		setTitle("Editar Dados");
		tabela();
		botao();
		setVisible(true);
	}
	
	private JTable tabela;
	private JButton butaoDeExcluir, butaoDeEditar, sair ;
	
	@SuppressWarnings("serial")
	public void tabela() {
		DefaultTableModel modelo = new DefaultTableModel(){
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
		
	    tabela = new JTable(modelo);
		tabela.setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(80, 50, 250, 350);
		
		getPainel().add(scroll);
	}
	
	public void botao() {
		
		butaoDeEditar= new JButton("Editar");
		butaoDeEditar.setFont(getFontDoButao());
		butaoDeEditar.setBackground(Color.decode("#CD7C8D"));
		butaoDeEditar.setBounds(270, 420, 100, 25);
		butaoDeEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum colaborador selecionado");
				}
				else {
					dispose();
					new EditarColaborador(getCentral(), tabela.getSelectedRow());

				}
				
			}
		});
		getPainel().add(butaoDeEditar);
		
		butaoDeExcluir= new JButton("Desabilitar");
		butaoDeExcluir.setFont(getFontDoButao());
		butaoDeExcluir.setBackground(Color.decode("#CD7C8D"));
		butaoDeExcluir.setBounds(70, 420, 100, 25);
	
		butaoDeExcluir.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum colaborador selecionado");
				}
				else {
					for(int i = 0; i< getCentral().getHistorico().size() ; i++) {
						if(getCentral().getHistorico().get(i).getServico().getColaborador().getEmail().equals(
								getCentral().getTodosOsColaboradores().get(tabela.getSelectedRow()).getEmail())){
							
							if(getCentral().getHistorico().get(i).getStatus().equals(Status.pendente)) {
								try {
									Mensageiro m = new Mensageiro();
									m.enviaremail(getCentral().getHistorico().get(i).getEmail(),
											"Ola o colaborador agendado para seu horario no salao nao esta mais disponivel."
											+ " Porfavor reagende um novo horario com outro colaborador");
									
									getCentral().adicionarAosRegistros(i);
									getCentral().removerHistorico(i);
									Persistencia.salvarCentral(getCentral(), "arquivo-Central");
								
								}catch(Exception e5) {
									new TelaDeAviso("E-mail não enviado");
								}
							 }
						 }
					 }
					getCentral().getTodosOsColaboradores().remove(tabela.getSelectedRow());
					
					dispose();
					
					new TelaDeSelecaoColaborador(getCentral());
				    new TelaDeAviso("Colaborador excluido");
				}
			}
		});
		getPainel().add(butaoDeExcluir);

		
		
		sair = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		sair.setBounds(380, 465, 30, 30);
		sair.setBorder(null);
		sair.setOpaque(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaDoColaborador(getCentral());
			}
		});
		getPainel().add(sair);
		
	}
	
	public JButton getButaoDeEditar() {
		return butaoDeEditar;
	}
	public void setButaoDeEditar(JButton butaoDeEditar) {
		this.butaoDeEditar = butaoDeEditar;
	}
	
	public JButton getSair() {
		return sair;
	}
	public void setSair(JButton sair) {
		this.sair = sair;
	}
	
	public JTable getTabela() {
		return tabela;
	}
	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}
	
	public JButton getButaoDeExcluir() {
		return butaoDeExcluir;
	}
	public void setButaoDeExcluir(JButton butaoDeExcluir) {
		this.butaoDeExcluir = butaoDeExcluir;
	}
	
}
