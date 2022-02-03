import java.util.*;

public class Hero extends Person{
    private int maxHealth;
    private String name;
    private int gold;
    private Queue<Trader.Heals> backpack = new LinkedList<>();
    private int exp;
    static long currentTime;


    public void drinkPotion(){
        try {
            upHP(backpack.remove().getPointsHeal());
        } catch (Exception e) {
            System.out.println("Your backpack is empty. Go first to a trader.");
        }
        if (getHP() > maxHealth) setHP(maxHealth);
    }


    public void drinkPotionAtFight(Thread threadOfFight){
        if ((double)getHP()/maxHealth < 0.33) {
            if (((System.currentTimeMillis() - currentTime) >= 10000)
            && (!backpack.isEmpty())){
                int duration = backpack.remove().getDurationSec();
                new Thread(() -> {
                    System.out.println(this + " is drinking a potion.");
                    for (int i = 0; i < duration; i++) {
                        upHP(50);
                        if (getHP() > maxHealth) setHP(maxHealth);
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

    public void addPotion(Trader.Heals potion){
        backpack.add(potion);
    }

    public int getGold() {
        return gold;
    }
    public void pay(int cost){
        gold -= cost;
    }

    public void receiveGold(int coins){
        gold += coins;
    }

    public Hero() {
        super.setHP(150);
        super.setForce(20);
        this.gold = 500;
        this.maxHealth = 150;
        currentTime = System.currentTimeMillis();
        do {
            System.out.println("Give a name to your hero. Min 3 letters.");
            Scanner sc = new Scanner(System.in);
            this.name = sc.nextLine();
        } while (name.length() <=2 || !(name.matches("[a-zа-яA-ZА-ЯёЁ]+")));
    }


    @Override
    public String toString() {
        return name;
    }
}
