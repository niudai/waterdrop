package spacegame;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class MusicPlayer{
	URL bulletsound;
	URL bouncesound;
	URL explosionsound;
	URL backgroundsound;
	URL explosionsound2;
	public URL getBackgroundsound() {
		return backgroundsound;
	}
	public void setBackgroundsound(URL backgroundsound) {
		this.backgroundsound = backgroundsound;
	}
	public URL getBulletsound() {
		return bulletsound;
	}
	public void setBulletsound(URL bulletsound) {
		this.bulletsound = bulletsound;
	}
	public URL getBouncesound() {
		return bouncesound;
	}
	public void setBouncesound(URL bouncesound) {
		this.bouncesound = bouncesound;
	}
	public URL getExplosionsound() {
		return explosionsound;
	}
	public void setExplosionsound(URL explosionsound) {
		this.explosionsound = explosionsound;
	}
	public URL getExplosionsound2() {
		return explosionsound2;
	}
	public void setExplosionsound2(URL explosionsound) {
		this.explosionsound2 = explosionsound;
	}
	public void playSound() {
		try {
			//URL url = getClass().getResource("../Space Game2/res/BGM 1.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(backgroundsound);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
			clip.start();
			clip.loop(-1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void bulletSound() {
		try {
			//URL url = getClass().getResource("../Space Game2/res/bulletsound.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(bulletsound);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-5);
			clip.start();
			
		}catch(Exception e) {
			System.out.println("Why sound");
			e.printStackTrace();
		}
	}
	public void bouncesound() {
		try {
			//URL url = getClass().getResource("../Space Game2/res/bulletsound.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(bouncesound);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-5);
			clip.setFramePosition(0);
			clip.start();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void explosionsound() {
		try {
			//URL url = getClass().getResource("../Space Game2/res/bulletsound.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(explosionsound);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-5);
			clip.start();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void explosionsound2() {
		try {
			//URL url = getClass().getResource("../Space Game2/res/bulletsound.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(explosionsound2);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-5);
			clip.start();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
