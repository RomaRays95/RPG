import java.util.Random;

public class Skeleton extends Monster implements Runnable {

    public Skeleton(Person opponent) {
        super(opponent);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (super.getHP() > 0 && opponent.isAlive()) {
            sleep(500);
            attack(opponent);
            sleep(1000 + random.nextInt(1500));
        }
    }

    @Override
    public String toString() {
        return "Skeleton" + difficulty + "." + localCountRoundEnemies;
    }

    @Override
    public Monster newMonster() {
        return new Skeleton(opponent);
    }

}
