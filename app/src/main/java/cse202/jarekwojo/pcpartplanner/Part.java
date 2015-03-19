package cse202.jarekwojo.pcpartplanner;

import java.util.Date;

/**
 * Created by Wojonatior on 3/5/15.
 */
public class Part {
    private String name, type, warrantyDuration, manufacturer;
    private Date date;
    private Date warrantyStart;
    //Intent key strings
    public static String nameExtra = "nameExtra",
                         typeExtra = "typeExtra",
                         dateExtra = "dateExtra",
                         mfgExtra = "mfgExtra",
                         warrDurExtra = "warrDurExtra";

    public Part(String n, String d, Date date, String m, String w){
        this.name = n;
        this.type = d;
        this.date = date;
        this.manufacturer = m;
        this.warrantyDuration = w;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWarrantyDuration(String warrantyDuration) {
        this.warrantyDuration = warrantyDuration;

    }public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
