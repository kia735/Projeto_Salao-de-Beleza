package Back;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


public class Mensageiro {  
	
	public void enviaremail(String em, String msg){  
	      SimpleEmail email = new SimpleEmail();  
	      try {  
	      email.setDebug(true);  
	      email.setHostName("smtp.gmail.com");  //Acesso ao host gmail
	      email.setSmtpPort(465); // configuração de porta 
	      email.setAuthentication("ttestes091@gmail.com","Senha123@@");  
	      email.setSSLOnConnect(true);  // Conexão segura
	      email.addTo(em); //Destinatario
	      email.setFrom("ttestes091@gmail.com"); //Remetente
	      email.setSubject("Teste mensagem");  
	      email.setMsg(msg);  
	      email.send();  
	      } catch (EmailException e) {  
	    	  e.printStackTrace();
	      }   
   }  
}