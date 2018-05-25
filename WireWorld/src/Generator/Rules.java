package Generator;

public class Rules {
    public int getNewState( int state, int headsNumber ){
        switch( state ){
            case StatusIndicators.EMPTY:
                return StatusIndicators.EMPTY;
            case StatusIndicators.CONDUCTOR:
                if ( headsNumber == 1 || headsNumber == 2)
                    return StatusIndicators.HEAD;
                else
                    return StatusIndicators.CONDUCTOR;
            case StatusIndicators.TAIL:
                return StatusIndicators.CONDUCTOR;
            case StatusIndicators.HEAD:
                return StatusIndicators.TAIL;
            default:
                return StatusIndicators.EMPTY;
        }
    }
}
