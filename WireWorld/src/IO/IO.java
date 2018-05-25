package IO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import Generator.BoardAdapter;

public class IO {

    public void readBoardConfiguration(File file, BoardAdapter adapter){
        int[][] matrix = loadFileToIntMatrix(file);
        if( matrix == null)
            return;
        updateBoard(matrix, adapter);
    }

    public void saveBoard(File file, BoardAdapter adapter){
        try {
            BufferedImage img = new BufferedImage(adapter.getWidth(), adapter.getHeight(), BufferedImage.TYPE_INT_ARGB);
            WritableRaster raster = img.getRaster();

            int[][][] RGBAMatrix = loadBoardToRGBAMatrix( adapter );
            for( int y =0; y < RGBAMatrix[0].length; y++){
                for( int x = 0; x < RGBAMatrix.length; x++){
                    raster.setPixel(x, y, RGBAMatrix[x][y]);
                }
            }
            ImageIO.write(img, "png", file.getAbsoluteFile());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private int[][][] loadBoardToRGBAMatrix( BoardAdapter adapter){
        int[][][] RGBAMatrix = new int[adapter.getWidth()][adapter.getHeight()][4];
        for( int y =0; y < RGBAMatrix[0].length; y++){
            for( int x = 0; x < RGBAMatrix.length; x++){
                RGBAMatrix[x][y] = pickAColorIntToRGBA(x, y, adapter);
            }
        }
        return  RGBAMatrix;
    }

    public int[][] readTemplate(File file){
        return loadFileToIntMatrix(file);
    }

    public int[][] loadFileToIntMatrix(File file){
        try {
            BufferedImage img = ImageIO.read(file);
            if( img == null) {
                System.out.println("Podano zly plik");
                return null;
            }
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
                    matrix[x][y] = pickAColorRGBAToInt( tmpPixel );
                }
            }
            return matrix;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateBoard(int[][] matrix, BoardAdapter adapter){
        for (int y = 0; y < adapter.getHeight() && y < matrix[0].length; y++) {
            for (int x = 0; x < adapter.getWidth() && x < matrix.length; x++) {
                adapter.setCellStateAt(x, y, matrix[x][y]);
            }
        }
    }

    private int[] pickAColorIntToRGBA( int x, int y, BoardAdapter adapter){
        /* R G B A */
        /* red: 230-255, 0-25, 0-25, 230-255 -> 3
        /* yellow: 230-255, 230-255, 0-25, 230-255 -> 2
        /* black: 230-255, 230-255, 230-255, 230-255 -> 1
        /* white: 0-25, 0-25, 0-25, 230-255 -> 0
        */
        int[] pixel = new int[4];
        switch( adapter.getCellStateAt(x,y)){
            case 0:
                pixel[0] = 255;
                pixel[1] = 255;
                pixel[2] = 255;
                pixel[3] = 255;
                break;
            case 1:
                pixel[0] = 0;
                pixel[1] = 0;
                pixel[2] = 0;
                pixel[3] = 255;
                break;
            case 2:
                pixel[0] = 255;
                pixel[1] = 255;
                pixel[2] = 0;
                pixel[3] = 255;
                break;
            case 3:
                pixel[0] = 255;
                pixel[1] = 0;
                pixel[2] = 0;
                pixel[3] = 255;
                break;
            default:
                pixel[0] = 0;
                pixel[1] = 0;
                pixel[2] = 0;
                pixel[3] = 0;
                break;
        }
        return pixel;
    }

    private int pickAColorRGBAToInt( int[] px ){
        /* R G B A */
        /* red: 230-255, 0-25, 0-25, 230-255 -> 3
        /* yellow: 230-255, 230-255, 0-25, 230-255 -> 2
        /* black: 230-255, 230-255, 230-255, 230-255 -> 1
        /* white: 0-25, 0-25, 0-25, 230-255 -> 0
        */

        if( (px[0] >= 220)
                && (px[0] <= 255)
                && (px[1] >= 0)
                && (px[1] <= 40)
                && (px[2] >= 0)
                && (px[2] <= 40)
                && (px[3] >= 220)
                && (px[3] <= 255))
            return 3;

        if( (px[0] >= 220)
                && (px[0] <= 255)
                && (px[1] >= 220)
                && (px[1] <= 255)
                && (px[2] >= 0)
                && (px[2] <= 40)
                && (px[3] >= 220)
                && (px[3] <= 255))
            return 2;

        if( (px[0] >= 0)
                && (px[0] <= 40)
                && (px[1] >= 0)
                && (px[1] <= 40)
                && (px[2] >= 0)
                && (px[2] <= 40)
                && (px[3] >= 220)
                && (px[3] <= 255))
            return 1;

        return 0;
    }
}
