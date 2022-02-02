import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Fighting {
    Person hero;
    Queue<Monster> monsters;
    Random random = new Random();


    public Fighting(Person hero1) {
        monsters = new LinkedList<>();
        this.hero = hero1;
        monsters.add(createRandomMonster());
        monsters.add(createRandomMonster());
        monsters.add(createRandomMonster());
        monsters.add(createRandomMonster());
    }

    private Monster createRandomMonster(){
        if (random.nextBoolean()) return new Skeleton(hero);
        else return new Goblin(hero);
    }
}
