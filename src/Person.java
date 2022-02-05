import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Person {
    private int force;
    private int agility;
    private boolean alive = true;
    Random random = new Random();
    // Атомик, т.к. кроме получения урона есть и другие методы в потоках, которые влияют на этот параметр.
    private final AtomicInteger HP = new AtomicInteger();


    public synchronized boolean getDamage(int damage) {
        if (HP.get() > 0) {
            plusHP(-damage);
            if (HP.get() <= 0) {
                setHP(0);
                alive = false;
            }
            return true;
        } else return false;
    }

    public void attack(Person person) {
        if (this.alive) {
            if (3 * agility >= random.nextInt(100)) {
                if (person.getDamage(force)) {
                    System.out.println(this + " attacked " + person + "(" + person.HP.get() + ")");
                    if (!person.alive) System.out.println(person + " died!");
                }
            } else {
                if (person.alive) System.out.println(this + " miss");
            }
        }
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getHP() {
        return HP.get();
    }

    public void setHP(int i) {
        HP.set(i);
    }

    public void plusHP(int i) {
        HP.addAndGet(i);
    }
}
