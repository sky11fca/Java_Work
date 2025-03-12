package homework;

import java.time.LocalDate;

/**
 * The main program
 */
public class Main
{
    /**
     * The program's entry point
     * @param args String[]: A list of arguments given by user
     */
    public static void main(String[] args)
    {


        //declaring objects of type project
        Project p1 = new Project("Graph Analyser", ProjectType.THEORETICAL);
        Project p2 = new Project("Simple Chat Application", ProjectType.PRACTICAL);
        Project p3 = new Project("Encryption Algorithm", ProjectType.THEORETICAL);
        Project p4 = new Project("PBInfo Like Website", ProjectType.PRACTICAL);
        Project p5 = new Project("A platformer game", ProjectType.PRACTICAL);
        Project p6 = new Project("A game in 3 Dimensions", ProjectType.PRACTICAL);
        Project p7 = new Project("Cryptocurrency", ProjectType.THEORETICAL);
        Project p8 = new Project("Operating System", ProjectType.PRACTICAL);


        //Declaring objects of type Student
        Student s1 = new Student("Jennifer Rose", LocalDate.of(2002, 3, 19), 1L, new Project[]{p1, p2});
        Student s2 = new Student("John Pork", LocalDate.of(1999, 2, 28), 2L, new Project[]{p1, p4});
        Student s3 = new Student("Vasile Popescu", LocalDate.of(2000, 1, 1), 3L, new Project[]{p5, p6});
        Student s4 = new Student("Ion Popescu", LocalDate.of(2001, 2, 2), 4L, new Project[]{p7, p8});

        //Declaring objects of type Teacher
        Teacher t1 = new Teacher("Professor1 Java", LocalDate.of(1999, 1, 1), new Project[]{p1, p3});
        Teacher t2 = new Teacher("Professor2 Website", LocalDate.of(2000, 2, 1), new Project[]{p2, p4});
        Teacher t3 = new Teacher("Professor3 GameDev", LocalDate.of(2000, 3, 1), new Project[]{p5, p6});
        Teacher t4 = new Teacher("Professor4 Other", LocalDate.of(2000, 4, 1), new Project[]{p7, p8});

        //Declaring the assignation problem
        Problem problem = new Problem(new Student[]{s1, s2, s3, s4}, new Teacher[]{t1, t2, t3, t4});

        //Printing out the persons that contributed to the problem
        for (Person person:problem.getAllPersons())
        {
            System.out.println(person);
        }

        //Solving the assignation problem
        Solution sol = new Solution(problem);

        String[] result = sol.solver();

        for(String s:result)
        {
            System.out.println(s);
        }


    }

}
