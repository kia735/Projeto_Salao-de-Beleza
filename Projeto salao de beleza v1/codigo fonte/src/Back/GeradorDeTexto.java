package Back;

public class GeradorDeTexto {

	public static String Gerador(Salao central) {
		
		String texto = "";
		
		for(int i = 0; i < central.getServicos().size(); i++) {
			
			texto += central.getServicos().get(i).toString() + ": \n";
			
			for(int x = 0; x < central.getAssociacao().size(); x++) {
				
				if(central.getServicos().get(i).getId() == central.getAssociacao().get(x).getServico().getId()) {
					
					texto += central.getAssociacao().get(x).getColaborador().getNome() + ". \n";
				}
			}
		}
		
		return texto;
	}

}
