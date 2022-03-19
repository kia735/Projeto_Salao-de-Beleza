package Back;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import Telas.TelaDeAviso;

public class ValidarAgendamento {
	
	public static boolean validaHora(String hora) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setLenient(false);

		try {
			sdf.parse(hora);
		} catch (java.text.ParseException e) {
			return false;
		}

		return true;
	}
	
	public static boolean isDateValid(String strDate) {
		
		Date dataAtual = new Date();
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		Date inserida = null;
		
		try {
			inserida = formatar.parse(strDate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		if(inserida.compareTo(dataAtual) != -1) {
			return VerificarData.verificar(strDate);
		}
		
		return false;
	    
	}
	
	public static boolean isValido(String hora, String strDate) {
		
		if(validaHora(hora)) {
			if(isDateValid(strDate)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isOcuped(Salao salao, String hora, String data, String colaborador) {

		boolean isProf = true;
		
		for (int i = 0; i < salao.getHistorico().size(); i++) {
			if (salao.getHistorico().get(i) != null) {
				if (salao.getHistorico().get(i).getData().equals(data)
						&& salao.getHistorico().get(i).getHora().equals(hora)
						&& salao.getHistorico().get(i).getServico().getColaborador().getNome().equals(colaborador)) {
					
					isProf = false;
					break;
				}
			}
		}
		
		return isProf;
	}

	@SuppressWarnings("rawtypes")
	public static boolean verificandoAgendamento(Salao salao, JTextField emailText, JTextField nomeText,
			JTextField dataText, JTextField horaText, String[] osServicos, String[] osColaboradores,
			JComboBox listDeServicos, JComboBox listaDeColaboradores) {

		if (!emailText.getText().isEmpty() && !nomeText.getText().isEmpty() && !dataText.getText().isEmpty()
				&& !horaText.getText().isEmpty() && listDeServicos.getSelectedIndex() != -1
				&& listaDeColaboradores.getSelectedIndex() != -1) {

			String[] retirarFomato = horaText.getText().split(":");
			String semFormato = retirarFomato[0] + retirarFomato[1];
			int horaInt = Integer.parseInt(semFormato);

			if (800 <= horaInt && horaInt < 1100 || 1330 <= horaInt && horaInt < 1600) {

				if (isValido(horaText.getText(), dataText.getText())) {
					if (ValidarEntrada.validarEmail(emailText.getText())) {
						if (isOcuped(salao, horaText.getText(), dataText.getText(),
								osColaboradores[listaDeColaboradores.getSelectedIndex()])) {
							try {
								
								CadastrarServico.cadastrarServicoDoCliente(salao, nomeText.getText(),
										dataText.getText(), horaText.getText(), emailText.getText(),
										osServicos[listDeServicos.getSelectedIndex()],
										osColaboradores[listDeServicos.getSelectedIndex()]);
								Persistencia.salvarCentral(salao, "arquivo-Central");
								
								return true;	
									
							} catch (Exception e1) {
								new TelaDeAviso("Nao foi possivel agendar");
								
							}

							new TelaDeAviso("Agendado Com Sucesso!");
						}
						else {
							new TelaDeAviso("Colaborador já tem agendamento para essa hora!");
						}
					}
					else {
						new TelaDeAviso("Verifique o email inserido!");
					}
				} 
				else {
					new TelaDeAviso("Verifique a hora e a data!");
				}
			} 
			else {
				new TelaDeAviso("Pedido fora do horario de funcionamento!");
			}
		} 
		else {
			new TelaDeAviso("Preencha todos os campos!");
		}
		return false;
	}
}
