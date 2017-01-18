package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import gameobjects.Jugador;

public class Hud {
	
	LinkedList<Jugador> jugadores;
	Handler hand;
	
	private int w;
	private int h;
	
	private int alturavidacasa;
	private int posxvidacasa;
	private int anchovidacasa;
	private int posyvidacasa;
	
	private int alturapuntajesjugadores;
	private int espacioentrepuntajesjugadores;
	private int posxpuntajejug;
	private int posypuntajejug;
	
	public Hud(Handler hand, int w, int h){
		this.hand = hand;
		jugadores = hand.getJugadores();
		this.w = w;
		this.h = h;
		
		alturavidacasa= (int) (h/30);
		posxvidacasa = (int) (w/50);
		anchovidacasa = (int) (48*w/50);
		posyvidacasa = 0;
		
		alturapuntajesjugadores = (int) (1.5*alturavidacasa);
		espacioentrepuntajesjugadores = w/6;
		posxpuntajejug = 4*w/40;
		posypuntajejug = alturavidacasa*5; 
	}
	
	public void render(Graphics g){
		if (Game.estado ==StatusPantalla.juego){
			dibujarVida(g);
			dibujarScore(g);
			dibujarMunicion(g);
		}
	}
	
	private void dibujarVida(Graphics g){
		g.setColor(Color.red);
		g.fillRect(posxvidacasa, 0, anchovidacasa, alturavidacasa);
		
		g.setColor(Color.GREEN);
		float v = hand.getCasa().getVida();
		float vm = hand.getCasa().getVidaMax();
		g.fillRect(posxvidacasa, 0, (int) (anchovidacasa*(v/vm)), alturavidacasa);
		
		g.setColor(Color.white);
		g.drawString("Vida: "+hand.getCasa().getVida()+"/"+hand.getCasa().getVidaMax(), posxvidacasa, alturavidacasa*2);
	}
	
	private void dibujarScore(Graphics g){
		int posx = posxpuntajejug;
		int i = 0;
		for (Jugador j : jugadores){
			dibujarScoreJugador(j,(posx)+(i*espacioentrepuntajesjugadores),g);
			i++;
		}
	}
	
	private void dibujarScoreJugador(Jugador j, int x, Graphics g){
		g.setColor(j.getColor());
		g.drawString("Score player "+(j.getId()+1)+": "+j.getScore(), x, posypuntajejug);
		}
	
	private void dibujarMunicion(Graphics g){
		int posx = posxpuntajejug;
		int i = 0;
		for (Jugador j : jugadores){
			dibujarMunicionJugador(j,(posx)+(i*espacioentrepuntajesjugadores),g);
			i++;
		}
	}
	
	private void dibujarMunicionJugador(Jugador j, int x, Graphics g){
		float c = j.getArma().getClip();
		float cm = j.getArma().getClipMax();
		float tr = j.getArma().getTicksRecargando();
		float tdr = j.getArma().getTicksDeRecarga();
		
		g.setColor(j.getColor());
		g.drawString("ammo: "+(int) (c)+"/"+(int) (cm), x, alturavidacasa*6);
		
		if(j.getArma().estaRecargando()){
			g.fillRect(x, alturavidacasa*7, (int ) ((espacioentrepuntajesjugadores/2)*(tr/tdr)), alturavidacasa/2);
		}
		else g.fillRect(x, alturavidacasa*7, (int ) ((espacioentrepuntajesjugadores/2)*(c/cm)), alturavidacasa/2);
		
	}

}
