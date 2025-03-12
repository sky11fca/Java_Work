package labWork;

public class studentInstance {
    private students student;
    private project project;

    public studentInstance(students student, project project)
    {
        this.student = student;
        this.project = project;
    }

    public studentInstance() {}

    public students getStudent() {
        return student;
    }

    public void setStudent(students student) {
        this.student = student;
    }

    public project getProject() {
        return project;
    }

    public void setProject(project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "studentInstance{" +
                "student=" + student.toString() +
                ", project=" + project.toString() +
                '}';
    }
}
