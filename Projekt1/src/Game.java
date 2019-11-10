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
    private Sprite testSprite;
    public int z=0;
    public Player a;
    private int keyCode=0;
    private Map1 map;

    

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
    testSprite=new Sprite("Flash3.png");
    a=new Player(100, 100, 50, testSprite);
    
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
            a.moveUp();
            break;

            case KeyEvent.VK_DOWN:
            a.moveDown();
            break;

            case KeyEvent.VK_LEFT:
            a.moveLeft();
            break;

            case KeyEvent.VK_RIGHT:
            a.moveRight();
            break;
            default:
            break;

        }


    }


    //It renders all components on the screen
    public void render()
    {
        BufferStrategy bufferStrategy=canvas.getBufferStrategy();
      
        Graphics graphics= bufferStrategy.getDrawGraphics();

        super.paint(graphics);
 
        map.loadMap(renderer);

        a.renderPlayer(renderer);
        
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