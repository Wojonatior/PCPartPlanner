package cse202.jarekwojo.pcpartplanner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddPartActivity extends ActionBarActivity {

    private static Date mDate;
    private static TextView mDateText;
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
        //Reference and setup spinner adapter
        final Spinner partTypeSpinner = (Spinner) findViewById(R.id.partTypeSpinner);
        ArrayAdapter<String> typeSpinnerAdapter = new ArrayAdapter<String>(
                AddPartActivity.this,
                android.R.layout.simple_spinner_item,
                partTypes);
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partTypeSpinner.setAdapter(typeSpinnerAdapter);

        //Set default date if necessary
        if(mDate == null){
            mDate = new Date();
        }

        //Reference date
        mDateText = (TextView) findViewById(R.id.dateTextView);

        //Reference DatePickerButton & on click functionality
        Button datePickerButton = (Button) findViewById(R.id.datePickerButton);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(),"date");
            }
        });
        //Reference nameTextView, manufacturerTextView, durationTextView
        final TextView nameTextView = (TextView) findViewById(R.id.addPart_PartName);
        final TextView manufacturerTextView = (TextView) findViewById(R.id.addPart_ManufacturerName);
        final TextView durationTextView = (TextView) findViewById(R.id.addPart_warrantyDuration);
        //Reference SubmitButton & on click functionality
        Button SubmitButton = (Button) findViewById(R.id.addPart_SubmitButton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Assume Valid Input
                //Assign user input to variables
                String name = nameTextView.getText().toString();
                String type = (String) partTypeSpinner.getSelectedItem();
                String manufacturer = manufacturerTextView.getText().toString();
                String duration = durationTextView.getText().toString();
                //Declare and package AddPartActivity return intent (on Activity Result)
                Intent data = new Intent();
                // Add part
                Part p = new Part(name, type, mDate, manufacturer, duration);
                data.putExtra(Part.PART_EXTRA, p);
                setResult(RESULT_OK, data);
                //End Activity
                finish();
            }
        });
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

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();

            //Use current date as default
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

            //create and return new instance of dialog
            return new DatePickerDialog(getActivity(),this,year,month,dayOfMonth);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.clear();
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,monthOfYear);
            c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            mDate = c.getTime();
            mDateText.setText(formatDate(mDate));
        }

        private String formatDate(Date d){
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());

            return sdf.format(d);
        }


    }
}
