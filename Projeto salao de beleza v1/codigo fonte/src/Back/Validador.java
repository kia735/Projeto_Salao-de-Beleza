package Back;
import java.util.Random;
public class Validador {
	
	public Validador(String email) {
		enviarEmail(email);
	}
	
	private int codigo;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void enviarEmail(String email) {
		Random random = new Random();
	    this.codigo = random.nextInt(1000) *100 ;
		String mensagem = "Seu codigo de verificação de email; " + this.codigo;
		Mensageiro m = new Mensageiro();
		m.enviaremail(email, mensagem);
	}
	
}
