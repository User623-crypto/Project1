
public class Player {
    private int posx, posy, hp;
    Sprite _sprite; 
    int speed=10;
    int up,down,left,right;

    Player(int x, int y, int h, Sprite sprite) {
        posx = x;
        posy = y;
        hp = h;
        _sprite = sprite;

        
    }


    public void renderPlayer(RenderHandler renderer) {
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
    

    public void setSprite(Sprite sprite)
    {
        _sprite=sprite;
    }
    

    
}