public class project
{
    private String pname;
    private int maxMark;
    private TYPE type;


    public project(String pname, int maxMark, TYPE type)
    {
        this.pname=pname;
        this.maxMark=maxMark;
        this.type=type;
    }

    public project(){}

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "project{" +
                "pname='" + pname + '\'' +
                ", maxMark=" + maxMark +
                ", type=" + type +
                '}';
    }
}