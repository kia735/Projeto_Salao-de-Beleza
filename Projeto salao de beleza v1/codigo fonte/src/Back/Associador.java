package Back;
public class Associador {

	public Associador(Colaborador c, Servico s) {
		colaborador = c;
		servico = s;
	}
	
	public Associador() {
	}
	
	private Colaborador colaborador;
	private Servico servico;

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public String toString() {
		return "Colaborador: " + colaborador.toString() + "\nServiço: " + servico.toString();
	}

}
