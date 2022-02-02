import java.util.Scanner;

public class Hero extends Person{
    private String name;
    private int gold;
    private int exp;

    public Hero() {
        super.setHP(110);
        do {
            System.out.println("Give a name to your hero. Min 3 letters.");
            Scanner sc = new Scanner(System.in);
            this.name = sc.nextLine();
        } while (name.length() <=2 || !(name.matches("[a-zа-яA-ZА-ЯёЁ]+")));
    }

    @Override
    public String toString() {
        return name;
    }
}
