package utils;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class OpcionInicioCF extends OpcionInicio {

	public OpcionInicioCF(int x, int y, int valor, int ancho, int alto, Game game) {
		super(x, y, valor, ancho, alto, game);
		text = valor + " filas";
		posLl = 2;
	}
	
	public void accion() {
		game.filas = valor;

	}
	
	@Override
	public void render(Graphics g){
				
		g.setColor(Color.gray);
		g.fillRect(posx, posy, (int) (ancho*0.75), alto);
		
		g.setColor(Color.white);
		
		g.drawString(text, (int) (posx+ancho*0.25), posy+alto/2);
	
	}
	
	@Override
	public void renderElegido(Graphics g){
		g.setColor(Color.blue);
		g.drawRect(posx, posy, (int) (ancho*0.75), alto);
		
	}

}
