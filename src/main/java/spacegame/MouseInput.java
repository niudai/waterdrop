package spacegame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	Game game;
	MouseInput(Game game0){
		game = game0;
	}
	int mx,my;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*public Rectangle playBotton = new Rectangle(Game.WIDTH/2 + 120, 150, 100, 50);
	public Rectangle helpBotton = new Rectangle(Game.WIDTH/2 + 120, 250, 100, 50);
	public Rectangle quitBotton = new Rectangle(Game.WIDTH/2 + 120, 350, 100, 50);
		 */
		mx = e.getX();
		my = e.getY();
		if(game.state == Game.STATE.MENU || game.state == Game.STATE.MENU2 || game.state == Game.STATE.WIN)
		{	
			if (mx > game.menu.playBotton.x && mx < game.menu.playBotton.x+game.menu.playBotton.width)
			{	if(my > game.menu.playBotton.y && my < game.menu.playBotton.y+game.menu.playBotton.height) {
				game.setState(Game.STATE.GAME);			
				} else if(my > game.menu.helpBotton.y && my < game.menu.helpBotton.y+game.menu.helpBotton.height) {
					game.setState(Game.STATE.HELP);
				} else if(my > game.menu.quitBotton.y && my < game.menu.quitBotton.y+game.menu.quitBotton.height) {
					game.setState(Game.STATE.STOP);
				}
		// TODO Auto-generated method stub
		}
		}else if(game.state == Game.STATE.HELP) {
			if (mx > game.menu.backBotton.x && 
					mx < game.menu.backBotton.x+game.menu.backBotton.width && 
					my > game.menu.backBotton.y && 
					my < game.menu.backBotton.y+game.menu.backBotton.height) {
				game.setState(Game.STATE.MENU);
			    
			}
		}else if(game.state == Game.STATE.MENU1_2) {
			if(mx > game.menu.enterBotton.x && 
					mx < game.menu.enterBotton.x+game.menu.enterBotton.width && 
					my > game.menu.enterBotton.y && 
					my < game.menu.enterBotton.y+game.menu.enterBotton.height) {
				game.setState(Game.STATE.GAME2);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
