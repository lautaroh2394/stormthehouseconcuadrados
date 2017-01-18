package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;
import main.Handler;
import utils.Pos;

public class Casa extends GameObject{
	
	private int posx;
	private int posy;
	
	private int vidaMax = 100;
	private int vida = vidaMax;
	
	private int ancho;
	private int alto;
	
	private int cfilas;
	
	private boolean finNivel = false;
	
	private LinkedList<Pos> posiciones;
	private LinkedList<Jugador> jugadores;
	
	private Handler handler;
	
	public Casa(int w, int h, int f, int cj,Handler hand){
		this.posy = (int) (0.3*h);
		this.posx = (int) (29*w/36);
		
		this.alto = (int) (0.6*h);
		this.ancho = (int) (4*w/36);
		
		this.cfilas = f;
		
		this.handler = hand;
		
		guardarPosiciones(cj);
		jugadores = new LinkedList<Jugador>();
		agregarJug(cj);
		
	}
	
	private void agregarJug(int cj){
		for (int i = 0; i<cj;i++){
			agregar(new Jugador(i,this));
		}
		
		if (Game.ayuda) {
			for (Jugador j : jugadores){
				for (int i = 0; i<15;i++){
					j.mejoraClip();
					j.mejoraRecarga();
					j.mejoraVelocidad();
					j.sumarScore(1000);
				}
			}
		}
	}

	@Override
	public void tick() {
		chequearQueNoMori();
		
		for (Jugador j : jugadores){
			j.tick();
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(posx, posy, ancho, alto);
		
		for(Jugador j : jugadores){
			j.render(g);
		}
		
	}
	
	private void chequearQueNoMori(){
		if (vida<=0){handler.derrota();}
	}
	
	private void guardarPosiciones(int cj){
		posiciones = new LinkedList<Pos>();
		int a = (ancho/(cj+1));
		int x = posx;
		int ax = (alto/cfilas);
		int y = posy-(ax/2);
		
		for (int i = 0; i<cfilas;i++){
			y = y+(ax);
			posiciones.add(new Pos(x, y, a));
		}
		
	}
	
	private void agregar(Jugador j){
		jugadores.add(j);
	}
	
	public LinkedList<Pos> getPos(){
		return posiciones;
	}
	
	public void meMuevo(Jugador j, int i){
		int index = posiciones.indexOf(j.getPos());
		int proxPos = index + i;
		
		if (proxPos<posiciones.size() && proxPos>= 0){
			j.setPos(posiciones.get(proxPos));
		}
	}
	
	public LinkedList<Jugador> getJugadores(){
		return jugadores;
	}
	
	public int getAncho(){
		return ancho;
	}
	
	public int getPosX(){
		return posx;
	}
	
	public void teAtaque(int danio){
		vida -= danio; 
	}
	
	public int getVida(){
		return vida;
	}
	
	public int getVidaMax(){
		return vidaMax;
	}
	
	public void finNivel(){
		for(Jugador j: jugadores){
			j.esFinNivel();
		}
		vidaMax*=1.1;
		if (vidaMax>1500) {vidaMax = 1500;};
		vida = vidaMax;
		finNivel = true;
	}
	
	public void comienzaNivel(){
		for(Jugador j: jugadores){
			j.comienzaNivel();
		}
		finNivel = false;;
	}	
	
	public boolean getfinnivel(){
		return finNivel;
	}


}
