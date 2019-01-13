package spacegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Menu {
	Game game;
	int score = 0;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Rectangle playBotton = new Rectangle(Game.WIDTH*Game.SCALE/3, Game.HEIGHT*Game.SCALE/3, 
			Game.WIDTH*Game.SCALE/8, Game.HEIGHT*Game.SCALE/9);
	public Rectangle helpBotton = new Rectangle(Game.WIDTH*Game.SCALE/3, Game.HEIGHT*Game.SCALE/3+Game.HEIGHT*Game.SCALE/5,
			Game.WIDTH*Game.SCALE/8, Game.HEIGHT*Game.SCALE/9);
	public Rectangle quitBotton = new Rectangle(Game.WIDTH*Game.SCALE/3, Game.HEIGHT*Game.SCALE/3+Game.HEIGHT*Game.SCALE*2/5,
			Game.WIDTH*Game.SCALE/8, Game.HEIGHT*Game.SCALE/9);
	public Rectangle enterBotton = new Rectangle(Game.WIDTH*Game.SCALE/3, Game.HEIGHT*Game.SCALE/3+Game.HEIGHT*Game.SCALE*2/5,
			Game.WIDTH*Game.SCALE/8, Game.HEIGHT*Game.SCALE/9);
	public Rectangle backBotton = new Rectangle(Game.WIDTH*Game.SCALE/3, Game.HEIGHT*Game.SCALE/5,
			Game.WIDTH*Game.SCALE/8, Game.HEIGHT*Game.SCALE/11);
	public BufferedImage background;
	public BufferedImage getBackground() {
		return background;
	}
	public void setBackground(BufferedImage background) {
		this.background = background;
	}
	Menu(Game game0){
		game = game0;
	}
	public void render_start(Graphics g) {
		g.drawImage(background, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
		g.drawImage(game.pics.getText_head(), Game.WIDTH*Game.SCALE/6, 
				Game.HEIGHT*Game.SCALE/9, Game.WIDTH*Game.SCALE*4/6, 
				Game.HEIGHT*Game.SCALE/9, null);
		g.drawImage(game.pics.getText_designerlogo(), Game.WIDTH*Game.SCALE*3/5, 
				Game.HEIGHT*Game.SCALE*3/9, Game.WIDTH*Game.SCALE*1/3, 
				Game.HEIGHT*Game.SCALE/9, null);
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		Font fnt1 = new Font("arial", Font.ITALIC, 30);
		g.setFont(fnt1);
		g.drawImage(game.pics.getText_play(), playBotton.x, playBotton.y, 
				playBotton.width, playBotton.height, null);
		g.drawImage(game.pics.getText_help(), helpBotton.x, helpBotton.y, 
				helpBotton.width, helpBotton.height, null);
		g.drawImage(game.pics.getText_quit(), quitBotton.x, quitBotton.y, 
				quitBotton.width, quitBotton.height, null);
		g2d.draw(playBotton);
		g2d.draw(helpBotton);
		g2d.draw(quitBotton);
	}
	public void render_over(Graphics g) {
		g.drawImage(background, 0, 0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE,null);
		g.drawImage(game.pics.getText_head(), Game.WIDTH*Game.SCALE/6, 
				Game.HEIGHT*Game.SCALE/9, Game.WIDTH*Game.SCALE*4/6, 
				Game.HEIGHT*Game.SCALE/9, null);
		g.drawImage(game.pics.getText_youscored(), Game.WIDTH*Game.SCALE*3/5, 
				Game.HEIGHT*Game.SCALE*3/9, Game.WIDTH*Game.SCALE*1/3, 
				Game.HEIGHT*Game.SCALE/9, null);
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, Game.HEIGHT*Game.SCALE/9);
		g.setFont(fnt0);
		g.setColor(Color.white);	
		g.drawString(""+score,Game.WIDTH*Game.SCALE*14/15, Game.HEIGHT*Game.SCALE*4/9);
		g.drawImage(game.pics.getText_playagain(), playBotton.x, playBotton.y, 
				playBotton.width, playBotton.height, null);
		g.drawImage(game.pics.getText_help(), helpBotton.x, helpBotton.y, 
				helpBotton.width, helpBotton.height, null);
		g.drawImage(game.pics.getText_quit(), quitBotton.x, quitBotton.y, 
				quitBotton.width, quitBotton.height, null);
		g2d.draw(playBotton);
		g2d.draw(helpBotton);
		g2d.draw(quitBotton);
	}
	public void render_help(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(background, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
		g.drawImage(game.pics.getText_head(), Game.WIDTH*Game.SCALE/6, 
				Game.HEIGHT*Game.SCALE/9, Game.WIDTH*Game.SCALE*4/6, 
				Game.HEIGHT*Game.SCALE/9, null);
		Font fnt0 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawImage(game.pics.getText_help_text(), Game.WIDTH*Game.SCALE/6, 
				Game.HEIGHT*Game.SCALE*2/9+30, Game.WIDTH*Game.SCALE*4/6, 
				Game.HEIGHT*Game.SCALE*6/9, null);
		g.drawImage(game.pics.getText_backtomenu(), backBotton.x, backBotton.y,
				backBotton.width, backBotton.height, null);
		g2d.draw(backBotton);
	}
	public void render_next(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(background, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
		g.drawImage(game.pics.getText_head(), Game.WIDTH*Game.SCALE/6, 
				Game.HEIGHT*Game.SCALE/9, Game.WIDTH*Game.SCALE*4/6, 
				Game.HEIGHT*Game.SCALE/9, null);
		g.drawImage(game.pics.getText_next(), enterBotton.x, enterBotton.y,
				enterBotton.width, enterBotton.height, null);
		g2d.draw(enterBotton);
	}
	public void render_win(Graphics g) {
		g.drawImage(background, 0, 0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE,null);
		g.drawImage(game.pics.getText_head(), Game.WIDTH*Game.SCALE/6, 
				Game.HEIGHT*Game.SCALE/9, Game.WIDTH*Game.SCALE*4/6, 
				Game.HEIGHT*Game.SCALE/9, null);
		g.drawImage(game.pics.getText_win(), Game.WIDTH*Game.SCALE*3/5, 
				Game.HEIGHT*Game.SCALE*3/9, Game.WIDTH*Game.SCALE*1/3, 
				Game.HEIGHT*Game.SCALE/9, null);
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(game.pics.getText_playagain(), playBotton.x, playBotton.y, 
				playBotton.width, playBotton.height, null);
		g.drawImage(game.pics.getText_help(), helpBotton.x, helpBotton.y, 
				helpBotton.width, helpBotton.height, null);
		g.drawImage(game.pics.getText_quit(), quitBotton.x, quitBotton.y, 
				quitBotton.width, quitBotton.height, null);
		g2d.draw(playBotton);
		g2d.draw(helpBotton);
		g2d.draw(quitBotton);
	}
}
