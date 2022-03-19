package Telas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import Back.Colaborador;
import Back.Generos;
import Back.Persistencia;
import Back.Salao;

public class EditarColaborador extends TelaDeCadastroColaborador {
	
	private static final long serialVersionUID = 8454534819014530433L;

	public EditarColaborador(Salao s, int p) {
		super(s);
		setTitle("Editar colaborador");
		posicao = p;
		preencher();
		getPainel().remove(getCadastrar());
		botao();
		setVisible(true);
	}
	
	private int posicao;

	public void preencher() {
		Colaborador c = getCentral().getTodosOsColaboradores().get(posicao);
		getTNome().setText(c.getNome());
		getTTelefone().setText(c.getTelefone());
		getTEmail().setText(c.getEmail());
		getTSenha().setText(c.getSenha());
		if(c.getSexo() != null) {
			if (c.getSexo().equals(Generos.FEMININO)) {
				getF().setSelected(true);
			} else {
				getM().setSelected(true);
			}
		}
	}
	
	public void botao() {
		setCadastrar(new JButton("Editar"));
		getCadastrar().setFont(getFontDoButao());
		getCadastrar().setBackground(Color.decode("#CD7C8D"));
		getCadastrar().setBounds(160, 420, 100, 25);
		getCadastrar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(getTitle().equals("Editar colaborador")) {
					try {
						
						getCentral().getTodosOsColaboradores().get(posicao).setEmail(getTEmail().getText());
						getCentral().getTodosOsColaboradores().get(posicao).setSenha(getTSenha().getText());
						getCentral().getTodosOsColaboradores().get(posicao).setNome(getTNome().getText());
						getCentral().getTodosOsColaboradores().get(posicao).setTelefone(getTTelefone().getText());
						
						dispose();
						
						Persistencia.salvarCentral(getCentral(), "arquivo-Central");
						
						 new TelaDeSelecaoColaborador (getCentral());
						 new TelaDeAviso("Dados do colaborador editado");
						
					} catch (Exception e1) {
						 new TelaDeAviso("Dados do colaborador não editado");
					}
					
				}
			}
		});
		getPainel().add(getCadastrar());
	}
	
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
}
