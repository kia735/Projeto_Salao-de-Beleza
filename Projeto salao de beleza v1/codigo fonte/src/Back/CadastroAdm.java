package Back;

public class CadastroAdm {

	public static void cadastrandoAdm(Salao central, String text, String text2, String senha) {
		
		Admin proprietario = new Admin(text,text2,senha);
		central.setProprietario(proprietario);
	}
}
