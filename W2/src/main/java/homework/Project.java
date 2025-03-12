package homework;

import java.util.Objects;

/**
 * Object of type Project
 */
public class Project
{
    /**
     * Name of the project
     */
    private String name;

    /**
     * The type of the project, can be THEORETICAL or PRACTICAL.
     * <p><strong>SEE PROJECT TYPE CLASS!!!</strong></p>
     */
    private ProjectType type;

    /**
     * Constructor for an object of type Project
     * @param name String: Project name
     * @param type ProjectType: Project type
     */
    public Project(String name, ProjectType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Getter for the name of the project
     * @return String: Project name
     */

    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the project
     * @param name String: Project name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the project type
     * @return ProjectType: Project type: THEORETICAL OR PRACTICAL
     */
    public ProjectType getType() {
        return type;
    }

    /**
     * Setter for the project type
     * @param type ProjectType: Project Type
     */
    public void setType(ProjectType type) {
        this.type = type;
    }

    /**
     * Overwritten toString method
     * @return String: All details regarding the project object
     */
    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    /**
     * Overwritten equals method to prevent same projects
     * @param o Object: a random object
     * @return boolean: Checks wether the given object is the same with other one
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) && type == project.type;
    }

}