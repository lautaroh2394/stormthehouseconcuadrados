package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import utils.Pos;

public class EnemigoLento extends EnemigoBasico {
	
	private static int vidaBase = 50;
	Color color = Color.GREEN;
	int w = super.w*2;
	int h = super.h*2;
	int timerParaAtacarMax = 60;

	public EnemigoLento(Pos p, int l, Casa c, GeneradorEnemigos g, int dif, int nivel) {
		super(p, l, c, g, dif, vidaBase,nivel);
		valorPorMuerte = 500;
		this.valorPorMuerte = (int)(valorPorMuerte * (dif+1) *((nivel/10)+1));
		velx = 0.25;
		danio = 40;
		w = super.w*2;
		h = super.h*2;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int) (x),(int) (y.y), w, h);	
	}
	
	

}
