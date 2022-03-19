package Back;
public abstract class Pessoa {
	private String nome;
	private String email;
	private Generos sexo;
	
	public Generos getSexo() {
		return sexo;
	}
	public void setSexo(Generos genero) {
		this.sexo = genero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
