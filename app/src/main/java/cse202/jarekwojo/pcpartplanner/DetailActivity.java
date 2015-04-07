package cse202.jarekwojo.pcpartplanner;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Show back button
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        //Get elements
        TextView nameView = (TextView) findViewById(R.id.partDetail_PartName);
        TextView descriptionView = (TextView) findViewById(R.id.partDetail_PartDescription);
        TextView warrantyStartView = (TextView) findViewById(R.id.partDetail_WarrantyStart);

        //Get part
        if(getIntent().getExtras() != null){
            Part part = getIntent().getParcelableExtra(Part.PART_EXTRA);
            nameView.setText(part.getName());
            descriptionView.setText(part.getType());
            warrantyStartView.setText(AddPartActivity.DatePickerFragment.formatDate(part.getWarrantyStart()));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
