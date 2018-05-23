package Generator;

public class GeneratorHandler extends Thread {

    int delay;
    static boolean play = false;

    public GeneratorHandler(int delay) {
        this.delay = delay;
    }

    static public void pauseGenerator() {
        play = false;
    }

    static public void playGenerator() {
        play = true;
    }

    public void run() {
        while (true) {
            while (play) {
                System.out.println("PracujePracujePracuje :D");
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static public void main( String[] args ){
        GeneratorHandler gen = new GeneratorHandler(500);
        //gen.run();
    }
}
