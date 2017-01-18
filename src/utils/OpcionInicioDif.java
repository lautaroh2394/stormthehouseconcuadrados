package utils;

import main.Game;

public class OpcionInicioDif extends OpcionInicio{

	public OpcionInicioDif(int x, int y, int valor, int ancho, int alto, Game game) {
		super(x, y, valor, ancho, alto, game);
		asignartexto(valor); 
		posLl = 1;
	}
	
	private void asignartexto(int v){
		if (v==1){text = "Facil";}
		if (v==2){text = "Normal";}
		if (v==3){text = "Dificil";}
		if (v==4){text = "IMPOSIBRUH";}
	}
	
	public void accion(){
		game.dificultad = valor;
	}

}
