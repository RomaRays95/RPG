import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Fighting implements Runnable{
    Person hero;
    Queue<Monster> monsters;
    Random random = new Random();
    Thread thread = new Thread(this);

    public Fighting(Person hero1) {
        monsters = new LinkedList<>();
        this.hero = hero1;
        monsters.add(createRandomMonster());
        monsters.add(createRandomMonster());
        thread.start();
    }

    private Monster createRandomMonster(){
        if (random.nextBoolean()) return new Skeleton(hero);
        else return new Goblin(hero);
    }

    @Override
    public void run() {
        System.out.println("The enemy is found!");
        while (!monsters.isEmpty() && hero.isAlive()){
            hero.attack(monsters.peek());
            if (!monsters.peek().isAlive()) monsters.remove();
            sleep(1000);
        }
        if (hero.isAlive()) System.out.println(hero + " has killed them all!");
        else System.out.println("Happy and short life of " + hero + " has finished :((((");
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
