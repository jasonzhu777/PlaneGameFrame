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
 * �ɻ���Ϸ��������
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
	int period; //��Ϸ������ʱ��
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		//super.paint(g);    //��дpaintʱ������ø���ķ�������һ������������ʹ������ķ�ʽ
		
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g); //���ɻ�
		
		//�������е��ڵ�
		for(int i=0;i<shells.length;i++) {
			shells[i].draw(g); 
			
			//�ɻ����ڵ�����ײ���
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
			//��ʱ���ܣ�������ʾ
			if(!plane.live) {
				g.setColor(Color.red);
				Font f = new Font("����",Font.BOLD,25);
				g.setFont(f);
				g.drawString("ʱ�䣺"+period+"��", 180,  250);
				
			}
		}
		    
		g.setColor(c);
	}

	//�ظ����ػ�����
	class PaintThread extends Thread {
	
		@Override
		public void run() {
			super.run();
			while(true) {
				repaint();  //�ػ�
				try {
					Thread.sleep(40);  //��λMs
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	//������̼������ڲ���
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
 * ��ʼ������
 */
	public void launchFrame() {
		
		this.setTitle("Jason����Ʒ");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDH, Constant.GAME_HEIGHT);
		this.setLocation(400,100);   //����λ��
		
		/**
		 * �رն��������ڹر�ʱ�������У���д��windowClosing������ע������ʹ�õ���
		 * windowClosing������windowClosed
		 */
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start(); //�����ػ����ڵ��߳�
		addKeyListener(new KeyMonitor());  //���������Ӽ��̵ļ���
		
		//��ʼ��25���ڵ�
		for(int i=0;i<shells.length;i++) {
			shells[i] = new Shell();
		}
		
	}
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
}
