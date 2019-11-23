import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ThrowBullet extends Rectangle implements KeyListener {
	
	public int _x,_y,_w = 10,_h = 10;
	private int[] _pixels;
	int speed = 10;

	Rectangle _bullet;
	ThrowBullet(Enemy _enemy)
	{
		_x = (int)_enemy.Enemy_X() + 32;
		_y = (int)_enemy.Enemy_Y()  +32;
		
	}
	
	ThrowBullet(Player player)
	{
		_x = (int)player.Getx() + 32;
		_y = (int)player.Gety()  +32;
		
	}
	
	public void create_bullet(int x,int y)
	{
		_x = x;
		_y = y;
	}
	
	
	 public void _generateGraphics(int color)
     {
         _pixels=new int[_w*_h];
         for(int y=0;y<_h;y++)
             for(int x=0;x<_w;x++)
                 _pixels[x+y*_w]=color;
        
         
     }
 
     public int [] getPixels()
     {
         if(_pixels!=null)
             return _pixels;
         else
         return null;
     }
     
     public double return_distance(Enemy _enemy,Player player)
     {
     	 return Math.sqrt((_enemy.Enemy_X()-player.Getx())*(_enemy.Enemy_X()-player.Getx()) + (_enemy.Enemy_Y()-player.Gety())*(_enemy.Enemy_Y()-player.Gety()));
     }
     
     public double traverse_distance(Enemy _enemy)
     {
     	 return Math.sqrt((_enemy.Enemy_X()-_x)*(_enemy.Enemy_X()-_x) + (_enemy.Enemy_Y()-_y)*(_enemy.Enemy_Y()-_y));
     }
     
     public double traverse_distance(Player player)
     {
     	 return Math.sqrt((player.Getx()-_x)*(player.Getx()-_x) + (player.Gety()-_y)*(player.Gety()-_y));
     }
     public void throw_bullet(Enemy _enemy,Player _player)
     {
    	 
    	 double start_X = _enemy.Enemy_X() + 32;
    	 double start_Y = _enemy.Enemy_Y() + 32;
    	 double dest_X = _player.Getx() + 32;
    	 double dest_Y = _player.Gety() + 32;
    	 
    	 double deltaX = dest_X - start_X;
    	 double deltaY = dest_Y - start_Y;
    	 double angle = Math.atan2( deltaY, deltaX );
    	 
    	 
    	 if(!_player.player_catch_enemy(_enemy))
    	 {
    		 _x += 5*Math.cos(angle);
    		 _y += 5*Math.sin(angle);
    		 
    	 }
    	 else
    	 {
    		 _x = (int)_enemy.Enemy_X() + 32;
    		 _y = (int)_enemy.Enemy_Y() + 32;
    		 _player.reduceHealth(1);
    	 }
    		 
    		 if(is_hitted(_player))
        	 {
    			// _player.setSprite(new Sprite("FlashAttack1.png"));
    			 if(_enemy.returnif_boss())
    			 {
    				 _x = (int)_enemy.Enemy_X() + 32;
            		 _y = (int)_enemy.Enemy_Y() + 32;
            		 _player.reduceHealth(6);  // LLoji enemy BOSS  heq me shume jete
            		 
    			 }
    			 else
    				 
    			 {
    				 _x = (int)_enemy.Enemy_X() + 32;
            		 _y = (int)_enemy.Enemy_Y() + 32;
            		 _player.reduceHealth(1);
            		 
    			 }
    			 
    			 System.out.println("HP : " + _player._hp);
        		 
        	 }
    		
    	 
    	 if(traverse_distance(_enemy) > 450 && !is_hitted(_player))
    	 {
    		 _x = (int)_enemy.Enemy_X() + 32;
    		 _y = (int)_enemy.Enemy_Y() + 32;
    	 }
    	 
    	 if(_x < 20 || _y < 20)
    	 {
    		 _x = (int)_enemy.Enemy_X() + 32;
    		 _y = (int)_enemy.Enemy_Y() + 32;
    	 }
    	
    	 
		 	 
    	 
     }
     
     
     
     
    
     
      public boolean is_hitted(Player player)
	 {
		 if(((_x - player.Getx() > 0) && (_x  - player.Getx() < 66))&& (((_y - player.Gety()) > 0) && ((_y - player.Gety()) < 66)))
			  return true;
		
		 return false;
	 }
      
      public boolean is_hitted(Enemy _enemy)
 	 {
 		 if(((this._x - _enemy.Enemy_X() > 0) && (this._x  - _enemy.Enemy_X() < _enemy._getwidth()))&& (((this._y - _enemy.Enemy_Y()) > 0) && ((this._y - _enemy.Enemy_Y()) < _enemy._getHeight())))
 			  return true;
 		
 		 return false;
 	 }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
      
	
}
