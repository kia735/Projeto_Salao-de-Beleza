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
import Back.Salao;

public class HistoricoDoColaborador extends JFrameBase {
	
	private static final long serialVersionUID = -976573976216354685L;
	private JTable tabela;
	private DefaultTableModel modelo;
	private JScrollPane painelTabela;
	private JButton sair;
	
	public HistoricoDoColaborador(Salao salao, String email) {
		setCentral(salao);
		setTitle("Historico Do Colaborador");
		configurandoTabela(email);
		botao(email);
		setVisible(true);
		
	}
	
	public void configurandoTabela(String email) {

		modelo = new DefaultTableModel();

		modelo.addColumn("Data");
		modelo.addColumn("Nome");
		modelo.addColumn("Status");

		for (Agenda a :getCentral().getRegistros()) {

			if (a.getServico() != null) {
				if (a.getServico().getColaborador().getEmail().equals(email)) {
					Object[] linha = new Object[3];
					linha[0] = a.getData();
					linha[1] = a.getNome();
					linha[2] = a.getStatus();
					modelo.addRow(linha);
				}
			}
		}

		tabela = new JTable(modelo);
		tabela.setDefaultEditor(Object.class, null);
		tabela.setBackground(Color.decode("#cccccc"));
		painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(80, 100, 270, 270);

		getPainel().add(painelTabela);

	}
	
	public void botao(String email) {

		sair = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		sair.setBounds(380, 465, 30, 30);
		sair.setBorder(null);
		sair.setOpaque(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaPrincipalColaborador(getCentral(), email);
			}
		});
		getPainel().add(sair);
	
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

	public JScrollPane getPainelTabela() {
		return painelTabela;
	}

	public void setPainelTabela(JScrollPane painelTabela) {
		this.painelTabela = painelTabela;
	}

	public JButton getSair() {
		return sair;
	}

	public void setSair(JButton sair) {
		this.sair = sair;
	}

}
