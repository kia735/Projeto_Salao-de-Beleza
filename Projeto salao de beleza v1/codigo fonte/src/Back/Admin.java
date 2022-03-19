package Back;
import javax.swing.ImageIcon;

public class Admin extends Pessoa {
	private ImageIcon perfil;
	private String numero;
	private String senha;

	public Admin(String n, String e, String s) {
		senha = s;
		setEmail(e);
		setNome(n);
	}
	public Admin() {
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public ImageIcon getPerfil() {
		return perfil;
	}
	public void setPerfil(ImageIcon perfil) {
		this.perfil = perfil;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
