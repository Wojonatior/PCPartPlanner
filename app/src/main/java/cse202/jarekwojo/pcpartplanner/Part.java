package cse202.jarekwojo.pcpartplanner;

import java.util.Date;

/**
 * Created by Wojonatior on 3/5/15.
 */
public class Part {
    private String name, type;
    private Date date;
    private Date warrantyStart;
    //Intent key strings
    public static String nameExtra = "nameExtra",
                         typeExtra = "typeExtra",
                         dateExtra = "dateExtra";

    public Part(String n, String d, Date date){
        this.name = n;
        this.type = d;
        this.date = date;
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
