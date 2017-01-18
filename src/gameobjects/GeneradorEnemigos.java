package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import main.Game;
import main.StatusPantalla;
import utils.Pos;
import utils.Sound;

public class GeneradorEnemigos extends GameObject {
	
	public static GeneradorEnemigos instance;
	
	private LinkedList<Pos> posiciones;
	private LinkedList<EnemigoBasico> enemigos;
	private LinkedList<EnemigoBasico> enemigosAAgregar;
	private LinkedList<EnemigoBasico> enemigosABorrar;
	
	public int nivel = 1;
	
	private int enemigosNivel = 10;
	
	private int tiempoNivel = 1800;
	private int timerFinNivel= tiempoNivel;
	private int timerMax = tiempoNivel/enemigosNivel;
	private int timer = 0;
	
	private int dif;
	
	private Random r;
	
	private int filas;
	private int limite;
	
	private Casa casa;
	
	public GeneradorEnemigos(int filas, Casa c, int dif){
		
		this.dif = dif; 
		posiciones = c.getPos();
		this.filas = filas;
		limite = c.getPosX();
		r = new Random();
		this.casa = c;
		
		enemigos = new LinkedList<EnemigoBasico>();
		enemigosAAgregar = new LinkedList<EnemigoBasico>();
		enemigosABorrar = new LinkedList<EnemigoBasico>();
	}

	@Override
	public void tick() {
		
		if (timerFinNivel == 0) {
			finNivel();
		}
		else {
		
			timer--;
			timerFinNivel--;
			spawnear();
			tickEnemigos();
			agregarEnemigos();
			eliminarEnemigos();
		}
		
	}
	
	private void tickEnemigos(){
		for (EnemigoBasico b : enemigos){
			b.tick();
		}
	}
	
	private void agregarEnemigos(){

		for (EnemigoBasico b : enemigosAAgregar){
			enemigos.add(b);
			enemigosAAgregar.remove(b);
		}
	}
	
	private void eliminarEnemigos(){

		for (EnemigoBasico b : enemigosABorrar){
			enemigos.remove(b);
			enemigosABorrar.remove(b);
		}
	}

	@Override
	public void render(Graphics g) {
		for (EnemigoBasico b : enemigos){
			b.render(g);
		}
		g.setColor(Color.ORANGE);
		g.drawString("Nivel: "+nivel, Game.WIDTH/40, Game.HEIGHT*4/40);
		g.drawString("Tiempo restante: "+(timerFinNivel)/60 ,Game.WIDTH/40, Game.HEIGHT*19/160);
		
	}
	
	private void setEnemigosProxNivel(){
		enemigosNivel =(int) (enemigosNivel*1.25)+1;
	}
	
	private void setTimer(){
		tiempoNivel = (int) (tiempoNivel*1.1);
		if (tiempoNivel>(60*120)){tiempoNivel = 60*120;}
		timerFinNivel = tiempoNivel;
		timerMax = Math.round(tiempoNivel/enemigosNivel)-1; //30seg total
		timer = 0;
		
	}

	
	private void spawnear(){
		if (timer <= 0){
			EnemigoBasico tem = nuevoEnemigo();
			if (tem != null){ enemigosAAgregar.add(tem); timer = r.nextInt(timerMax);}
			
		}
	}
	
	public void spawnear(EnemigoBasico e){
		enemigosAAgregar.add(e);
	}
	
	private EnemigoBasico nuevoEnemigo(){
		
		int pos = r.nextInt(filas);
		int rndm = r.nextInt(101);
		
		if (nivel<=2) {
			return (new EnemigoBasico(posiciones.get(pos),limite,casa, this, dif,nivel));
		}
		
		else if (nivel<=6) {
			if (rndm<=65) {	return (new EnemigoBasico(posiciones.get(pos),limite,casa, this, dif,nivel));	}
			else if (rndm<=90) {return (new EnemigoRapido(posiciones.get(pos),limite,casa, this, dif,nivel));	}
			else return (new EnemigoLento(posiciones.get(pos),limite,casa, this, dif,nivel));   
		}
		
		else if (nivel<=9) 
		{
		if (rndm<=50){return (new EnemigoBasico(posiciones.get(pos),limite,casa, this, dif,nivel));}
		else if (rndm<=70){return (new EnemigoRapido(posiciones.get(pos),limite,casa, this, dif,nivel));	}
		else if (rndm<=82){return (new EnemigoLento(posiciones.get(pos),limite,casa, this, dif,nivel));}
		else {return new EnemigoSaltador(posiciones.get(pos),limite,casa, this, dif,nivel);}
	}		
	
		else if (nivel<=12) 
			{
			if (rndm<=40){return (new EnemigoBasico(posiciones.get(pos),limite,casa, this, dif,nivel));}
			else if (rndm<=60){return (new EnemigoRapido(posiciones.get(pos),limite,casa, this, dif,nivel));	}
			else if (rndm<=75){return (new EnemigoLento(posiciones.get(pos),limite,casa, this, dif,nivel));}
			else if (rndm<=92){return new EnemigoSaltador(posiciones.get(pos),limite,casa, this, dif,nivel);}
			else {return new EnemigoQueDispara(posiciones.get(pos),limite,casa, this, dif,nivel);}
		}
		
		else //if (nivel<=15){
		{
			{
				if (rndm<=40){return (new EnemigoBasico(posiciones.get(pos),limite,casa, this, dif,nivel));}
				else if (rndm<=60){return (new EnemigoRapido(posiciones.get(pos),limite,casa, this, dif,nivel));}
				else if (rndm<=75){return (new EnemigoLento(posiciones.get(pos),limite,casa, this, dif,nivel));}
				else if (rndm<=92){return new EnemigoSaltador(posiciones.get(pos),limite,casa, this, dif,nivel);}
				else if (rndm <= 99){return new EnemigoQueDispara(posiciones.get(pos),limite,casa, this, dif,nivel);}
				else return (new EnemigoUltraVeloz(posiciones.get(pos),limite,casa, this, dif,nivel));}
		}
	}
	
	public void seMurio(EnemigoBasico e){
		
		enemigosABorrar.add(e);
		
	}
	
	public LinkedList<EnemigoBasico> getEnemigosActivos(){
		return enemigos;
	}
	
	public void finNivel(){
		nivel++;
		setEnemigosProxNivel();

		enemigos = new LinkedList<EnemigoBasico>();

		setTimer();
		
		Sound.BackgrJuego.stop();
		Sound.Tienda.play();
		Game.estado = StatusPantalla.finNivel;
		
	}
	
	public int getNivel(){
		return nivel;
	}
	
	public LinkedList<Pos> getPos(){
		return posiciones;
	}

}
