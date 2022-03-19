package Back;

import javax.swing.JTable;

public class PagamentosColaborador extends HistoricoDeSaques{
	public PagamentosColaborador(float v, Colaborador c) {
		super(System.currentTimeMillis());
		setValor(v);
		colaborador = c;
	}
	
	private Colaborador colaborador;

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	
	public static void adicionandoValorAoCaixa(Salao salao, JTable tabela) {
		
		salao.getHistorico().get(tabela.getSelectedRow()).setStatus(Status.concluido);
		salao.adicionarAosRegistros(tabela.getSelectedRow());
		float colaborador = (float) (salao.getHistorico().get(tabela.getSelectedRow()).getServico()
				.getServico().getPreco() * 0.8f);
		salao.getCaixa().depositar(salao.getHistorico().get(tabela.getSelectedRow()).getServico()
				.getServico().getPreco());
		salao.recuperarColaborador(salao.getHistorico().get(tabela.getSelectedRow()).getServico()
				.getColaborador().getEmail())
				.setSalario(salao.getHistorico().get(tabela.getSelectedRow()).getServico()
						.getColaborador().getSalario() + colaborador);
		salao.removerHistorico(tabela.getSelectedRow());
		
	}

	
}
