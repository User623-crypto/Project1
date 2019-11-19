
public class Follow_Player extends Barrier_test {
	

	public Player player;
	Enemy _enemy;
	Thread my_thread;
	
	
	 /* Added by ADEM 
     * 
     * 
     * 
     */
    
    
    //Move and Follow my character by an enemy
    
    //leviz vetem horizontal
    public void move_horizontally(Enemy _enemy,Player player)
    {
    	if(_enemy.Enemy_X() < player.Getx())
		
			move_right(_enemy,player);
		else
			move_left(_enemy,player);
		
    }
    
    public void move_right(Enemy _enemy,Player player)
    {
    
		  
		  while(_enemy.Enemy_X()< player.Getx())
		  {
			  
            try {         	
                Thread.sleep(200);
            	_enemy.move_right(1);
            	} 
            
            catch (Exception ex) {
            	
            }
		  }
    }
    
    public void move_left(Enemy _enemy,Player player)
    {
    	
    
		  
		  while(_enemy.Enemy_X() > player.Getx())
		  {
			  
            try {
            	
            	
            	Thread.sleep(200);
            	
            	_enemy.move_left(1);
            	} 
            
            catch (Exception ex) {
            	
            }
		  }
    }
    
    //leviz vetem vertical
    public void move_vertically(Enemy _enemy,Player player)
    {
    	if(_enemy.Enemy_Y() > player.Gety())
    		move_up(_enemy,player);
    	else
    		move_down(_enemy,player);
    }
    
    public void move_up(Enemy _enemy,Player player)
    {
    
		 while(_enemy.Enemy_Y() > player.Gety())
		  {
             
               try {
               
            	   Thread.sleep(200);
               	
            	   _enemy.move_up(1);
               	} 
               
               catch (Exception ex) {
               	
               }
		  }
    }
    
    public void move_down(Enemy _enemy,Player player)
    {
    	
		 while(_enemy.Enemy_Y() < player.Gety())
		  {
             
               try {
               
               	Thread.sleep(200);
               
               	_enemy.move_down(1);
               	} 
               
               catch (Exception ex) {
               	
               }
		  }
    }
    
    //funksioni qe do te kryeje levizjen e enemy
    public void move_enemy(Enemy _enemy,Player player)
    {
    	//Thread animationThread = new Thread(new Runnable() {
    		//@Override
    	
    	Runnable runnable = new Runnable() {
           // @Override
            public void run() {
            	
            		if(_enemy.Enemy_Y() - player.Gety() < 10 && _enemy.Enemy_Y() - player.Gety() > -10)
                	{

      					  move_horizontally(_enemy,player);
      			     }
                	
                	
                	//kontrollon kushtin per levizjen verticale
                	if(_enemy.Enemy_X() - player.Getx() < 10 && _enemy.Enemy_X() - player.Getx() > -10)
        			  {
        				  
        					  move_vertically(_enemy,player);
        			     }
                	
                	
                	
                //Behet levizja edhe diagonale ne rastin kur enemy ndodhet lart PLAYER-it	
                  if(_enemy.Enemy_Y() < player.Gety())
          		   {
                		 
                	  	//levizja diagonal kur enemy ndodhet lart dhe majtas PLAYER-it
          		           if(_enemy.Enemy_X() < player.Getx())
          		           {
          		        	
          		        	  while(_enemy.Enemy_X() < player.Getx() && _enemy.Enemy_Y() < player.Gety())
          		        	  {
          	                        try {
          	                        	
          	                        	Thread.sleep(200);
          	                        		
          	                        		_enemy.move_right(0.1);
          	                        		_enemy.move_down(0.1);
          	                        		
          	                        	} 
          	                        
          	                        catch (Exception ex) {
          	                        	
          	                        }
          		        	  }
          		        	  
          		           }
          		           
          		           
          		           //levizja diagonal kur enemy ndodhet lart dhe djathtas PLAYEr-it
          		         if(_enemy.Enemy_X() > player.Getx())
        		           {
        		        	
        		        	  while(_enemy.Enemy_X() > player.Getx() && _enemy.Enemy_Y() < player.Gety())
        		        	  {

        	                        try {
        	                        		Thread.sleep(200);
        	                        
        	                        		_enemy.move_left(0.1);
        	                        		_enemy.move_down(0.1);
        	                        		
        	                        	} 
        	                        
        	                        catch (Exception ex) {
        	                        	
        	                        }
        		        	  }
        		           }
          		   }
                	 
                	 
                  //Levizja e enemy kur ndodhet poshte PLAYER- it
                if(_enemy.Enemy_Y() > player.Gety())
            		   {
                  		 	// Poshte majtas
            		           if(_enemy.Enemy_X() < player.Getx())
            		           {
            		        	 
            		        	  while(_enemy.Enemy_X() < player.Getx() && _enemy.Enemy_Y() > player.Gety())
            		        	  {

            	                        try {
            	                        	
            	                        	Thread.sleep(200);
            	                        		
            	                        		_enemy.move_right(0.1);
            	                        		_enemy.move_up(0.1);
            	                        		
            	                        	} 
            	                        
            	                        catch (Exception ex) {
            	                        	
            	                        }
            		        	  }
            		           }
            		           
            		           
            		           //poshte djathtas
            		         if(_enemy.Enemy_X() > player.Getx())
          		           	{
            		        	 
          		        	  while(_enemy.Enemy_X() > player.Getx() && _enemy.Enemy_Y() > player.Gety())
          		        	  {

          	                        try {
          	                        	
          	                        	
          	                        	Thread.sleep(200);
          	                        		
          	                        		_enemy.move_left(0.1);
          	                        		_enemy.move_up(0.1);
          	                        		
          	                        	} 
          	                        
          	                        catch (Exception ex) {
          	                        	
          	                        }
          		        	  }
          		           }
            		   
                }
            	}
        };
        
        my_thread = new Thread(runnable);      
        my_thread.start();

    }
    
    public void on_left(Enemy _enemy,Player _player, Rectangle _barrier)
    {
    	
    }
    
    
    public boolean stuck_on_left(Enemy _enemy,Rectangle _barrier)
	{
		if((((_enemy.Enemy_X() + 180 > _barrier.x) ) && (_enemy.Enemy_Y() + 64 > _barrier.y ) ) /*||
				
				((_enemy.Enemy_X() + 60 > _barrier.x)) && (_enemy.Enemy_Y() < _barrier.y + _barrier.w)*/)
				
				 
			return true;
		return false;
	}
    //  -- FUND  ADDED  BY ADEM
}
