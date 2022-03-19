package Back;
public class Agenda {
	
	private String nomeDoCliente,hora, data, email; 
	private Status status = Status.pendente; 
	private Associador servico;
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getNomeDoCliente() {
		return nomeDoCliente;
	}
	public void setNomeDoCliente(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Associador getServico() {
		return servico;
	}
	public void setServico(Associador servico) {
		this.servico = servico;
	}
	public String getNome() {
		return nomeDoCliente;
	}
	public void setNome(String nome) {
		this.nomeDoCliente = nome;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
