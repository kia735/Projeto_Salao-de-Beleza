package Back;
import java.util.ArrayList;

public class Salao {
	
	private Admin proprietario;
	private CaixaSalao caixa = new CaixaSalao(this);
	private ArrayList<Colaborador> todosOsColaboradores = new ArrayList<Colaborador>();
	private ArrayList<Servico> servicos = new ArrayList<Servico>();
	private ArrayList<Associador> associacao = new ArrayList<Associador>();
	private ArrayList<Agenda> historico = new ArrayList<Agenda>();
	private ArrayList<Agenda> registros = new ArrayList<Agenda>();
	
	public ArrayList<Agenda> getRegistros() {
		return registros;
	}
	public void setRegistros(ArrayList<Agenda> registros) {
		this.registros = registros;
	}
	
	public CaixaSalao getCaixa() {
		return caixa;
	}
	public void setCaixa(CaixaSalao caixa) {
		this.caixa = caixa;
	}
	
	public void setServicos(ArrayList<Servico> servicos) {
		this.servicos = servicos;
	}
	public ArrayList<Agenda> getHistorico() {
		return historico;
	}
	
	public void setHistorico(ArrayList<Agenda> historico) {
		this.historico = historico;
	}
	public void guardaNoHistoricoAgenda(Agenda historico) {
		this.historico.add(historico);
	}
	
	public Admin getProprietario() {
		return proprietario;
	}
	public void setProprietario(Admin proprietario) {
		this.proprietario = proprietario;
	}
	
	public ArrayList<Colaborador> getTodosOsColaboradores() {
		return todosOsColaboradores;
	}
	public void setTodosOsColaboradores(ArrayList<Colaborador> todosOsColaboradores) {
		this.todosOsColaboradores = todosOsColaboradores;
	}
	
	public ArrayList<Servico> getServicos() {
		return servicos;
	}
	public void setServiços(ArrayList<Servico> serviços) {
		this.servicos = serviços;
	}
	
	public ArrayList<Associador> getAssociacao() {
		return associacao;
	}
	public void setAssociacao(ArrayList<Associador> associacao) {
		this.associacao = associacao;
	}
	
	public boolean adicionarColaborador(Colaborador p) {
			this.todosOsColaboradores.add(p);
			if(todosOsColaboradores.contains(p)) {
				return true;
		     } 
			return false;
	}
	public Colaborador recuperarColaborador(String e) {
		Colaborador v = null;
		for(int i = 0; i < todosOsColaboradores.size(); i++) {
			if(todosOsColaboradores.get(i).getEmail().equals(e) == true) {
				v = todosOsColaboradores.get(i);
			}
	     }
		return v;
	}
	
	public boolean adicionarServiço(Servico a1) {
		if(recuperarServiço(a1.getId()) == null) {
			servicos.add(a1);
			return true;
	     } 
		return false;
	}
	public Servico recuperarServiço(long i) {
		Servico s = null;
		for(int n = 0; n < servicos.size(); n++) {
			if(servicos.get(n).getId() == i) {
				s = servicos.get(n);
			}
	     }
		return s;
	}
	
	public void adicionarAssociador(Associador s ) {
		associacao.add(s);
	}
	
	public boolean buscarAcossiacao(String e, long i1) {
		for(int i = 0; i < associacao.size(); i++) {
			if(associacao.get(i).getColaborador().getEmail().equals(e) && associacao.get(i).getServico().getId() == i1) {
				return false;
			 }
		}
		return true;
	}
	
	public boolean adicionarAosRegistros(int a) {
		try {
			registros.add(historico.get(a));
			return true;
		}catch(Exception e){
			System.out.print("registro não adicionado");
		}
		return false;
	}
	public boolean removerHistorico(int h) {
		historico.remove(h);
		try {
			historico.get(h);
			return false;
		}catch(Exception e) {
		}
		return true;
	}
}
