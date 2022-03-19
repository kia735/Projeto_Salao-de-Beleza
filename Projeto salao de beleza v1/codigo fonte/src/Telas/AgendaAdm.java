package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Back.Agenda;

import Back.PagamentosColaborador;
import Back.Persistencia;
import Back.Salao;

public class AgendaAdm extends TelaPrincipalBase {
	private static final long serialVersionUID = -5731712410373610686L;

	public AgendaAdm (Salao s) {
		setCentral(s);
		setTitle("Agendamentos");
		getPainelLateral().remove(getImagemCentral());
		getPainel().remove(getSair());
		adicionandoImagem();
		tabela();
		adicionandoJButton(s);
		setVisible(true);
	}
	
	private JTable tabela;
	
	public void adicionandoImagem() {
		setImagemCentral(new JLabel(new ImageIcon(getClass().getResource("/img/IconSalao.png"))));
		getImagemCentral().setBounds(25, 80, 229, 267);
		getPainelLateral().add(getImagemCentral());
	}
	
	public void adicionandoJButton(Salao salao) {
		setSair(new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png"))));
		getSair().setBounds(250, 417, 16, 16);
		getSair().setBorder(null);
		getSair().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaPrincipalAdm(getCentral());
			}
		});
		getPainelLateral().add(getSair());
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setFont(getFontDoButao());
		cancelar.setBackground(Color.decode("#CD7C8D"));
		cancelar.setBounds(60, 410, 100, 25);
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabela.getSelectedRow() < 0) {
					new TelaDeAviso("Nenhum índice selecionado");
				} else {
					try {
						Persistencia.salvarCentral(salao, "arquivo-Central");
						dispose();
						new AgendaAdm(salao);
						new TelaDeAviso("Cancelado");
					} catch (Exception e1) {
						new TelaDeAviso("Não foi possivel cancelar");
					}
				}
			}
		});
		getPainel().add(cancelar);
		
		JButton concluir = new JButton("Concluir");
		concluir.setFont(getFontDoButao());
		concluir.setBackground(Color.decode("#CD7C8D"));
		concluir.setBounds(240, 410, 100, 25);
		concluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabela.getSelectedRow() < 0) {
					new TelaDeAviso("Nenhum índice selecionado");
				} else {
					PagamentosColaborador.adicionandoValorAoCaixa(salao, tabela);
					try {
						Persistencia.salvarCentral(salao, "arquivo-Central");
					} catch (Exception e1) {
						new TelaDeAviso("Não foi possivel aceitar");
					}

				}
				dispose();
				new AgendaAdm(salao);
				new TelaDeAviso("Horario concluido");

			}
		});
		getPainel().add(concluir);
		
	}
	
	@SuppressWarnings("serial")
	public void tabela() {
		DefaultTableModel modelo = new DefaultTableModel(){
			public boolean isCellEditable(int lin, int colun) {
				return false;
			}
		};
		modelo.addColumn("Cliente");
		modelo.addColumn("Servico");
		modelo.addColumn("Colaborador");
		modelo.addColumn("Data");
		modelo.addColumn("Preco");
		
		
		for(int i = 0; i < getCentral().getHistorico().size(); i++) {
			Agenda a = getCentral().getHistorico().get(i);
			Object[] linha = new Object[5];
			linha[0] = a.getNomeDoCliente();
			linha[1] = a.getServico().getServico().getNome();
			linha[2] = a.getServico().getColaborador().getNome();
			linha[3] = a.getData();
			linha[4] = a.getServico().getServico().getPreco();
			modelo.addRow(linha);
		}
		
		tabela = new JTable(modelo);
	    tabela.setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(14, 14, 375, 350);
		
		getPainel().add(scroll);
	}	
}


