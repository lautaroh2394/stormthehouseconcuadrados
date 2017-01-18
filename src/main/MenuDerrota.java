package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameobjects.Jugador;

public class MenuDerrota extends MouseAdapter {

	Handler hand;
	Game game;
	
	public MenuDerrota(Handler h, Game g){
		this.hand = h;
		this.game = g;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		fondo(g);
		textoDerrota(g);
		scores(g);
		botonInicio(g);
	}
	
	private void fondo(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);
	}
	
	private void textoDerrota(Graphics g){
		g.setColor(Color.red);
		g.setFont(new Font(null,1,40));
		g.drawString("Derrota", (int ) (Game.WIDTH*0.2), (int) (Game.HEIGHT*0.2));
	}
	
	private void scores(Graphics g){
		g.setFont(new Font(null,1, 20));
		g.drawString("Llegaron al nivel: "+hand.getGenem().getNivel(), (int ) (Game.WIDTH*0.2), (int) (Game.HEIGHT*0.2 +40));
		g.drawString("Puntajes: ", (int ) (Game.WIDTH*0.2), (int) (Game.HEIGHT*0.2 +60));
		
		
		int i = 2;
		for (Jugador j : hand.getJugadores()){
			g.drawString("P"+(j.getId()+1)+": "+j.getScore(), (int ) (Game.WIDTH*0.2), (int) (Game.HEIGHT*0.2 +40+(20*i)));
			i++;
		}
	}
	
	private void botonInicio(Graphics g){
		
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH/3, Game.HEIGHT*10/14, Game.WIDTH/6, Game.HEIGHT/7);
		
		g.setColor(Color.WHITE);
		g.drawString("Inicio", Game.WIDTH*5/12, Game.HEIGHT*23/28);
		
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if (Game.estado == StatusPantalla.Derrota && 
				mx < Game.WIDTH/3 + Game.WIDTH/6 && mx > Game.WIDTH/3 && my < Game.HEIGHT*10/14 + Game.HEIGHT/7 && my > Game.HEIGHT*10/14){
			inicio();
		}
		
	}
	
	private void inicio(){
		Game.estado = StatusPantalla.inicio;
	}

}
