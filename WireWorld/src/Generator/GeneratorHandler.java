package Generator;

public class GeneratorHandler extends Thread {
    CellularAutomaton generator;

    int delay;
    static  boolean play = false;
    private volatile boolean running = true;

    public GeneratorHandler(int delay, BoardAdapter adapter) {
        this.delay = delay;
        generator = new CellularAutomaton(adapter);
    }
    public void terminate(){
        pauseGenerator();
        running = false;
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static public void pauseGenerator() {
        play = false;
    }

    static public void playGenerator() {
        play = true;
    }

    public void run() {
        while (running) {
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
