package spacegame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 * The index of the game. Main method is in this class.
 */
public class Game extends Canvas implements Runnable{
	/*************************************
	 * properties declaration: 
	 *************************************/
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 360; // width of the canvas
	public static final int HEIGHT = 360/16*9; // height of the canvas
	public static final int SCALE = 4; // the real size of the canvas is mutiplied by this variable.
	public final String TITLE = "Space Game"; // the title of the game
	public double x = 0;
	private boolean running = false;
	public static Dimension preferredSize = new Dimension(WIDTH * SCALE, HEIGHT*SCALE);
	private BufferedImage image;
	public Menu menu = new Menu(this);
	Thread thread = new Thread(this);
	public MusicPlayer soundplayer = new MusicPlayer();
	private static final Color color = new Color(100, 50, 5);
	public Player player;
	public Player2 player2;
	private BufferedImage spriteSheet = null;
	private BufferedImage bullet = null;
	public SpriteSheet pics = new SpriteSheet();
	public double xOfBackground = 1; // the x coordinate of background image.
	public double yOfBackground = 1; // the y coordinate of background image.
	public double velOfBackground = 0.3; // the velocity of background image. (background image is moving)
	public enum STATE {
		MENU,
		GAME,
		MENU1_2,
		GAME2,
		STOP,
		MENU2,
		HELP,
		WIN
	}
	public STATE state = STATE.MENU;
	public MouseInput mouseinput = new MouseInput(this);

	/************************************
	 * Getters and Setters of properties
	 ************************************/
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public BufferedImage getBullet() {
		return bullet;
	}
	public void setBullet(BufferedImage bullet) {
		this.bullet = bullet;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}	
	public SpriteSheet getPics() {
		return pics;
	}
	public void setPics(SpriteSheet bg) {
		this.pics = bg;
	}
	public STATE getState() {
		return state;
	}
	public void setState(STATE state) {
		this.state = state;
	}
	

	/**
	 * load audio files
	 * @throws IOException
	 */
	private void initSound() throws IOException {
		////////initialize audio files for bouncing, shooting, explosion.
		soundplayer.setBackgroundsound(getClass().getResource("/bgm1.wav"));
		soundplayer.setBouncesound(getClass().getResource("/bounce1.wav"));
		soundplayer.setBulletsound(getClass().getResource("/bulletsound.wav"));
		soundplayer.setExplosionsound(getClass().getResource("/explosion1.wav"));
		soundplayer.setExplosionsound2(getClass().getResource("/explosion2.wav"));
	}

