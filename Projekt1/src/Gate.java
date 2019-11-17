public class Gate extends Object{
    private static Sprite _sprite = new Sprite("Gate.png");

    Gate(int posx, int posy) {
        super(posx, posy, _sprite);
    }
    @Override
    public int returnType() {
        
        return 2;
    }

}