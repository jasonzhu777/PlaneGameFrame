package cn.sxt.game;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends GameObject{

	double degree;
	
	public Shell() {
		setX(200);
		setY(200);
		setWidth(10);
		setHeight(10);
		setSpeed(3);
		degree = Math.random()*Math.PI*2;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.yellow);
		
		g.fillOval((int)getX(), (int)getY(), getWidth(), getHeight());
		
		//炮弹沿着任意角度去飞
		setX(getX() + getSpeed()*Math.cos(degree));
		setY(getY() + getSpeed()*Math.sin(degree));
		
		if(getX()<0||getX()>Constant.GAME_WIDH - getWidth()) {
			degree = Math.PI - degree;
		}
		if(getY()<30||getY()>Constant.GAME_HEIGHT - getHeight()) {
			degree = -degree;
		}
		
		g.setColor(c);
	}
}
