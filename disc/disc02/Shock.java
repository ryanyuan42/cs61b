import javax.net.ssl.SNIHostName;

public class Shock {
    public static int bang;
    public static Shock baby;

    public Shock() {
        this.bang = 100;
    }

    public Shock(int num) {
        this.bang = num;
        baby = starter();
        this.bang += num;
    }

    public static  Shock starter() {
        Shock gear = new Shock();
        return gear;
    }

    public static void shrink(Shock statik) {
        statik.bang -= 1;
    }

    public static void main(String[] args) {
        Shock gear = new Shock(200);
        System.out.println(gear.bang);    // what will this print ?
        shrink(gear);
        shrink(starter());
        System.out.println(gear.bang);    // what will this print ?
    }
}


/**
 * We need to notice that bang and baby are static variables.
 * A static variable is common to all the instances of the class,
 * only a single copy of static variable is created and shared among all the instances.
 */










/**
 * Answer: 400, 99
 */


