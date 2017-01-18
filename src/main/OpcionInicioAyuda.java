package main;

import java.util.LinkedList;

import utils.OpcionInicio;

public class OpcionInicioAyuda extends OpcionInicio {
	
	MenuInicio mi;

	public OpcionInicioAyuda(int x, int y, int valor, int ancho, int alto, Game game, MenuInicio m) {
		super(x, y, valor, ancho, alto, game);
		text = "Empezar con ayuda";
		mi = m;
		posLl = 4;
	}

	@Override
	public void accion() {
		this.game.ayuda = !this.game.ayuda;
	}
	
	@Override
	protected void setearse(LinkedList<OpcionInicio> loi){
		if (this.game.ayuda){
			if (!loi.contains(this)) {loi.add(this);}
		}
		else {
			if (loi.contains(this)) {loi.remove(this);}
		}
	}

}
