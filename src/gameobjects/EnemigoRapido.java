package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import utils.Pos;

public class EnemigoRapido extends EnemigoBasico{

	private static int vidaBase = 10;
	int vida = 2;
	private Color color = Color.YELLOW;
	protected int timerParaAtacarMax = 30;
	
	public EnemigoRapido(Pos p, int l, Casa c, GeneradorEnemigos g, int dif,int nivel) {
		super(p, l, c, g, dif,vidaBase,nivel );
		valorPorMuerte = 200;
		this.valorPorMuerte = (int)(valorPorMuerte * (dif+1) *((nivel/10)+1));
		velx = 4;
		danio = 1;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int) (x),(int) (y.y), w, h);	
	}

}
