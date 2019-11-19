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
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.lang.Runnable;

import javax.swing.JFrame;




//JFrame creates the window, Runnable makes the code to run maxspeed
public class Game extends JFrame implements Runnable,KeyListener { 
    
    //!!Dont use
    public static int alpha=0xFFFA00DC;

    private static final int  Width=1600;private final int  Height=960;
    private Canvas canvas=new Canvas();
    private RenderHandler renderer;
    private Rectangle as;
    private Sprite Flash,RunningFlash,Flashleft,Flashup,Flashdown;
    public int z=0;
    public Player player;
    private int keyCode=0;
    private Map1 map;
    private Border border;
    Rectangle ad;
    Rectangle ak[];
    private Object []vektor;
    private Object water;
    private Object bllok1,bllok2,bllok3,bllok4,bllok5,bllok6,vWall,vWall2;
    private Object gate;
    
    private Enemy enemy;
    Follow_Player follow;

    

    public Game()
    {
        /***************************************************************************************** */
        //Objektet qe do shfaqen ne Harte
        //Inicializim
        //vektor=new Object[4];
        

        

        //Inicializim i murit vertical
        Sprite wallv=new Sprite("thisiswall.png");
        Sprite wallh=new Sprite("thiswall2.png");
        vWall=new Bllok(1200,16,wallv);
        vWall2=new Bllok(1200,544,wallv);
        bllok1=new Bllok(250,200,wallv);
        bllok2=new Bllok(250,600,wallh);
        
        //Lake
        water=new Water(772,568);

        //Porta
        gate=new Gate(1200, 416);




        //Objektet qe do shfaqen
        vektor=new Object[] {bllok1,bllok2,water,vWall,vWall2,gate};
        
        






        
        as=new Rectangle(200,300,50,50);
        as.generateGraphics(11234);
   
 
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
    
    //Inicilizmi i Enemy
    enemy = new Enemy(10,25,new Sprite("Flash3.png"));
    //Initialize Player
    Flash=new Sprite("Flash3.png"); Flashdown=new Sprite("Flashrun-down.png");
    RunningFlash=new Sprite("Flashrun.png"); Flashleft=new Sprite("Flashrun-left.png");
    Flashup=new Sprite("Flashrun-up.png");
    player=new Player(100, 100, 50, Flash);
    
    //Initialize Map
    Sprite tileGrass=new Sprite("GrassTile.png");
     map=new Map1(Width, Height, tileGrass);

     //SetBorder
     border=new Border(Width, Height);

    }
    
    //It updates the elements on the screen
    public void update()
    {
    	
    	//Funksioni per levizjen e kundershtarit ...
        enemy.move_enemy(enemy, player);
    	if(outOfBoundUp(enemy))
    	{

    		enemy.move_up(-2);
    	}
    	if(outOfBoundDown(enemy))
    	{

    		enemy.move_down(-2);
    	}
    	if(outOfBoundLeft(enemy))
    	{

    		enemy.move_left(-2);
    	}
    	if(outOfBoundRight(enemy))
    	{

    		enemy.move_right(-2);
    	}
    	/*
    	if(!enemy.checkObjcollisionDown(vektor))
    	{
    		enemy.move_enemy(enemy, player);
    	}
    	
    	if(!enemy.checkObjcollisionUp(vektor))
    	{
        	enemy.move_enemy(enemy, player);
    	}
    	*/
    	if(enemy.checkObjcollisionLeft(vektor))
    	{
        	enemy.move_right(16);
    	}
    	else if(enemy.checkObjcollisionRight(vektor))
    	{
        	enemy.move_left(16);
    	}
    	
    	else if(enemy.checkObjcollisionDown(vektor))
    	{
    		enemy.move_up(10);  
    		}
    	
    	else if(enemy.checkObjcollisionUp(vektor))
    	{
        	enemy.move_down(10);
    	}
    	/*
    	if(!enemy.checkObjcollisionRight(vektor)&&!outOfBoundRight(enemy))
    	{
        	enemy.move_enemy(enemy,player);
    	}
    	
    	if(!enemy.checkObjcollisionLeft(vektor)&&!outOfBoundLeft(enemy))
    	{
        	enemy.move_enemy(enemy,player);
    	}*/
        switch(keyCode)
        {
            case KeyEvent.VK_UP:
            player.setSprite(Flashup);
            //Checks if the player collide with any of the objects inside the vector && if he is out of border
            if(!player.checkObjcollisionUp(vektor)&&!outOfBoundUp(player)){
                
                player.moveUp(); 
                }
                else{
                        //Checks the object type so it acts different in different types
                        if(vektor[player.poistionV()].returnType()==1&&!outOfBoundUp(player))
                            {   player.halfspeed();
                                player.moveUp();
                            }
                    }
            break;

            case KeyEvent.VK_DOWN:
            player.setSprite(Flashdown);
            if(!player.checkObjcollisionDown(vektor)&&!outOfBoundDown(player)){
                player.moveDown(); 
                }
                else{
                        if(vektor[player.poistionV()].returnType()==1&&!outOfBoundDown(player))
                        {   player.halfspeed();
                            player.moveDown();
                        }
                    }   
            break;

            case KeyEvent.VK_LEFT:
            player.setSprite(Flashleft);
            if(!player.checkObjcollisionLeft(vektor)&&!outOfBoundLeft(player)){
                player.moveLeft(); 
                }
                else{
                    if(vektor[player.poistionV()].returnType()==1&&!outOfBoundLeft(player))
                    {   player.halfspeed();
                        player.moveLeft();
                        
                    }
                }
            break;

            case KeyEvent.VK_RIGHT:
            player.setSprite(RunningFlash);
            if(!player.checkObjcollisionRight(vektor)&&!outOfBoundRight(player)){
                player.moveRight(); 
                }
                else{
                    if(vektor[player.poistionV()].returnType()==1&&!outOfBoundRight(player))
                    {   player.halfspeed();
                        player.moveRight();
                        
                    }
                }
            break;
            
            case KeyEvent.VK_A:
            	//player.setSprite(sprite);
            	break;
            
            default:
            player.setSprite(Flash);
            player.fullspeed();
            break;

        }
    

    }


