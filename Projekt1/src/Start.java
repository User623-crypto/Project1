import java.lang.Thread;
public class Start{
    public static void main(String[] args) {
        Game a=new Game();
        Thread gamethread=new Thread(a);//thread ekzekuton nje run dhe duhet te marri parameter
        gamethread.start();
    }
}