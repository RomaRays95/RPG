import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hero extends Person{
    private String name;
    private int gold;
    private List<Trader.Heals> backpack = new ArrayList<>();

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

    private int exp;

    public Hero() {
        super.setHP(110);
        super.setForce(20);
        this.gold = 500;
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
