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
    private Sprite Flash,RunningFlash,Flashleft,Flashup,Flashdown,Flashattack;
    
    public int z=0;
    public Player player;
    private int keyCode=0;
    private Map1 map;
    private Border border;
    Rectangle ad;
    Rectangle ak[];
    private Object []vektor;
    private Object water;
    private Object bllok1,bllok2,bllok3,bllok4,bllok5,bllok6,vWall,vWall2,vWall3;
    private Object gate;

    
    private Enemy enemy,enemy2,enemy3,enemy4,enemy_fixed1,enemy_fixed2,enemy_fixed3,enemy_fixed4,enemy_fixed5;
    private  Enemy [] enemyvektor;
    
    //Krijon nje enemy vetem per bossin 
    // Bosi do te jete i pavarur nga te tjeret 
    
    private Enemy BOSS;
    //krijimi i bullet per enemy
    private ThrowBullet boss_bullet;
    Follow_Player follow;
    private boolean victorycondition1=false;
    private boolean victorycondition2=false;
    
    private ThrowBullet bullet,bullet1,bullet2,bullet3,bullet_fixed1,bullet_fixed2,bullet_fixed3,bullet_fixed4,bullet_fixed5;
    private ThrowBullet [] bulletvektor;
    
    

    public Game()
    {
        /***************************************************************************************** */
        //Objektet qe do shfaqen ne Harte
        //Inicializim
        //vektor=new Object[4];
        requestFocusInWindow();

         

        //Inicializim i murit vertical
        Sprite wallv=new Sprite("thisiswall.png");
        Sprite wallh=new Sprite("thiswall2.png");
        vWall=new Bllok(1200,16,wallv);
        vWall2=new Bllok(1200,494,new Sprite("thisiswall2.png"));
        bllok1=new Bllok(250,200,wallv);
        bllok2=new Bllok(250,600,wallh);
        
        //Lake
        water=new Water(772,500);

        //Porta
        gate=new Gate(1200, 416);

        //Celsi
        bllok3=new Key(200,800);


        //Objektet qe do shfaqen
        //deren leje gjithmone te fundit
        vektor=new Object[] {bllok1,bllok2,bllok3,water,vWall,vWall2,gate};
        
        


        //Invisible
        



        //Testing
        as=new Rectangle(200,300,50,50);
        as.generateGraphics(11234);
   
 
    //SetsBounds of the frame
    setBounds(0,0,Width,Height);
    
    //Sets to center the frame
    setLocationRelativeTo(null);

    //It adds Canvas to the window (able to draw graphical components) 
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
    
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
    enemy = new Enemy(400,45,new Sprite("Flash3.png"),"Move_type","Long","Short_range","NO");
    enemy2 = new Enemy(70,700,new Sprite("Flash3.png"),"Move_type","Short","Short_range","NO");
    enemy3 = new Enemy(150,700,new Sprite("Flash3.png"),"Move_type","Short","Short_range","NO");
    enemy4 = new Enemy(400,115,new Sprite("Flash3.png"),"Move_type","Long","Short_range","NO");
    
    enemy_fixed1 =  new Enemy(320,300,new Sprite("Flash3.png"),"Disable_type","","Long_range","NO");
    enemy_fixed2 =  new Enemy(320,370,new Sprite("Flash3.png"),"Disable_type","","Long_range","NO");
    enemy_fixed3 =  new Enemy(320,450,new Sprite("Flash3.png"),"Disable_type","","Long_range","NO");
    enemy_fixed4 =  new Enemy(380,200,new Sprite("Flash3.png"),"Disable_type","","Long_range","NO");
    enemy_fixed5 =  new Enemy(380,600,new Sprite("Flash3.png"),"Disable_type","","Long_range","NO");
    
  //INICIALIZIMI I BOSIT DHE BULLET
    BOSS = new Enemy(1400,350,new Sprite("Flash3.png"),"Disabled_type","","Short_range","BOSS");
    enemyvektor=new Enemy[]{enemy,enemy2,enemy3,enemy4,enemy_fixed1,enemy_fixed2,enemy_fixed3,enemy_fixed4,enemy_fixed5,BOSS};
    
    
    
    //Bullet;
    bullet = new ThrowBullet(enemy);
    bullet1 = new ThrowBullet(enemy2);
    bullet2 = new ThrowBullet(enemy3);
    bullet3 = new ThrowBullet(enemy4);
    bullet_fixed1 = new ThrowBullet(enemy_fixed1);
    bullet_fixed2 = new ThrowBullet(enemy_fixed2);
    bullet_fixed3 = new ThrowBullet(enemy_fixed3);
    bullet_fixed4 = new ThrowBullet(enemy_fixed4);
    bullet_fixed5 = new ThrowBullet(enemy_fixed5);
    boss_bullet = new ThrowBullet(BOSS);
    bulletvektor = new ThrowBullet[] {bullet,bullet1,bullet2,bullet3,bullet_fixed1,bullet_fixed2,bullet_fixed3,bullet_fixed4,bullet_fixed5,boss_bullet};
    //Initialize Player
    Flash=new Sprite("Flash3.png"); Flashdown=new Sprite("Flashrun-down.png");
    RunningFlash=new Sprite("Flashrun.png"); Flashleft=new Sprite("Flashrun-left.png");
    Flashup=new Sprite("Flashrun-up.png"); Flashattack=new Sprite("FlashAttack1.png");
    player=new Player(100, 100, 500, Flash);
    
    //Initialize Map
    Sprite tileGrass=new Sprite("GrassTile.png");
     map=new Map1(Width, Height, tileGrass);

     //SetBorder
     border=new Border(Width, Height);

    }
    
    //It updates the elements on the screen
    public void update()
    {
    	
    	for(int i=0;i<enemyvektor.length;i++) {
    	//Funksioni per levizjen e kundershtarit ...
    		{
    			enemyvektor[i].follow(enemyvektor[i], player);
    			enemyvektor[i].shoot_bullet(player, bulletvektor[i]);
        		
    		}
    		
    	if(outOfBoundUp(enemyvektor[i]))
    	{

    		enemyvektor[i].move_up(-2);
    	}
    	if(outOfBoundDown(enemyvektor[i]))
    	{

    		enemyvektor[i].move_down(-2);
    	}
    	if(outOfBoundLeft(enemyvektor[i]))
    	{

    		enemyvektor[i].move_left(-2);
    	}
    	if(outOfBoundRight(enemyvektor[i]))
    	{

    		enemyvektor[i].move_right(-2);
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
    	if(enemyvektor[i].checkObjcollisionLeft(vektor))
    	{
    		enemyvektor[i].move_right(16);
    	}
    	else if(enemyvektor[i].checkObjcollisionRight(vektor))
    	{
    		enemyvektor[i].move_left(16);
    	}
    	
    	else if(enemyvektor[i].checkObjcollisionDown(vektor))
    	{
    		enemyvektor[i].move_up(10);  
    		}
    	
    	else if(enemyvektor[i].checkObjcollisionUp(vektor))
    	{
    		enemyvektor[i].move_down(10);
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
    	}
    	
        switch(keyCode)
        {
            case KeyEvent.VK_UP:
            player.setSprite(Flashup);
            //Checks if the player collide with any of the objects inside the vector && if he is out of border
            if(!player.checkObjcollisionUp(vektor)&&!outOfBoundUp(player))
                {
                    player.moveUp(); 
                }
                else{
                        //Checks the object type so it acts different in different types
                        if(!outOfBoundUp(player))
                        {   
                            switch (vektor[player.poistionV()].returnType()) 
                            {
                                case 1:
                                    player.halfspeed();
                                    player.moveUp();
                                    break;
                                case 2:
                                    victorycondition1=true;
                                    vektor = removeArrayElement(vektor, vektor[player.poistionV()],vektor[vektor.length-1]);
                                    player.moveUp();
                                    break;
                                default:
                                    break;
                            }      
                        }
                    }
            break;

            case KeyEvent.VK_DOWN:
            player.setSprite(Flashdown);
            if(!player.checkObjcollisionDown(vektor)&&!outOfBoundDown(player)){
                player.moveDown(); 
                }
                else{
                    //Checks the object type so it acts different in different types
                    if(!outOfBoundUp(player))
                    {   
                        switch (vektor[player.poistionV()].returnType()) 
                        {
                            case 1:
                                player.halfspeed();
                                player.moveDown();
                                break;
                            case 2:
                                victorycondition1=true;
                                vektor = removeArrayElement(vektor, vektor[player.poistionV()],vektor[vektor.length-1]);
                                player.moveDown();
                                break;
                            default:
                                break;
                        }      
                    }
                }   
            break;

            case KeyEvent.VK_LEFT:
            player.setSprite(Flashleft);
            if(!player.checkObjcollisionLeft(vektor)&&!outOfBoundLeft(player)){
                player.moveLeft(); 
                }
                else{
                    //Checks the object type so it acts different in different types
                    if(!outOfBoundUp(player))
                    {   
                        switch (vektor[player.poistionV()].returnType()) 
                        {
                            case 1:
                                player.halfspeed();
                                player.moveLeft();
                                break;
                            case 2:
                                victorycondition1=true;
                                vektor = removeArrayElement(vektor, vektor[player.poistionV()],vektor[vektor.length-1]);
                                player.moveLeft();
                                break;
                            default:
                                break;
                        }      
                    }
                }
            break;

            case KeyEvent.VK_RIGHT:
            player.setSprite(RunningFlash);
            if(!player.checkObjcollisionRight(vektor)&&!outOfBoundRight(player)){
                player.moveRight(); 
                }
                else{
                    //Checks the object type so it acts different in different types
                    if(!outOfBoundUp(player))
                    {   
                        switch (vektor[player.poistionV()].returnType()) 
                        {
                            case 1:
                                player.halfspeed();
                                player.moveRight();
                                break;
                            case 2:
                                victorycondition1=true;
                                vektor = removeArrayElement(vektor, vektor[player.poistionV()],vektor[vektor.length-1]);
                                player.moveRight();
                                break;
                            default:
                                break;
                        }      
                    }
                }
            break;
            
            case KeyEvent.VK_A:
                player.setSprite(Flashattack);
                for(int i=0;i<enemyvektor.length;i++){
            	if(player.player_catch_enemy(enemyvektor[i])){
                    
                    player.attack(enemyvektor[i]);
                    if(enemyvektor[i].GetHp()<=0)
                    {
                        //Removes the enemy from the enemyvector
                        enemyvektor=removEnemies(enemyvektor, enemyvektor[i]);
                    }

                }
            }	
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
        
        for(int i=0;i<enemyvektor.length;i++)
        {
            enemyvektor[i].renderEnemy(renderer);
            
        }
        //renderer.renderBullet(bullet, 1, 1);
        for(int i=0;i<bulletvektor.length - 1;i++)
        {
            renderer.renderBullet(bulletvektor[i], 1, 1);
            renderer.renderBullet(bulletvektor[bulletvektor.length - 1], 2, 2);
            
        }
        player.renderPlayer(renderer);
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

            //Kushti i fitores 
            if(enemyvektor[enemyvektor.length-1].enemydefeated()){victorycondition2=true;}
            if((victorycondition1&&victorycondition2)||player.playerDied()){
                break;}
              
        }
        if(player.playerDied()) {System.out.println("Humbe");}
        else {
        System.out.println("Fitove");}
        //Dispose  the frame
        dispose();

    }
 

    /****************************************************************************************************** */
    //Kanë problem
    @Override
    public void keyTyped(KeyEvent e) {

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

    /******************************************************* */
    public Object [] removeArrayElement(Object [] a,Object b,Object g)
    {
        Object []c =new Object[a.length-2];
        int j=0;
        for(int i=0;i<a.length;i++)
        {
            
            if(a[i]!=b  && (a[i]!=g))
            {
               c[j]=a[i];
               j++;
            }
        }
        return c;
    }
    
    public Enemy [] removEnemies(Enemy[]a,Enemy b)
    {
        Enemy c[] =new Enemy[a.length-1];
        int j=0;
        for(int i=0;i<a.length;i++)
        {
            
            if(a[i]!=b)
            {
               c[j]=a[i];
               j++;
            }
        }
        return c;
    }
}