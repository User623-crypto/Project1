
public class Player {
    private int posx, posy;
	public  double _hp;
    private Sprite _sprite; 
    private int speed=10;
    private int _height;
    private int _width;
    private static final int tolerance_range=8;//Marzh gabimi
    private int nr_i_vektorit=0;
    private int _attack=8;
    Rectangle _hpline;

    
  //Vektori per te ruajtur levizjet dmth djathtas majtas ....
  	static int[]  move_memory = new int[2];
    Player(int x, int y, int h,  Sprite sprite) {
        posx = x;
        posy = y;
        _hp = h;
        _sprite = sprite;
        _height=sprite.getHeight();
        _width=sprite.getWidth();
        _hpline=new Rectangle(posx, posy-10, (int)_hp, 8);
        _hpline.generateGraphics(0xFFFF0000);
        

        
    }

    boolean playerDied()
    {
    	if(_hp<=0)
    		return true;
    	else
    		return false;
    }

    public void renderPlayer(RenderHandler renderer) {
        
        
        if(!playerDied()) {
        renderer.renderRectangle(_hpline,posx,posy-10,(int)_hp/10, 8);
        renderer.renderSprite(_sprite, posx, posy);
        }

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

    public void attack(Enemy a)
    {
        a.reduceHp(_attack);
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

    public double Gethp()
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
    public void reduceHealth(double a)
    {
        _hp-=a;
    }

    
    
    /*
     * ADDED BY ADEM
     * GJUAN PLUMBIN PER PLAYERIN
     */
    public void player_shoot(Enemy _enemy,ThrowBullet _bullet)
    {
    	if(!player_catch_enemy(_enemy))
    		
    	{
    		player_bullet_move(_enemy,_bullet);
    		if(next_shoot(_enemy, _bullet))
    		{
    			_bullet._x = this.Getx() + 30;
    			_bullet._y = this.Gety() + 30;
    			
    			Player.move_memory[1] = 0;
    		}
    	 
    	 if(move_memory[1] == 0)
    	 {
    		 _bullet._x = this.Getx() + 30;
 			_bullet._y = this.Gety() + 30;
    	 }
    	 
    	 
    	 
      }
    	
    	else
    	{
    		
    		_bullet._x = this.Getx() + 30;
    		_bullet._y = this.Gety() + 30;
    	
    	}
    	 
    	
    	 
    	 
    }
   
    
    /*    
     * Levizja e plumbit per PLAYER - in
     */
    
    public void player_bullet_move(Enemy _enemy,ThrowBullet _bullet)
    {
   	 double start_X = this.Getx() + 32;
   	 double start_Y = this.Gety() + 32;
   	 int player_range = 500;

   	 
   	 if(move_memory[0] == 227 && move_memory[1] == 65)
   	 {
   		  _bullet._x+= 2;
   		  
   	 }
   	if(move_memory[0] == 226 && move_memory[1] == 65)
  	 {
  		  _bullet._x-= 2;
  	 }
   	if(move_memory[0] == 224 && move_memory[1] == 65)
  	 {
  		  _bullet._y-= 2;
  	 }
   	if(move_memory[0] == 225 && move_memory[1] == 65)
  	 {
  		  _bullet._y+= 2;
  	 }
   	 
    }
    
    public  boolean next_shoot(Enemy _enemy,ThrowBullet _bullet)
    {
    	if(_bullet.is_hitted(_enemy) || _bullet.traverse_distance(this) > 400)
    	{
    		if(_bullet.is_hitted(_enemy) &&  !player_catch_enemy(_enemy))
    	   	 {
    	   		 attack(_enemy); 
    	   	 }
    		return true;
    	}
    		
    	return false;
    }
    public static boolean _next_shoot(Enemy _enemy,ThrowBullet _bullet,Player player)
    {
    	if(_bullet.is_hitted(_enemy) || _bullet.traverse_distance(player) > 400)
    		return true;
    	return false;
    }
    
    
    /*
     * 
     * ADDED BY ADEM 
     * KTHEN CIla taste eshte bere pressed
     */
    
    
    //Kthen nje vektor ku do te ruaj vlerat e shigjetave te shtypura 
    //Ideja eshte qe kur shtypet p.sh -> dhe me pas shtypet A ath do te behet gjuajtja
    /*Dhe  p.sh kur heren e papre shtyp ->   dhe heren e dyte shtyp <-  ath  ai ruan po ne qelizen e pare vleren e shigjetes <-
    public int[] store_pressed()
    {
   	 int i;
   	 if(Game.return_keycode() == 227)
   	 {
   		 i = 0;
   		move_memory[i] = 227; 
   	 }
   	 if(Game.return_keycode() == 226)
   	 {
   		 i=0;
   		move_memory[i] = 226; 
   	 }
   	 if(Game.return_keycode() == 225)
   	 {
   		 i=0;
   		move_memory[i] = 225; 
   	 }
   	 if(Game.return_keycode() == 224)
   	 {
   		 i=0;
   		move_memory[i] = 224; 
   	 }
   	 if(Game.return_keycode() == 65)
   	 {
   		 if(move_memory[0] == 0)
   			 move_memory[1] = 0;
   		 else if(move_memory[0] == 227 || move_memory[0] == 226 || move_memory[0] == 225 || move_memory[0] == 224 )
   			 move_memory[1] = 65;
   		 
   		
   	 }
   	 return move_memory;
   	 
    }*/
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
    	if( (((_enemy.Enemy_X() + _enemy._getwidth() ) - Getx()  > 0)) &&  ((_enemy.Enemy_X() - Getx()  < _enemy._getwidth()))

    			&&  ((_enemy.Enemy_Y() + _enemy._getHeight() ) - Gety()  > 0) &&  ((_enemy.Enemy_Y() - Gety()  < _enemy._getHeight())))
		 //if(((_x  - Getx() > 0) && (_x  - Getx() < 40))&& (((_y - player.Gety()) > 0) && ((y - player.Gety()) < 40)))
			  return true;
		
		 return false;
	 }	
   
}