import java.util.Random;

public class Goblin extends Monster{
    private Person opponent;

    public Goblin(Person opponent) {
        super.setHP(100);
        super.setForce(10);
        this.opponent = opponent;
        new Thread(this).start();
    }

    @Override
    public void run() {
        Random random = new Random();
        while (super.getHP() > 0 && opponent.getHP() > 0){
            attack(opponent);
            sleep(1000 + random.nextInt(500));
        }
    }

    @Override
    public String toString() {
        return "Goblin";
    }
}
