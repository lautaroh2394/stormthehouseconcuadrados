package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import utils.Pos;

public class EnemigoQueDispara extends EnemigoBasico{
	
	private static int vidaBase = 70;
	Color color = Color.pink;
	int w;
	int h;
	int timerParaAtacarMax = 60;
	private int dist;
	Random r;
	LinkedList<EnemigoDisparado> minions;
	LinkedList<EnemigoDisparado> aBorrar;
	LinkedList<EnemigoDisparado> aAgregar;

	public EnemigoQueDispara(Pos p, int l, Casa c, GeneradorEnemigos g, int dif,int nivel) {
		super(p, l, c, g, dif,vidaBase,nivel);
		valorPorMuerte = 800;
		this.valorPorMuerte = (int)(valorPorMuerte * (dif+1) *((nivel/10)+1));
		velx = 0.75;
		w = (int) (super.w*1.5);
		h = (int) (super.h*1.5);
		r = new Random();
		dist = r.nextInt((int ) (l*0.4))+l/2;
		minions = new LinkedList<EnemigoDisparado>();
		aBorrar = new LinkedList<EnemigoDisparado>();
		aAgregar = new LinkedList<EnemigoDisparado>();
	}
	
	@Override
	public void tick() {
		
		setPos();
		chequearSiMurio();
		atacarSiPuede();
		
		tickMinions();
		
		agregar();
		borrar();
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int) (x),(int) (y.y), w, h);
		
		for (EnemigoDisparado e : minions){
			e.render(g);
		}
	}
	
	private void tickMinions(){
		for(EnemigoDisparado e : minions){
			e.tick();
		}
	}
	
	private void agregar(){
		for (EnemigoDisparado e: aAgregar){
			minions.add(e);
		}
		aAgregar = new LinkedList<EnemigoDisparado>();
	}
	
	private void borrar(){
		for (EnemigoDisparado e: aBorrar){
			minions.remove(e);
		}
		aBorrar = new LinkedList<EnemigoDisparado>();
	}
	
	protected void setPos(){
		x = x+velx;		
		if(x> dist){x = dist; velx = 0;};
	}
	
	protected void atacarSiPuede(){
		if (velx == 0){ disparar();}
	}
	
	private void disparar(){
		if (timerParaAtacar <= 0){
			
			aAgregar.add(new EnemigoDisparado(this.y,this.x,this.limite,casa,genem, dif,this, 1));

			timerParaAtacar = timerParaAtacarMax;
			}
		
		else timerParaAtacar--;
		
	}
	
	public void cumpliMiMision(EnemigoDisparado m){
		aBorrar.add(m);
	}

}
