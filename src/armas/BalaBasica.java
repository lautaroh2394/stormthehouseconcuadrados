package armas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import gameobjects.EnemigoBasico;
import gameobjects.GameObject;
import gameobjects.GeneradorEnemigos;
import main.Game;
import utils.Pos;

public class BalaBasica extends GameObject{
	
	private int danio = 5;
	private Pos pos;
	private ArmaBasica pertenezcoA;
	private double velx = 1;
	private double x;
	private int radio = 15;
	private Color c;
	
	private LinkedList<EnemigoBasico> enemigos;
	
	
	public BalaBasica(Pos p, ArmaBasica a){
		this.pos = p;
		x = p.x;
		pertenezcoA = a;
		c = a.getColor();
	}


	@Override
	public void tick() {
		
		x = x - velx;
		
		destruirSiEstoyFuera();
		
		chequearSiPegaAlguien();
		
	}


	@Override
	public void render(Graphics g) {
		
		g.setColor(c);
		g.drawOval((int) (x), (int) (pos.y), radio, radio);
		
	}
	
	private void destruirSiEstoyFuera(){
		if (this.x <= 0) {
			pertenezcoA.noSirve(this);
		}
	}
	
	private void chequearSiPegaAlguien(){
		enemigos = GeneradorEnemigos.instance.getEnemigosActivos();
		int seMuere = 0;
		
		for (int i = 0; i<enemigos.size(); i++){
			if (enemigos.get(i).teToca(this)){
				seMuere = enemigos.get(i).teAtaca(danio);
				i = enemigos.size();
				if (seMuere>0) { pertenezcoA.matoUno(seMuere);}
				pertenezcoA.noSirve(this);
				
			}
		}
	}
	
	public Pos getPos(){
		return pos;
	}
	
	public double getPosX(){
		return x;
	}
	
	public int getRadio(){
		return radio;
	}

}
