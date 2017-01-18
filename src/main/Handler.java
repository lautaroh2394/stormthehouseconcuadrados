package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import gameobjects.Casa;
import gameobjects.GameObject;
import gameobjects.GeneradorEnemigos;
import gameobjects.Jugador;
import utils.Sound;

public class Handler {
	
	private int width;
	private int height;
	private int filas;
	private int dif;
	private int cantJug;
	
	private Casa casa;
	private GeneradorEnemigos genem;
	
	private LinkedList<GameObject> listaGameObj;
	
	private boolean perdimos = false;
	
	
	public Handler(int width, int height, int filas, int dificultad, int cj) {
		this.width = width;
		this.height = height;
		this.filas = filas;
		this.dif = dificultad;
		this.cantJug = cj;
		
		listaGameObj = new LinkedList<GameObject>();
		
		agregarGO();
		
		
	}
	
	private void agregarGO(){
		agregarCasa(cantJug);
		agregarGE();
	}
	
	private void agregarCasa(int cantJug){
		casa = new Casa(width,height,filas,cantJug,this);
		listaGameObj.add(casa);
	}
	
	private void agregarGE(){
		GeneradorEnemigos.instance = new GeneradorEnemigos(filas,casa, dif); 
		this.genem = GeneradorEnemigos.instance;
		listaGameObj.add(genem);
	}
	

	public void render(Graphics g){
		
		if (!perdimos && Game.estado == StatusPantalla.juego){
			for(GameObject go : listaGameObj){
				go.render(g);
			}
		}else mensajeDerrota(g);
	}
	
	public void tick(){
		if (!perdimos && Game.estado == StatusPantalla.juego){
			for(GameObject go : listaGameObj){
				go.tick();
			}
		}
	}
	
	public Casa getCasa(){
		return casa;
	}
	
	public void mensajeDerrota(Graphics g){
		g.setColor(Color.red);
		g.drawString("Perdimos", 30, 30);
	}
	
	public void derrota(){
		Sound.BackgrJuego.stop();
		Sound.Derrota.play();
		Game.estado =StatusPantalla.Derrota;
	}
	
	public LinkedList<Jugador> getJugadores(){
		return casa.getJugadores();
	}
	
	public void finNivel(){
		if (!casa.getfinnivel()){
			casa.finNivel();
		}
	}
	
	public void comienzoNivel(){
		casa.comienzaNivel();
		Game.estado = StatusPantalla.juego;
	}
	
	public GeneradorEnemigos getGenem(){
		return genem;
	}

}
