package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Back.Agenda;
import Back.Persistencia;
import Back.Salao;
import Back.Status;

public class TelaDePagamentoDeClientes extends TelaDeRelatorioSaque{
	
	private static final long serialVersionUID = -9019235930560252412L;

	public TelaDePagamentoDeClientes(Salao s) {
		super(s);
		setTitle("Relatorio de pagamentos dos clientes");
		getPainel().remove(getTabela());
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
		modelo.addColumn("Colaborador");
		modelo.addColumn("Cliente");
		modelo.addColumn("Valor");
		modelo.addColumn("Data");
		
		for(int i = 0; i < getCentral().getRegistros().size(); i++) {
			if(getCentral().getRegistros().get(i).getStatus().equals(Status.concluido)) {
				Agenda a = getCentral().getRegistros().get(i);
				Object[] linha = new Object[4];
				linha[0] = a.getServico().getColaborador().getNome();
				linha[1] = a.getNomeDoCliente();
				linha[2] = a.getServico().getServico().getPreco();
				linha[3] = a.getData();
				modelo.addRow(linha);
			}
		}
		
	    setTabela(new JTable(modelo));
	    getTabela().setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(getTabela());
		scroll.setBounds(20, 30, 400, 400);
		
		getPainel().add(scroll);
	}
	
	public void botao() {
		setButaoDeExcluir(new JButton("Excluir"));
		getButaoDeExcluir().setFont(getFontDoButao());
		getButaoDeExcluir().setBackground(Color.decode("#CD7C8D"));
		getButaoDeExcluir().setBounds(170, 450, 100, 25);
	
		getButaoDeExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum indece selecionado");
				}
				else {
						getCentral().getRegistros().remove(getTabela().getSelectedRow());
						dispose();
					
					try {
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					new TelaDePagamentoDeClientes(getCentral());
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





