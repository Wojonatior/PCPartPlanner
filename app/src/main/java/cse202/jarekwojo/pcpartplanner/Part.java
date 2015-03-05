package cse202.jarekwojo.pcpartplanner;

/**
 * Created by Wojonatior on 3/5/15.
 */
public class Part {
    private String name;
    private String description;

    public Part(String n, String d){
        this.name = n;
        this.description = d;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
