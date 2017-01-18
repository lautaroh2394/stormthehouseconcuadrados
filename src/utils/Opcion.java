package utils;

import java.awt.Color;
import java.awt.Graphics;

import gameobjects.Jugador;
import main.Game;
import main.Handler;
import main.MenuTienda;

public class Opcion {
	
	int id;
	Jugador j;
	MenuTienda m;
	
	int x;
	int y;
	
	int h;
	int w;
	
	int costo = 0;
	
	String text;
	
	public Opcion(Jugador j, int x, int y, Handler h, MenuTienda m){

		this.m = m;
		
		this.x = x;
		this.y = y;
		
		this.h = Game.HEIGHT/15;
		this.w = Game.WIDTH/(h.getJugadores().size()+3);
		
		this.id = j.getId();
		this.j = j;
		j.agregarBoton(this);
	}
	
	public void render(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, w, h);
		
		g.setColor(Color.gray);
		dibujarText(g);
	}
	
	public void dibujarText(Graphics g){
		g.drawString(text+" $"+costo, x+w/10, y+2*h/3);
	}
	
	public void clickeada(){
		
	}
	
	public void seleccionada(Graphics g){
		g.setColor(Color.white);
		g.drawRect((int) (x-1), (int) (y-1), (int) (w+1), (int) (h+1));
		
		g.setColor(Color.gray);
		dibujarText(g);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	

}
