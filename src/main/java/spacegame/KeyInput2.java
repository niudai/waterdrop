package spacegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyInput2 extends KeyAdapter{
	Player2 player;
	public KeyInput2(Player2 player) {
		this.player = player;
	}
	public void keyPressed(KeyEvent e) {
		try {
			player.keyPressed(e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
}
