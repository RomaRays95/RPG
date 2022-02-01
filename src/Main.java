import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero();
        List<Person> ar1= new ArrayList<>();
        ar1.add(hero1);
        System.out.println(ar1);
        System.out.println(hero1);
    }
}
