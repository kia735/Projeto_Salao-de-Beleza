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
import Back.Persistencia;
import Back.Salao;

public class TelaDePagamentosC  extends TelaDeRelatorioSaque{
	
	private static final long serialVersionUID = -8661808176809069387L;


	public TelaDePagamentosC(Salao s) {
		super(s);
		setTitle("Faturamento colaborador");
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
		modelo.addColumn("Salario");
		
		for(int i = 0; i < getCentral().getTodosOsColaboradores().size(); i++) {
			Colaborador c = getCentral().getTodosOsColaboradores().get(i);
			Object[] linha = new Object[2];
			linha[0] = c.getNome();
			linha[1] = Float.toString(c.getSalario());
			modelo.addRow(linha);
		}
		
	    setTabela(new JTable(modelo));
	    getTabela().setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(getTabela());
		scroll.setBounds(20, 30, 400, 400);
		
		getPainel().add(scroll);
	}
	

	public void botao() {
		setButaoDeExcluir(new JButton("Antecipar"));
		getButaoDeExcluir().setFont(getFontDoButao());
		getButaoDeExcluir().setBackground(Color.decode("#CD7C8D"));
		getButaoDeExcluir().setBounds(60, 450, 100, 25);
	
		getButaoDeExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum indece selecionado");
				}
				else {
					float ant = (float) (getCentral().getTodosOsColaboradores().get(getTabela().getSelectedRow()).getSalario()
							- getCentral().getTodosOsColaboradores().get(getTabela().getSelectedRow()).getSalario());
					if(getCentral().getCaixa().sacar(ant)){
						getCentral().getTodosOsColaboradores().get(getTabela().getSelectedRow()).setSalario(0);
						dispose();
						new TelaDeAviso("Antecipacao concluida 10% de acrescimo ao caixa");
						new TelaDePagamentosC(getCentral());
					}
					else {
						new TelaDeAviso("Saldo do caixa insuficiente");
					}
					
					try {
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				    

				}
			}
		});
		getPainel().add(getButaoDeExcluir());
		
		setButaoDeEditar(new JButton("Pagar"));
		getButaoDeEditar().setFont(getFontDoButao());
		getButaoDeEditar().setBackground(Color.decode("#CD7C8D"));
		getButaoDeEditar().setBounds(255, 450, 100, 25);
		getButaoDeEditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum colaborador selecionado");
				}
				else {
					
					float ant = (float) (getCentral().getTodosOsColaboradores().get(getTabela().getSelectedRow()).getSalario() * 0.1);
					
					if(getCentral().getCaixa().sacar(ant)){
						
						getCentral().getTodosOsColaboradores().get(getTabela().getSelectedRow()).setSalario(0);
						dispose();
						new TelaDeAviso("Pagamento concluido");
						new TelaDePagamentosC(getCentral());
						
						try {
							Persistencia.salvarCentral(getCentral(), "arquivo-Central");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else {
						new TelaDeAviso("Saldo do caixa insuficiente");
					}
				}
			}
		});
		getPainel().add(getButaoDeEditar());
		
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