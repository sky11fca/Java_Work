import java.time.LocalDate;

public class Main
{
    public static void main(String[] args)
    {
        students s1 = new students();

        s1.setName("Jennifer Rose");
        s1.setBirthdate(LocalDate.of(2002, 3, 19));
        s1.setRegNumber(123456789l);
        System.out.println(s1.getName());

        students s2 = new students("Sarah H. Rose");
        System.out.println(s2.getName());

        students s3 = new students("Jennifer Rose");

        System.out.println(s1==s3);
        System.out.println(s1.equals(s3));

        System.out.println(s1.toString());

        project p1 = new project("Graph Visualiser", 10, TYPE.PRACTICAL);

        System.out.println(p1.toString());

        project p2 = new project();

        p2.setPname("Personal OS");
        p2.setMaxMark(10);
        p2.setType(TYPE.THEORETICAL);

        System.out.println(p2.equals(p1));

        p2=p1;

        System.out.println(p2.equals(p1));

    }
}