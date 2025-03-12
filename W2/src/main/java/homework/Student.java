package homework;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class student derived from Person
 */

public class Student extends Person
{
    /**
     * The id of the student
     */
    private Long regNumber;
    /**
     * An array of the Student projects wants to acomplish
     */
    private Project[] preferedProjects;

    /**
     * Constructor of the object of type student
     * @param name String: Name of the student
     * @param birthDate LocalData: Student's date of birth
     * @param regNumber Long: Student's id
     * @param preferedProjects Project[]: array of projects the student wants
     */
    public Student(String name, LocalDate birthDate, Long regNumber,  Project[] preferedProjects)
    {
        super(name, birthDate);
        this.regNumber = regNumber;
        this.preferedProjects = preferedProjects;
    }

    /**
     * Getter for the student ID
     * @return Long: Student id
     */

    public Long getRegNumber() {
        return regNumber;
    }

    /**
     * Setter for the student id
     * @param regNumber Long: Student id
     */

    public void setRegNumber(Long regNumber) {
        this.regNumber = regNumber;
    }

    /**
     * Getter for the array of projects student desires
     * @return Project[]: Array of prefered projects
     */

    public Project[] getPreferedProjects() {
        return preferedProjects;
    }

    /**
     * Setter for the array of prefered projects
     * @param preferedProjects Project[]: The array of project the student desires
     */

    public void setPreferedProjects(Project[] preferedProjects) {
        this.preferedProjects = preferedProjects;
    }

    /**
     * Overwritten toString method
     * @return String: Information about the student: Desired project name, id, name, dob
     */
    @Override
    public String toString() {
        StringBuilder projectString = new StringBuilder("[");
        for(Project project : preferedProjects)
        {
            projectString.append(project.getName()).append(", ");
        }

        if(preferedProjects.length > 0)
        {
            projectString.setLength(projectString.length()-2);
        }

        projectString.append("]");

        return "Student{" +
                "registrationNumber=" + regNumber +
                ", acceptableProjects=" + projectString +
                "} " + super.toString();
    }

    /**
     * Overwritten equals method to prevent same student object
     * @param o object
     * @return boolean: wether the given object is the same of this object (by the student id)
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(regNumber, student.regNumber);
    }

}
