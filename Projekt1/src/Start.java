import java.applet.Applet;
import java.lang.Thread;
public class Start extends Applet{
   
    public static void main(String[] args) {
        Game a=new Game();
        Thread gamethread=new Thread(a);//thread ekzekuton nje run dhe duhet te marri parameter
        gamethread.start();
    }
}

/*
	<applet code="Start.class" width="300" height="300"> 
		</applet>
*/