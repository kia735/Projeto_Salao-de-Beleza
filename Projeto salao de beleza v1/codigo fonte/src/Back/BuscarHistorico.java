package Back;

import java.util.ArrayList;



public class BuscarHistorico {
	
	private static ArrayList<Agenda> listDeAgenda;

	public static ArrayList<Agenda> getAgenda() {
		return listDeAgenda;
	}

	public static void setAgenda(ArrayList<Agenda> agenda) {
		listDeAgenda = agenda;
	}
	
	public static ArrayList<Agenda> buscar(Salao salao, String email) {
		listDeAgenda = new ArrayList<Agenda>();
		
		for (Agenda a : salao.getHistorico()) {
			if (a != null) {
				if (a.getEmail().equals(email)) {
					listDeAgenda.add(a);
				}
			}
		}
		return listDeAgenda;
	}	

}
