package Back;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorio {
	public static void gerarRelatorio(Salao central) {
		/* Biblioteca que gera o relatorio*/
	
		Document doc = new Document(PageSize.A4, 72,72,72,72); // O comando dentro é uma classe enum que define o tamanho da pagina
		try {
			
			PdfWriter.getInstance(doc,new FileOutputStream("Cabelelilaleila.pdf"));
			doc.open(); // Abre documento 
			Paragraph p;
			Font font = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD); // Modifica fonte do documento
				p = new Paragraph("Associados:\n" + GeradorDeTexto.Gerador(central), font);
				doc.add(p); // Adiciona conteudo ao doc
			
			doc.close();
			System.out.println("Gerado com sucesso!");
			
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} 
	}
}