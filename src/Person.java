abstract class Person {
    private int HP;
    private int force;
    private int agility;
    private boolean alive = true;


    public synchronized boolean getDamage(int damage){
        if (HP > 0) {
            HP -= damage;
            if (HP <= 0){
                HP = 0;
                alive = false;
            }
            return true;
        }
        else return false;
    }

    public void attack (Person person){
        synchronized (person) {
            if (person.getDamage(force)) {
                System.out.println(this + " attacked " + person + "(" + person.HP + ")");
                if (!person.alive) System.out.println(person + " died!");
            }
        }
    }

    public void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void upHP(int x){
        this.HP += x;
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
}
