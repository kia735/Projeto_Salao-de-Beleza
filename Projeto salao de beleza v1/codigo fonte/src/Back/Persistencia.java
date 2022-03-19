package Back;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Persistencia {

	private static XStream xstream = new XStream(new DomDriver("ISO8859-1"));
	private static File arquivo;

	public static void salvarCentral(Salao c, String nomeDoArquivo) throws Exception {
		
		arquivo = new File(nomeDoArquivo + ".xml");
		
		String xml = xstream.toXML(c);
		
		try {
			
			arquivo.createNewFile();
			PrintWriter ar = new PrintWriter(arquivo);
			ar.print(xml);
			ar.close();
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}

	public static Salao recuperarCentra(String nomeDoArquivo) throws Exception {
		
		arquivo = new File(nomeDoArquivo + ".xml");
		
		try {
			
			if (arquivo.exists()) {
				
				FileInputStream insert = new FileInputStream(arquivo);
				return (Salao) xstream.fromXML(insert);
				
			}
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
			
		Salao d = new Salao();
		salvarCentral(d,nomeDoArquivo);
		return d;
	}

}
