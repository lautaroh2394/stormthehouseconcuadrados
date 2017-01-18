package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import gameobjects.Jugador;
import utils.Sound;


public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	Game game;
	MenuTienda m;
	

	public KeyInput(Handler handler, Game game, MenuTienda m){
		this.handler = handler;		
		 this.game = game;
		 this.m = m;
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if (Game.estado == StatusPantalla.juego){
			
			for(int i = 0; i<handler.getCasa().getJugadores().size();i++){
				Jugador tempJugador= handler.getCasa().getJugadores().get(i);
				
				if (tempJugador.getId() == 1) {		
					if (key == KeyEvent.VK_A) {tempJugador.moverse(1);}
					if (key == KeyEvent.VK_Q) {tempJugador.moverse(-1);}
					if (key == KeyEvent.VK_Z) {tempJugador.dispara();}
					if (key == KeyEvent.VK_X) {tempJugador.recarga();}
				}
				
				else if (tempJugador.getId() == 0) {		
					if (key == KeyEvent.VK_DOWN) {tempJugador.moverse(1);}
					if (key == KeyEvent.VK_UP) {tempJugador.moverse(-1);}
					if (key == KeyEvent.VK_LEFT) {tempJugador.dispara();}
					if (key == KeyEvent.VK_RIGHT) {tempJugador.recarga();}
				}
				
				else if (tempJugador.getId() == 2) {		
					if (key == KeyEvent.VK_H) {tempJugador.moverse(1);}
					if (key == KeyEvent.VK_Y) {tempJugador.moverse(-1);}
					if (key == KeyEvent.VK_N) {tempJugador.dispara();}
					if (key == KeyEvent.VK_M) {tempJugador.recarga();}
				}
				
				else if (tempJugador.getId() == 3) {		
					if (key == KeyEvent.VK_PAGE_DOWN) {tempJugador.moverse(1);}
					if (key == KeyEvent.VK_PAGE_UP) {tempJugador.moverse(-1);}
					if (key == KeyEvent.VK_BACK_SPACE) {tempJugador.dispara();}
					if (key == KeyEvent.VK_DELETE) {tempJugador.recarga();}
				}
			}	
		}
		else if (Game.estado == StatusPantalla.finNivel){
			for(int i = 0; i<handler.getCasa().getJugadores().size();i++){
				Jugador tempJugador= handler.getCasa().getJugadores().get(i);
				
				if (tempJugador.getId() == 1) {		
					if (key == KeyEvent.VK_A) {tempJugador.seleccion(1);}
					if (key == KeyEvent.VK_Q) {tempJugador.seleccion(-1);}
					if (key == KeyEvent.VK_X) {tempJugador.seleccionOpcion();}
				}
				
				else if (tempJugador.getId() == 0) {		
					if (key == KeyEvent.VK_DOWN) {tempJugador.seleccion(1);}
					if (key == KeyEvent.VK_UP) {tempJugador.seleccion(-1);}
					if (key == KeyEvent.VK_RIGHT) {tempJugador.seleccionOpcion();;}
				}
				
				else if (tempJugador.getId() == 2) {		
					if (key == KeyEvent.VK_H) {tempJugador.seleccion(1);}
					if (key == KeyEvent.VK_Y) {tempJugador.seleccion(-1);}
					if (key == KeyEvent.VK_M) {tempJugador.seleccionOpcion();}
				}
				
				else if (tempJugador.getId() == 3) {		
					if (key == KeyEvent.VK_PAGE_DOWN) {tempJugador.seleccion(1);}
					if (key == KeyEvent.VK_PAGE_UP) {tempJugador.seleccion(-1);}
					if (key == KeyEvent.VK_DELETE) {tempJugador.seleccionOpcion();}
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(1);
		
		int key = e.getKeyCode();
		if (Game.estado == StatusPantalla.juego){
			
			for(int i = 0; i<handler.getCasa().getJugadores().size();i++){
				Jugador tempJugador= handler.getCasa().getJugadores().get(i);
				
				if (tempJugador.getId() == 1) {
					if (key == KeyEvent.VK_Z) {tempJugador.getArma().toggleDisparoContinuo();}
				}
				
				else if (tempJugador.getId() == 0) {
					if (key == KeyEvent.VK_LEFT) {tempJugador.getArma().toggleDisparoContinuo();}
				}
				
				else if (tempJugador.getId() == 2) {
					if (key == KeyEvent.VK_N) {tempJugador.getArma().toggleDisparoContinuo();}
				}
				
				else if (tempJugador.getId() == 3) {
					if (key == KeyEvent.VK_BACK_SPACE) {tempJugador.getArma().toggleDisparoContinuo();}
				}
			}	
		}
	}
}

