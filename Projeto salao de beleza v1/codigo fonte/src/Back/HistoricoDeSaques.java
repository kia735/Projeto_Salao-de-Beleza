package Back;

import java.sql.Date;

public class HistoricoDeSaques {
	public HistoricoDeSaques(float v) {
	    data = new Date(System.currentTimeMillis());
		valor = v;
	}
	
	private Date data;
	private float valor;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
}
