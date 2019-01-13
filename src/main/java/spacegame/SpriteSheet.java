package spacegame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
public class SpriteSheet {
	//BufferedImageLoader loader = new BufferedImageLoader();
	public BufferedImage waterdrop;
	public ArrayList<BufferedImage> background = new ArrayList<BufferedImage>(3);
	public ArrayList<BufferedImage> spaceship2 = new ArrayList<BufferedImage>(3);
	public ArrayList<BufferedImage> bulletofSpaceship = new ArrayList<BufferedImage>(3);
	public ArrayList<BufferedImage> missile = new ArrayList<BufferedImage>(3);
	public BufferedImage spaceship1;
	public BufferedImage text_head;
	public BufferedImage text_play;
	public BufferedImage text_help;
	public BufferedImage text_help_text;
	public BufferedImage text_designerlogo;
	public BufferedImage text_playagain;
	public BufferedImage text_next;
	public BufferedImage text_quit;
	public BufferedImage text_backtomenu;
	public BufferedImage text_youscored;
	public BufferedImage text_win;
	public BufferedImage getText_win() {
		return text_win;
	}
	public void setText_win(BufferedImage text_win) {
		this.text_win = text_win;
	}
	public BufferedImage getText_help_text() {
		return text_help_text;
	}
	public void setText_help_text(BufferedImage text_help_text) {
		this.text_help_text = text_help_text;
	}
	public BufferedImage getText_youscored() {
		return text_youscored;
	}
	public void setText_youscored(BufferedImage text_youscored) {
		this.text_youscored = text_youscored;
	}
	public BufferedImage getText_playagain() {
		return text_playagain;
	}
	public void setText_playagain(BufferedImage text_playagain) {
		this.text_playagain = text_playagain;
	}
	public BufferedImage getText_next() {
		return text_next;
	}
	public void setText_next(BufferedImage text_next) {
		this.text_next = text_next;
	}
	public BufferedImage getText_designerlogo() {
		return text_designerlogo;
	}
	public void setText_designerlogo(BufferedImage text_disignerlogo) {
		this.text_designerlogo = text_disignerlogo;
	}
	public BufferedImage getText_help() {
		return text_help;
	}
	public void setText_help(BufferedImage text_help) {
		this.text_help = text_help;
	}
	public BufferedImage getText_play() {
		return text_play;
	}
	public void setText_play(BufferedImage text_play) {
		this.text_play = text_play;
	}
	public BufferedImage getText_quit() {
		return text_quit;
	}
	public void setText_quit(BufferedImage text_quit) {
		this.text_quit = text_quit;
	}
	public BufferedImage getText_backtomenu() {
		return text_backtomenu;
	}
	public void setText_backtomenu(BufferedImage text_backtomenu) {
		this.text_backtomenu = text_backtomenu;
	}
	
	public BufferedImage getText_head() {
		return text_head;
	}
	public void setText_head(BufferedImage text_head) {
		this.text_head = text_head;
	}
	public ArrayList<BufferedImage> getBackground() {
		return background;
	}
	public void setBackground(ArrayList<BufferedImage> background) {
		this.background = background;
	}
	public ArrayList<BufferedImage> getMissile() {
		return missile;
	}
	public void setMissile(ArrayList<BufferedImage> missile) {
		this.missile = missile;
	}
	public ArrayList<BufferedImage> getBulletofSpaceship() {
		return bulletofSpaceship;
	}
	public void setBulletofSpaceship(ArrayList<BufferedImage> bulletofSpaceship) {
		this.bulletofSpaceship = bulletofSpaceship;
	}
	public ArrayList<BufferedImage> getSpaceship2() {
		return spaceship2;
	}
	public void setSpaceship2(ArrayList<BufferedImage> spaceship2) {
		this.spaceship2 = spaceship2;
	}
	public BufferedImage explosion;
	public BufferedImage twoDpaper;
	
	
	public BufferedImage getTwoDpaper() {
		return twoDpaper;
	}
	public void setTwoDpaper(BufferedImage twoDpaper) {
		this.twoDpaper = twoDpaper;
	}
	public BufferedImage getExplosion() {
		return explosion;
	}
	public void setExplosion(BufferedImage explosion) {
		this.explosion = explosion;
	}
	public BufferedImage getWaterdrop() {
		return waterdrop;
	}
	public void setWaterdrop(BufferedImage waterdrop) {
		this.waterdrop = waterdrop;
	}
	public BufferedImage getSpaceship() {
		return spaceship1;
	}
	public void setSpaceship(BufferedImage spaceship) {
		this.spaceship1 = spaceship;
	}
	public BufferedImage grabWaterDrop() throws IOException {
		//image = loader.loadImage("/Waterdrop For Space Game.png");
		BufferedImage img = waterdrop.getSubimage(0, 0, 64, 64);
		return img;
	}
	public BufferedImage grabBackground(int x, int y, int Width, int Height, int num) throws IOException {
		BufferedImage img = background.get(num).getSubimage(x, x, Width, Height);
		return img;
	}
	public BufferedImage grabBullet() throws IOException {
		BufferedImage img = bulletofSpaceship.get(1);
		return img;
	}
	public BufferedImage grabspaceship(int x, int y) throws IOException {
		BufferedImage img = spaceship1.getSubimage(200*x-200,200*y-200,200,200);
		return img;
	}
	public BufferedImage grabbulletofSpaceship(int x) {
		BufferedImage img = bulletofSpaceship.get(x);
		return img;
	}
	public BufferedImage grabexplosion(int x, int y) throws IOException {
		BufferedImage img = explosion.getSubimage(200*x-200,200*y-200,200,200);
		return img;
	}
	public BufferedImage grabtwoDpaper() throws IOException {
		BufferedImage img = twoDpaper.getSubimage(0,0,100,100);
		return img;
	}
	public BufferedImage grabspaceship2(int num) {
		BufferedImage img = spaceship2.get(num);
		return img;
	}
	public BufferedImage grabMissile(int num) {
		BufferedImage img = missile.get(num);
		return img;
	}
}

