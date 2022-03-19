package Back;

public class Colaborador extends Pessoa{
	
	private String senha;
	private String telefone;
	private float salario;
	
	
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Colaborador(String name, String tell, Generos g, String e, String s) {
		setNome(name);
		telefone = tell;
		setSexo(g);
		setEmail(e);
		senha = s;
		salario = 0;
	}
	public Colaborador() {
	}
	
	public String toString() {
		return "Nome: " +  getNome() + ", Num: " + telefone + ", Sexo: " + String.valueOf(getSexo())
		+ ", E-mail: " +  getEmail();
	}
	public boolean equals(Colaborador p) {
		if(getEmail().equals(p.getEmail()))
			return true;
		return false;
	}
	
	public  static float previaSalarial(Salao salao, String email) {
		for(Colaborador c : salao.getTodosOsColaboradores()) {
			if(c != null) {
				if(c.getEmail().equals(email)) {
					return c.getSalario();
				}
			}
		}
		return 0;
	}

}
