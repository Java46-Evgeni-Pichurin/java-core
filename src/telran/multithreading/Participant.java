package telran.multithreading;

public class Participant extends Thread{

    private static final int MIN = 2;
    private static final int MAX = 5;
    private final int ID;
    private final int distance;

    public Participant(int ID, int distance){
        this.ID = ID;
        this.distance = distance;
    }

    @Override
    public void run() {
        for (int i = 0; i < distance; i++) {
            System.out.println(ID);
            try {
                sleep((int) (Math.random() * (1 + MAX)) + MIN);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
