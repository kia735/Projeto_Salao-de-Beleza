package Back;

interface Prototype{
	public Servico clonar();
}

public class Servico implements Prototype {

	public Servico(Servico s){
		id = s.getId();
		this.nome = s.nome;
		this.descricao = s.descricao;
		this.duracaoMedia = s.duracaoMedia;
		this.preco = s.preco;
	}

	public Servico(String nome, String descricao, int duracaoMedia, float preco) {
		id =  System.currentTimeMillis();
		this.nome = nome;
		this.descricao = descricao;
		this.duracaoMedia = duracaoMedia;
		this.preco = preco;
	}
	
	private long id;
	private String nome, descricao;
	private int duracaoMedia;
	private float preco;
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getDuracaoMedia() {
		return duracaoMedia;
	}
	public void setDuracaoMedia(int duracaoMedia) {
		this.duracaoMedia = duracaoMedia;
	}

	public String toString() {
		return "Nome: " + getNome() + ", ID: " + getId() + ", Descrição: " + getDescricao() + ", Duraçao: " + getDuracaoMedia()+ 
				"min" + " , Preço: " + getPreco()+"R$";
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public Servico clonar() {
		return new Servico(this);
	}
}