	/**
	 * load picture files.
	 * @throws IOException
	 */
	private void initPic() throws IOException {
		try {
		menu.setBackground(ImageIO.read(getClass().getResource("/background/background_5.jpg")));
		pics.getSpaceship2().add(ImageIO.read(getClass().getResource("/spaceship/spaceship_0.png")));
		pics.getSpaceship2().add(ImageIO.read(getClass().getResource("/spaceship/spaceship_1.png")));
		pics.getSpaceship2().add(ImageIO.read(getClass().getResource("/spaceship/spaceship_2.png")));
		pics.getSpaceship2().add(ImageIO.read(getClass().getResource("/spaceship/spaceship_3.png")));
		pics.getSpaceship2().add(ImageIO.read(getClass().getResource("/spaceship/spaceship_4.png")));
		pics.getSpaceship2().add(ImageIO.read(getClass().getResource("/spaceship/spaceship_5.png")));
		pics.getSpaceship2().add(ImageIO.read(getClass().getResource("/spaceship/spaceship_6.png")));
		pics.getSpaceship2().add(ImageIO.read(getClass().getResource("/spaceship/spaceship_7.png")));
		pics.getBulletofSpaceship().add(ImageIO.read(getClass().getResource("/bullet/bullet_0.png")));
		pics.getBulletofSpaceship().add(ImageIO.read(getClass().getResource("/bullet/bullet_1.png")));
		pics.getBulletofSpaceship().add(ImageIO.read(getClass().getResource("/bullet/bullet_2.png")));
		pics.getBulletofSpaceship().add(ImageIO.read(getClass().getResource("/bullet/bullet_3.png")));
		pics.getBulletofSpaceship().add(ImageIO.read(getClass().getResource("/bullet/bullet_4.png")));
		pics.getBulletofSpaceship().add(ImageIO.read(getClass().getResource("/bullet/bullet_5.png")));
		pics.getBulletofSpaceship().add(ImageIO.read(getClass().getResource("/bullet/bullet_6.png")));
		pics.getBulletofSpaceship().add(ImageIO.read(getClass().getResource("/bullet/bullet_7.png")));
		pics.getBackground().add(ImageIO.read(getClass().getResource("/background/background_0.png")));
		pics.getBackground().add(ImageIO.read(getClass().getResource("/background/background_1.jpg")));
		pics.getBackground().add(ImageIO.read(getClass().getResource("/background/background_2.jpg")));
		pics.getBackground().add(ImageIO.read(getClass().getResource("/background/background_3.jpg")));
		pics.getBackground().add(ImageIO.read(getClass().getResource("/background/background_4.jpg")));
		pics.getBackground().add(ImageIO.read(getClass().getResource("/background/background_5.jpg")));
		pics.getMissile().add(ImageIO.read(getClass().getResource("/missile/missile_0.png")));
		pics.getMissile().add(ImageIO.read(getClass().getResource("/missile/missile_1.png")));
		pics.getMissile().add(ImageIO.read(getClass().getResource("/missile/missile_2.png")));
		pics.getMissile().add(ImageIO.read(getClass().getResource("/missile/missile_3.png")));
		pics.getMissile().add(ImageIO.read(getClass().getResource("/missile/missile_4.png")));
		pics.getMissile().add(ImageIO.read(getClass().getResource("/missile/missile_5.png")));
		pics.setSpaceship(ImageIO.read(getClass().getResource("/spaceship1.png")));
		pics.setWaterdrop(ImageIO.read(getClass().getResource("/waterdrop1.png")));
		pics.setExplosion(ImageIO.read(getClass().getResource("/explosion1.png")));
		pics.setTwoDpaper(ImageIO.read(getClass().getResource("/2Dpaper.png")));
		pics.setText_next(ImageIO.read(getClass().getResource("/text/next_0.png")));
		pics.setText_head(ImageIO.read(getClass().getResource("/text/head_1.png")));
		pics.setText_play(ImageIO.read(getClass().getResource("/text/play_0.png")));
		pics.setText_help(ImageIO.read(getClass().getResource("/text/help_0.png")));
		pics.setText_quit(ImageIO.read(getClass().getResource("/text/quit_0.png")));
		pics.setText_backtomenu(ImageIO.read(getClass().getResource("/text/backtomenu_0.png")));
		pics.setText_designerlogo(ImageIO.read(getClass().getResource("/text/designerlogo_0.png")));
		pics.setText_playagain(ImageIO.read(getClass().getResource("/text/playagain_0.png")));
		pics.setText_youscored(ImageIO.read(getClass().getResource("/text/youscored_0.png")));
		pics.setText_help_text(ImageIO.read(getClass().getResource("/text/help_text_0.png")));
		pics.setText_win(ImageIO.read(getClass().getResource("/text/win_0.png")));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * initialize the player object.
	 */
	public void initPlayer() {
		try {
			player = new Player(WIDTH*SCALE/2, HEIGHT*SCALE/2, this);
			player2 = new Player2(WIDTH*SCALE/2, HEIGHT*SCALE/2, this);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * initialze the mouselistener.
	 */
	private void initListener() {
		addMouseListener(new MouseInput(this));
	}

	/**
	 * the main method is where the game starts off.
	 * @param args
	 */
	public static void main(String args[]) {
		Game game = new Game();
		game.setPreferredSize(preferredSize);
		game.setMaximumSize(preferredSize);
		game.setMinimumSize(preferredSize);
		game.setBackground(color);
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

	/**
	 * start the main thread it if not running.
	 */
	private synchronized void start() {
		if(running)
			return;
		running = true;
		thread.start();
	}

	/**
	 * stop it if the state is set to STATE.STOP.
	 * @throws InterruptedException
	 */
	private synchronized void stop() throws InterruptedException {
		if(state == STATE.STOP) {
		System.out.println(state);
		running = false;
		System.exit(1);
		thread.join();
		}
		return;
	}

	/**
	 * run the game in this method.
	 */
	@Override
	public void run() {
		try {
			// initilize all.
			initSound();
			initPic();
			initPlayer();
			initListener();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		soundplayer.playSound();
		long lastTime = System.nanoTime();
		// the amountOfTicks specifies how many times the game updates the image.
		final double amountOfTicks = 60.0; 
		double timeInterval = 1000000000/amountOfTicks;
		double time = 0;
		setPreferredSize(preferredSize);
		while (state != STATE.STOP) {
			long now = System.nanoTime();
			time += (now - lastTime);
			lastTime = now;
			// if time is greater than the timeInterval, render and tick it.
			if (time >= timeInterval) {
				/**
				 * tick() is used to update the data of game elements, such as 
				 * position, velocity, etc.
				 * */ 
				try {
					tick();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					render();
				} catch (IOException e) {
					e.printStackTrace();
				}
				time-=timeInterval;
			}
		}
		try {
			stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * render is used to redraw the image of the game elements
	 */
	private void render() throws IOException {
		BufferStrategy bufferedStrategy = this.getBufferStrategy();
		if(bufferedStrategy == null) {
			createBufferStrategy(8);
			return;
		}
		Graphics g = bufferedStrategy.getDrawGraphics();

		/***************************************
		 * moving the background
		 ***************************************/
		if (xOfBackground >= 150 || xOfBackground <= 0) {
			velOfBackground = -velOfBackground;
		}
			xOfBackground+=velOfBackground;
			yOfBackground+=velOfBackground;

		if (state == STATE.GAME) {
			g.drawImage(pics.grabBackground((int)xOfBackground, (int)yOfBackground , WIDTH*SCALE, 
				HEIGHT*SCALE,4), 0, 0, getWidth(), getHeight(), null);
			player.render(g);
		} else if(state == STATE.MENU) {
			menu.render_start(g);
		} else if(state == STATE.MENU2) {
			menu.render_over(g);
		} else if(state == STATE.HELP) {
			menu.render_help(g);
		} else if(state == STATE.GAME2) {
			g.drawImage(pics.grabBackground((int)xOfBackground, (int)yOfBackground , WIDTH*SCALE, 
				HEIGHT*SCALE,1), 0, 0, getWidth(), getHeight(), null);
			player2.render(g);
		} else if(state == STATE.MENU1_2) {
			menu.render_next(g);
		} else if(state == STATE.WIN) {
			menu.render_win(g);
		}
		g.dispose();
		bufferedStrategy.show();
	}

	/**
	 * tick() is used to update the data of game elements, such as position, velocity, etc.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void tick() throws IOException, InterruptedException {
		if(state == STATE.GAME) {	
			player.tick();
		} else if(state == STATE.GAME2) {
			player2.tick();
		}	
	}

	/*public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}*/

}
