package IO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class IO {

    private int[][] loadFileToIntMatrix(String filename){
        try {
            BufferedImage img = ImageIO.read(new File(filename));
            int width = img.getWidth();
            int height = img.getHeight();
            Raster raster = img.getData();

            /* matrix to be filled with cells' states */
            int[][] matrix = new int[width][height];
            /* temporary array to store data about pixel colour */
            int[] tmpPixel = new int[4];

            for( int y = 0; y < height; y++){
                for( int x = 0; x < width; x++){
                    raster.getPixel( x, y, tmpPixel);
                    matrix[x][y] = pickAColor( tmpPixel );
                }
            }

            printIntMatrix(matrix);
            return matrix;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void readBoardConfiguration(String filename){
        int[][] matrix = loadFileToIntMatrix(filename);
        fillBoard(matrix);
    }

    public void saveBoard(){}
    public void readTemplate(){}

    private void fillBoard(int[][] matrix){
        return;
    }

    private void printIntMatrix( int[][] matrix ){
        System.out.println("pierwszy wymiar: "+matrix.length + " Drugi: "+matrix[0].length);
        for( int y = 0; y < (matrix[0]).length; y++){
            for( int x = 0; x < matrix.length; x++) {
                System.out.print(matrix[x][y] + "");
            }
            System.out.println();
        }
    }

    private int pickAColor( int[] px ){
        /* R G B A */
        /* red: 230-255, 0-25, 0-25, 230-255 -> 3
        /* yellow: 230-255, 230-255, 0-25, 230-255 -> 2
        /* black: 230-255, 230-255, 230-255, 230-255 -> 1
        /* white: 0-25, 0-25, 0-25, 230-255 -> 0
        */

        if( (px[0] >= 230)
                && (px[0] <= 255)
                && (px[1] >= 0)
                && (px[1] <= 25)
                && (px[2] >= 0)
                && (px[2] <= 25)
                && (px[3] >= 230)
                && (px[3] <= 255))
            return 3;

        if( (px[0] >= 230)
                && (px[0] <= 255)
                && (px[1] >= 230)
                && (px[1] <= 255)
                && (px[2] >= 0)
                && (px[2] <= 25)
                && (px[3] >= 230)
                && (px[3] <= 255))
            return 2;

        if( (px[0] >= 0)
                && (px[0] <= 25)
                && (px[1] >= 0)
                && (px[1] <= 25)
                && (px[2] >= 0)
                && (px[2] <= 25)
                && (px[3] >= 230)
                && (px[3] <= 255))
            return 1;

        return 0;
    }

    public static void main ( String []args){
        IO io = new IO();
        io.readBoardConfiguration("MINconfiguration.png");
    }
}