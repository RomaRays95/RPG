import java.util.Random;

public class Goblin extends Monster {
    Random random = new Random();

    public Goblin(Person opponent) {
        super(opponent);
    }

    @Override
    public void run() {
        while (getHP() > 0 && opponent.isAlive()) {
            sleep(500);
            attack(opponent);
            sleep(1200 + random.nextInt(700));
        }
    }

    @Override
    public String toString() {
        return "Goblin" + difficulty + "." + localCountRoundEnemies;
    }

    @Override
    public Monster newMonster() {
        return new Goblin(opponent);
    }
}
