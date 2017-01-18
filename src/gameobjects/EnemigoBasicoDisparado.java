package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import armas.BalaBasica;
import utils.Pos;

public class EnemigoBasicoDisparado extends EnemigoBasico {

	static int vidaBasica = 1;
	EnemigoUltraVeloz jefe;
	Color color;
	
	public EnemigoBasicoDisparado(Pos p,double x, int l, Casa c, GeneradorEnemigos g, int dif,EnemigoUltraVeloz e, int nivel) {
		super(p, l, c, g, dif,nivel, vidaBasica);
		this.x = x;
		velx = 1;
		danio = 1;
		w = (int) (super.w);
		h = (int) (super.h);
		limite = l-w;
		jefe = e;
		color = new Color(104, 0, 83);
	}
	
	
	protected void atacarCasa(){
		
		this.casa.teAtaque(danio);
		genem.seMurio(this);
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int) (x),(int) (y.y), w, h);	
	}
	
	public boolean teToca(BalaBasica b){
		if (this.x>=b.getPosX() && this.x <= b.getPosX()+b.getRadio() &&
				this.y.y == b.getPos().y){
			return true;
		}
		else return false;
	}
	
	
}
