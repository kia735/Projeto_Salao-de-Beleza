package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class TelaDeAviso extends JFrameBase{
	
	private static final long serialVersionUID = -1197631747410326704L;

	public TelaDeAviso(String msg) {
		this.msg = msg;
		setSize(350,250);
		notificando();
		butOk();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void notificando() {
		
		Font fontDoButao = new Font("Roboto", Font.BOLD, 18);
		JTextArea msg = new JTextArea(getMsg());
		msg.setWrapStyleWord(true);
		msg.setLineWrap(true);
		msg.setBounds(85, 50, 150, 100);
		msg.setBackground(Color.decode("#cccccc"));
		msg.setFont(fontDoButao);
		msg.setEditable(false);
		getPainel().add(msg);
		
	}
	
	private String msg;
	private JButton ok;

	public void butOk() {
		ok = new JButton(" OK ");
		ok.setBorder(null);
		ok.setBackground(Color.decode("#CD7C8D"));
		ok.setBounds(130, 150, 70, 25);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}	
		});
		getPainel().add(ok);
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
