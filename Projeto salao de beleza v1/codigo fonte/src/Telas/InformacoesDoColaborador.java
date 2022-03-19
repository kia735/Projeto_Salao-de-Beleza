package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Back.Salao;

public class InformacoesDoColaborador extends EditarColaborador{

	private static final long serialVersionUID = 765502894620393107L;

	public InformacoesDoColaborador(Salao salao, int i, String email) {
		super(salao, i);
		
		getPainel().remove(getCadastrar());
		desabilitarEdicao();
		acaoDoBotao(email);
		setTitle("Seu Perfil");
	}
	
	public void desabilitarEdicao() {
		
		getTNome().setEditable(false);
		getTEmail().setEditable(false);
		getTTelefone().setEditable(false);
		getTSenha().setEditable(false);
		getM().setEnabled(false);
		getF().setEnabled(false);
	}
	
	public void acaoDoBotao( String email) {
		
		getSair().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTitle().equals("Seu Perfil")) {
					dispose();
					new TelaPrincipalColaborador(getCentral(),email);
				}
			}
		});
	}

}

