package utils;

import java.awt.Graphics;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

import gameobjects.Jugador;
import main.Handler;
import main.MenuTienda;

public class OpcionOK extends Opcion{

	public OpcionOK(Jugador j, int x, int y, Handler h, MenuTienda m) {
		super(j, x, y, h, m);
		this.text = "Listo";
	}
	
	@Override
	public void dibujarText(Graphics g){
		g.drawString(text, x+w/5, y+h/2);
	}
	
	@Override
	public void clickeada(){

		this.j.comienzaNivel();
		this.m.jugadorEstaListo(j);
	}

}
