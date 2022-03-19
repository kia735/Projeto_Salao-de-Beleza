package Telas;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class CirculoJComponent extends JComponent{

	private static final long serialVersionUID = -1581703328779113592L;
	Color color;
    Shape shape;
    boolean selected;
    
    public CirculoJComponent(){
    	super();
        this.setSize(100, 100);
        this.setLocation(0,0);
        shape = new Ellipse2D.Float(5F,5F, (float)(this.getSize().getWidth()-10),(float) (this.getSize().getHeight() - 10));
    }
    
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //Suavisa a linha aplicando anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //Suavisa texto aplicando anti-aliasing
        
        g2d.setStroke(new BasicStroke(5.0F)); //Define expessura da linha
        
        g2d.setColor(Color.decode("#CD7C8D")); //Define cor
        g2d.draw(shape); //Desenha o contorno do shape, no caso uma circuferencia definida no construtor
        g2d.setColor(Color.decode("#CD7C8D")); //Define cor novamente
        g2d.fill(shape); //Desenha o preenchimento do shape
        g.dispose();
        
    }
}


