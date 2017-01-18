package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import gameobjects.GeneradorEnemigos;
import gameobjects.Jugador;
import utils.OpcionMejoraClip;
import utils.OpcionMejoraRecarga;
import utils.OpcionMejoraVelocidad;
import utils.OpcionOK;
import utils.Sound;

public class MenuTienda {
	
	
	Handler handler;
	LinkedList<Jugador> jugadores;
	LinkedList<Jugador> jugadoresOK;
	
	HudTienda hud;
	
	
	public MenuTienda(Handler h){
		
		handler = h;
		jugadores = h.getCasa().getJugadores();
		jugadoresOK = new LinkedList<Jugador>();
		
		setOpciones();
		
		hud = new HudTienda(jugadores);
		
		
	}
	
	public void render(Graphics g){
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.white);
		
		for(Jugador j : jugadores){
			j.renderTienda(g);
		}
		
		hud.render(g);
		
	}
	
	public void tick(){
		chequearSiEstanListos();
		tickJugadores();
	}
	
	public void jugadorEstaListo(Jugador j){
		jugadoresOK.add(j);
	}
	
	private void chequearSiEstanListos(){
		if (jugadoresOK.size() == jugadores.size()){
			avisarQueEmpiezaNivel();
			jugadoresOK = new LinkedList<Jugador>();
			
			Sound.Tienda.stop();
			Sound.BackgrJuego.play();
			Game.estado = StatusPantalla.juego;
			
			
		}
	}
	
	private void avisarQueEmpiezaNivel(){

		handler.getCasa().comienzaNivel();
	}
	
	private void tickJugadores(){
		for (Jugador j: jugadores){
			j.tickTienda();
		}
	}
	
	private void setOpciones(){
		
		int alto = Game.HEIGHT/15;
		int ancho = Game.WIDTH/(jugadores.size()+2);
		
		int posx = ancho/2;
		int posy = alto;
		
		for(Jugador j : jugadores){
			
			agregarMejoraVelocidad(j,posx, posy); posy = posy+alto*3; 
			agregarMejoraClip(j,posx,posy); posy = posy+alto*3;
			agregarMejoraRecarga(j,posx,posy);posy = posy+alto*3;
			agregarOK(j,posx,posy);
			
			posx = (int) (posx + 1.5*ancho);
			posy = alto;
			
		}
		
	}
	
	private void agregarMejoraVelocidad(Jugador j, int x, int y){
		new OpcionMejoraVelocidad(j,x,y,handler,this);
	}
	
	private void agregarMejoraClip(Jugador j, int x, int y){
		new OpcionMejoraClip(j,x,y,handler,this);
	}
	
	private void agregarMejoraRecarga(Jugador j, int x, int y){
		new OpcionMejoraRecarga(j,x,y,handler,this);
	}
	
	private void agregarOK(Jugador j, int x, int y){
		new OpcionOK(j,x,y,handler,this);
	}

}
