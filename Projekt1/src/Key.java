public class Key extends Object{
    private static Sprite _sprite = new Sprite("key.png");

    Key(int posx, int posy) {
        super(posx, posy, _sprite);
    }

    @Override
    protected int returnType() {
        
        return 2;
    }
    
    @Override
    protected void disappear() {
        _sprite=null;
    }

    
    
    
    

}