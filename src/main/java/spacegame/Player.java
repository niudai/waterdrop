package spacegame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.KeyEvent;

import spacegame.Game.STATE;;

public class Player {
	Game game = new Game();
	////////////////// variables for waterdrop:
	private double x, y;
	private float velx, vely;
	private int direction = 0;	
	/////////////////variables for player
	private int numOftwoDpaper = 0;
	private int shootnumber = 0;//the number of spaceships hit by bullet.
	private int hp = 20;//healthpoint of the waterdrop.
	private Rectangle scorebar = new Rectangle(0, 0, 150, 50);//show the current score.
	private Rectangle twoDpaperbar = new Rectangle(Game.WIDTH*Game.SCALE-200, Game.HEIGHT*Game.SCALE-50, 200, 50);
	////////////////variables for bullet.
	private boolean bulletcreat = false;
	private ArrayList<Integer> bx = new ArrayList<Integer>(3);//coordinates of bullets
	private ArrayList<Integer> by = new ArrayList<Integer>(3);
	private ArrayList<BufferedImage> bullet = new ArrayList<BufferedImage>(3);
	////////////////spaceship
	private boolean hit = false;
	private Random rand = new Random();//used to generate spaceship
	private double timecounter = 0;
	private float velOfspaceship = 0.8f;
	private ArrayList<BufferedImage> SpaceShip = new ArrayList<BufferedImage>(3);
	private ArrayList<BufferedImage> bulletImage = new ArrayList<BufferedImage>(3);
	private ArrayList<Float> xOfspaceship = new ArrayList<Float>(3);
	private ArrayList<Float> yOfspaceship = new ArrayList<Float>(3);
	private ArrayList<Float> xOfspritesheet = new ArrayList<Float>(3);//the velocity of the spritesheet.
	private ArrayList<Float> velOfupdates = new ArrayList<Float>(3);
	/////////////////bullets of spaceship
	private int spaceshipcounter = 0;
	private List<List<BufferedImage>> bulletOfSpaceships = new ArrayList<List<BufferedImage>>(3);
	private List<List<Float>> xOfBulletOfSpaceships = new ArrayList<List<Float>>(3);
	private List<List<Float>> yOfBulletOfSpaceships = new ArrayList<List<Float>>(3);
	private ArrayList<Float> timeCounter = new ArrayList<Float>(3);
	private List<List<Float>> cosOfBulletOfSpaceships = new ArrayList<List<Float>>(3);
	private List<List<Float>> sinOfBulletOfSpaceships = new ArrayList<List<Float>>(3);
	public BufferedImage bulletOfSpaceship0;
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
	////////////key input
	//////

