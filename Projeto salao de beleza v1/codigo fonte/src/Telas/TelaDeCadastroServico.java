package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import Back.CadastrarServico;
import Back.Persistencia;
import Back.Salao;

public class TelaDeCadastroServico extends JFrameBase{
	
	private static final long serialVersionUID = -3422347154150788932L;


	public TelaDeCadastroServico(Salao salao) throws Exception {
		setCentral(salao);
		label();
		texto();
		separador();
		botao();
		getPainel().setSize(300,300);
		getBarraPadraoSuperio().setSize(100,8);
		getBarraPadraoinferior().setBounds(0, 8, 6, 100);
		setTitle("Novo Serviço");
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private JLabel label;
	private JTextField nomeText, duracaoText, descricaoText;
	private JSeparator separador;
	private JButton salvar, sair;
	
	
	public void label() {
		
		label = new JLabel("Nome:");
		label.setBounds(20, 30, 100, 20);
		getPainel().add(label);
		
		label = new JLabel("Descrição:");
		label.setBounds(20, 80, 100, 20);
		getPainel().add(label);
		
		label = new JLabel("Duração:");
		label.setBounds(20, 150, 100, 20);
		getPainel().add(label);
		
		
	}
	
	public void texto() throws Exception {
		
		nomeText = new JTextField();
		nomeText.setBounds(80, 30, 150, 20);
		nomeText.setBackground(Color.decode("#cccccc"));
		nomeText.setBorder(null);
		getPainel().add(nomeText);
		
		descricaoText = new JTextField();
		descricaoText.setBounds(20, 100, 210, 20);
		descricaoText.setBackground(Color.decode("#cccccc"));
		descricaoText.setBorder(null);
		getPainel().add(descricaoText);
		
		duracaoText =  new JFormattedTextField(new MaskFormatter("##:##"));
		duracaoText.setBounds(80, 150, 50, 20);
		duracaoText.setBackground(Color.decode("#cccccc"));
		duracaoText.setBorder(null);
		getPainel().add(duracaoText);
		
	}
	
	public void separador() {
		
		separador = new JSeparator();
		separador.setBounds(80, 50, 150, 5);
		separador.setBackground(Color.decode("#CD7C8D"));
		separador.setForeground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(20, 120, 210, 5);
		separador.setBackground(Color.decode("#CD7C8D"));
		separador.setForeground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
		separador = new JSeparator();
		separador.setBounds(80, 170, 50, 5);
		separador.setBackground(Color.decode("#CD7C8D"));
		separador.setForeground(Color.decode("#CD7C8D"));
		getPainel().add(separador);
		
		
	}
	
	public void botao() {
		
		salvar = new JButton(new ImageIcon(getClass().getResource("/img/iconChecked.png")));
		salvar.setText("Cadastrar");
		salvar.setBackground(Color.decode("#cccccc"));
		salvar.setBorder(null);
		salvar.setBounds(90, 200, 100, 22);
		salvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					String[] vetor = duracaoText.getText().split(":");
					String horaConv = vetor[0] + vetor[1];
					int tempo = Integer.parseInt(horaConv);
					float  v = 0;
					if(nomeText.getText().equals("") || descricaoText.getText().equals("")) {
						new TelaDeAviso("Erro!! verifique os campos.");
					}
					else{
						if(CadastrarServico.NovoServico(getCentral(), nomeText.getText(), descricaoText.getText(), tempo, v)){
						
							Persistencia.salvarCentral(getCentral(), "arquivo-Central");
							dispose();
							new TelaDeCadastroServico(getCentral());
							new TelaDeAviso("Serviço salvo!");
						}
						else {
							new TelaDeAviso("Serviço não salvo!");
						}
					}
					
				}catch(Exception e1) {
					new TelaDeAviso("Erro!! verifique os campos.");
				}
			}
		});
		
		getPainel().add(salvar);
		
		sair = new JButton(new ImageIcon(getClass().getResource("/img/iconExit.png")));
		sair.setBounds(230, 220, 30, 30);
		sair.setBorder(null);
		sair.setOpaque(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    new TelaDeServico(getCentral());
			}
		});
		getPainel().add(sair);
	}

	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JTextField getNomeText() {
		return nomeText;
	}
	public void setNomeText(JTextField nomeText) {
		this.nomeText = nomeText;
	}

	public JTextField getDuracaoText() {
		return duracaoText;
	}
	public void setDuracaoText(JTextField duracaoText) {
		this.duracaoText = duracaoText;
	}

	public JTextField getDescricaoText() {
		return descricaoText;
	}
	public void setDescricaoText(JTextField descricaoText) {
		this.descricaoText = descricaoText;
	}

	public JSeparator getSeparador() {
		return separador;
	}
	public void setSeparador(JSeparator separador) {
		this.separador = separador;
	}

	public JButton getSalvar() {
		return salvar;
	}
	public void setSalvar(JButton salvar) {
		this.salvar = salvar;
	}
	public JButton getSair() {
		return sair;
	}
	public void setSair(JButton sair) {
		this.sair = sair;
	}
}
