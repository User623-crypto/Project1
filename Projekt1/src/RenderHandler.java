import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

//Takes care of rendering in pixels all the frame and images
public class RenderHandler{

    private BufferedImage view=null;
    private int[] pixels;

    //It makes pixilate screen  !!!Dont Use
    public RenderHandler(int width,int height)
    {
        //Create a Bufferimage that will represent our view 
        view=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);


        //It takes care of transition of pixels??
        pixels=((DataBufferInt) view.getRaster().getDataBuffer()).getData();               
    }

    //It renders the Graphics in the window !!!Dont Use
    public void render(Graphics graphics)
    {
      graphics.drawImage(view, 0, 0, view.getWidth(),view.getHeight(),null);
    }

    //Renders Image
    public void renderImage(BufferedImage image,int xPosition,int yPosition)
    {    
       
     int [] imagePixels=((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
     for(int y=0;y<image.getHeight();y++)
      {
         for(int x=0;x<image.getWidth();x++)
         {
           //setPixels it has the function of the code below in comments
          setPixles(imagePixels[x+y*image.getWidth()], xPosition+x, yPosition+y);
            //imagePixels[x+y*image.getWidth()] eshte vektor i perdorur si matrice ku y ndan vektorin ne pjese ex(0*12+12)(1*12+12)(2*12+12)
            //pixels[(xPosition+x)+(yPosition+y)*view.getWidth()]= imagePixels[x+y*image.getWidth()];  

         }
      }

    }

    //Ignore
    public void renderImage(BufferedImage image,int xPosition,int yPosition,int xZoom,int yZoom)
    {    
       
     int [] imagePixels=((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
    renderArray(imagePixels, image.getWidth(), image.getHeight(), xPosition, yPosition, xZoom, yZoom);

    }

    //Ignore
    public void renderArray(int[] renderPixels,int width,int height,int xPosition,int yPosition,int xZoom,int yZoom)
    {
      for(int y=0;y<height;y++)
      {
         for(int x=0;x<width;x++)
         {
           for(int yZoomPosition=0;yZoomPosition<yZoom;yZoomPosition++)
           {
              for(int xZoomPosition=0;xZoomPosition<xZoom;xZoomPosition++)
              {
                setPixles(renderPixels[x+y*width],(xPosition+(x*xZoom)+xZoomPosition),(yPosition+(y*yZoom)+yZoomPosition));
                
              }
            }
         }
      }
    }
    
    //Helper function to other renderfunctions
    public void renderArray(int[] renderPixels,int width,int height,int xPosition,int yPosition)
    {
      for(int y=0;y<height;y++)
      {
         for(int x=0;x<width;x++)
         {
           
           setPixles(renderPixels[x+y*width],(xPosition+x),(yPosition+y));
                 
         }
      }
    }

    //Sets the pixels
    private void setPixles(int pixel,int x,int y)
    {
      
        int pixelIndex=(x)+(y)*view.getWidth();
        //OutofBounds Check
        if(pixels.length>pixelIndex && pixel!=Game.alpha)
         pixels[pixelIndex]=pixel;
      
      
    }

    //Renders Sprite
    public void renderSprite(Sprite sprite,int xPosition,int yPosition)
    {
        renderArray(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), xPosition, yPosition);
    }

    //Ignore
    public void renderSprite(Sprite sprite,int xPosition,int yPosition,int xZoom,int yZoom)
    {
        renderArray(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), xPosition, yPosition,xZoom,yZoom);
    }

    //Renders an rectangle
    public void renderRectangle(Rectangle rectangle,int xZoom,int yZoom)
    {
        int[] rectanglePixels=rectangle.getPixels();
        if(rectanglePixels!=null)
          renderArray(rectanglePixels, rectangle.w, rectangle.h, rectangle.x, rectangle.y,xZoom,yZoom);
    }
    
    public void renderBullet(ThrowBullet _bullet,int xZoom,int yZoom)
    {
        int[] rectanglePixels=_bullet.getPixels();
        if(rectanglePixels!=null)
          renderArray(rectanglePixels, _bullet._w, _bullet._h, _bullet._x, _bullet._y,xZoom,yZoom);
    }
}