package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import armas.ArmaBasica;
import utils.Opcion;
import utils.Pos;

public class Jugador extends GameObject{
	
	private int id;
	private Pos pos;
	private int posx;
	private ArmaBasica arma;
	
	private int radio = 15;
	private boolean finNivel = false;
	
	private LinkedList<Opcion> opciones;
	private int opcionseleccionada = 0;
	
	private Casa casa;
	
	private Color col;
	
	private int score = 0;
	private long maney = 2500;
	
	public Jugador(int id,Casa c){
		this.id = id;
		setCasa(c);
		arma = new ArmaBasica(this);
		
		setColor();
		
		opciones = new LinkedList<Opcion>();
	}
	
	public void setCasa(Casa c){
		this.casa =c;
		setPos(c.getPos().get(0));
		posx = (int) (pos.x+pos.ancho*id+10);
		
	}

	@Override
	public void tick() {
		arma.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(col);
		g.fillOval((int) (posx), (int) (pos.y), radio, radio);
		
		arma.render(g);
	}
	
	public void moverse(int i){
		//-1 = abajo, 1 = arriba
		this.casa.meMuevo(this,i);
	}
	
	public Pos getPos(){
		return pos;
	}
	
	public void setPos(Pos p){
		this.pos = p;
	}
	
	public void dispara(){
		arma.dispara();
	}
	
	private void setColor(){
		if (id == 0) {this.col = Color.yellow;}
		if (id == 1) {this.col = Color.WHITE;}
		if (id == 2) {this.col = Color.DARK_GRAY;}
		if (id == 3) {this.col = Color.green;}
	}
	
	public int getId(){
		return id;
	}
	
	public Color getColor(){
		return col;
	}
	
	public void sumarScore(int v){
		score+=v;
		maney+=v;
	}
	
	public int getScore(){
		return score;
	}
	
	public long getMoney(){
		return maney;
	}
	
	public void recarga(){
		arma.activarRecarga();
	}
	
	public ArmaBasica getArma(){
		return arma;
	}
	
	public void esFinNivel(){
		finNivel = true;
		arma.esFinNivel();
	}
	
	public void comienzaNivel(){
		finNivel = false;
		arma.comienzaNivel();
	}
	
	public void seleccionOpcion(){
		this.opciones.get(this.opcionseleccionada).clickeada();
	}

	public void tickTienda() {
		// TODO Auto-generated method stub
		
	}
	
	public void renderTienda(Graphics g){
		for (Opcion o : opciones){
			o.render(g);
		}
		
		renderSeleccionado(g);
	}
	
	private void renderSeleccionado(Graphics g){
		this.opciones.get(this.opcionseleccionada).seleccionada(g);
	}
	
	public void agregarBoton(Opcion o){
		opciones.add(o);
	}

	public boolean mejora(int c){
		if (c<=maney){
			maney -= c;
			return true;
		}
		else return false;
	}
	
	public void mejoraClip(){
		arma.mejoraClip();
	}
	
	public void mejoraVelocidad(){
		arma.mejoraVelocidad();
	}
	
	public void mejoraRecarga(){
		arma.mejoraRecarga();
	}
	
	public void seleccion(int n){
		if (finNivel){
			int proxopcionseleccionada = opcionseleccionada + n;
			if (proxopcionseleccionada >= 0 && proxopcionseleccionada < this.opciones.size()){
				opcionseleccionada +=n;
			}
		}
	}
	
	public LinkedList<Opcion> getOpciones(){
		return opciones; 
	}
}
