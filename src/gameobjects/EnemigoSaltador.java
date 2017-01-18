package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import utils.Pos;

public class EnemigoSaltador extends EnemigoBasico {
	
	protected static int vidaBase = 15;
	private Color color = Color.cyan;

	public EnemigoSaltador(Pos p, int l, Casa c, GeneradorEnemigos g, int dif,int nivel) {
		super(p, l, c, g, dif,vidaBase,nivel);
		valorPorMuerte = 300;
		this.valorPorMuerte = (int)(valorPorMuerte * (dif+1) *((nivel/10)+1));
		danio = 40;
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
	
	protected void setNuevaPos(){
		int i = this.genem.getPos().indexOf(this.y);
		Random r = new Random();
		boolean nextpos = r.nextBoolean();
		if (nextpos){i = Math.floorMod(i+1, genem.getPos().size());} else {i = Math.floorMod(i-1, genem.getPos().size());};
		this.y = this.genem.getPos().get(i);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int) (x),(int) (y.y), w, h);
	}

}
