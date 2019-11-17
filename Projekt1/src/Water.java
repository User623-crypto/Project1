public class Water extends Object{
    private static Sprite _sprite = new Sprite("water1.jpg");

    Water(int posx, int posy) {
        super(posx, posy, _sprite);
    }
    @Override
    public int returnType() {
        
        return 1;
    }

}