    //It renders all components on the screen
    public void render()
    {
        BufferStrategy bufferStrategy=canvas.getBufferStrategy();
      
        Graphics graphics= bufferStrategy.getDrawGraphics();

        super.paint(graphics);
        border.loadBorder(renderer);
        map.loadMap(renderer);
        for(int i=0;i<vektor.length;i++)
        {
            vektor[i].renderObject(renderer);
        }
        
        
        player.renderPlayer(renderer);
        enemy.renderEnemy(renderer);
        renderer.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }


    //The method that gets executed by the thread
    public void run(){//ASAP
        //setFocusable(true);
        
        addKeyListener(this);
        

        /* Menaxhimi i frames*/
        long lastTime=System.nanoTime();
        double nanoSecondConversion=1000000000.0/60;//60 frames per second
        double changeInSeconds=0;

        
        //Standard
        while(true){
            requestFocus();
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
 

    /****************************************************************************************************** */
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
//********************************************************************************************************** */
    //Border collision
    public boolean outOfBounds(Player a)
    {
        if(16<a.Getx()-10 && a.Getx()+a.Getwidth()+10<Width-16 && 16<a.Gety()-10 && a.Gety()+a.getHeight()+10<Height-16 ){return false;}
        return true;
    }
    public boolean outOfBoundLeft(Player a)
    {
        if(16<a.Getx()){return false;}
        return true;
    }
    public boolean outOfBoundRight(Player a)
    {
        if(a.Getx()+a.Getwidth()<Width-16 ){return false;}
        return true;
    }
    public boolean outOfBoundUp(Player a)
    {
        if(30<a.Gety()){return false;}
        return true;
    }
    public boolean outOfBoundDown(Player a)
    {
        if(a.Gety()+a.getHeight()<Height-16){return false;}
        return true;
    }
    
    
    
    
    
    public boolean outOfBounds(Enemy a)
    {
        if(16<a.Enemy_X()-10 && a.Enemy_X()+a._getwidth()+10<Width-16 && 16<a.Enemy_Y()-10 && a.Enemy_Y()+a._getHeight()+10<Height-16 ){return false;}
        return true;
    }
    public boolean outOfBoundLeft(Enemy a)
    {
        if(16<a.Enemy_X()){return false;}
        return true;
    }
    public boolean outOfBoundRight(Enemy a)
    {
        if(a.Enemy_X()+a._getwidth()<Width-16 ){return false;}
        return true;
    }
    public boolean outOfBoundUp(Enemy a)
    {
        if(45<a.Enemy_X()){return false;}
        return true;
    }
    public boolean outOfBoundDown(Enemy a)
    {
        if(a.Enemy_Y()+a._getHeight()<Height-16){return false;}
        return true;
    }
    
}