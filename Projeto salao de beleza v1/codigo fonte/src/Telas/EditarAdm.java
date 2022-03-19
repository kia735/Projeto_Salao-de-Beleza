package Telas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import Back.Generos;
import Back.Persistencia;
import Back.Salao;


public class EditarAdm extends TelaDeCadastroColaborador{
	
	private static final long serialVersionUID = 1294553058164845328L;

		public EditarAdm(Salao s) {
			super(s);
			setTitle("Editor Proprietario");
			preencher();
			getPainel().remove(getCadastrar());
			getPainel().remove(getSair());
			getPainel().remove(getLabel());
			botao();
			imagem();
			setVisible(true);
		}
		
		public void imagem() {
			setLabel(new JLabel(new ImageIcon(getClass().getResource("/img/iconAdm.png"))));
			getLabel().setBounds(120, 30, 172, 130);
			getPainel().add(getLabel());
		}
		
		public void preencher() {
			getTNome().setText(getCentral().getProprietario().getNome());
			getTTelefone().setText(getCentral().getProprietario().getNumero());
			getTEmail().setText(getCentral().getProprietario().getEmail());
			getTSenha().setText(getCentral().getProprietario().getSenha());
			if(getCentral().getProprietario().getSexo() != null) {
				if (getCentral().getProprietario().getSexo().equals(Generos.FEMININO)) {
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
						try {
							
							getCentral().getProprietario().setEmail(getTEmail().getText());
							getCentral().getProprietario().setSenha(getTSenha().getText());
							getCentral().getProprietario().setNome(getTNome().getText());
							getCentral().getProprietario().setNumero(getTTelefone().getText());
							getCentral().getProprietario().setSexo(getGen());
							
							dispose();
							
							Persistencia.salvarCentral(getCentral(), "arquivo-Central");
							
							 new TelaPrincipalAdm (getCentral());
							 new TelaDeAviso("Dados editados");
							
						} catch (Exception e1) {
							 new TelaDeAviso("Dados não editados");
						}
				}
			});
			getPainel().add(getCadastrar());
			
			setSair(new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png"))));
			getSair().setBounds(380, 450, 30, 30);
			getSair().setBorder(null);
			getSair().setOpaque(false);
			getSair().setContentAreaFilled(false);
			getSair().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new TelaPrincipalAdm (getCentral());
				}
			});
			getPainel().add(getSair());
			
		}
}


