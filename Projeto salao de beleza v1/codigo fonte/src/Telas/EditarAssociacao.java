package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Back.Associador;

import Back.Persistencia;
import Back.Salao;


public class EditarAssociacao extends TelaDeSelecaoServico {

	private static final long serialVersionUID = -2932378991473854684L;

	public EditarAssociacao(Salao s) {
		super(s);
		setTitle("Editor de Associados");
		getPainel().remove(getButaoDeEditar());
		getPainel().remove(getSair());
		getPainel().remove(getTabela());
		getPainel().remove(getButaoDeExcluir());
		botao();
		valor();
		setVisible(true);
	}
	
	private JTextField valor; 
	
	@SuppressWarnings("serial")
	public void tabela() {
		DefaultTableModel modelo = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int lin, int colun) {
				return false;
			}
		};
		modelo.addColumn("Serviço");
		modelo.addColumn("Nome");
		modelo.addColumn("Preço");
		
		for(int i = 0; i < getCentral().getAssociacao().size(); i++) {
			Associador c = getCentral().getAssociacao().get(i);
			Object[] linha = new Object[3];
			linha[0] = c.getColaborador().getNome();
			linha[1] = c.getServico().getNome();
			linha[2] = c.getServico().getPreco();
			modelo.addRow(linha);
		}
		
		setTabela(new JTable(modelo));
	    getTabela().setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(getTabela());
		scroll.setBounds(30, 30, 375, 350);
		
		getPainel().add(scroll);
	}
	
	public void botao() {
		
		setButaoDeEditar( new JButton("Editar valor"));
		getButaoDeEditar().setFont(getFontDoButao());
		getButaoDeEditar().setBackground(Color.decode("#CD7C8D"));
		getButaoDeEditar().setBounds(270, 440, 100, 25);
		getButaoDeEditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum índice selecionado");
				}
				else {
					try {
						float p = Float.parseFloat(valor.getText());
						getCentral().getAssociacao().get(getTabela().getSelectedRow()).getServico().setPreco(p);
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
						dispose();
						new EditarAssociacao(getCentral());
						
					}catch(Exception e7) {
						new TelaDeAviso("Formato passado nao aceito. R$ 10.50");
					}
				}
				
			}
		});
		getPainel().add(getButaoDeEditar());
		
		setButaoDeExcluir(new JButton("Excluir"));
		getButaoDeExcluir().setFont(getFontDoButao());
		getButaoDeExcluir().setBackground(Color.decode("#CD7C8D"));
		getButaoDeExcluir().setBounds(70, 440, 100, 25);
	
		getButaoDeExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTabela().getSelectedRow() < 0) {
					 new TelaDeAviso("Nenhum índice selecionado");
				}
				else {
					getCentral().getAssociacao().remove(getTabela().getSelectedRow());
					dispose();
					
					try {
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					new TelaDeAssociacao(getCentral());
				    new TelaDeAviso("Associação excluida");

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
				new TelaDeAssociacao(getCentral());
			}
		});
		getPainel().add(getSair());
		
	}
	
	public void valor() {
		JLabel s = new JLabel("Novo Valor R$");
		s.setBounds(120, 400, 80, 20);
		getPainel().add(s);
		
		valor = new JTextField("0.00");
		valor.setBounds(205, 398, 90, 25);
		getPainel().add(valor);
		
	}

}
