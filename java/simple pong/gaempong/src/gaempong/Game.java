package gaempong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")

public class Game extends JPanel {
	boolean up = false;
	boolean right = false;
	boolean left = false;
	boolean down = false;
	boolean p1up = false;
	boolean p1down = false;
	boolean p2up = false;
	boolean p2down = false;
	static int x = 270;	//ball position
	static int y = 170;	
	int xv = -2;		//ball x velocity
	int yv = 0;	//ball y velocity
	static int p1pos = 130;	//player position
	static int p2pos = 130;	//player position
	static int p1score = 0;
	static int p2score = 0;

	public void moveBall() {
		if (true)
		{
			y = y + yv;
		}
		if (true)
		{
			x = x + xv;
		}
		if (p1up)
		{
			if (p1pos>-30){
				p1pos = p1pos - 1;
			}
		}
		if (p1down)
		{
			if (p1pos<330){
				p1pos = p1pos + 1;
			}
		}
		if (p2up)
		{
			if (p2pos>-30){
				p2pos = p2pos - 1;
			}
		}
		if (p2down)
		{
			if (p2pos<330){
				p2pos = p2pos + 1;
			}
		}
	}
	
	public void ballVector(){
		if ((x <= 50)||(x >= 550))
		{
			if(xv < 0)
			{
				xv = -2;
			}
			if(xv > 0)
			{
				xv = +2;
			}
		}
	}
	public void checkCollide(){
		//ball hits p1top
		if ((x <= 35)&&(x >= 15)){
			if ((y <= (p1pos+1))&&(y >= (p1pos-30))){
				xv = 2;
				yv = -1;
				//System.out.println("Hit detected T");
			}
		}
		//y = p1pos;
		//ball hits p1mid
		if ((x <= 35)&&(x >= 15)){
			if ((y <= (p1pos+40))&&(y >= (p1pos))){
				xv = 2;
				yv = 0;
				//System.out.println("Hit detected M");
			}
		}
		
		//ball hits p1bot
		if ((x <= 35)&&(x >= 15)){
			if ((y <= (p1pos+70))&&(y >= (p1pos+41))){
				xv = 2;
				yv = 1;
				//System.out.println("Hit detected B");
			}
		}
		
		
		//ball hits p2top
		if ((x >= 525)&&(x <= 545)){
			if ((y <= (p2pos+1))&&(y >= (p2pos-30))){
				xv = -2;
				yv = -1;
				//System.out.println("Hit detected T");
			}
		}
		
		//ball hits p2mid
		if ((x >= 525)&&(x <= 545)){
			if ((y <= (p2pos+40))&&(y >= (p2pos))){
				xv = -2;
				yv = 0;
				//System.out.println("Hit detected M");
			}
		}
		
		//ball hits p2bot
		if ((x >= 525)&&(x <= 545)){
			if ((y <= (p2pos+70))&&(y >= (p2pos+41))){
				xv = -2;
				yv = 1;
				//System.out.println("Hit detected B");
			}
		}
		
		if (y<=0){
			yv = 2;
			//System.out.println("OOB");
		}
		if (y>=330){
			yv = -2;
			//System.out.println("OOB");
		}
		
	}
	
	public void scoreCheck(){
		if(x <= -50){
			p2score +=1;
			xv = 1;
			yv = 0;
			x = 270;
			y = 170;
			System.out.println(p1score+ " --- " + p2score);
		}
		
		if(x>= 650){
			p1score += 1;
			xv = -1;
			yv = 0;
			x =270;
			y = 170;
			System.out.println(p1score+ " --- " + p2score);
		}
		
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(x, y, 30, 30);
		g2d.fillRect(20, p1pos, 20, 70);
		g2d.fillRect(550, p2pos, 20, 70);
		
		
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Sample Frame");
		Game game = new Game();
		frame.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{//key listener should be in the instance
				
				if (e.getKeyCode() == KeyEvent.VK_Q)
				{
					game.p1up = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_A)
				{
					game.p1down = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_O)
				{
					game.p2up = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_L)
				{
					game.p2down = true;
				}
			}
			 public void keyReleased(KeyEvent e)
	            {
	                if (e.getKeyCode() == KeyEvent.VK_Q)
	                {
	                    game.p1up = false;
	                }
	                if (e.getKeyCode() == KeyEvent.VK_A)
	                {
	                    game.p1down = false;
	                }
	                if (e.getKeyCode() == KeyEvent.VK_O)
	                {
	                    game.p2up = false;
	                }
	                if (e.getKeyCode() == KeyEvent.VK_L)
	                {
	                    game.p2down = false;
	                }
	            }
			}
				);
		frame.add(game);
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//game.grabFocus();

		while (true) {
			game.ballVector();
			game.moveBall();
			game.repaint();
			game.checkCollide();
			game.scoreCheck();
			//System.out.println("x: " +x+ " y:" +y);
			//System.out.println("p1: " +p1pos);
			//System.out.println("p2: " +p2pos);
			Thread.sleep(10);
		}
	}
}

