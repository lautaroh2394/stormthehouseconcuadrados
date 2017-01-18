package utils;

import gameobjects.Jugador;
import main.Handler;
import main.MenuTienda;

public class OpcionMejoraClip extends Opcion{

	public OpcionMejoraClip(Jugador j, int x, int y, Handler h, MenuTienda m) {
		super(j, x, y, h, m);
		this.text = "Mejora Clip";
		this.costo = 750;
	}
	
	@Override
	public void clickeada(){
		if (this.j.mejora(costo)){
			this.j.mejoraClip();
			costo = (int) (costo+50);
		}
	}

}