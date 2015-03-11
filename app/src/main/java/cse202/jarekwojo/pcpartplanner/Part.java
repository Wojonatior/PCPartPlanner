package cse202.jarekwojo.pcpartplanner;

import java.util.Date;

/**
 * Created by Wojonatior on 3/5/15.
 */
public class Part {
    private String name;
    private String type;
    private Date warrantyStart;

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

    public void setWarrantyStart(Date warrantyStart) {
        this.warrantyStart = warrantyStart;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Date getWarrantyStart() {
        return warrantyStart;
    }
}
