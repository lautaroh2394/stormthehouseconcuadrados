package utils;

import gameobjects.Jugador;
import main.Handler;
import main.MenuTienda;

public class OpcionMejoraRecarga extends Opcion {

	public OpcionMejoraRecarga(Jugador j, int x, int y, Handler h, MenuTienda m) {
		super(j, x, y, h, m);
		this.text = "Mejora Recarga";
		this.costo = 3000;
		}
	
	@Override
	public void clickeada(){
//		if (this.j.mejora(costo)){
		if (this.j.getArma().puedeMejorarRec() && this.j.mejora(costo)){
			this.j.mejoraRecarga();
			costo = (int) (costo*1.15);
		}
	}

}
