import java.util.Random;

abstract class Monster extends Person implements Runnable {
    private int gold;
    private int exp;
    Random random = new Random();
    protected final Person opponent;
    //счетчик глубины леса. Чем дальше - тем сложнее
    public static int difficulty = 1;
    //счетчик призванных мобов за раунд.
    public static int countRoundEnemies;
    protected int localCountRoundEnemies;


    public Monster(Person opponent) {
        setHP(80 + (10 * difficulty));
        setForce(10 + random.nextInt(4) + difficulty * 2);
        setAgility(12 + random.nextInt(6) + difficulty);
        setGold(10 + random.nextInt(10) + 3 * difficulty);
        setExp(15 + 2 * difficulty);
        this.opponent = opponent;
        countRoundEnemies ++;
        localCountRoundEnemies = countRoundEnemies;
        new Thread(this).start();
    }

    abstract public Monster newMonster();

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

}
