package Tests;

public class Test {
    public static void main ( String[] args ){
        BoardAdapterTest boardAdapterTest = new BoardAdapterTest();
        CellularAutomationTest cellTest = new CellularAutomationTest();
        RulesTest rulesTest = new RulesTest();
        NeighboursTest neighboursTest = new NeighboursTest();
        StatusIndicatorsTest statusTest = new StatusIndicatorsTest();

        boardAdapterTest.test();
        cellTest.test();
        rulesTest.test();
        neighboursTest.test();
        statusTest.test();
    }
}
