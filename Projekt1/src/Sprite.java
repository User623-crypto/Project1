import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite{
    private int width,height;
    private int[] pixels;


    public Sprite(String path)
    {
        
        BufferedImage image=loadImage(path);
        
        
        width=image.getWidth();
        height=image.getHeight();

        pixels=new int[width*height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int [] getPixels()
    {
        return pixels;
    }

    //!!!Dont use
    private BufferedImage loadImage(String path)
    {
        try {
            BufferedImage loadedImage= ImageIO.read(Game.class.getResource(path)) ;//kthen nje buffered image
            BufferedImage formattedImage=new BufferedImage(loadedImage.getWidth(),loadedImage.getHeight(),BufferedImage.TYPE_INT_RGB);//e formaton imazhin ne llojin RGB
            formattedImage.getGraphics().drawImage(loadedImage,0,0,null);

            return formattedImage; 
            
            
        } catch (IOException e ) {
            // return null;
            e.printStackTrace();
            return null;
         }
       
    }

}