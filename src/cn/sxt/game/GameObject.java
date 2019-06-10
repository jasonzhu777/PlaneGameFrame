package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 游戏物体的父类
 * @author Jason_zhu
 *
 */
public class GameObject {
	private Image img;
	private double x,y;
	private int speed;
	private int width,height;
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public Image getImg() {
		return img;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}

	
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
	}

	public GameObject(Image img, double x, double y, int speed, int width, int height) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}

	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	public GameObject() {
		
	}
	
	/**
	 * 返回物体所在在的矩形，便于碰撞检测
	 * @return
	 */
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
}
