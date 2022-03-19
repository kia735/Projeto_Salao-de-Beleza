package Back;
import java.util.ArrayList;

public class Cliente extends Pessoa{
	private ArrayList<Agenda> agendar = new ArrayList<Agenda>();
	

	public ArrayList<Agenda> getAgendar() {
		return agendar;
	}
	public void AdicionarNaAgenda(Agenda agenda) {
		this.agendar.add(agenda);
	}
}
