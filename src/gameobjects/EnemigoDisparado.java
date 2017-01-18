package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import armas.BalaBasica;
import utils.Pos;

public class EnemigoDisparado extends EnemigoBasico {

	EnemigoBasico jefe;
	Color color = Color.magenta;
	
	public EnemigoDisparado(Pos p,double x, int l, Casa c, GeneradorEnemigos g, int dif,EnemigoQueDispara e, int nivel) {
		super(p, l, c, g, dif,nivel);
		this.x = x;
		velx = 8;
		danio = 1;
		w = (int) (super.w*0.2);
		h = (int) (super.h*0.2);
		jefe = e;
	}	
	
	protected void atacarCasa(){
		
		this.casa.teAtaque(danio);
		jefe.cumpliMiMision(this);
		
	}
	
	public boolean teToca(BalaBasica b){
		return false;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int) (x),(int) (y.y), w, h);	
	}
	
	

}
