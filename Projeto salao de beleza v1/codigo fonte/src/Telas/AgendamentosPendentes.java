package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Back.Agenda;
import Back.PagamentosColaborador;
import Back.Persistencia;
import Back.Salao;
import Back.Status;


public class AgendamentosPendentes extends HistoricoDoColaborador {

	private static final long serialVersionUID = 8871789130469875116L;
	private JButton aceitar, rejeitar;

	public AgendamentosPendentes(Salao salao, String email) {
		super(salao, email);
		getPainel().remove(getPainelTabela());
		setTitle("Pendentes");
		configurandoLinhas(email);
		botao(email);
		setVisible(true);
	}

	public void configurandoLinhas( String email) {

		setModelo(new DefaultTableModel());

		getModelo().addColumn("Data");
		getModelo().addColumn("Nome");
		getModelo().addColumn("Status");

		for (Agenda a : getCentral().getHistorico()) {

			if (a.getServico() != null) {
				if (a.getServico().getColaborador().getEmail().equals(email)) {
					if (a.getStatus().equals(Status.pendente)) {
						Object[] linha = new Object[3];
						linha[0] = a.getData();
						linha[1] = a.getServico().getServico().getNome();
						linha[2] = a.getStatus();
						getModelo().addRow(linha);
					}
				}
			}
		}

		setTabela(new JTable(getModelo()));
		getTabela().setDefaultEditor(Object.class, null);
		getTabela().setBackground(Color.decode("#cccccc"));
		setPainelTabela(new JScrollPane(getTabela()));
		getPainelTabela().setBounds(80, 100, 270, 270);

		getPainel().add(getPainelTabela());

	}

	public void botao(String email) {

		aceitar = new JButton("Concluir");
		aceitar.setFont(getFontDoButao());
		aceitar.setBackground(Color.decode("#CD7C8D"));
		aceitar.setBounds(270, 420, 100, 25);
		aceitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getTabela().getSelectedRow() < 0) {
					new TelaDeAviso("Nenhum índice selecionado");
				} else {
					PagamentosColaborador.adicionandoValorAoCaixa( getCentral(), getTabela());
					try {
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					} catch (Exception e1) {
						new TelaDeAviso("Não foi possivel aceitar");
					}

				}
				dispose();
				new TelaPrincipalColaborador(getCentral(), email);
				new TelaDeAviso("Horario concluido");

			}
		});
		getPainel().add(aceitar);

		rejeitar = new JButton("Cancelar");
		rejeitar.setFont(getFontDoButao());
		rejeitar.setBackground(Color.decode("#CD7C8D"));
		rejeitar.setBounds(70, 420, 100, 25);
		rejeitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getTabela().getSelectedRow() < 0) {
					new TelaDeAviso("Nenhum índice selecionado");
				} else {
					 getCentral().getHistorico().get(getTabela().getSelectedRow()).setStatus(Status.cancelado);
					 getCentral().adicionarAosRegistros(getTabela().getSelectedRow());
					 getCentral().removerHistorico(getTabela().getSelectedRow());
					try {
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
						dispose();
						new TelaPrincipalColaborador(getCentral(), email);
						new TelaDeAviso("Cancelado");
					} catch (Exception e1) {
						new TelaDeAviso("Não foi possivel cancelar");
					}
				}
			}
		});
		getPainel().add(rejeitar);

	}

	public JButton getAceitar() {
		return aceitar;
	}

	public void setAceitar(JButton aceitar) {
		this.aceitar = aceitar;
	}

	public JButton getRejeitar() {
		return rejeitar;
	}

	public void setRejeitar(JButton rejeitar) {
		this.rejeitar = rejeitar;
	}

}
