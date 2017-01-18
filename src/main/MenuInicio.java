package main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import utils.OpcionInicio;
import utils.OpcionInicioCF;
import utils.OpcionInicioCJ;
import utils.OpcionInicioDif;
import utils.OpcionInicioEmpezar;
import utils.OpcionInicioSalir;
import utils.Sound;

public class MenuInicio extends MouseAdapter {
	
	private Game game;
	private int h;
	private int w;
	
	private int anchobtn;
	private int altobtn;
	
	LinkedList<OpcionInicio> botones; 
	LinkedList<OpcionInicio> elegidos;
	
	public MenuInicio(Game game){
		this.game = game;
		this.h = Game.HEIGHT;
		this.w = Game.WIDTH;
		setearBotones(h,w);
		setearElegidos();
		Sound.BackgrInicio.loop();
	}	
	
	private void setearElegidos(){
		elegidos= new LinkedList<OpcionInicio>();
		for (int i = 0; i<3;i++){
			elegidos.add(botones.get(i*4));
			botones.get(i*4).accion();
		}
	}
	
	private void setearBotones(int h, int w){
		
		int unidadx = w/35;
		int unidady = h/20;
		
		altobtn = 2*unidady;
		anchobtn = 7*unidadx;
		
		botones = new LinkedList<OpcionInicio>();
		
		agregarBtnCJ(unidadx,unidady);
		agregarBtnDif(unidadx,unidady);
		agregarBtnCF(unidadx,unidady);
		agregarBtnEmpezar(unidadx,unidady);
		agregarBtnSalir(unidadx,unidady);
		agregarBtnAyuda(unidadx,unidady);
		
	}
		
	private void agregarBtnCJ(int x, int y){
		
		int cantOpCj = 4;
		int auxx = x;
		int valor = 1;
		for (int i = 0; i<cantOpCj; i++){
			botones.add(new OpcionInicioCJ(auxx,y,valor,anchobtn,altobtn,game));
			auxx = x*8+auxx;
			valor++;
		}
		
	}
	
	private void agregarBtnDif(int x, int y){
			
		int cantOpCj = 4;
		int auxx = x;
		int auxy = y*5;
		int valor = 1;
		for (int i = 0; i<cantOpCj; i++){
			botones.add(new OpcionInicioDif(auxx,auxy,valor,anchobtn,altobtn,game));
			auxx = x*8+auxx;
			valor++;
		}
	}
	
	private void agregarBtnCF(int x, int y){
		
		int cantOpCj = 6;
		int auxx = x;
		int auxy = y*9;
		int valor = 4;
		for (int i = 0; i<cantOpCj; i++){
			if (valor == 20) {valor = 30;};
			botones.add(new OpcionInicioCF(auxx,auxy,valor,anchobtn,altobtn,game));
			auxx = x*7+auxx;
			valor+= 4;
		}
	}
	
	private void agregarBtnEmpezar(int x, int y){
		botones.add(new OpcionInicioEmpezar(10*x,17*y,0, anchobtn,altobtn,game));
	}
	private void agregarBtnSalir(int x, int y){
		botones.add(new OpcionInicioSalir(18*x,17*y,0, anchobtn,altobtn,game));
	}
	
	private void agregarBtnAyuda(int x, int y){
		botones.add(new OpcionInicioAyuda(28*x,17*y,0, anchobtn,altobtn/2,game,this));
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();

		if (Game.estado == StatusPantalla.inicio){ 
			for (OpcionInicio oi : botones){
				if (oi.mouseOver(mx,my)){
					Sound.play(Sound.Click);	
					oi.elegido(elegidos);
					
				}
			}
		}

	}
	
	
	public void render(Graphics g){
		
		opciones(g);
		opcionesElegidas(g);
		
	}
	
	private void opciones(Graphics g){
		for(OpcionInicio oi : botones){
			oi.render(g);
		}
	}
	

	private void opcionesElegidas(Graphics g){
		
		if (elegidos != null){
			for (OpcionInicio oi : elegidos){
				oi.renderElegido(g);
			}
		}
	}
	

}
