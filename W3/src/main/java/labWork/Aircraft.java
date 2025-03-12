package labWork;

import java.io.Serializable;

abstract class Aircraft
{
    protected String name;
    protected String model;

    public Aircraft(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public String toString() {
        return "Aircraft{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                '}'+ super.toString();
    }
}
