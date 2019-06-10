package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	
	boolean left,up,right,down;
	boolean live = true;
	int speed = 10;
	public void drawSelf(Graphics g) {
		
		if(live) {
			g.drawImage(getImg(), (int)getX(), (int)getY(), null);
			if(getX()>10&&getX()<Constant.GAME_WIDH-30) {
				if(left) {
					setX(getX() - speed);
				}
				if(right) {
					setX(getX() + speed);
				}
			}else if(getX()==10){
				if(right) {
					setX(getX() + speed);
				}
			}else {
				if(left) {
					setX(getX() - speed);
				}
			}
			
			if(getY()>30&&getY()<Constant.GAME_HEIGHT-40) {
				if(up) {
					setY(getY() - speed);
				}
				if(down) {
					setY(getY() + speed);
				}
			}else if(getY()==30) {
				if(down) {
					setY(getY() + speed);
				}
			}else {
				if(up) {
					setY(getY() - speed);
				}
			}
			
		}else {
			
		}
		
		
	}
	
	public Plane(Image img,double x,double y) {
	    this.setImg(img);
		this.setX(x);
		this.setY(y);
		this.setWidth(img.getWidth(null));
		this.setHeight(img.getHeight(null));
	}
	
	//按下某个键，增加相应的方向
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;
		}
	}
	
	//松开某个键，取消相应的方向
	public void minusDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;
		}
	}
}
