package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import utils.Sound;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 1024, HEIGHT = WIDTH /12*9;
	
	public String text;
	
	private Thread thread;
	private boolean running;
	
	private Handler handler;
	private Hud hud;
	
	private MenuInicio menuinicio;
	private MenuTienda menutienda;
	private MenuDerrota menuderrota;
	
	private KeyInput kinput;
	
	static public StatusPantalla estado;// = StatusPantalla.juego;
	
	public static boolean ayuda = false;
	public int filas = 6;
	public int cantJug = 3;
	public int dificultad = 1; //1 facil, 2 normal, 3 dif, 4 imposible
	
	public Game(){
		
		inicio();
		
		new Ventana(WIDTH,HEIGHT,"Storm the circles - "+text, this);
		
		
	}
	
	public void inicio(){
		text = "";
		
		estado = StatusPantalla.inicio;
		
		menuinicio = new MenuInicio(this);
		
		this.addMouseListener(menuinicio);
	}
	
	public void empiezaElJuego(){
		handler = new Handler(WIDTH, HEIGHT, filas, dificultad, cantJug);
		hud = new Hud(handler, WIDTH, HEIGHT);
		
		menutienda = new MenuTienda(handler);
		
		kinput = new KeyInput(handler,this,menutienda); 
		
		this.addKeyListener(kinput);
		
		menuderrota = new MenuDerrota(handler,this);
		this.addMouseListener(menuderrota);
		
	}
	
	public void borrar(){
		this.removeMouseListener(menuderrota);
		this.removeKeyListener(kinput);
		
		handler = null;
		hud = null;
		menutienda = null;
		kinput = null; 
		menuderrota = null;
		
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running=false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();//hace que la ventana tenga el foco automaticamente, la trae "adelante" al iniciar
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns= 1000000000/amountOfTicks; //1 seg sobre amountOfTicks -> seria el intervalo entre ticks
		double delta=0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		
		while (running){
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1){
				tick();
				delta--;
			}
			if (running) {render();}
			frames++;
			
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				text = "fps: "+frames;
				Ventana.UpdateTitle(text);
				System.out.println(text);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		if (Game.estado == StatusPantalla.inicio) {}
		else if (Game.estado == StatusPantalla.juego) {handler.tick();}
		else if (Game.estado == StatusPantalla.finNivel) {menutienda.tick();handler.finNivel();}
		else if (Game.estado == StatusPantalla.Derrota) {menuderrota.tick();}
	}
	
	private void render(){
		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);//crea tres buffers en el game
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (estado == StatusPantalla.inicio){
			menuinicio.render(g);
		}
		else if (estado == StatusPantalla.juego){
			handler.render(g);
			hud.render(g);
		}
		else if (estado == StatusPantalla.finNivel){
			menutienda.render(g);
		}
		else if (estado == StatusPantalla.Derrota){
			menuderrota.render(g);
		} 
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]){
		new Game();
	}

}
