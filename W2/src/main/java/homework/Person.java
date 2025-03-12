package homework;

import java.time.LocalDate;
import java.util.Objects;


/**
 * Abstract person class
 */
public abstract class Person
{
    /**
     * Name of the person
     */
    protected String name;

    /**
     * Date of Birth of the person
     */
    protected LocalDate birthDate;

    /**
     * Constructor for Person
     * @param name String, Name of a student, teacher
     * @param birthDate LocalDate: Date of birth of a student, teacher
     */
    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    /**
     * Constructor for empty Person
     */
    public Person() {
    }

    /**
     * Getter for the person's name
     * @return String, person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the person's name
     * @param name person's name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the persons date of birth
     * @return LocalDate, person's date of birth
     */

    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * setter for the person's date of birth
     * @param birthDate LocalDate: Person's date of birth
     */

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Overwritten method for the toString Method
     * @return String: String containing infos about the class attributes
     */

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    /**
     * Overwritten method of the equals function to prevent same student, teacher, person, project, problem.
     * @param o object
     * @return boolean: checks wether 2 objects of type Person are the same
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(birthDate, person.birthDate);
    }


}