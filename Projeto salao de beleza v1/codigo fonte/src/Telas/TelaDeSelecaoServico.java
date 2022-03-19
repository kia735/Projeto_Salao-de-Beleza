package Telas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Back.Persistencia;
import Back.Salao;
import Back.Servico;

public class TelaDeSelecaoServico extends JFrameBase {
	
	private static final long serialVersionUID = 2894729644634394714L;


	public TelaDeSelecaoServico (Salao s) {
		setCentral(s);
		setTitle("Editar Serviço");
		tabela();
		botao();
		setVisible(true);
	}
	
	private JTable tabela;
	private JButton butaoDeExcluir, butaoDeEditar, sair ;
	
	
	@SuppressWarnings("serial")
	public void tabela() {
		DefaultTableModel modelo = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int lin, int colun) {
				return false;
			}
		};
		modelo.addColumn("Selecione o Serviço");
		
		for(int i = 0; i < getCentral().getServicos().size(); i++) {
			Servico s = getCentral().getServicos().get(i);
			Object[] linha = new Object[1];
			linha[0] = s.getNome();
			modelo.addRow(linha);
		}
		
	    tabela = new JTable(modelo);
		tabela.setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(80, 50, 250, 350);
		
		getPainel().add(scroll);
	}
	
	public void botao() {
		
		butaoDeEditar = new JButton("Editar");
		butaoDeEditar.setFont(getFontDoButao());
		butaoDeEditar.setBackground(Color.decode("#CD7C8D"));
		butaoDeEditar.setBounds(270, 420, 100, 25);
		butaoDeEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum Serviço selecionado");
				}
				else {
					dispose();
					try {
						new EditarServico(getCentral(), getTabela().getSelectedRow());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				
			}
		});
		getPainel().add(butaoDeEditar);
		

		butaoDeExcluir = new JButton("Excluir");
		butaoDeExcluir.setFont(getFontDoButao());
		butaoDeExcluir.setBackground(Color.decode("#CD7C8D"));
		butaoDeExcluir.setBounds(70, 420, 100, 25);
	
		butaoDeExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum Serviço selecionado");
				}
				else {
					getCentral().getServicos().remove(getTabela().getSelectedRow());
					dispose();
					
					try {
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					new TelaDeSelecaoServico(getCentral());
				    new TelaDeAviso("Serviço excluido");

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
				new TelaDeServico(getCentral());
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
