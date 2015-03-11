package cse202.jarekwojo.pcpartplanner;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class AddPartActivity extends ActionBarActivity {

    //Array of part types for the spinner
    String[] partTypes = new String[] {
        "Motherboard",
        "CPU",
        "Video Card",
        "Hard Drive",
        "Ram",
        "Case",
        "Power Supply",
        "Custom"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner partTypeSpinner = (Spinner) findViewById(R.id.partTypeSpinner);

        ArrayAdapter<String> typeSpinnerAdapter = new ArrayAdapter<String>(
                AddPartActivity.this,
                android.R.layout.simple_spinner_item,
                partTypes);

        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        partTypeSpinner.setAdapter(typeSpinnerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_part, menu);
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
