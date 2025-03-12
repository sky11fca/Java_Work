package homework;

import java.util.Arrays;
import java.util.Objects;

/**
 * Problem Class
 */
public class Problem {
    /**
     * Array of Students involved in the projects
     */
    private Student[] students;
    /**
     * Array of Professors that assigned the projects
     */
    private Teacher[] teachers;

    /**
     * Problem Constructor
     * @param students: Student[] Array of students
     * @param teachers: Teacher[] Array of teachers
     */
    public Problem(Student[] students, Teacher[] teachers) {
        this.students = students;
        this.teachers = teachers;
    }

    /**
     * Getter for students
     * @return The array of students
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * setter for students
     * @param students Student[]: an array of students
     */

    public void setStudents(Student[] students) {
        this.students = students;
    }

    /**
     * Getter for the array of teachers
     * @return Teacher[]: array of teachers
     */
    public Teacher[] getTeachers() {
        return teachers;
    }

    /**
     * Setter for the teacher array
     * @param teachers Teacher[]: array of teachers
     */
    public void setTeachers(Teacher[] teachers) {
        this.teachers = teachers;
    }

    /**
     * Method that returns all the projects proffesors proposed
     * @return Project[]: all project proposed
     */

    public Project[] getProposedProjects()
    {
        int length = 0;
        int size=0;
        for (int i = 0; i < teachers.length; i++)
        {
            length+=teachers[i].getProjectsProposed().length;
        }

        Project[] projects = new Project[length];

        for (int i = 0; i < teachers.length; i++)
        {
            for(Project project:teachers[i].getProjectsProposed())
            {
                projects[size]=project;
                size++;
            }
        }

        return projects;
    }


    /**
     * Array of persons that consists of all the students and professors involved in this problem of student project assignment
     * @return persons Person[]: array of persons involved
     */

    public Person[] getAllPersons()
    {
        Person[] persons = new Person[students.length + teachers.length];
        System.arraycopy(students, 0, persons, 0, students.length);
        System.arraycopy(teachers, 0, persons, students.length, teachers.length);
        return persons;
    }

    /**
     * Overwritten method of toString()
     * @return String that contains the object info: All informations of every student, professor and project
     */

    @Override
    public String toString() {
        return "Problem{" +
                "students=" + Arrays.toString(students) +
                ", teachers=" + Arrays.toString(teachers) +
                '}';
    }
}