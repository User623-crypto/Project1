
public class Enemy extends Follow_Player  {

	
	private double enemy_x;
	private double enemy_y;
	private int enemy_height;
	private int enemy_width;
	Sprite _sprite;
	Player player;
	double speed = 1;
	int _tolerance_range = 8;
	private int _nr_i_vektorit=0;
	Thread my_thread;
	Rectangle _barrier;
	Enemy(int x, int y, Sprite sprite) {
		enemy_x = x;
		enemy_y = y;
		_sprite = sprite;
		enemy_height=sprite.getHeight();
        enemy_width=sprite.getWidth();
		
		
	}

	
	public void renderEnemy(RenderHandler renderer) {
        renderer.renderSprite(_sprite, (int)enemy_x, (int)enemy_y);
    }
	
	public void move_right(double my_speed)
	{
		enemy_x = enemy_x + my_speed;
	}
	
	public void move_left(double my_speed)
	{
		enemy_x = enemy_x - my_speed;
	}
	
	public void move_up(double my_speed)
	{
		enemy_y -= my_speed;
	}
	public void move_down(double my_speed)
	{
		enemy_y += my_speed;
	}
	
	
	
	public double Enemy_X()
    {
    	return enemy_x;
    }
    public double Enemy_Y()
    {
    	return enemy_y;
    }
    
    
    public int _getwidth()
    {
        return enemy_width;
    }
    public int _getHeight()
    {
        return enemy_height;
    }
    
    public int _poistionV()
    {
        return _nr_i_vektorit;
    }
    
    /* Added by ADEM 
     * 
     * 
     * 
     */
    
    
    //Move and Follow my character by an enemy
    
    
    
    
    public boolean checkleft( Object a )
    {
            if(enemy_x<=(a.getX()+a.getWidth()+_tolerance_range) && ((enemy_x+_tolerance_range)>a.getX()) && (enemy_y+enemy_height)>a.getY() && enemy_y<(a.getY()+a.getHeight()))
                  return true;
            
        return false;
    }

    public boolean checkright(Object a)
    {
        if((enemy_x+enemy_width+_tolerance_range)>a.getX() && ((enemy_x)<=a.getX()+_tolerance_range) && (enemy_y+enemy_height)>a.getY() && enemy_y<(a.getY()+a.getHeight()))
               return true;
        
        return false;
    }

    public boolean checkup(Object a)
    {
        if(enemy_y<=(a.getY()+a.getHeight()+_tolerance_range) && ((enemy_y+_tolerance_range)>=a.getY()) && (enemy_x+enemy_width)>a.getX() && (enemy_x)<(a.getX()+a.getWidth()))
            return true;
        
        return false;
    }

    public boolean checkdown(Object a)
    {
        if((enemy_y+enemy_height+_tolerance_range)>=(a.getY()) && ((enemy_y)<=a.getY()+_tolerance_range) && (enemy_x+enemy_width)>a.getX() && (enemy_x)<(a.getX()+a.getWidth()))
             return true;
        
        return false;
    }


    public boolean checkObjcollisionRight(Object [] a )
    {
        for(int i=0;i<a.length;i++){
            {_nr_i_vektorit=i;
            if(checkright(a[i])){return true;}}}
        return false;
    }
    public boolean checkObjcollisionLeft(Object [] a )
    {
        for(int i=0;i<a.length;i++){_nr_i_vektorit=i;
             if(checkleft(a[i])){return true;}}

        return false;
    }

    public boolean checkObjcollisionUp(Object [] a )
    {
        for(int i=0;i<a.length;i++){_nr_i_vektorit=i;
            if(checkup(a[i])){return true;}}
        
        return false;
    }

    public boolean checkObjcollisionDown(Object [] a )
    {
        for(int i=0;i<a.length;i++){_nr_i_vektorit=i;
            if(checkdown(a[i])){return true;}}
        
        return false;
    }
    //leviz vetem horizontal
    
    
    public void follow(Enemy _enemy,Player player,Rectangle _barrier)
    {
    	if(return_distance(player) < 180)
    	{
    		//if(!stuck_on_left(_enemy,_barrier))
    			move_enemy(this,player);
    		
    		
    	}
    		

    	
    	
    }
    
    
    public void shoot_bullet(Player player,ThrowBullet _bullet)
    {

    	if(return_distance(player) < 440)
    	{
    		_bullet._generateGraphics(0xFF0000FF);
    		_bullet.throw_bullet(this,player);
    	}
    	else
    		_bullet._generateGraphics(0xFFFA00DC);
    }
    //  -- FUND  ADDED  BY ADEM
    
    
    //Gjen dhe llogarit distancen 
    public double return_distance(Player player)
    {
    	 return Math.sqrt((enemy_x-player.Getx())*(enemy_x-player.Getx()) + (enemy_y-player.Gety())*(enemy_y-player.Gety()));
    }
    
    

}
