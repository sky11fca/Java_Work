package homework;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * Professor class derived from person
 */
public class Teacher extends Person
{
    /**
     * Array of projects proposed by the professor
     */
    private Project[] projectsProposed;

    /**
     * Constructor for the object of type Teacher
     * @param name String Name of the professor
     * @param birthDate LocalDate Professor's date of birth
     * @param projectsProposed Project[] Array of projects proposed by the professor
     */
    public Teacher(String name, LocalDate birthDate, Project[] projectsProposed) {
        super(name, birthDate);
        this.projectsProposed = projectsProposed;
    }

    /**
     * Getter for the projects proposed by the professor
     * @return Project[]: array of proposed projects
     */
    public Project[] getProjectsProposed() {
        return projectsProposed;
    }

    /**
     * setter for the proposed projects
     * @param projectsProposed Project[]: projectProposed
     */
    public void setProjectsProposed(Project[] projectsProposed) {
        this.projectsProposed = projectsProposed;
    }

    /**
     * overwritten toString method
     * @return String that contains info about the object of type Teacher: Name of the projects proposed, name, date of birth
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("[");
        for (Project project : projectsProposed)
        {
            builder.append(project.getName()).append(", ");
        }

        if(projectsProposed.length>0)
        {
            builder.setLength(builder.length()-2);
        }

        builder.append("]");
        return "Teacher{" +
                "projectsProposed=" + builder +
                '}' + super.toString();
    }

    /**
     * Overwritten equals method to prevent same objects of type Teacher
     * @param o object
     * @return boolean: wether given teacher as parameter is the same as this teacher
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.deepEquals(projectsProposed, teacher.projectsProposed);
    }

}