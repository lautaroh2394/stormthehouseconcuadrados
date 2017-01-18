package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import armas.BalaBasica;
import utils.Pos;

public class EnemigoUltraVeloz extends EnemigoSaltador{
		
	public static int vidaBase = 40;
	private Color color;
	private int timerDisparo = 0;
	private int timer = 60;
	
	static int Creados = 0;
	
	Random r;
	
	GeneradorEnemigos genem;


	public EnemigoUltraVeloz(Pos p, int l, Casa c, GeneradorEnemigos g, int dif, int nivel) {
		super(p, l, c, g, dif, vidaBase);
		Creados++;
		vida = vidaBase;
		valorPorMuerte = 1200;
		this.valorPorMuerte = (int)(valorPorMuerte * (dif+1) *((nivel/10)+1));
		w = (int) (super.w*0.75);
		h = (int) (super.h*0.75);
		velx = 16;
		danio = 0;
		limite = l-w;
		x = w/2 +1;

		color = new Color(245, 211, 255);
		r = new Random();
		genem = g;
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int) (x),(int) (y.y), w, h);
		
		g.drawString(""+timerDisparo, 30, 200);
		
	}
	
	@Override
	public void tick() {
		
		setPos();
		chequearSiMurio();
		atacarSiPuede();		
	}
	
	@Override
	public int teAtaca(int danioentrante){
		this.vida -= danioentrante;
		if( vida<= 0) return valorPorMuerte;
		else {
			setNuevaPos();
			return 0;
		}
	}
		
	@Override
	protected void setPos(){
		
		x = x+velx;
		
		if(x> limite){velx = -1*velx; setNuevaPos();}
		else if(x< w/2){x = 0; velx = -1*velx;setNuevaPos();};
		
	}	
	
	@Override
	protected void atacarSiPuede(){
		if (timerDisparo>=timer){ disparar();}
		else timerDisparo++;
	}
	
	private void disparar(){
			genem.spawnear(new EnemigoBasicoDisparado(this.y,this.x,this.limite,casa,genem, dif,this, 1));

			timerDisparo = r.nextInt(timer); 
		
	}
		

}
