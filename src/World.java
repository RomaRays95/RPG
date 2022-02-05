import java.util.List;
import java.util.Scanner;

public class World {
    public boolean exit;

    public World(){
        Hero hero = new Hero();
        Trader trader = new Trader();
        exit = true;
        do {
            if (!hero.isAlive()) break;
            printWhatToDo(hero);
            try {
                Scanner sc = new Scanner(System.in);
                int input = sc.nextInt();
                switch (input) {
                    case 1 -> {
                        Fighting fighting = new Fighting(hero);
                        fighting.thread.join();
                        Monster.difficulty++;
                    }
                    case 2 -> {
                        dialog(trader, hero);
                        Monster.difficulty = 1;
                    }
                    case 3 -> {
                        trader = new Trader();
                        dialog(trader, hero);
                        Monster.difficulty = 1;
                    }
                    case 4 -> hero.drinkPotion();
                    case 5 -> exit = false;
                    default -> System.out.println("Repeat");
                }
            } catch (Exception e) {
                System.out.println("Repeat");
            }
        } while (exit);

    }

    private void printWhatToDo(Hero hero) {
        System.out.println(hero + " has " + hero.getHP() + " HP and "
                + hero.getGold() + " coins.");
        System.out.println("What do you want to do?");
        System.out.println("    1. I wanna go to dark forest!");
        System.out.println("    2. I wanna go to trader.");
        System.out.println("    3. I wanna find a new trader");
        System.out.println("    4. I wanna drink a potion");
        System.out.println("    5. I tired. Kill myself!");
        System.out.println("Input number of your choice.");
    }

    private void dialog(Trader trader, Hero hero) {
        System.out.println("Trader: I have some potions. Which one do you want?");
        System.out.println("1. Nothing. Goodbye.");
        for (Trader.Heals heal : trader.whatWeHave) {
            System.out.println(trader.whatWeHave.indexOf(heal) + 2 + ". " + heal);
        }
        try {
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            switch (input) {
                case 1 -> System.out.println();
                case 2 -> buying(trader.whatWeHave, 2, hero);
                case 3 -> buying(trader.whatWeHave, 3, hero);
                case 4 -> buying(trader.whatWeHave, 4, hero);
                case 5 -> buying(trader.whatWeHave, 5, hero);
                default -> throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Trader: I don't have this potion. Choose other.");
            dialog(trader, hero);
        }
    }
    private void buying(List<Trader.Heals> list, int index, Hero hero){
        if (hero.getGold() >= list.get(index - 2).getCost()){
            hero.pay(list.get(index-2).getCost());
            hero.addPotion(list.remove(index-2));
            System.out.println("Purchase completed.");
        } else {
            System.out.println("Not enough gold.");
        }
    }
}
