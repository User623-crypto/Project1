
public class Map1{

    private int _width,_height;
    private Sprite _sprite;
     Map1(int Width,int Height,Sprite sprite)
    {
        _width=Width;
        _height=Height;
        _sprite = sprite;
    }
    void loadMap(RenderHandler renderer)
    {


        for(int i=16;i<_width-16;i=i+16)
        {
            for(int j=16;j<_height-16;j=j+16)
            {
                renderer.renderSprite(_sprite, i, j);
                
            }
        }  
    }
}