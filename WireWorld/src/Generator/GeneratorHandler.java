package Generator;

public class GeneratorHandler extends Thread {
    CellularAutomaton generator;

    int delay;
    static boolean play = false;

    public GeneratorHandler(int delay, BoardAdapter adapter) {
        this.delay = delay;
        generator = new CellularAutomaton(adapter);
    }

    static public void pauseGenerator() {
        play = false;
    }

    static public void playGenerator() {
        play = true;
    }

    public void run() {
        while (true) {
            try {
                while (play) {
                    generator.generateNextFrame();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
