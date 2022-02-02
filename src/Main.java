import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero();
//        new Thread(() -> {
//            while (true){
//                long start = System.currentTimeMillis();
//
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(System.currentTimeMillis() - start);
//            }
//        }).start();
        new Fighting(hero1);
    }
}
