package Back;

public class CadastrarServico {
	
	public static void cadastrarServicoDoCliente(Salao salao,String nome, String data, String hora, String email ,String servico, String colaborador) throws Exception {
		
		Agenda agenda = new Agenda();
		
		agenda.setNome(nome);
		agenda.setHora(hora);
		agenda.setData(data);
		agenda.setEmail(email);
		
		for(int i = 0; i < salao.getAssociacao().size(); i++) {
			if(salao.getAssociacao().get(i).getColaborador().getNome().equals(colaborador) && salao.getAssociacao().get(i).getServico().getNome().equals(servico)) {
				agenda.setServico(new Associador(salao.getAssociacao().get(i).getColaborador(), salao.getAssociacao().get(i).getServico()));
				salao.getHistorico().add(agenda);
				break;
			}
		}

	}
	
	public static boolean NovoServico(Salao salao, String nome, String descicao, int duracao, float preco) {

		for (int i = 0; i < salao.getServicos().size(); i++) {
			if (salao.getServicos().get(i).getNome().equals(nome)) {
				return false;
			}
		}

		try {
			Servico s = new Servico(nome, descicao, duracao, preco);
			salao.adicionarServiço(s);
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}
	
}

