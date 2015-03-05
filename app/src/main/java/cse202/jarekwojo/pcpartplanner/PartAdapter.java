package cse202.jarekwojo.pcpartplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojonatior on 3/5/15.
 */
public class PartAdapter extends BaseAdapter {
    private Context mContext;
    private List<Part> partList = new ArrayList<Part>();

    public PartAdapter(Context context, List<Part> parts) {
        this.mContext = context;
        this.partList = parts;
    }
    @Override
    public int getCount() {
        return partList.size();
    }

    @Override
    public Object getItem(int position) {
        return partList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Inflate item layout
        View view = LayoutInflater.from(mContext).inflate(R.layout.part_list_item, parent, false);

        //Get text views
        TextView nameView = (TextView) view.findViewById(R.id.partName);
        TextView descriptionView = (TextView) view.findViewById(R.id.partDescription);

        //Set part
        Part part = (Part) getItem(position);

        //Set texts
        nameView.setText(part.getName());
        descriptionView.setText(part.getDescription());

        return view;
    }
}
