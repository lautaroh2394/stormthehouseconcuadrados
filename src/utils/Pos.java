package utils;

public class Pos {
	
	public double x;
	public double y;
	public int ancho;
	
	public Pos(double x2, double y2){
		this.x = x2;
		this.y = y2;
	}
	
	public Pos(double x2, double y2, int a){
		this(x2,y2);
		this.ancho = a;
	}

}
