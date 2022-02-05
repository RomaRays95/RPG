import java.util.*;

public class Hero extends Person {
    private int maxHealth;
    private String name;
    private int gold;
    private final Queue<Trader.Heals> backpack = new LinkedList<>();
    private int exp;
    private int lvl;
    //Время нужно чтобы ограничить употребление зелий во время боя.
    static long currentTime;
    private int regeneration;

    public Hero() {
        setHP(200);
        setForce(20);
        this.exp = 0;
        this.lvl = 1;
        this.gold = 150;
        this.maxHealth = 200;
        this.regeneration = 2;
        setAgility(25);
        currentTime = System.currentTimeMillis();
        do {
            System.out.println("Give a name to your hero. Min 3 letters.");
            Scanner sc = new Scanner(System.in);
            this.name = sc.nextLine();
        } while (name.length() <= 2 || !(name.matches("[a-zа-яA-ZА-ЯёЁ]+")));
    }

    public void upHP(int x) {
        plusHP(x);
        if (getHP() > maxHealth) setHP(maxHealth);
    }

    public void drinkPotion() {
        try {
            upHP(backpack.remove().getPointsHeal());
        } catch (Exception e) {
            System.out.println("Your backpack is empty. Go first to a trader.");
        }
    }


    public void drinkPotionAtFight(Thread threadOfFight) {
        upHP(regeneration);
        if ((double) getHP() / maxHealth < 0.33) {
            if (((System.currentTimeMillis() - currentTime) >= 10000)
                    && (!backpack.isEmpty())) {
                int duration = backpack.remove().getDurationSec();
                new Thread(() -> {
                    System.out.println(this + " is drinking a potion.");
                    for (int i = 0; i < duration; i++) {
                        upHP(50);
                        try {
                            if (threadOfFight.isAlive()) Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Healing ended. HP = " + getHP());
                    currentTime = System.currentTimeMillis();
                }).start();
            }
        }
    }

    private void lvlUp(){
        lvl++;
        maxHealth += 10 + 2 * lvl;
        setForce(getForce() + 2);
        setAgility(getAgility() + 1);
        plusHP(30);
        regeneration = lvl;
    }

    public void receiveExp(int x) {
        exp += x;
        if (exp >= (20 + 10 * lvl)) {
            exp -= 20 + lvl * 10;
            lvlUp();
            System.out.println(this + " upped " + lvl + " level.");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void addPotion(Trader.Heals potion) {
        backpack.add(potion);
    }

    public int getGold() {
        return gold;
    }

    public void pay(int cost) {
        gold -= cost;
    }

    public void receiveGold(int coins) {
        gold += coins;
    }

}
