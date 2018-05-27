package Tests;

import Generator.Rules;
import static org.assertj.core.api.Assertions.*;

public class RulesTest {
    Rules rules;

    public RulesTest(){
        rules = new Rules();
    }

    public void test(){
        assertThat( rules.getNewState(1,0)).as("Checking if cells change states properly 1.").isEqualTo(1);
        assertThat( rules.getNewState(1,1)).as("Checking if cells change states properly 2.").isEqualTo(3);
        assertThat( rules.getNewState(1,2)).as("Checking if cells change states properly 3.").isEqualTo(3);
        assertThat( rules.getNewState(1,3)).as("Checking if cells change states properly 4.").isEqualTo(1);
        assertThat( rules.getNewState(1,4)).as("Checking if cells change states properly 5.").isEqualTo(1);
        assertThat( rules.getNewState(1,-4)).as("Checking if cells change states properly 6.").isEqualTo(1);

        assertThat( rules.getNewState(0,5)).as("Checking if cells change states properly 7.").isEqualTo(0);
        assertThat( rules.getNewState(0,0)).as("Checking if cells change states properly 8.").isEqualTo(0);
        assertThat( rules.getNewState(0,-100)).as("Checking if cells change states properly 9.").isEqualTo(0);

        assertThat( rules.getNewState(2,1)).as("Checking if cells change states properly 10.").isEqualTo(1);
        assertThat( rules.getNewState(2,10)).as("Checking if cells change states properly 11.").isEqualTo(1);
        assertThat( rules.getNewState(2,-110)).as("Checking if cells change states properly 12.").isEqualTo(1);

        assertThat( rules.getNewState(3,0)).as("Checking if cells change states properly 13.").isEqualTo(2);
        assertThat( rules.getNewState(3,32)).as("Checking if cells change states properly 14.").isEqualTo(2);
        assertThat( rules.getNewState(3,-123)).as("Checking if cells change states properly 15.").isEqualTo(2);
    }

    static public void main( String[] args ){
        RulesTest rulesT = new RulesTest();
        rulesT.test();
    }
}
