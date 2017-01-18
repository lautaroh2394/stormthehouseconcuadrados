package armas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import gameobjects.Jugador;
import utils.Sound;

public class ArmaBasica {
	
	private int clip = 10;
	private int clipMax = 10;
	
	private double ticksDeRecargaPorBala = 2.5;
	private int ticksRecargando = 0;
	private boolean recargando = false;
	
	private double limiteTickDisparoPorBala = 0.01;
	private double limiteTickRecargaPorBala = 0.01;
	
	private int ticksDisparando = 45;
	private double ticksDeDisparoPorBala = 4.5;
	
	private boolean disparoContinuo = false;
	
	
	private Jugador miJug;
	private LinkedList<BalaBasica> balas;
	
	private LinkedList<BalaBasica> balasABorrar;
	private LinkedList<BalaBasica> balasAAgregar;
	
	private Color color;
	

	public ArmaBasica(Jugador j){
		miJug = j;
		balas = new LinkedList<BalaBasica>();
		balasABorrar = new LinkedList<BalaBasica>();
		balasAAgregar = new LinkedList<BalaBasica>();
		
		setColor(j.getColor());
	}
	public void dispara() {
		if (!disparoContinuo && clip>0 && !recargando && (ticksDisparando >= ticksDeDisparoPorBala*clipMax)){
			Sound.Disparo.play();;
			balasAAgregar.add(new BalaBasica(miJug.getPos(), this));
			ticksDisparando=0;
			restaClip();
		}
		if (clip<=0){ Sound.DisparoSinBalas.play();;}
	}
	
	private void disparoContinuo(){
		if (disparoContinuo && ticksDeDisparoPorBala<=0.25 && clipMax>=150){
			if (clip>0 && !recargando && (ticksDisparando >= ticksDeDisparoPorBala*clipMax)){
				Sound.Disparo.play();;
				balasAAgregar.add(new BalaBasica(miJug.getPos(), this));
				ticksDisparando=0;
				restaClip();
			}
			if (clip<=0){ Sound.DisparoSinBalas.play();;}
		}
		
	}
	
	public void recarga(){
		Sound.Recarga.play();
		clip = clipMax;
		recargando = false;
		ticksRecargando = 0;
		
	}
	
	private void restaClip(){
		clip--;
		if (clip<0) {clip = 0;};
	}
	
	public void noSirve(BalaBasica b){
		Sound.Golpe.play();
		balasABorrar.add(b);
	}
	
	public void render(Graphics g){
		for (BalaBasica b : balas){
			b.render(g);
		}
	}
	
	private void agregarbalas(){
		for(BalaBasica b : balasAAgregar){
			balas.add(b);
			balasAAgregar.remove(b);
		}
		
		balasAAgregar = new LinkedList<BalaBasica>();
	}
	
	private void borrarbalas(){
		for(BalaBasica b : balasABorrar){
			balas.remove(b);
		}
		balasABorrar = new LinkedList<BalaBasica>();
	}
	
	public void tick(){
		
		disparoContinuo();
		
		sumarTickDisparo();
		
		if (recargando){
			ticksRecargando++;
			if (ticksRecargando >= ticksDeRecargaPorBala*clipMax){
				recarga();
			}
		}
		
		for (BalaBasica b : balas){
			b.tick();
		}

		agregarbalas();
		borrarbalas();
		
	}
	
	private void setColor(Color c){
		color = c;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void matoUno(int v){
		miJug.sumarScore(v);
	}
	
	public void activarRecarga(){
		recargando = true;
	}
	
	public int getClip(){
		return clip;
	}
	
	public int getClipMax(){
		return clipMax;
	}
	
	public boolean estaRecargando(){
		return recargando;
	}
	
	public int getTicksRecargando(){
		return ticksRecargando;
	}
	
	private void sumarTickDisparo(){
		if (ticksDisparando<ticksDeDisparoPorBala*clipMax){
		ticksDisparando+=1;}
	}
	
	public int getTicksDeRecarga(){
		return (int) (ticksDeRecargaPorBala*clipMax);
	}
	
	public void esFinNivel(){
		
		
	}
	
	public void mejoraClip(){
		this.clipMax++;
	}
	
	public void mejoraRecarga(){
		this.ticksDeRecargaPorBala = ticksDeRecargaPorBala*0.9;
		if (ticksDeRecargaPorBala < limiteTickRecargaPorBala){ticksDeRecargaPorBala = limiteTickRecargaPorBala;};
	}
	
	public void mejoraVelocidad(){
		this.ticksDeDisparoPorBala = ticksDeDisparoPorBala*0.9;
		if (ticksDeDisparoPorBala < limiteTickDisparoPorBala){ticksDeDisparoPorBala = limiteTickDisparoPorBala;};
	}
	
	public boolean puedeMejorarVel(){
		return !(ticksDeDisparoPorBala <= limiteTickDisparoPorBala);
	}
	
	public boolean puedeMejorarRec(){
		return !(ticksDeRecargaPorBala <= limiteTickRecargaPorBala);
	}
	
	public void comienzaNivel(){
		
		this.recargando = false;
		this.ticksDisparando = (int) (ticksDeDisparoPorBala*clipMax);
		this.clip = clipMax;
		this.ticksRecargando = 0;
		balas = new LinkedList<BalaBasica>();
		
	}
	
	public void toggleDisparoContinuo(){
		disparoContinuo = !disparoContinuo; 
	}

}
