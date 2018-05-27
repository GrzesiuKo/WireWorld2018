package IO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Board.Template;
import Generator.BoardAdapter;

public class IO {

    public Template readTemplate(File file){
        ArrayList<Integer> list = new ArrayList<Integer>();

        int[][] matrix = fileToIntMatrix(file);
        if( matrix == null)
            return null;

        list = intMatrixToArrayList( matrix, list);
        Template template = new Template(matrix.length, matrix[0].length, list);
        template.setName(file.getName());

        return template;
    }

    private ArrayList<Integer> intMatrixToArrayList( int[][] matrix, ArrayList<Integer> list ){
        for( int y=0; y < matrix[0].length; y++){
            for( int x=0; x < matrix.length; x++){
                list.add(matrix[x][y]);
            }
        }
        return list;
    }

    public void readBoardConfiguration(File file, BoardAdapter adapter){
        int[][] matrix = fileToIntMatrix(file);
        if( matrix == null)
            return;
        updateBoard(matrix, adapter);
    }

    private void updateBoard(int[][] matrix, BoardAdapter adapter){
        for (int y = 0; y < adapter.getHeight() && y < matrix[0].length; y++) {
            for (int x = 0; x < adapter.getWidth() && x < matrix.length; x++) {
                adapter.setCellStateAt(x, y, matrix[x][y]);
            }
        }
    }

    public void saveBoard(File file, BoardAdapter adapter){
        try {
            BufferedImage img = new BufferedImage(adapter.getWidth(), adapter.getHeight(), BufferedImage.TYPE_INT_ARGB);
            WritableRaster raster = img.getRaster();

            int[][][] RGBAMatrix = boardToRGBAMatrix( adapter );

            updateRaster( RGBAMatrix, raster);

            ImageIO.write(img, "png", file.getAbsoluteFile());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateRaster( int[][][] matrix, WritableRaster raster){
        for( int y =0; y < matrix[0].length; y++){
            for( int x = 0; x < matrix.length; x++){
                raster.setPixel(x, y, matrix[x][y]);
            }
        }
    }

    private int[][][] boardToRGBAMatrix( BoardAdapter adapter){
        int[][][] RGBAMatrix = new int[adapter.getWidth()][adapter.getHeight()][4];
        for( int y =0; y < RGBAMatrix[0].length; y++){
            for( int x = 0; x < RGBAMatrix.length; x++){
                RGBAMatrix[x][y] = ColorPicker.IntToRGBA(x, y, adapter);
            }
        }
        return  RGBAMatrix;
    }

    public int[][] fileToIntMatrix(File file){
        try {
            if( !file.exists())
                return null;
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
                    matrix[x][y] = ColorPicker.RGBAToInt( tmpPixel );
                }
            }
            return matrix;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
