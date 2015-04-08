package cse202.jarekwojo.pcpartplanner;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Wojonatior on 3/5/15.
 */
public class Part implements Parcelable{
    public static final String ITEM_SEP = System.getProperty("line.separator");
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private String name;
    private String type;
    private String manufacturer;
    private String duration;
    private Date warrantyStart;
    private String notes;

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
                    source.readString(), //Duration
                    source.readString() //Notes

            );
        }

        @Override
        public Part[] newArray(int size) {
            return new Part[0];
        }
    };

    public Part(String n, String d, Date date, String m, String dur, String notes){
        this.name = n;
        this.type = d;
        this.warrantyStart = date;
        this.manufacturer = m;
        this.duration = dur;
        this.notes = notes;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        dest.writeString(getManufacturer());
        dest.writeString(getDuration());
        dest.writeString(getNotes());
    }

    @Override
    public String toString() {
        return name + ITEM_SEP + type + ITEM_SEP + FORMAT.format(warrantyStart) + ITEM_SEP +
                manufacturer + ITEM_SEP + duration + ITEM_SEP + notes;
    }
}
