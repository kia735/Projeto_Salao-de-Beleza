package Back;

public class BuscarColaboradores {
	
	private  Salao central;
	
	public BuscarColaboradores(Salao salao) {
		central = salao;
	}
	
	public String[] listaDeServicos(){
		
		String[] osServicos = new String[central.getServicos().size()];
		osServicos[0] = "vazio";
		
		for (int i = 0; i < central.getServicos().size(); i++) {
			if (central.getServicos().get(i) != null) {
				osServicos[i] = central.getServicos().get(i).getNome();
			}
		}
		return osServicos;
	
	}
	    		
	
	public String[] listaDeColaboradores(){
		
		String[] osColaboradores = new String[central.getTodosOsColaboradores().size()];
		osColaboradores[0] = "vazio";
		
		for (int i = 0; i <central.getTodosOsColaboradores().size(); i++) {
			if (central.getServicos().get(i) != null) {
				osColaboradores[i] = central.getTodosOsColaboradores().get(i).getNome();
			}
		}
		return osColaboradores;
	
	}
}
