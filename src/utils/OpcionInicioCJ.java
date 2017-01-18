package utils;

import main.Game;

public class OpcionInicioCJ extends OpcionInicio{
	
	public OpcionInicioCJ(int x, int y, int valor, int ancho, int alto, Game game){
		super(x,y,valor,ancho,alto,game);
		text = valor + "Jugadores";
		posLl = 0;
	}
	@Override
	public void accion(){
		game.cantJug = valor;
	}

}
