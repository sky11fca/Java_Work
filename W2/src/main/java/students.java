import java.time.LocalDate;

public class students
{
    private String name;
    private LocalDate birthdate;
    private Long regNumber;

    public students(){}
    public students(String name)
    {
        this(name, null, null);
    }

    public students(String name, LocalDate birthdate, Long regNumber)
    {
        this.name = name;
        this.birthdate = birthdate;
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Long getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(Long regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj==null || !(obj instanceof students)) return false;

        students other = (students) obj;

        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "students{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", regNumber=" + regNumber +
                '}';
    }
}