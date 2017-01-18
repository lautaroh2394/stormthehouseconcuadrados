package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;

public abstract class OpcionInicio {
	
	int posx;
	int posy;
	int ancho;
	int alto;
	int valor;
	protected int posLl;
	protected Game game;
	
	protected String text;
	
	public OpcionInicio(int x, int y, int valor, int ancho, int alto, Game game){
		this.posx = x;
		this.posy = y;
		this.valor = valor;
		this.ancho = ancho;
		this.alto = alto;
		this.game = game;
	}
	
	public boolean mouseOver(int mx, int my){
		if(mx > posx && mx < posx + ancho && my > posy && my < posy + alto){
			return true;
		}else return false;
	}
	
	public void elegido(LinkedList<OpcionInicio> loi){
		
		accion();
		setearse(loi);
		
	}
	
	public abstract void accion();
	
	protected void setearse(LinkedList<OpcionInicio> loi){
		loi.set(posLl, this);
	}
	
	public void render(Graphics g){
		Font f = new Font(null,1,15);
		g.setFont(f);
		g.setColor(Color.gray);
		g.fillRect(posx, posy, ancho, alto);
		
		g.setColor(Color.white);
		
		g.drawString(text, (int) (posx+(ancho*0.25)), posy+(alto/2));
	}
	
	public void renderElegido(Graphics g){
		g.setColor(Color.blue);
		g.drawRect(posx, posy, ancho, alto);
		
	}

}
