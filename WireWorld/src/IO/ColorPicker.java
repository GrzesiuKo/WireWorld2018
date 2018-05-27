package IO;

import Generator.BoardAdapter;

public class ColorPicker {
    static public int[] IntToRGBA( int x, int y, BoardAdapter adapter){
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

    static public int RGBAToInt( int[] px ){
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
