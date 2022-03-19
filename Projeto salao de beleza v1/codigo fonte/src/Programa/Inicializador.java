package Programa;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import Back.Persistencia;
import Back.Salao;
import Telas.CadastroDoAdm;
import Telas.Inicio;

public class Inicializador {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		
		Persistencia recuperar = new Persistencia();
		Salao central = recuperar.recuperarCentra("arquivo-Central");
		
	try {
			
			FlatArcOrangeIJTheme.setup();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				if(central.getProprietario() != null) {
					
					 new Inicio(central);
					 
				}else {
					 new CadastroDoAdm(central);
				}
				
			}
		});
		recuperar.salvarCentral(central, "arquivo-Central");
		
	}
}

