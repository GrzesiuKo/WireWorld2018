package Generator;

public class GeneratorHandler extends Thread {
    CellularAutomation generator;

    int delay;
    static boolean play = false;
    private volatile boolean running = true;

    public GeneratorHandler(int delay, BoardAdapter adapter) {
        this.delay = delay;
        generator = new CellularAutomation(adapter);
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void terminate() {
        pauseGenerator();
        running = false;
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pauseGenerator() {
        play = false;
    }

    public void playGenerator() {
        play = true;
    }

    public void setDelay( int delay ){
        this.delay = delay;
    }

    public void setBorders( boolean mode ){
        generator.setBorders( mode );
    }

}
