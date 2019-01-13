package spacegame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import spacegame.Game.STATE;
public class Player2 {
	Game game = new Game();
	////////////////// variables for waterdrop:
	private double x, y;
	private float velx, vely;
	private int direction = 0;	
	/////////////////variables for player
	private int numOftwoDpaper = 0;
	private int shootnumber = 0;//the number of spaceships hit by bullet.
	private int hp = 5;//healthpoint of the waterdrop.
	private Rectangle scorebar = new Rectangle(0, 0, 150, 50);//show the current score.
	private Rectangle twoDpaperbar = new Rectangle(Game.WIDTH*Game.SCALE-200, Game.HEIGHT*Game.SCALE-50, 200, 50);
	////////////////spaceship
	private Random rand = new Random();//used to generate spaceship
	private double timecounter = 0;
	private float velOfspaceship = 1.6f;
	private ArrayList<BufferedImage> SpaceShip = new ArrayList<BufferedImage>(3);
	private ArrayList<BufferedImage> bulletImage = new ArrayList<BufferedImage>(3);
	private ArrayList<Float> xOfspaceship = new ArrayList<Float>(3);
	private ArrayList<Float> yOfspaceship = new ArrayList<Float>(3);
	private ArrayList<Float> xOfspritesheet = new ArrayList<Float>(3);//the velocity of the spritesheet.
	private ArrayList<Float> velOfupdates = new ArrayList<Float>(3);
	/////////////////bullets of spaceship
	private int spaceshipcounter = 0;
	/////////////////explosion
	private ArrayList<BufferedImage> explosion = new ArrayList<BufferedImage>(3);
	private ArrayList<Float> xOfexplosion = new ArrayList<Float>(3);
	private ArrayList<Float> yOfexplosion = new ArrayList<Float>(3);
	private ArrayList<Float> xOfspritesheet0 = new ArrayList<Float>(3);
	private Boolean clear = false;
	/////////////////2Dpaper
	private float timecounter2;
	private ArrayList<BufferedImage> twoDpaper = new ArrayList<BufferedImage>(3);
	private ArrayList<Point> xyOftwoDpaper = new ArrayList<Point>(3);
	public Boolean getClear() {
		return clear;
	}
	public void setClear(Boolean clear) {
		this.clear = clear;
	}	
	public int getShootnumber() {
		return shootnumber;
	}
	public void setShootnumber(int shootnumber) {
		this.shootnumber = shootnumber;
	}
	
 	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public float getVelx() {
		return velx;
	}
	public void setVelx(float velx) {
		this.velx = velx;
	}
	public float getVely() {
		return vely;
	}
	public void setVely(float vely) {
		this.vely = vely;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public Player2(double x, double y,Game game0) throws IOException {
		this.x = x;
		this.y = y;
		game = game0;
		game.addKeyListener(new KeyInput2(this));
		//SpriteSheet ss = new SpriteSheet() ;
		//StarShip = ss.grabWaterDrop();
	}
	public void keyPressed(KeyEvent e) throws IOException {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_DOWN) {
			setDirection(2);
		} else if(key == KeyEvent.VK_UP){
			setDirection(1);
		} else if(key == KeyEvent.VK_RIGHT){
			setDirection(4);
		} else if(key == KeyEvent.VK_LEFT) {
			setDirection(3);
		} else if(key == KeyEvent.VK_T) {
			setClear(true);
		}
		
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_DOWN) {
			setDirection(0);
		} else if(key == KeyEvent.VK_UP){
			setDirection(0);
		} else if(key == KeyEvent.VK_RIGHT){
			setDirection(0);
		} else if(key == KeyEvent.VK_LEFT) {
			setDirection(0);
		} 
	}
	public void tick() throws IOException {	
		tickMenu();
		tickWaterdrop();
		tickSpaceship();
		tickTwoDpaper();	
		tickExplosion();
	}
	public void tickMenu() {
		if(hp == 0) {		
			game.menu.setScore(shootnumber);
			game.initPlayer();
			game.state = Game.STATE.MENU2;
		}
	}
	public void tickWaterdrop() {
		//updates the coordinate of the waterdrop.
		changeVel(direction);
		x+=velx;
		y+=vely;
		//if the waterdrop hits the wall, change its velocity.
		if(x >= (Game.WIDTH*Game.SCALE-64) || x < 0) {
			velx=-velx;
			game.soundplayer.bouncesound();
			hp-=1;
		}else if(y >= (Game.HEIGHT*Game.SCALE-64) || y < 0) {
			vely=-vely;
			game.soundplayer.bouncesound();
			hp-=1;
		}
	}
	public void tickTwoDpaper() throws IOException {
		//create 2Dpaper every some seconds.
		timecounter2 += 0.02;
		if(timecounter2>=8) {
			timecounter2-=8;
			twoDpaperCreat();
		}
		for	(int i = 0; i<=twoDpaper.size()-1; i++) {
			xyOftwoDpaper.set(i, new Point(xyOftwoDpaper.get(i).x, xyOftwoDpaper.get(i).y+2));
			if(xyOftwoDpaper.get(i).y >= Game.WIDTH*Game.SCALE) {
				twoDpaper.remove(i);
				xyOftwoDpaper.remove(i);
				i--;
			}else if(y<=xyOftwoDpaper.get(i).y+100 &&
				y+50>=xyOftwoDpaper.get(i).y &&
				x+50>=xyOftwoDpaper.get(i).x &&
				x<=xyOftwoDpaper.get(i).x+100)
				{	
					numOftwoDpaper+=1;
					twoDpaper.remove(i);
					xyOftwoDpaper.remove(i);
					i--;
				}
		}
	}
	public void tickSpaceship() throws IOException {
		//create spaceships every second.
		timecounter += 0.02;	
		if(timecounter>=1) {
			timecounter-=1;
			spaceshipCreat();
		}
		/////if waterdrop hits spaceships.
		for (int i=0; i<=SpaceShip.size()-1; i++) {	
			 if(y<=yOfspaceship.get(i)+100 &&
				y>=yOfspaceship.get(i)-30 &&
				x>=xOfspaceship.get(i)-30 &&
				x<=xOfspaceship.get(i)+100)
					{	explosionCreat(xOfspaceship.get(i)-20, yOfspaceship.get(i));
						game.soundplayer.explosionsound();
						SpaceShip.remove(i);
						xOfspritesheet.remove(i);
						xOfspaceship.remove(i);
						yOfspaceship.remove(i);
						velOfupdates.remove(i);
						shootnumber+=1;
						i--;
					}
		}
	}
	public void tickExplosion() throws IOException {
		for(int i=0; i<=explosion.size()-1; i++) {
			xOfspritesheet0.set(i, xOfspritesheet0.get(i)+0.2f);
			explosion.set(i, game.pics.grabexplosion(Math.round(xOfspritesheet0.get(i)), 3));
			if(xOfspritesheet0.get(i)>=8) {
				explosion.remove(i);
				xOfexplosion.remove(i);
				yOfexplosion.remove(i);
				xOfspritesheet0.remove(i);
				i--;
			}
		}
	}
	public void render(Graphics g) throws IOException {
		renderMenu(g);
		renderWaterdrop(g);
		renderSpaceship(g);
		renderTwoDpaper(g);
		renderExplosion(g);
		if(clear == true) {
		clearAll();
		clear = false;
		}
	}		
	public void renderMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Score:"+shootnumber, 10, 35);
		g.drawString("2Dpaper: "+numOftwoDpaper, Game.WIDTH*Game.SCALE-170 , Game.HEIGHT*Game.SCALE-10);
		g.drawString("HP:"+hp, Game.WIDTH*Game.SCALE-150, 35);
		g2d.draw(twoDpaperbar);
		g2d.draw(scorebar);
	}
	public void renderWaterdrop(Graphics g) throws IOException {
		g.drawImage(game.pics.grabWaterDrop(), (int)x, (int)y, null);
	}
	public void renderSpaceship(Graphics g) throws IOException {
		for(int i = 0; i<=SpaceShip.size()-1; i++) {
			//////draw images of every spaceships
			//SpaceShip.set(i, game.pics.grabspaceship(Math.round(xOfspritesheet.get(i)),1));
			g.drawImage(SpaceShip.get(i), Math.round(xOfspaceship.get(i)), Math.round(yOfspaceship.get(i)), 120, 120, null);
			/////change positions of the sprite sheet.
			xOfspritesheet.set(i, xOfspritesheet.get(i)+velOfupdates.get(i));
			//if spaceships have not arrived to a certain area, keep it moving.
			yOfspaceship.set(i, yOfspaceship.get(i)+velOfspaceship);
			if(yOfspaceship.get(i)>=Game.HEIGHT*Game.SCALE) {
				xOfspaceship.remove(i);
				yOfspaceship.remove(i);
				xOfspritesheet.remove(i);
				velOfupdates.remove(i);
				SpaceShip.remove(i);
				hp-=1;
			}
		}
	}
	public void renderTwoDpaper(Graphics g) {
		//draw every 2Dpaper.
		for(int i = 0; i<=twoDpaper.size()-1; i++) {
			g.drawImage(twoDpaper.get(i), xyOftwoDpaper.get(i).x, xyOftwoDpaper.get(i).y, null);
		}
	}
	public void renderExplosion(Graphics g) {
		for(int i=0; i<=explosion.size()-1; i++) {
			g.drawImage(explosion.get(i), Math.round(xOfexplosion.get(i)), Math.round(yOfexplosion.get(i)), null);
		}
	}
	public void spaceshipCreat() throws IOException {
		if(SpaceShip.size()<=4) {	
		if(spaceshipcounter%4 == 0) {
			SpaceShip.add(game.getPics().grabMissile(0));	
		} else if(spaceshipcounter%4 == 1) {
			SpaceShip.add(game.getPics().grabMissile(1));
		} else if(spaceshipcounter%4 == 2) {
			SpaceShip.add(game.getPics().grabMissile(2));
			bulletImage.add(game.getPics().grabbulletofSpaceship(2));
		} else if(spaceshipcounter%4 == 3) {
			SpaceShip.add(game.getPics().grabMissile(3));
		} else if(spaceshipcounter%4 == 4) {
			SpaceShip.add(game.getPics().grabMissile(4));
		}
		xOfspaceship.add((float)rand.nextInt(Game.WIDTH*Game.SCALE-100)+1);
		yOfspaceship.add(-400f);
		xOfspritesheet.add(1.5f);
		velOfupdates.add(0.1f);
		spaceshipcounter += 1;
		}
		if(spaceshipcounter == 20) {
			game.state = STATE.WIN;
		}
	}
	public void explosionCreat(float x, float y) throws IOException {
		explosion.add(game.pics.grabexplosion(1, 3));
		xOfexplosion.add(x);
		yOfexplosion.add(y);
		xOfspritesheet0.add(2f);
	}
	public void twoDpaperCreat() throws IOException {
		twoDpaper.add(game.pics.grabtwoDpaper());
		xyOftwoDpaper.add(new Point(rand.nextInt(Game.WIDTH*Game.SCALE-100)+1,0));
	}
	public void changeVel(int direction) {
		if(direction == 0) {
			return;
		}
		if(direction == 1){
			vely-=0.2;
			}
		if(direction == 2) {
			vely+=0.2;
		}
		if(direction == 3) {
			velx-=0.2;
		}
		if(direction == 4) {
			velx+=0.2;
		}
	}
	public void clearAll() throws IOException {
		if(numOftwoDpaper > 0) {
			for (int i=0; i<=SpaceShip.size()-1; i++) {
				/////if waterdrop hits spaceships.
				explosionCreat(xOfspaceship.get(i), yOfspaceship.get(i));
				game.soundplayer.explosionsound();
				SpaceShip.remove(i);
				xOfspritesheet.remove(i);
				xOfspaceship.remove(i);
				yOfspaceship.remove(i);
				velOfupdates.remove(i);
				shootnumber+=1;
				i--;
			}
			numOftwoDpaper-=1;
		}
	}
	
	
		
	}
	