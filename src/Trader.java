import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trader extends Person{
    public List<Heals> whatWeHave = new ArrayList<>();
    public Trader() {
        new Shop();
    }


    class Shop{

        Random random = new Random();

        public Shop() {
            for (int i = 0; i < 4; i++) {
                whatWeHave.add(new Heals(random.nextInt(3)+1));
            }
        }
    }
    static class Heals{
        private int pointsHeal;
        private int cost;
        private int durationSec;

        public int getPointsHeal() {
            return pointsHeal;
        }

        public Heals(int var) {
            switch (var){
                case 1 -> {
                    this.pointsHeal = 100;
                    this.cost = 100;
                    this.durationSec = 2;
                }
                case 2 -> {
                    this.pointsHeal = 200;
                    this.cost = 300;
                    this.durationSec = 4;
                } case 3 -> {
                    this.pointsHeal = 300;
                    this.cost = 500;
                    this.durationSec = 6;
                }
            }
        }

        public int getCost() {
            return cost;
        }

        public int getDurationSec() {
            return durationSec;
        }

        @Override
        public String toString() {
            return "Potion heal on " + pointsHeal + " HP and cost " + cost + " coins";
        }

    }
}