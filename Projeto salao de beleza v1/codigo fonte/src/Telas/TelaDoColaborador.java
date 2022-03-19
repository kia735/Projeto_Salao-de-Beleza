package Telas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Back.Salao;

public class TelaDoColaborador extends JFrameBase {
	
	private static final long serialVersionUID = 1765332339260426615L;

	public TelaDoColaborador(Salao s) {
			setCentral(s);
			addImagens();
			botoes();
			setVisible(true);
		}
		
	private ImageIcon IconAdd, IconEdit, IconSair;
	private JButton adicionar, editar, vincular, sair;
	
	public void addImagens() {
		IconSair = new ImageIcon(getClass().getResource("/img/iconExit.png"));
		IconEdit = new ImageIcon(getClass().getResource("/img/IconeditarUsuário.png"));
		IconAdd = new ImageIcon(getClass().getResource("/img/IconAdicionarUsuário.png"));
	}
	
	public void botoes() {

		adicionar = new JButton(IconAdd);
		adicionar.setBounds(150, 80, 100, 100);
		adicionar.setBorder(null);
		adicionar.setOpaque(false);
		adicionar.setContentAreaFilled(false);
		adicionar.setBackground(Color.decode("#CD7C8D"));
		adicionar.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				dispose();
			    new TelaDeCadastroColaborador(getCentral());
						
			}
		  }
		);
		getPainel().add(adicionar);

		editar = new JButton(IconEdit);
		editar.setBounds(150, 290, 100, 100);
		editar.setBorder(null);
		editar.setOpaque(false);
		editar.setContentAreaFilled(false);
		editar.setBackground(Color.decode("#CD7C8D"));
		editar.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				dispose();
			    new TelaDeSelecaoColaborador(getCentral());		
			}
		  }
		);
		getPainel().add(editar);
		
		
		
		sair = new JButton(IconSair);
		sair.setBounds(350, 430, 30, 30);
		sair.setBorder(null);
		sair.setOpaque(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaPrincipalAdm(getCentral());
			}
		});
		getPainel().add(sair);
		
	}
	
	public ImageIcon getIconAdd() {
		return IconAdd;
	}
	public void setIconAdd(ImageIcon iconAdd) {
		IconAdd = iconAdd;
	}

	public ImageIcon getIconEdit() {
		return IconEdit;
	}
	public void setIconEdit(ImageIcon iconEdit) {
		IconEdit = iconEdit;
	}

	public JButton getAdicionar() {
		return adicionar;
	}
	public void setAdicionar(JButton adicionar) {
		this.adicionar = adicionar;
	}

	public JButton getEditar() {
		return editar;
	}
	public void setEditar(JButton editar) {
		this.editar = editar;
	}

	public JButton getVincular() {
		return vincular;
	}
	public void setVincular(JButton vincular) {
		this.vincular = vincular;
	}

}
