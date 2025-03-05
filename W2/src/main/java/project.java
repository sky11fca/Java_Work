public class project
{
    private String projectName;
    private int maxMark;
    private projectType type;


    public project(String projectName, int maxMark, projectType type)
    {
        this.projectName=projectName;
        this.maxMark=maxMark;
        this.type=type;
    }

    public project(){}

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    public projectType getType() {
        return type;
    }

    public void setType(projectType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "project{" +
                "projectName='" + projectName + '\'' +
                ", maxMark=" + maxMark +
                ", type=" + type +
                '}';
    }
}