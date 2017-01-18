package utils;

import gameobjects.Jugador;
import main.Handler;
import main.MenuTienda;

public class OpcionMejoraVelocidad extends Opcion{

	public OpcionMejoraVelocidad(Jugador j, int x, int y, Handler h,MenuTienda m) {
		super(j, x, y, h, m);
		this.text = "Mejora Velocidad";
		this.costo = 2500;
	}
	
	@Override
	public void clickeada(){
//		if (this.j.mejora(costo)){
		if (this.j.getArma().puedeMejorarVel() && this.j.mejora(costo)){
			this.j.mejoraVelocidad();
			costo = (int) (costo*1.15);
		}
	}

}
