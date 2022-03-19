package Telas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import Back.Persistencia;
import Back.Salao;
import Back.Servico;

public class EditarServico extends TelaDeCadastroServico {
	
	private static final long serialVersionUID = -7013973990496368994L;

	public EditarServico(Salao s, int p) throws Exception {
		super(s);
		setTitle("Editor Servico");
		posicao = p;
		getPainel().remove(getSalvar());
		botao();
		getPainel().remove(getSair());
		preencher();
		setVisible(true);
	}
	
	private int posicao;

	public void preencher() {
		Servico c = getCentral().getServicos().get(posicao);
		getNomeText().setText(c.getNome());
		getDuracaoText().setText(String.valueOf(c.getDuracaoMedia()));
		getDescricaoText().setText(c.getDescricao());
		
	}
	
	public void botao() {
		
		setSalvar(new JButton(new ImageIcon(getClass().getResource("/img/iconChecked.png"))));
		getSalvar().setText("Editar");
		getSalvar().setBackground(Color.decode("#cccccc"));
		getSalvar().setBorder(null);
		getSalvar().setBounds(90, 200, 100, 22);
		getSalvar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					String[] vetor = getDuracaoText().getText().split(":");
					String horaConv = vetor[0] + vetor[1];
					int tempo = Integer.parseInt(horaConv);
					
					getCentral().getServicos().get(posicao).setDuracaoMedia(tempo);
					getCentral().getServicos().get(posicao).setNome(getNomeText().getText());
					getCentral().getServicos().get(posicao).setDescricao(getDescricaoText().getText());
					
					dispose();
					
					Persistencia.salvarCentral(getCentral(), "arquivo-Central");
					
					 new TelaDeSelecaoServico (getCentral());
					 new TelaDeAviso("Dados do Serviço editado");
					
				} catch (Exception e1) {
					 new TelaDeAviso("Erro verifique as informacoes");
				}
			}
		});
		getPainel().add(getSalvar());
		
		setSair(new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png"))));
		getSair().setBounds(380, 465, 30, 30);
		getSair().setBorder(null);
		getSair().setOpaque(false);
		getSair().setContentAreaFilled(false);
		getSair().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaDeSelecaoServico(getCentral());
			}
		});
		getPainel().add(getSair());
	}
	
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
}
