import java.io.EOFException;

public class Enemy extends Follow_Player  {

	
	
	private double enemy_x;
	private double enemy_y;
	private int enemy_height;
    private int enemy_width;
    private int enemy_hp=220;/* Added from R */
	Sprite _sprite;
	Player player;
	double speed = 1;
	public Sprite Invisible  = new Sprite("Invisible_enemy.png");
	public Sprite set_enemy_again = new Sprite("Flash3.png");
	String enemy_type; // Added by Adem
	int _tolerance_range = 8;
	private int _nr_i_vektorit=0;
	Thread my_thread;
    Rectangle _barrier;
    Rectangle _enemyhpline;/*Added by R */
    
    /*
     * 
     * ADDED BY ADEM
     * 
     * LEJON NJE RANGE TE NDRYSHEM PER CDO ENEMY Per levijen dhe gjuajtetjen
     */
    //private final int beginning_X = 1111;
    private String enemy_move_range;
    private String enemy_shoot_range;
    
    //IF BOSS OR NO
    private String if_BOSS;
	Enemy(int x, int y, Sprite sprite,String _enemy_type,String move_range,String shoot_range,String ifboss) {
		enemy_x = x;
		enemy_y = y;
		_sprite = sprite;
		enemy_height=sprite.getHeight();
        enemy_width=sprite.getWidth();
        _enemyhpline=new Rectangle((int)enemy_x,(int)enemy_y,enemy_hp,7);
        _enemyhpline.generateGraphics(0xFFFF0000);
        
        /*
         * ADDED BY ADEM
         */
        enemy_type = _enemy_type;
        enemy_move_range = move_range;
        enemy_shoot_range = shoot_range;
        if_BOSS = ifboss;
        
        if(if_BOSS == "BOSS")
        {
        	enemy_hp = 400;
 
        }
		
		
        
        
	}

    /*ADDED FROM R */
    public int GetHp()
    {
        return enemy_hp;
    }
    public boolean enemydefeated()
    {
    	if(enemy_hp<=0)
    		return true;
    	return false;
    }
    public void reduceHp(int x)
    {
        enemy_hp-=x;
    }

    public void enemy_setSprite(Sprite sprite)
    {
        _sprite=sprite;
    }

    public boolean returnif_boss()
    {
    	if(this.if_BOSS == "BOSS")
    		return true;
    	return false;
    }

    /*************************************************************** */
	public void renderEnemy(RenderHandler renderer) {
        if(enemy_hp>0){
        renderer.renderRectangle(_enemyhpline,(int)enemy_x,(int)enemy_y-10, enemy_hp/3,7);/**Added by R */
        renderer.renderSprite(_sprite, (int)enemy_x, (int)enemy_y);
        }
        else{enemy_x=0;enemy_y=0;}
        //else{_sprite=null; enemy_x=0;enemy_y=0;}
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
    
    
    public void follow(Enemy _enemy,Player player)
    {
    	if(_enemy.enemy_move_range == "Long" && _enemy.enemy_type == "Move_type")
    	{
    		if(return_distance(player) < 300  )
        	{
        		//if(!stuck_on_left(_enemy,_barrier))
        			move_enemy(this,player);
        			
        		
        		
        	}
    	}
    	
    	
    	if(_enemy.enemy_move_range == "Short" && _enemy.enemy_type == "Move_type")
    	{
    		if(return_distance(player) < 160  )
        	{
        		//if(!stuck_on_left(_enemy,_barrier))
        			move_enemy(this,player);
        				
        	}
    	}
    	
    	if(_enemy.enemy_type == "Move_type")
    	{
    		if(return_distance(player) > 450)
    		{
    			_enemy.enemy_setSprite(Invisible);
        		_enemyhpline.generateGraphics(0xFFFA00DC);
    		}
    		
    		else
        	{
        		_enemy.enemy_setSprite(set_enemy_again);
        		 _enemyhpline.generateGraphics(0xFFFF0000);
        	}
    	}
    	
    	
    	
    	

    	
    
    }
    
    
    /*
     * 
     * FUKSIONE DHE VEPRIME VETEM PER BOSS in
     */
    
    
    public void shoot_bullet(Player player,ThrowBullet _bullet)
    {
    	
    	if(this.enemy_shoot_range == "Long_range")
    	{
    		if(return_distance(player) < 400)
        	{
    			//this.enemy_setSprite(new Sprite("FlashAttack1.png"));
        			_bullet._generateGraphics(0xFF0000FF);
            		_bullet.throw_bullet(this,player);
        		
        		
        		
        	}
        	else
        	{
        		//this.enemy_setSprite(new Sprite("Flash3.png"));
        		_bullet._generateGraphics(0xFFFA00DC);
        		_bullet._x = (int)this.Enemy_X() + 32;
        		_bullet._y = (int)this.Enemy_Y() + 32;
        	}
    	}
    	
    	if(this.enemy_shoot_range == "Short_range")
    	{
    		if(return_distance(player) < 200)
        	{
    			//this.enemy_setSprite(new Sprite("FlashAttack1.png"));
        			_bullet._generateGraphics(0xFF0000FF);
            		_bullet.throw_bullet(this,player);
        		
        		
        		
        	}
        	else
        	{
        		//this.enemy_setSprite(new Sprite("Flash3.png"));
        		_bullet._generateGraphics(0xFFFA00DC);
        		_bullet._x = (int)this.Enemy_X() + 32;
        		_bullet._y = (int)this.Enemy_Y() + 32;
        	}
    	}
    }
    //  -- FUND  ADDED  BY ADEM
    
    
    //Gjen dhe llogarit distancen 
    public double return_distance(Player player)
    {
    	 return Math.sqrt((enemy_x-player.Getx())*(enemy_x-player.Getx()) + (enemy_y-player.Gety())*(enemy_y-player.Gety()));
    }
    
    

}
