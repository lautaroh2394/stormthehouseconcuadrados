package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import armas.BalaBasica;
import utils.Pos;

public class EnemigoBasico extends GameObject{
	
	protected GeneradorEnemigos genem;
	protected Casa casa;
	
	protected static int vidaBase = 2;
	
	protected int danio = 2;
	protected int vida = 5;
	protected int valorPorMuerte = 100;
	protected int dif;
	
	private Color color = Color.red;
	
	protected double x=0;
	protected Pos y;
	
	protected int w = 10;
	protected int h = 10;
	
	protected double velx = 1;
	protected int limite;
	
	protected int timerParaAtacarMax = 45;
	protected int timerParaAtacar = 0;
	
	public EnemigoBasico(Pos p, int l,Casa c,GeneradorEnemigos g, int dif,int nivel){
		this.dif = dif;
		y = p;
		limite = (int) (l- w);
		this.casa = c;
		genem = g;
		this.vida = vida*dif;
		this.valorPorMuerte = (int)(valorPorMuerte * (dif+1) *((nivel/10)+1));
	}
	
	public EnemigoBasico(Pos p, int l,Casa c,GeneradorEnemigos g, int dif, int vida, int nivel){
		this.dif = dif;
		y = p;
		limite = (int) (l-w);
		this.casa = c;
		genem = g;
		this.vida = vida*dif;
		this.valorPorMuerte = (int)(valorPorMuerte * (dif+1) *((nivel/10)+1));
	}

	@Override
	public void tick() {
		
		setPos();
		chequearSiMurio();
		atacarSiPuede();
		
	}
	
	protected void setPos(){
		x = x+velx;
		
		if(x> limite){x = limite; velx = 0;};
	}
	
	protected void chequearSiMurio(){
		if (this.vida <= 0) {genem.seMurio(this);}
	}
	
	protected void atacarSiPuede(){
		if (velx == 0){ atacarCasa();}
	}
	
	protected void atacarCasa(){
		if (timerParaAtacar <= 0){
		this.casa.teAtaque(danio);
		timerParaAtacar = timerParaAtacarMax;
		}else timerParaAtacar--;
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
	
	public int teAtaca(int danioentrante){
		this.vida -= danioentrante;
		if( vida<= 0) return valorPorMuerte;
		else return 0;
	}
	
	public void cumpliMiMision(EnemigoDisparado e){
	}

}
