package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Back.HistoricoDeSaques;
import Back.Persistencia;
import Back.Salao;

public class TelaDeRelatorioSaque extends TelaDeSelecaoColaborador{
	
	private static final long serialVersionUID = 6550958527322916357L;

	public TelaDeRelatorioSaque(Salao s) {
		super(s);
		setTitle("Relatorio de saques");
		getPainel().remove(getTabela());
		getPainel().remove(getButaoDeExcluir());
		botao();
		setVisible(true);
	}
	
	@SuppressWarnings("serial")
	public void tabela() {
		DefaultTableModel modelo = new DefaultTableModel(){
			public boolean isCellEditable(int lin, int colun) {
				return false;
			}
		};
		modelo.addColumn("Data");
		modelo.addColumn("Valor");
		
		for(int i = 0; i < getCentral().getCaixa().getSaques().size(); i++) {
			HistoricoDeSaques c = getCentral().getCaixa().getSaques().get(i);
			Object[] linha = new Object[2];
			linha[0] = c.getData();
			linha[1] = Float.toString(c.getValor());
			modelo.addRow(linha);
		}
		
	    setTabela(new JTable(modelo));
	    getTabela().setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(getTabela());
		scroll.setBounds(80, 50, 250, 350);
		
		getPainel().add(scroll);
	}
	
	public void botao() {
		setButaoDeExcluir(new JButton("Excluir"));
		getButaoDeExcluir().setFont(getFontDoButao());
		getButaoDeExcluir().setBackground(Color.decode("#CD7C8D"));
		getButaoDeExcluir().setBounds(170, 430, 100, 25);
	
		getButaoDeExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum indece selecionado");
				}
				else {
						getCentral().getCaixa().getSaques().remove(getTabela().getSelectedRow());
						dispose();
					
					try {
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					new TelaDeRelatorioSaque(getCentral());
				    new TelaDeAviso("Excluido");

				}
			}
		});
		getPainel().add(getButaoDeExcluir());
		
		setSair( new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png"))));
		getSair().setBounds(380, 465, 30, 30);
		getSair().setBorder(null);
		getSair().setOpaque(false);
		getSair().setContentAreaFilled(false);
		getSair().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CaixaAdm(getCentral());
			}
		});
		getPainel().add(getSair());
		
	}

}
