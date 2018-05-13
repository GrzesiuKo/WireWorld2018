import javax.imageio.ImageIO;
import java.awt.desktop.SystemEventListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class FileReader {

    public static void main ( String []args){
        BufferedImage img = null;
        try {
            BufferedImage image = ImageIO.read(new File("configuration.png"));

            Raster rast = image.getData();
            int[] siemka = new int[10];
            rast.getPixel(19,0,siemka);

            for( int x: siemka){
                System.out.println("XX:  " + x);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
