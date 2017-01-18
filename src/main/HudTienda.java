package main;

import java.awt.Graphics;
import java.util.LinkedList;

import gameobjects.Jugador;

public class HudTienda {
	
	LinkedList<MiniHud> minihuds;
	
	public HudTienda(LinkedList<Jugador> js){
		minihuds = new LinkedList<MiniHud>();
		
		setMiniHuds(js);
	}
	
	private void setMiniHuds(LinkedList<Jugador> js){
		MiniHud temp; 
		for (Jugador j : js){
			temp = new MiniHud(j);
			minihuds.add(temp);
		}
	}
	
	public void render(Graphics g){
		for(MiniHud m : minihuds){
			m.render(g);
		}
	}

}
