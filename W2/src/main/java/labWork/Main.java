package labWork;

import java.time.LocalDate;

public class Main
{
    public static void main(String[] args)
    {
        //declaring an empty object
        students student1 = new students();
        //using the setters
        student1.setName("Jennifer Rose");
        student1.setValue(9.0f);
        student1.setRegNumber(123_456_789_0L);
        //getters
        System.out.println("Name: " + student1.getName() + "\nMark: "+ student1.getValue() + "\nID: "+student1.getRegNumber());

        //declaring an object with arguments
        students student2 = new students("John Doe", 10.0f, null);
        //using the overloaded toString() function
        System.out.println(student1.toString());
        System.out.println(student2.toString());

        //project declaration demo
        project p1 = new project();

        //getters and setters
        p1.setProjectName("Generic Project Name");
        p1.setMaxMark(10.0f);
        p1.setType(projectType.PRACTICAL);

        //constructors
        project p2 = new project("Generic Project Name", 10.0f, projectType.THEORETICAL);
        //toString() overload
        System.out.println(p1.toString());
        System.out.println(p2.toString());

        studentInstance s1 = new studentInstance(student1, p1);

        System.out.println(s1.toString());

        studentInstance s2 = new studentInstance();

        s2.setProject(p2);
        s2.setStudent(student2);

        System.out.println("Student: "+s2.getStudent().getName() + "\nWith the grade " + s2.getStudent().getValue() + "\nWants the project with the name: " + s2.getProject().getProjectName() + "\nwith the required mark: " + s2.getProject().getMaxMark());


    }
}