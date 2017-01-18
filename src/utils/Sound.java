package utils;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Sound{

	
	public static AudioClip Disparo = Applet.newAudioClip(Sound.class.getResource("popDisparoJugador.wav"));
	public static AudioClip Golpe = Applet.newAudioClip(Sound.class.getResource("popGolpeAEnemigo.wav"));
	public static AudioClip Click = Applet.newAudioClip(Sound.class.getResource("click.wav"));
	public static AudioClip DisparoSinBalas = Applet.newAudioClip(Sound.class.getResource("popDisparoSinBalas.wav"));
	public static AudioClip Recarga = Applet.newAudioClip(Sound.class.getResource("Recarga.wav"));
	public static AudioClip BackgrInicio = Applet.newAudioClip(Sound.class.getResource("BackgroundInicioBeaster-Dancing Alone.wav"));
	public static AudioClip Tienda = Applet.newAudioClip(Sound.class.getResource("Elevator-music.wav"));
	public static AudioClip BackgrJuego = Applet.newAudioClip(Sound.class.getResource("BackgrJuego.wav"));
	public static AudioClip Derrota = Applet.newAudioClip(Sound.class.getResource("Derrota.wav"));
	
	
//	public static AudioClip BackgrJuego1 = Applet.newAudioClip(Sound.class.getResource("02-Bass-Loop-114-bpm.wav"));
//	public static AudioClip BackgrJuego2 = Applet.newAudioClip(Sound.class.getResource("03-Bass-Loop-114-bpm.wav"));
//	public static AudioClip BackgrJuego3 = Applet.newAudioClip(Sound.class.getResource("04-Bass-Loop-114-bpm.wav"));
//	public static AudioClip BackgrJuego4 = Applet.newAudioClip(Sound.class.getResource("07-Bass-Loop-115-bpm.wav"));
//	public static AudioClip BackgrJuego5 = Applet.newAudioClip(Sound.class.getResource("08-Bass-Loop-115-bpm.wav"));
//	public static AudioClip BackgrJuego6 = Applet.newAudioClip(Sound.class.getResource("09-Bass-Loop-117-bpm.wav"));
//	public static AudioClip BackgrJuego7 = Applet.newAudioClip(Sound.class.getResource("10-Bass-Loop-117-bpm.wav"));
//	public static AudioClip BackgrJuego8 = Applet.newAudioClip(Sound.class.getResource("11-Bass-Loop-117-bpm.wav"));
//	public static AudioClip BackgrJuego9 = Applet.newAudioClip(Sound.class.getResource("12-Bass-Loop-117-bpm.wav"));
//	public static AudioClip BackgrJuego10= Applet.newAudioClip(Sound.class.getResource("14-Bass-Loop-117-bpm.wav"));
	
	public static void play(AudioClip c){
		c.stop();
		c.play();
	}
//	
//	public static Hilo h;
//	
//	public static void musicaFJ(){
//		h = new Hilo();
//		h.run();
//	}
//	
//	public static void musicaFJparar(){
//		h.b=false;
//	}
	
	
}

//class Hilo extends Thread{
//	
//	public boolean b = true;
//	String[] files = {	"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\02-Bass-Loop-114-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\03-Bass-Loop-114-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\03-Bass-Loop-114-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\04-Bass-Loop-114-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\07-Bass-Loop-115-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\08-Bass-Loop-115-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\09-Bass-Loop-117-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\10-Bass-Loop-117-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\11-Bass-Loop-117-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\12-Bass-Loop-117-bpm.wav",
//			"C:\\Users\\Lau\\workspace\\stormthehouseconcuadrados\\src\\utils\\14-Bass-Loop-117-bpm.wav",
//			};
//	
//	
//	public Hilo(){
//	}
//	
//	public void run(){
//			
//		    byte[] buffer = new byte[4096];
//		    for(int i = 0; i<files.length;i++){
//		    	if (b){
//			        File file = new File(files[i]);
//			        try {
//			            AudioInputStream is = AudioSystem.getAudioInputStream(file);
//			            AudioFormat format = is.getFormat();
//			            SourceDataLine line = AudioSystem.getSourceDataLine(format);
//			            line.open(format);
//			            line.start();
//			            while (is.available() > 0) {
//			                int len = is.read(buffer);
//			                line.write(buffer, 0, len);
//			            }
//			            line.drain();
//			            line.close();
//			        } catch (Exception ex) {
//			            ex.printStackTrace();
//			        }
//		        }
//		    }
//		}
//	}