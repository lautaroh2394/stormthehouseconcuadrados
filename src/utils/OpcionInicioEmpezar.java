package utils;

import main.Game;
import main.StatusPantalla;

public class OpcionInicioEmpezar extends OpcionInicio {

	public OpcionInicioEmpezar(int x, int y, int valor, int ancho, int alto, Game game) {
		super(x, y, valor, ancho, alto, game);
		text = "Empezar";
	}

	@Override
	public void accion() {
		Sound.BackgrInicio.stop();
		Sound.BackgrJuego.play();
		game.empiezaElJuego();
		Game.estado = StatusPantalla.juego;

	}

}
