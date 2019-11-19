abstract class Object{
    private int _posx;
    private int _posy;
    private Sprite _sprite;
    private int _width;
    private int _height;
    private int type=0;

    Object(int posx,int posy,Sprite sprite)
    {
        _posx=posx;
        _posy=posy;
        _sprite=sprite;
        _width=sprite.getWidth();
        _height=sprite.getHeight();
    }
    public int getHeight()
    {
        return _height;
    }
    public int getWidth()
    {
        return _width;
    }
    public int getX()
    {
        return _posx;
    }
    public int getY()
    {
        return _posy;
    }
    public int type()
    {
        return type;
    }
    public void renderObject(RenderHandler renderer) {
        renderer.renderSprite(_sprite, _posx, _posy);
    }
    protected int returnType()
    {
        return type;
    }
    protected void disappear()
    {
    }
}