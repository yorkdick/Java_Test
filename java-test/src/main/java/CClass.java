import com.myself.java.test.ParentClass;

public class CClass extends ParentClass {
    public String a = "a";

    public static void main(String[] args) {
        CClass c = new CClass();

        ParentClass p = (ParentClass) c;

        System.out.println(c.a);
        System.out.println(c.getA());
        System.out.println(p.a);
        System.out.println(p.getA());
    }

    public String getA() {
        return a;
    }
}
