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
import Back.Colaborador;
import Back.Persistencia;
import Back.Salao;
import Back.Servico;

public class AssociadorSAC extends EnviaEmailC {
	
	private static final long serialVersionUID = 192364912086571340L;

	public AssociadorSAC(Salao s) {
		
		super(s);
		setTitle("Vicular Serviços ao Colaborador");
		getPainel().remove(getButaoDeExcluir());
		getPainel().remove(getButaoDeEditar());
		getPainel().remove(getSair());
		botoes();
		valor();
		setVisible(true);

	}

	private JTable tabelaDeServico;
	private JTextField valor;

	@SuppressWarnings("serial")
	public void tabela() {

		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int lin, int colun) {
				return false;
			}
		};
		modelo.addColumn("Selecione o Colaborador");

		for (int i = 0; i < getCentral().getTodosOsColaboradores().size(); i++) {
			Colaborador c = getCentral().getTodosOsColaboradores().get(i);
			Object[] linha = new Object[1];
			linha[0] = c.getNome();
			modelo.addRow(linha);
		}

		setTabela(new JTable(modelo));
		getTabela().setBackground(Color.decode("#cccccc"));
		JScrollPane scroll = new JScrollPane(getTabela());
		scroll.setBounds(80, 25, 250, 180);
		getPainel().add(scroll);

		DefaultTableModel modelo1 = new DefaultTableModel() {
			public boolean isCellEditable(int lin, int colun) {
				return false;
			}
		};
		modelo1.addColumn("Selecione o Serviço");

		for (int i = 0; i < getCentral().getServicos().size(); i++) {
			Servico s = getCentral().getServicos().get(i);
			Object[] linha = new Object[1];
			linha[0] = s.getNome();
			modelo1.addRow(linha);
		}

		tabelaDeServico = new JTable(modelo1);
		tabelaDeServico.setBackground(Color.decode("#cccccc"));
		JScrollPane scroll1 = new JScrollPane(tabelaDeServico);
		scroll1.setBounds(80, 220, 250, 180);
		getPainel().add(scroll1);

	}

	public void botoes() {

		setButaoDeExcluir(new JButton("Vincular Dados"));
		getButaoDeExcluir().setFont(getFontDoButao());
		getButaoDeExcluir().setBackground(Color.decode("#CD7C8D"));
		getButaoDeExcluir().setBounds(140, 450, 150, 25);
		getButaoDeExcluir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getTabela().getSelectedRow() < 0 || tabelaDeServico.getSelectedRow() < 0) {
					new TelaDeAviso("Selecione Colaborador e Serviço");
				} else {
					try {
						Associador f = new Associador(
								getCentral().getTodosOsColaboradores().get(getTabela().getSelectedRow()),
								getCentral().getServicos().get(tabelaDeServico.getSelectedRow()).clonar());
						f.getServico().setPreco(Float.parseFloat(valor.getText()));

						if (getCentral().buscarAcossiacao(f.getColaborador().getEmail(), f.getServico().getId())) {

							getCentral().adicionarAssociador(f);

							Persistencia.salvarCentral(getCentral(), "arquivo-Central");

							new TelaDeAviso("Associação concluida");
						} else {
							new TelaDeAviso("Associação ja existente");
						}
					} catch (Exception e1) {
						new TelaDeAviso("Formato passado nao aceito. R$ 10.50");
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

				new TelaDeAssociacao(getCentral());
			}
		});
		getPainel().add(getSair());
	}

	public void valor() {
		JLabel s = new JLabel("Valor do Serviço R$");
		s.setBounds(120, 415, 115, 20);
		getPainel().add(s);

		valor = new JTextField("0.00");
		valor.setBounds(240, 413, 90, 25);
		getPainel().add(valor);

	}

	public JTable getTabelaDeServico() {
		return tabelaDeServico;
	}

	public void setTabelaDeServico(JTable tabelaDeServico) {
		this.tabelaDeServico = tabelaDeServico;
	}

	public JTextField getValor() {
		return valor;
	}

	public void setValor(JTextField valor) {
		this.valor = valor;
	}

}