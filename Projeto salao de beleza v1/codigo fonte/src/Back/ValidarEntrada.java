package Back;

import java.util.regex.Matcher;


import java.util.regex.Pattern;

import javax.swing.JPasswordField;

public class ValidarEntrada {
	private Salao central;
	public ValidarEntrada(Salao p) {
		central = p;
	}
	
	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	
	public static boolean validarEmail(String campoEmail) {
		
		Matcher  matcher = pattern.matcher(campoEmail);
	    return matcher.matches();
		
	}
	
	public static boolean validarSenha(String campoSenha) {
		
		if(campoSenha.length() >= 6) {
			
			return true;
		}
		
		return false;
	}
	
	public static void configurarVisibilidadeDeSenha(JPasswordField campoSenha) {
		if(campoSenha.getEchoChar() == '*'){
			campoSenha.setEchoChar((char) 0);
		}else{
			campoSenha.setEchoChar('*');
		}
		
	}
	public boolean souAdmin(String campoEmail, String campoSenha) {
		if(campoEmail.equals(central.getProprietario().getEmail()) &&
				campoSenha.equals(central.getProprietario().getSenha())) {
			return true;
		}
		return false;
	}
		

	public Colaborador souColaborador(String campoEmail, String campoSenha){
		for (int i = 0; i < central.getTodosOsColaboradores().size(); i++) {
			if (central.getTodosOsColaboradores().get(i) != null) {
				if (central.getTodosOsColaboradores().get(i).getEmail().equals(campoEmail)
						&& central.getTodosOsColaboradores().get(i).getSenha().equals(campoSenha)) {
					return central.getTodosOsColaboradores().get(i);
				}
			}
		}
		return null;
		
	}

}
