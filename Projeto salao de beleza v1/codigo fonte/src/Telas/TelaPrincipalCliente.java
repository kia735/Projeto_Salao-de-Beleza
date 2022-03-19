package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import Back.Salao;

public class TelaPrincipalCliente extends TelaPrincipalBase {

	private static final long serialVersionUID = 899815332055216635L;

	public TelaPrincipalCliente(Salao salao) {

		setCentral(salao);
		imagens();
		botoes();
		setTitle("Cliente");
		setVisible(true);

	}

	private ImageIcon iconWelcone, iconHistorico, iconAgendar;
	private JLabel welcone;
	private JButton historico, agendar;

	public void imagens() {

		iconWelcone = new ImageIcon(getClass().getResource("/img/IconBemVindx.png"));
		welcone = new JLabel(iconWelcone);
		welcone.setBounds(60, 0, 251, 131);
		getPainel().add(welcone);

	}

	public void botoes() {

		iconHistorico = new ImageIcon(getClass().getResource("/img/iconHistory.png"));
		historico = new JButton(iconHistorico);
		historico.setBorder(null);
		historico.setText("Em Aberto");
		historico.setForeground(Color.decode("#71C2FF"));
		historico.setBackground(Color.decode("#cccccc"));
		historico.setBounds(130, 140, 140, 70);
		historico.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (getTitle().equalsIgnoreCase("Cliente")) {
					dispose();
					new HistoricoDoCliente(getCentral());
				}
			}
		});
		getPainel().add(historico);

		iconAgendar = new ImageIcon(getClass().getResource("/img/iconSchedule.png"));
		agendar = new JButton(iconAgendar);
		agendar.setBorder(null);
		agendar.setText("Agendar");
		agendar.setForeground(Color.decode("#F78F8F"));
		agendar.setBackground(Color.decode("#cccccc"));
		agendar.setBounds(130, 250, 140, 78);
		agendar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (getTitle().equals("Cliente")) {
					try {
						new SolicitarServico(getCentral());
						dispose();
					} catch (Exception e1) {
						new TelaDeAviso("Ops, estamos sem proficionais ativos!");
					}
				}
			}
		});
		getPainel().add(agendar);

	}

	public ImageIcon getIconWelcone() {
		return iconWelcone;
	}

	public void setIconWelcone(ImageIcon iconWelcone) {
		this.iconWelcone = iconWelcone;
	}

	public ImageIcon getIconAgendar() {
		return iconAgendar;
	}

	public void setIconAgendar(ImageIcon iconAgendar) {
		this.iconAgendar = iconAgendar;
	}

	public JLabel getWelcone() {
		return welcone;
	}

	public void setWelcone(JLabel welcone) {
		this.welcone = welcone;
	}

	public JButton getAgendar() {
		return agendar;
	}

	public void setAgendar(JButton agendar) {
		this.agendar = agendar;
	}

	public ImageIcon getIconHistorico() {
		return iconHistorico;
	}

	public void setIconHistorico(ImageIcon iconHistorico) {
		this.iconHistorico = iconHistorico;
	}

	public JButton getHistorico() {
		return historico;
	}

	public void setHistorico(JButton historico) {
		this.historico = historico;
	}
}
