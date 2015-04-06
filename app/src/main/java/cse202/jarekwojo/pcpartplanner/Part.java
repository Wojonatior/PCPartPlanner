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
    private String name, type;
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

    public Part(String n, String d, Date date){
        this.name = n;
        this.type = d;
        this.warrantyStart = date;
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

    @Override
    public String toString() {
        return name + ITEM_SEP + type + ITEM_SEP + FORMAT.format(warrantyStart);
    }
}
