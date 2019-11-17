
public class Border{

    private int _width,_height;
    private Sprite wall;
     Border(int Width,int Height)
    {
        _width=Width;
        _height=Height;
        wall=new Sprite("wall.png");
        
    }
    void loadBorder(RenderHandler renderer)
    {
         //****Muri lart */
         for(int i=0;i<_width;i=i+16)
         {
             
                 renderer.renderSprite(wall, i, 0);
                 
         } 
        //**Murin majtas */
        for(int j=16;j<_height;j=j+16)
        {
            
                renderer.renderSprite(wall, 0, j);         
        }  

        //Muri Djathtas
        for(int j=16;j<_height;j=j+16)
        {
            
                renderer.renderSprite(wall,(_width-16), j);
                
                
        } 
        //****Muri poshte */
        for(int i=16;i<_width;i=i+16)
        {
            
                renderer.renderSprite(wall, i, _height-16);
                
        } 
        

        
    }
}