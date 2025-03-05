import java.time.LocalDate;

public class students
{
    private String name;
    private LocalDate birthDate;
    private Long regNumber;

    public students(){}

    public students(String name, LocalDate birthDate, Long regNumber)
    {
        this.name = name;
        this.birthDate = birthDate;
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthDate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthDate = birthdate;
    }

    public Long getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(Long regNumber) {
        this.regNumber = regNumber;
    }


    @Override
    public String toString() {
        return "students{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", regNumber=" + regNumber +
                '}';
    }
}