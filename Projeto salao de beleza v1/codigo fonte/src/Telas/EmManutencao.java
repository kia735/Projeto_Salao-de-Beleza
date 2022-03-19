package Telas;
import java.awt.Font;
import javax.swing.JLabel;


public class EmManutencao extends JFrameBase{
	private static final long serialVersionUID = -2399174847604005563L;

	public EmManutencao() {
		
		notificando();
		
		setVisible(true);
		
	}
	
	public void notificando() {
		
		Font fontDoButao = new Font("Roboto", Font.BOLD, 18);
		JLabel msg = new JLabel("Em Desenvolvimento");
		msg.setBounds(130, 150, 200, 200);
		msg.setFont(fontDoButao);

		
	}
	
	
}
