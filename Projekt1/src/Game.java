//Buffering strategy setup
/**
 * Game
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

//Events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.lang.Runnable;

import javax.swing.JFrame;




//JFrame creates the window, Runnable makes the code to run maxspeed
public class Game extends JFrame implements Runnable,KeyListener { 
    
    //!!Dont use
    public static int alpha=0xFFFA00DC;

    private static int Width=1600;private int Height=960;
    private Canvas canvas=new Canvas();
    private RenderHandler renderer;
    private Rectangle as;
    private Sprite Flash,RunningFlash,Flashleft,Flashup,Flashdown;
    public int z=0;
    public Player player;
    private int keyCode=0;
    private Map1 map;
    int c = 0;
    public Player enemy;
    public Sprite enemy_sprite;

    private double move_X;
    private double move_Y;

    public Game()
    {
        as=new Rectangle(0,0,50,50);
        as.generateGraphics(11234);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
 
    //SetsBounds of the frame
    setBounds(0,0,Width,Height);

    //Sets to center the frame
    setLocationRelativeTo(null);

    //It adds Canvas to the window (able to draw graphical components)   
    add(canvas);


    //Makes frame Visible
    setVisible(true);
    

    //Create Buffer Strategy for the Canvas
    canvas.createBufferStrategy(3);

    //krijon nje vektor me pixels sa madhesia e Frames
    renderer=new RenderHandler(Width,Height);

    //testImage=loadImage("Beton.png");
    //testImage2=loadImage("GrassTile.png");
    //BufferedImage testImage3=loadImage("Flash3.png");

    //Initialize Player
    Flash=new Sprite("Flash3.png"); Flashdown=new Sprite("Flashrun-down.png");
    RunningFlash=new Sprite("Flashrun.png");
    Flashleft=new Sprite("Flashrun-left.png");
    Flashup=new Sprite("Flashrun-up.png");
    
    
    enemy_sprite = new Sprite("Flash3.png");
    enemy = new Player(0,0,50,enemy_sprite);
    //Initialize Player
    player=new Player(100, 100, 50, Flash);
    
    //Initialize Map
    Sprite tileGrass=new Sprite("GrassTile.png");
     map=new Map1(Width, Height, tileGrass);

    }
    
    //It updates the elements on the screen
    public void update()
    {
        
        switch(keyCode)
        {
            case KeyEvent.VK_UP:
            player.moveUp();
            player.setSprite(Flashup);
            //move_enemy();
            break;

            case KeyEvent.VK_DOWN:
            player.moveDown();
            player.setSprite(Flashdown);
           // move_enemy();
            break;

            case KeyEvent.VK_LEFT:
            player.moveLeft();
            player.setSprite(Flashleft);
            //move_enemy();
            break;

            case KeyEvent.VK_RIGHT:
            player.moveRight();
            player.setSprite(RunningFlash);
            //move_enemy();
            break;
            default:
            player.setSprite(Flash);
            break;

        }


    }


    
    /* Added by ADEM 
     * 
     * 
     * 
     */
    
    
    //Move and Follow my character by an enemy
    
    //leviz vetem horizontal
    public void move_horizontally()
    {
    	if(enemy.Player_X() < player.Player_X())
		
			move_right();
		else
			move_left();
		
    }
    
    public void move_right()
    {
    	move_Y = enemy.Player_Y();
    	move_X = enemy.Player_X();
		  
		  while(move_X< player.Player_X())
		  {
			  
            try {
            	
            	
                Thread.sleep(200);
            	move_X = move_X + 1;
            	//move_Y = move_Y - dif;
            	enemy = new Player((int)move_X,(int)move_Y,50,enemy_sprite);
            	} 
            
            catch (Exception ex) {
            	
            }
		  }
    }
    
    public void move_left()
    {
    	move_Y = enemy.Player_Y();
    	move_X = enemy.Player_X();
		  
		  while(move_X > player.Player_X())
		  {
			  
            try {
            	
            	
            	Thread.sleep(200);
            	move_X = move_X - 1;
            	//move_Y = move_Y - dif;
            	enemy = new Player((int)move_X,(int)move_Y,50,enemy_sprite);
            	} 
            
            catch (Exception ex) {
            	
            }
		  }
    }
    
    //leviz vetem vertical
    public void move_vertically()
    {
    	if(enemy.Player_Y() > player.Player_Y())
    		move_up();
    	else
    		move_down();
    }
    
    public void move_up()
    {
    	move_X = enemy.Player_X();
		move_Y = enemy.Player_Y();
		 while(move_Y > player.Player_Y())
		  {
             
               try {
               
               	Thread.sleep(200);
               	//move_X = move_X - 1;
               	move_Y = move_Y - 1;
               	enemy = new Player((int)move_X,(int)move_Y,50,enemy_sprite);
               	} 
               
               catch (Exception ex) {
               	
               }
		  }
    }
    
    public void move_down()
    {
    	move_X = enemy.Player_X();
		move_Y = enemy.Player_Y();
		 while(move_Y < player.Player_Y())
		  {
             
               try {
               
               	Thread.sleep(200);
               	//move_X = move_X - 1;
               	move_Y = move_Y + 1;
               	enemy = new Player((int)move_X,(int)move_Y,50,enemy_sprite);
               	} 
               
               catch (Exception ex) {
               	
               }
		  }
    }
    
    //funksioni qe do te kryeje levizjen e enemy
    public void move_enemy()
    {
    	
    	//Thread animationThread = new Thread(new Runnable() {
    		//@Override
    	
    	Runnable runnable = new Runnable() {
           @Override
            public void run() {
            	while(!Thread.interrupted())
            	{
            		if(enemy.Player_Y() - player.Player_Y() < 10 && enemy.Player_Y() - player.Player_Y() > -10)
                	{

      					  move_horizontally();
      			     }
                	
                	
                	//kontrollon kushtin per levizjen verticale
                	if(enemy.Player_X() - player.Player_X() < 10 && enemy.Player_X() - player.Player_X() > -10)
        			  {
        				  
        					  move_vertically();
        			     }
                	
                	
                	
                //Behet levizja edhe diagonale ne rastin kur enemy ndodhet lart PLAYER-it	
                  if(enemy.Player_Y() < player.Player_Y())
          		   {
                		 
                	  	//levizja diagonal kur enemy ndodhet lart dhe majtas PLAYER-it
          		           if(enemy.Player_X() < player.Player_X())
          		           {
          		        	 move_X = enemy.Player_X();
          		        	 move_Y = enemy.Player_Y();
          		        	  while(enemy.Player_X() < player.Player_X() && enemy.Player_Y() < player.Player_Y())
          		        	  {
          	                        try {
          	                        	double dif = 50/(double)player.Player_X() - enemy.Player_X();
          	                        	Thread.sleep(200);
          	                        		move_X = move_X + 1;
          	                        		move_Y = move_Y + dif;
          	                        		enemy = new Player((int)move_X,(int)move_Y,50,enemy_sprite);
          	                        		
          	                        	} 
          	                        
          	                        catch (Exception ex) {
          	                        	
          	                        }
          		        	  }
          		           }
          		           
          		           
          		           //levizja diagonal kur enemy ndodhet lart dhe djathtas PLAYEr-it
          		         if(enemy.Player_X() > player.Player_X())
        		           {
        		        	 move_X = enemy.Player_X();
        		        	 move_Y = enemy.Player_Y();
        		        	  while(enemy.Player_X() > player.Player_X() && enemy.Player_Y() < player.Player_Y())
        		        	  {

        	                        try {
        	                        	double dif = 50/(double)player.Player_X() - enemy.Player_X();

        	                        	Thread.sleep(200);
        	                        		move_X = move_X - 1;
        	                        		move_Y = move_Y + dif;
        	                        		enemy = new Player((int)move_X,(int)move_Y,50,enemy_sprite);
        	                        		
        	                        	} 
        	                        
        	                        catch (Exception ex) {
        	                        	
        	                        }
        		        	  }
        		           }
          		   }
                	 
                	 
                  //Levizja e enemy kur ndodhet poshte PLAYER- it
                if(enemy.Player_Y() > player.Player_Y())
            		   {
                  		 	// Poshte majtas
            		           if(enemy.Player_X() < player.Player_X())
            		           {
            		        	 move_X = enemy.Player_X();
            		        	 move_Y = enemy.Player_Y();
            		        	  while(enemy.Player_X() < player.Player_X() && enemy.Player_Y() > player.Player_Y())
            		        	  {

            	                        try {
            	                        	double dif = 50/(double)player.Player_X() - enemy.Player_X();
            	                        	Thread.sleep(200);
            	                        		move_X = move_X + 1;
            	                        		move_Y = move_Y - dif;
            	                        		enemy = new Player((int)move_X,(int)move_Y,50,enemy_sprite);
            	                        		
            	                        	} 
            	                        
            	                        catch (Exception ex) {
            	                        	
            	                        }
            		        	  }
            		           }
            		           
            		           
            		           //poshte djathtas
            		         if(enemy.Player_X() > player.Player_X())
          		           	{
            		        	 move_X = enemy.Player_X();
            		        	 move_Y = enemy.Player_Y();
          		        	  while(enemy.Player_X() > player.Player_X() && enemy.Player_Y() > player.Player_Y())
          		        	  {

          	                        try {
          	                        	double dif = 50/(double)player.Player_X() - enemy.Player_X();
          	                        	
          	                        	Thread.sleep(200);
          	                        		move_X = move_X - 1;
          	                        		move_Y = move_Y - dif;
          	                        		enemy = new Player((int)move_X,(int)move_Y,50,enemy_sprite);
          	                        		
          	                        	} 
          	                        
          	                        catch (Exception ex) {
          	                        	
          	                        }
          		        	  }
          		           }
            		   }
                }
            	}
        };
        
        Thread my_thread = new Thread(runnable);      
        my_thread.start();

    }
    //  -- FUND  ADDED  BY ADEM
    //It renders all components on the screen
    public void render()
    {
        BufferStrategy bufferStrategy=canvas.getBufferStrategy();
      
        Graphics graphics= bufferStrategy.getDrawGraphics();

        super.paint(graphics);
 
        map.loadMap(renderer);
       
        player.renderPlayer(renderer);
        enemy.renderPlayer(renderer);
        
        renderer.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }


    //The method that gets executed by the thread
    public void run(){//ASAP
        
        addKeyListener(this);

        /* Menaxhimi i frames*/
        long lastTime=System.nanoTime();
        double nanoSecondConversion=1000000000.0/60;//60 frames per second
        double changeInSeconds=0;

        
        //Standard
        while(true){
           long now=System.nanoTime();
           changeInSeconds +=(now-lastTime)/nanoSecondConversion;
            while(changeInSeconds>=1)
            {
                update(); 
                changeInSeconds=0;
            }
            render();
            lastTime=now;
        }

        

    }
 

    
 
    //Kanë problem
    @Override
    public void keyTyped(KeyEvent e) {
        keyCode=e.getKeyCode();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode=e.getKeyCode();
                
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCode=0;

    }
    
}