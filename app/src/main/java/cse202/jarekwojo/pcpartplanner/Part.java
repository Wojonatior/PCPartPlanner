package cse202.jarekwojo.pcpartplanner;

/**
 * Created by Wojonatior on 3/5/15.
 */
public class Part {
    private String name;
    private String type;

    public Part(String n, String d){
        this.name = n;
        this.type = d;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
