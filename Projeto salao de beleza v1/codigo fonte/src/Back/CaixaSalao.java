package Back;
import java.util.ArrayList;

public class CaixaSalao {
	public CaixaSalao(Salao s) {
		central = s;
		saldo = 0;
		porcentagem = 20;
	}
	private Salao central;	
	private float saldo;
	private int porcentagem;
	
	private ArrayList<Agenda> recebidos = new ArrayList<Agenda>();
	private ArrayList<HistoricoDeSaques> saques  = new ArrayList<HistoricoDeSaques>(); 
	private ArrayList<PagamentosColaborador> adiantamentos  = new ArrayList<PagamentosColaborador>();
	
	public Salao getCentral() {
		return central;
	}
	public void setCentral(Salao central) {
		this.central = central;
	}
	
	public int getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(int porcentagem) {
		this.porcentagem = porcentagem;
	}

	public boolean sacar(float i) {
		if(saldo < i) {
			return false;
		}
		else {
			HistoricoDeSaques h = new HistoricoDeSaques(i);
			saques.add(h);
			saldo = saldo - i;
			return true;
		}
	}
	
	public void depositar(float a){
		saldo += a;
	}
	
	public boolean adiantarPagamento(float v, String email) {
		if(saldo > v) {
			for(int i = 0; i < central.getTodosOsColaboradores().size(); i++) {
				if(central.getTodosOsColaboradores().get(i).getEmail().equals(email)){
					central.getTodosOsColaboradores().get(i).setSalario(central.getTodosOsColaboradores().get(i).getSalario() + v );
					adiantamentos.add(new PagamentosColaborador(v, central.getTodosOsColaboradores().get(i)));
					saldo = saldo - v;
					return true;
				}
			}
		}
		
		return false;
	}	
	
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public ArrayList<Agenda> getRecebidos() {
		return recebidos;
	}
	public void setRecebidos(ArrayList<Agenda> recebidos) {
		this.recebidos = recebidos;
	}

	public ArrayList<HistoricoDeSaques> getSaques() {
		return saques;
	}
	public void setSaques(ArrayList<HistoricoDeSaques> saques) {
		this.saques = saques;
	}

	public ArrayList<PagamentosColaborador> getAdiantamentos() {
		return adiantamentos;
	}
	public void setAdiantamentos(ArrayList<PagamentosColaborador> adiantamentos) {
		this.adiantamentos = adiantamentos;
	}

	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