	public boolean isBulletcreat() {
		return bulletcreat;
	}
	public void setBulletcreat(boolean bulletcreat) {
		this.bulletcreat = bulletcreat;
	}
	public Boolean getClear() {
		return clear;
	}
	public void setClear(Boolean clear) {
		this.clear = clear;
	}
	public BufferedImage getBulletOfSpaceship0() {
		return bulletOfSpaceship0;
	}
	public void setBulletOfSpaceship0(BufferedImage bulletOfSpaceship0) {
		this.bulletOfSpaceship0 = bulletOfSpaceship0;
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
	
	public Player(double x, double y,Game game0) throws IOException {
		this.x = x;
		this.y = y;
		game = game0;
		game.addKeyListener(new KeyInput(this));
	}
	public void keyPressed(KeyEvent e) throws IOException {
		int key = e.getKeyCode();
		if(game.state == STATE.GAME) {
			if(key == KeyEvent.VK_DOWN) {
				setDirection(2);
			} else if(key == KeyEvent.VK_UP){
				setDirection(1);
			} else if(key == KeyEvent.VK_RIGHT){
				setDirection(4);
			} else if(key == KeyEvent.VK_LEFT) {
				setDirection(3);
			} else if(key == KeyEvent.VK_R) {
				game.soundplayer.bulletSound();
				setBulletcreat(true);	
			} else if(key == KeyEvent.VK_T) {
				setClear(true);
			}
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
		} else if(key == KeyEvent.VK_R) {
			//player.bullet(0);
		}
	}
	public void tick() throws IOException, InterruptedException {	
		tickMenu();
		tickWaterdrop();
		tickSpaceship();
		tickBullet();
		tickBulletOfspaceship();
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
	public void tickSpaceship() throws IOException, InterruptedException {
		//create spaceships every second.
		timecounter += 0.02;	
		if(timecounter>=1) {
			timecounter-=1;
			spaceshipCreat();
		}
		//check if any bullet hits the spaceship, if so, delete that spaceship.
		for (int i=0; i<=bullet.size()-1; i++) {
			for (int j=0; j<=SpaceShip.size()-1;j++)
			{	//if bullet hit spaceship , spaceship would vanish.
				if(by.get(i)<=yOfspaceship.get(j)+SpaceShip.get(i).getHeight() && 
					by.get(i)>=yOfspaceship.get(j) &&
					bx.get(i)>=xOfspaceship.get(j) &&
					bx.get(i)<=xOfspaceship.get(j)+SpaceShip.get(i).getWidth())
				{	explosionCreat(xOfspaceship.get(j)+SpaceShip.get(i).getWidth()-190, yOfspaceship.get(j)+SpaceShip.get(i).getHeight()-220);
					game.soundplayer.explosionsound();
					SpaceShip.remove(j);
					xOfspritesheet.remove(j);
					xOfspaceship.remove(j);
					yOfspaceship.remove(j);
					velOfupdates.remove(j);
					bulletImage.remove(j);
					hit = true;
					shootnumber+=1;
					break;	
				}
			}
			//hit or fly away, bullet deleted.
			if(hit == true || by.get(i)<=0) {
				bullet.remove(i);
				bx.remove(i);
				by.remove(i);
				i--;
				hit = false;
			}			
		}
		/////if waterdrop hits spaceships.
		for (int i=0; i<=SpaceShip.size()-1; i++) {	
			 if(y<=yOfspaceship.get(i)+150 &&
				y+30>=yOfspaceship.get(i) &&
				x+30>=xOfspaceship.get(i) &&
				x<=xOfspaceship.get(i)+150)
					{	explosionCreat(xOfspaceship.get(i), yOfspaceship.get(i));
						game.soundplayer.explosionsound();
						SpaceShip.remove(i);
						xOfspritesheet.remove(i);
						xOfspaceship.remove(i);
						yOfspaceship.remove(i);
						velOfupdates.remove(i);
						shootnumber+=1;
						bulletImage.remove(i);
						i--;
					}
		}
	}
	public void tickBullet() throws IOException {
		for (int i=0; i<=bullet.size()-1; i++) {
			by.set(i, by.get(i)-2);
		}
		if(bulletcreat  == true) {
			bulletCreat();
			bulletcreat = false;
		}
	}
	public void tickBulletOfspaceship() {
		//creat bullets of every spaceship
		for (int i=0; i<=SpaceShip.size()-1; i++) {
			timeCounter.set(i, timeCounter.get(i)+0.01f);	
			if(timeCounter.get(i)>=1) {
				timeCounter.set(i, timeCounter.get(i)-1f);
				bulletOfSpaceships.get(i).add(bulletImage.get(i));
				
				xOfBulletOfSpaceships.get(i).add(xOfspaceship.get(i)+SpaceShip.get(i).getWidth()/2);
				yOfBulletOfSpaceships.get(i).add(yOfspaceship.get(i)+SpaceShip.get(i).getHeight());
				float dy = (float) (y+32-yOfspaceship.get(i)-SpaceShip.get(i).getHeight());
				float dx = (float) (x+32-xOfspaceship.get(i)-SpaceShip.get(i).getWidth()/2);
				
				cosOfBulletOfSpaceships.get(i).add((float) (dy/Math.sqrt(Math.pow(dy,2)+Math.pow(dx,2))));
				sinOfBulletOfSpaceships.get(i).add((float) (dx/Math.sqrt(Math.pow(dy,2)+Math.pow(dx,2))));
			}
		}
		//move the bullets, delete hit bullets. 
		for (int i=0; i<=bulletOfSpaceships.size()-1; i++) {
			for(int j=0; j<=bulletOfSpaceships.get(i).size()-1; j++) {
				yOfBulletOfSpaceships.get(i).set(j, yOfBulletOfSpaceships.get(i).get(j)+3f*cosOfBulletOfSpaceships.get(i).get(j));
				xOfBulletOfSpaceships.get(i).set(j, xOfBulletOfSpaceships.get(i).get(j)+3f*sinOfBulletOfSpaceships.get(i).get(j));
				//if bullets hit the water drop , the bullet would disappear.
				if(xOfBulletOfSpaceships.get(i).get(j) >= x &&
						   xOfBulletOfSpaceships.get(i).get(j) <= x+64 &&
						   yOfBulletOfSpaceships.get(i).get(j) >= y &&
						   yOfBulletOfSpaceships.get(i).get(j) <= y+64) {
						   bulletOfSpaceships.get(i).remove(j);
						   xOfBulletOfSpaceships.get(i).remove(j);
						   yOfBulletOfSpaceships.get(i).remove(j);
						   cosOfBulletOfSpaceships.get(i).remove(j);
						   sinOfBulletOfSpaceships.get(i).remove(j);
						   j--;
						   hp-=1;
						   game.soundplayer.explosionsound2();
				}//if bullets of spaceships fly out of the vision.
					else if(yOfBulletOfSpaceships.get(i).get(j) >= Game.HEIGHT*Game.SCALE) {
					bulletOfSpaceships.get(i).remove(j);
					xOfBulletOfSpaceships.get(i).remove(j);
					yOfBulletOfSpaceships.get(i).remove(j);
					cosOfBulletOfSpaceships.get(i).remove(j);
					sinOfBulletOfSpaceships.get(i).remove(j);
					j--;
				}
			}
		}
		for(int i = SpaceShip.size(); i<=timeCounter.size()-1; i++ ) {
			if(bulletOfSpaceships.get(i).size() == 0) {
				bulletOfSpaceships.remove(i);
				xOfBulletOfSpaceships.remove(i);
				yOfBulletOfSpaceships.remove(i);
				cosOfBulletOfSpaceships.remove(i);
				sinOfBulletOfSpaceships.remove(i);
				timeCounter.remove(i);
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
		renderBullet(g);
		renderBulletOfspaceship(g);
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
			g.drawImage(SpaceShip.get(i), Math.round(xOfspaceship.get(i)), Math.round(yOfspaceship.get(i)), null);
			/////change positions of the sprite sheet.
			xOfspritesheet.set(i, xOfspritesheet.get(i)+velOfupdates.get(i));
			//if spaceships have not arrived to a certain area, keep it moving.
			if (yOfspaceship.get(i)<=0) {
				yOfspaceship.set(i, yOfspaceship.get(i)+velOfspaceship);
			}
			if (xOfspritesheet.get(i)>5 || xOfspritesheet.get(i)<1) {
				velOfupdates.set(i, -velOfupdates.get(i));
			}
		}
	}
	public void renderBullet(Graphics g) {
		for (int i=0; i<=bullet.size()-1; i++) {
			g.drawImage(bullet.get(i), bx.get(i)+31, by.get(i)-5, null);
			by.set(i, by.get(i)-10);
		}
	}
	public void renderBulletOfspaceship(Graphics g) {
		for (int i=0; i<=bulletOfSpaceships.size()-1; i++) {
			 ////draw images of bullets of every spaceships.
			for(int j=0; j<=bulletOfSpaceships.get(i).size()-1; j++) {
				g.drawImage(bulletOfSpaceships.get(i).get(j), Math.round(xOfBulletOfSpaceships.get(i).get(j)), Math.round(yOfBulletOfSpaceships.get(i).get(j)),null);	
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
	public void bulletCreat() throws IOException {
		//once this method is called, a new bullet is created.
		bullet.add(game.pics.grabBullet());
		bx.add((int)x);
		by.add((int)y);
	}
	
	public void spaceshipCreat() throws IOException, InterruptedException {
		if(SpaceShip.size()<=4) {	
		if(spaceshipcounter < 5) {
			SpaceShip.add(game.getPics().grabspaceship2(0));
			bulletImage.add(game.getPics().grabbulletofSpaceship(0));
			
		} else if(spaceshipcounter < 10) {
			SpaceShip.add(game.getPics().grabspaceship2(1));
			bulletImage.add(game.getPics().grabbulletofSpaceship(1));
		} else if(spaceshipcounter < 15) {
			SpaceShip.add(game.getPics().grabspaceship2(2));
			bulletImage.add(game.getPics().grabbulletofSpaceship(2));
		} else if(spaceshipcounter < 20) {
			SpaceShip.add(game.getPics().grabspaceship2(3));
			bulletImage.add(game.getPics().grabbulletofSpaceship(3));
		} else if(spaceshipcounter < 25) {
			SpaceShip.add(game.getPics().grabspaceship2(4));
			bulletImage.add(game.getPics().grabbulletofSpaceship(4));
		} else if(spaceshipcounter < 30) {
			SpaceShip.add(game.getPics().grabspaceship2(5));
			bulletImage.add(game.getPics().grabbulletofSpaceship(5));
		} else if(spaceshipcounter < 35) {
			SpaceShip.add(game.getPics().grabspaceship2(6));
			bulletImage.add(game.getPics().grabbulletofSpaceship(7));
		} else {
			game.state = STATE.MENU1_2;
			return;
		}
		xOfspaceship.add((float)rand.nextInt(Game.WIDTH*Game.SCALE-100)+1);
		yOfspaceship.add(-400f);
		xOfspritesheet.add(1.5f);
		velOfupdates.add(0.1f);
		timeCounter.add(0f);
		bulletOfSpaceships.add(new ArrayList<BufferedImage>(3));
		xOfBulletOfSpaceships.add(new ArrayList<Float>(3));
		yOfBulletOfSpaceships.add(new ArrayList<Float>(3));
		cosOfBulletOfSpaceships.add(new ArrayList<Float>(3));
		sinOfBulletOfSpaceships.add(new ArrayList<Float>(3));
		spaceshipcounter += 1;
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
	public void winWait() throws InterruptedException {
		
		Thread.sleep(5000);
	}
	public void clearAll() throws IOException {
		if(numOftwoDpaper > 0) {
			for (int i=0; i<=SpaceShip.size()-1; i++) {
				/////if waterdrop hits spaceships.
				explosionCreat(xOfspaceship.get(i), yOfspaceship.get(i));
				bulletImage.remove(i);
				game.soundplayer.explosionsound();
				SpaceShip.remove(i);
				xOfspritesheet.remove(i);
				xOfspaceship.remove(i);
				yOfspaceship.remove(i);
				velOfupdates.remove(i);
				timeCounter.remove(i);
				bulletOfSpaceships.remove(i);
				xOfBulletOfSpaceships.remove(i);
				yOfBulletOfSpaceships.remove(i);
				cosOfBulletOfSpaceships.remove(i);
				sinOfBulletOfSpaceships.remove(i);
				shootnumber+=1;
				i--;
			}
			numOftwoDpaper-=1;
		}
	}
	
	
		
	}
	