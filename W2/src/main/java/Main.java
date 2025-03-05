import java.time.LocalDate;

public class Main
{
    public static void main(String[] args)
    {

        students student1 = new students();

        student1.setName("Jennifer Rose");
        student1.setBirthdate(LocalDate.of(2002, 3, 19));
        student1.setRegNumber(123_456_789_0L);

        System.out.println("Name: " + student1.getName() + "\nBirthdate: "+ student1.getBirthdate() + "\nID: "+student1.getRegNumber());

        students student2 = new students("John Doe", LocalDate.of(1990, 1, 1), null);

        System.out.println(student1.toString());
        System.out.println(student2.toString());


        project p1 = new project();

        p1.setProjectName("Generic Project Name");
        p1.setMaxMark(10);
        p1.setType(projectType.PRACTICAL);

        project p2 = new project("Generic Project Name", 10, projectType.THEORETICAL);

        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}