package utils;

import main.Game;

public class OpcionInicioSalir extends OpcionInicio {

	public OpcionInicioSalir(int x, int y, int valor, int ancho, int alto, Game game) {
		super(x, y, valor, ancho, alto, game);
		text = "Salir";
	}

	@Override
	public void accion() {
		System.exit(1);

	}

}
