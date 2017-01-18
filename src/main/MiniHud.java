package main;

import java.awt.Color;
import java.awt.Graphics;

import gameobjects.Jugador;

public class MiniHud {
	
	int x;
	int y;
	int id;
	
	Color c;
	Jugador j;
	
	public MiniHud(Jugador j){
		this.x = j.getOpciones().get(0).getX();
		this.y = (int) (j.getOpciones().get(0).getY()*0.5);
		this.id = j.getId();
		c = j.getColor();
		this.j =j; 
		
	}
	
	public void render(Graphics g){
		g.setColor(c);
		g.drawString("P"+(id+1)+": $"+j.getMoney()+"  ||  Score: "+j.getScore(),x-20,y);
	}

}
