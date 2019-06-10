package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

/**
 * 飞机游戏的主窗口
 * @author Jason_zhu
 *
 */
public class MyGameFrame extends JFrame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Image plane_Image = GameUtil.getImage("images/plane.png");
	
	Plane plane = new Plane(plane_Image,230,400);
	
	Shell shell = new Shell();
	Shell[] shells = new Shell[50];
	
	Explode bao;
	Date startTime = new Date();
	Date endTime;
	int period; //游戏持续的时间
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		//super.paint(g);    //重写paint时，需调用父类的方法来绘一个背景，或者使用上面的方式
		
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g); //画飞机
		
		//画出所有的炮弹
		for(int i=0;i<shells.length;i++) {
			shells[i].draw(g); 
			
			//飞机和炮弹的碰撞检测
			boolean peng = shells[i].getRect().intersects(plane.getRect());
		
			if(peng) {
				plane.live = false;
				
				if(bao==null) {
					bao = new Explode(plane.getX(), plane.getY());					
				
					endTime = new Date();
					period = (int)((endTime.getTime() - startTime.getTime())/1000);
				}
				bao.draw(g);
			}
			//计时功能，给出提示
			if(!plane.live) {
				g.setColor(Color.red);
				Font f = new Font("宋体",Font.BOLD,25);
				g.setFont(f);
				g.drawString("时间："+period+"秒", 180,  250);
				
			}
		}
		    
		g.setColor(c);
	}

	//重复的重画窗口
	class PaintThread extends Thread {
	
		@Override
		public void run() {
			super.run();
			while(true) {
				repaint();  //重画
				try {
					Thread.sleep(40);  //单位Ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	//定义键盘监听的内部类
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
	}

/**
 * 初始化窗口
 */
	public void launchFrame() {
		
		this.setTitle("Jason的作品");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDH, Constant.GAME_HEIGHT);
		this.setLocation(400,100);   //设置位置
		
		/**
		 * 关闭动作，窗口关闭时结束运行，重写了windowClosing方法，注意这里使用的是
		 * windowClosing而不是windowClosed
		 */
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start(); //启动重画窗口的线程
		addKeyListener(new KeyMonitor());  //给窗口增加键盘的监听
		
		//初始化25个炮弹
		for(int i=0;i<shells.length;i++) {
			shells[i] = new Shell();
		}
		
	}
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
}
