package stormthehouseconcuadrados;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gameobjects.Casa;
import gameobjects.Jugador;

public class TestMovimientoJugador {

	Casa casa;
	Jugador jug;
	
	@Before
	public void setup(){
		casa = new Casa(1000,1000,6,1,null);
		jug = new Jugador(1,casa);
	}
	@Test
	public void test() {
		assertEquals(6, casa.getPos().size());
	}
	
	@Test
	public void testposjug1() {
		
		assertEquals(805, casa.getPos().get(0).x, 0);
		assertEquals(350, casa.getPos().get(0).y, 0);
		
		assertEquals(jug.getPos().x, casa.getPos().get(0).x, 0);
		assertEquals(jug.getPos().y, casa.getPos().get(0).y, 0);
	}
	
	@Test
	public void testmover(){
		assertEquals(jug.getPos().x, casa.getPos().get(0).x, 0);
		assertEquals(jug.getPos().y, casa.getPos().get(0).y, 0);
		jug.moverse(-1);
		assertEquals(jug.getPos().x, casa.getPos().get(0).x, 0);
		assertEquals(jug.getPos().y, casa.getPos().get(0).y, 0);
	}
	
	@Test
	public void testmover2(){
		assertEquals(jug.getPos().x, casa.getPos().get(0).x, 0);
		assertEquals(jug.getPos().y, casa.getPos().get(0).y, 0);
		jug.moverse(1);
		assertEquals(jug.getPos().x, casa.getPos().get(1).x, 0);
		assertEquals(jug.getPos().y, casa.getPos().get(1).y, 0);
	}

}
