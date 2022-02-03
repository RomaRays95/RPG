import java.util.Random;

public class Skeleton extends Monster implements Runnable{
    private Person opponent;

    public Skeleton(Person opponent) {
        super.setHP(100);
        super.setForce(10);
        this.opponent = opponent;
        new Thread(this).start();
    }

    @Override
    public void run() {
        Random random = new Random();
        while (super.getHP() > 0 && opponent.isAlive()){
            attack(opponent);
            sleep(1500 + random.nextInt(1500));
        }
    }

    @Override
    public String toString() {
        return "Skeleton";
    }
}
