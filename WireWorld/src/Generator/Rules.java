package Generator;

public class Rules {
    public int getNewState( int state, int headsNumber ){
        switch( state ){
            case 0:
                return 0;
            case 1:
                if ( headsNumber == 1 || headsNumber == 2)
                    return 3;
                else
                    return 1;
            case 2:
                return 1;
            case 3:
                return 2;
            default:
                return 0;
        }
    }
}
