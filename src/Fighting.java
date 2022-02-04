import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Fighting implements Runnable{
    Hero hero;
    Queue<Monster> monsters;
    Random random = new Random();
    Thread thread = new Thread(this);

    public Fighting(Hero hero1) {
        Monster.countRoundEnemies = 0;
        monsters = new LinkedList<>();
        this.hero = hero1;
        monsters.add(createRandomMonster());
        thread.start();
    }

    private Monster createRandomMonster(){
        if (random.nextBoolean()) return new Skeleton(hero);
        else return new Goblin(hero);
    }

    private void createTheSameMonster(){
        //вероятность появления монстра
        if (random.nextInt(100) <= 15 - Monster.countRoundEnemies - monsters.size() + 2 * Monster.difficulty) {
            assert monsters.peek() != null;
            Monster newM = monsters.peek().newMonster();
            monsters.add(newM);
            System.out.println("New enemy here! Other " + newM);
            System.out.println(hero + " fighting with " + monsters.size() + " enemies!");
        }
    }

    @Override
    public void run() {
        System.out.println("The enemy is found!");
        sleep();
        while (!monsters.isEmpty() && hero.isAlive()){
            assert monsters.peek() != null;
            hero.attack(monsters.peek());
            createTheSameMonster();
            assert monsters.peek() != null;
            if (!monsters.peek().isAlive()) {
                assert monsters.peek() != null;
                hero.receiveExp(monsters.peek().getExp());
                hero.receiveGold(monsters.remove().getGold());
            }
            hero.drinkPotionAtFight(thread);
            sleep();
        }
        if (hero.isAlive()) System.out.println(hero + " has killed them all!");
        else System.out.println("Happy and short life of " + hero + " has finished :((((");
    }

    private void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
