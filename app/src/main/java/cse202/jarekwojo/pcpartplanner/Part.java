package cse202.jarekwojo.pcpartplanner;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Wojonatior on 3/5/15.
 */
public class Part implements Parcelable{
    private String name;
    private String type;
    private String manufacturer;
    private String duration;
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
                    (Date) source.readSerializable(), // Date
                    source.readString(), // Manufacturer
                    source.readString() // Warranty Duration
            );
        }

        @Override
        public Part[] newArray(int size) {
            return new Part[0];
        }
    };

    public Part(String n, String d, Date date, String manufacturer, String duration){
        this.name = n;
        this.type = d;
        this.date = date;
        this.manufacturer = manufacturer;
        this.duration = duration;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
