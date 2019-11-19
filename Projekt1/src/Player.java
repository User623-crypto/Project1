
public class Player {
    private int posx, posy;
	private  int _hp;
    private Sprite _sprite; 
    private int speed=10;
    private int _height;
    private int _width;
    private static final int tolerance_range=8;//Marzh gabimi
    private int nr_i_vektorit=0;
    private int _attack=10;
    Rectangle _hpline;

    Player(int x, int y, int h,  Sprite sprite) {
        posx = x;
        posy = y;
        _hp = h;
        _sprite = sprite;
        _height=sprite.getHeight();
        _width=sprite.getWidth();
        _hpline=new Rectangle(posx, posy-10, _hp, 2);
        _hpline.generateGraphics(0xFFFF0000);
        

        
    }



    public void renderPlayer(RenderHandler renderer) {
        
        
        renderer.renderRectangle(_hpline,posx,posy,1, 1);
        renderer.renderSprite(_sprite, posx, posy);

    }

    public void movePlayer() {
     
    }

    public void moveRight()
    {
        posx+=speed;
    }

    public void moveLeft()
    {
        posx-=speed;
    }

    public void moveUp()
    {
        posy-=speed;
    }

    public void moveDown()
    {
        posy+=speed;
    }

    public void halfspeed()
    {
        speed=2;
    }
    public void fullspeed()
    {
        speed=10;
    }
    
/****************************************************** */
    //Getters
    public int Getx()
    {
        return posx;
    }
    public int Gety()
    {
        return posy;
    }
    public int Getwidth()
    {
        return _width;
    }
    public int getHeight()
    {
        return _height;
    }
    public int getSpeed()
    {
        return speed;
    }

    public int poistionV()
    {
        return nr_i_vektorit;
    }

    public int Gethp()
    {
        return _hp;
    }

    public int Getattack()
    {
        return _attack;
    }
    /****************************************** */
    //Setters
    public void setSprite(Sprite sprite)
    {
        _sprite=sprite;
    }
    public void setSpeed(int s)
    {
        speed=s;
    }

    //Heq jeten e lojtarit
    public void reduceHealth(int a)
    {
        _hp-=a;
    }


    //********************************************************************************* */
    //COLLISION CHECK Testing

    /*public boolean checkleft( Rectangle a )
    {
            if(posx<=(a.x+a.w+tolerance_range) && ((posx+tolerance_range)>a.x) && (posy+_height)>a.y && posy<(a.y+a.h))
                  return true;
            
        return false;
    }

    public boolean checkright(Rectangle a)
    {
        if((posx+_width+tolerance_range)>a.x && ((posx)<=a.x+tolerance_range) && (posy+_height)>a.y && posy<(a.y+a.h))
               return true;
        
        return false;
    }

    public boolean checkup(Rectangle a)
    {
        if(posy<=(a.y+a.h+tolerance_range) && ((posy+tolerance_range)>=a.y) && (posx+_width)>a.x && (posx)<(a.x+a.w))
            return true;
        
        return false;
    }

    public boolean checkdown(Rectangle a)
    {
        if((posy+_height+tolerance_range)>=(a.y) && ((posy)<=a.y+tolerance_range) && (posx+_width)>a.x && (posx)<(a.x+a.w))
             return true;
        
        return false;
    }


    public boolean checkObjcollisionRight(Rectangle [] a )
    {
        for(int i=0;i<a.length;i++)
            if(checkright(a[i])){return true;}
        
        return false;
    }
    public boolean checkObjcollisionLeft(Rectangle [] a )
    {
        for(int i=0;i<a.length;i++)
             if(checkleft(a[i])){return true;}

        return false;
    }

    public boolean checkObjcollisionUp(Rectangle [] a )
    {
        for(int i=0;i<a.length;i++)
            if(checkup(a[i])){return true;}
        
        return false;
    }

    public boolean checkObjcollisionDown(Rectangle [] a )
    {
        for(int i=0;i<a.length;i++)
            if(checkdown(a[i])){return true;}
        
        return false;
    }*/

    /************************************************************************** */
    //Collision of OBJECT

    public boolean checkleft( Object a )
    {
            if(posx<=(a.getX()+a.getWidth()+tolerance_range) && ((posx+tolerance_range)>a.getX()) && (posy+_height)>a.getY() && posy<(a.getY()+a.getHeight()))
                  return true;
            
        return false;
    }

    public boolean checkright(Object a)
    {
        if((posx+_width+tolerance_range)>a.getX() && ((posx)<=a.getX()+tolerance_range) && (posy+_height)>a.getY() && posy<(a.getY()+a.getHeight()))
               return true;
        
        return false;
    }

    public boolean checkup(Object a)
    {
        if(posy<=(a.getY()+a.getHeight()+tolerance_range) && ((posy+tolerance_range)>=a.getY()) && (posx+_width)>a.getX() && (posx)<(a.getX()+a.getWidth()))
            return true;
        
        return false;
    }

    public boolean checkdown(Object a)
    {
        if((posy+_height+tolerance_range)>=(a.getY()) && ((posy)<=a.getY()+tolerance_range) && (posx+_width)>a.getX() && (posx)<(a.getX()+a.getWidth()))
             return true;
        
        return false;
    }


    public boolean checkObjcollisionRight(Object [] a )
    {
        for(int i=0;i<a.length;i++){
            {nr_i_vektorit=i;
            if(checkright(a[i])){return true;}}}
        return false;
    }
    public boolean checkObjcollisionLeft(Object [] a )
    {
        for(int i=0;i<a.length;i++){nr_i_vektorit=i;
             if(checkleft(a[i])){return true;}}

        return false;
    }

    public boolean checkObjcollisionUp(Object [] a )
    {
        for(int i=0;i<a.length;i++){nr_i_vektorit=i;
            if(checkup(a[i])){return true;}}
        
        return false;
    }

    public boolean checkObjcollisionDown(Object [] a )
    {
        for(int i=0;i<a.length;i++){nr_i_vektorit=i;
            if(checkdown(a[i])){return true;}}
        
        return false;
    }
    /************************************************************************************************* */
        
    /*
     * ADDED BY ADEM    - > - > Kontrollon nese lojtari eshte takuar me enemy
     */
    
    
    public boolean player_catch_enemy(Enemy _enemy)
	 {
    	if( ((Math.abs((_enemy.Enemy_X() + 32 ) - (Getx() + 32) ) < 10) &&  (Math.abs((_enemy.Enemy_Y() + 32 ) - (Gety() + 32) ) < 10)))
		 //if(((_x  - Getx() > 0) && (_x  - Getx() < 40))&& (((_y - player.Gety()) > 0) && ((y - player.Gety()) < 40)))
			  return true;
		
		 return false;
	 }	
   
}