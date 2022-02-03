import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class World {
    public boolean exit;
    public World() throws InterruptedException {
        Hero hero = new Hero();
        exit = true;
        do {
            if (!hero.isAlive()) break;
            System.out.println("Comands:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            switch (input){
                case 1 -> {
                    Fighting fighting = new Fighting(hero);
                    fighting.thread.join();
                }
                case 2 -> System.out.println("go to trader");
                case 3 -> exit = false;
                default -> System.out.println("Repeat");
            }
        } while (exit);

    }
}
