package spacegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyInput extends KeyAdapter{
	Player player;
	public KeyInput(Player player) {
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
/*How does KeyAdapter work?
	first , you need to creat a class X, which inherits the abstract class "KeyAdapter".abstract.
    class. Second , you need to use the method "AddKeyListener(X)" to creat a KeyListener.
    Once a key is pressed, the keypressed method of X is called.
 */ 