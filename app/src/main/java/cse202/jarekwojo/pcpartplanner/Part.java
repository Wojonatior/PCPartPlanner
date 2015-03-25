package cse202.jarekwojo.pcpartplanner;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Wojonatior on 3/5/15.
 */
<<<<<<< HEAD
public class Part implements Parcelable{
    private String name, type;
    private Date date;
    private Date warrantyStart;
    //Intent key strings
    public static final String PART_EXTRA = "partExtra";

    public static Creator<Part> CREATOR = new Creator<Part>() {
        @Override
        public Part createFromParcel(Parcel source) {
            return new Part(
                    source.readString(), // Name
                    source.readString(), // Type
                    (Date) source.readSerializable() // Date
            );
        }

        @Override
        public Part[] newArray(int size) {
            return new Part[0];
        }
    };
=======
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
>>>>>>> origin/master

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getType());
        dest.writeSerializable(getWarrantyStart());
    }
}
