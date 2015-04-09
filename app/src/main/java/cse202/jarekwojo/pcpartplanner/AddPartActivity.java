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
import android.widget.Toast;

import org.w3c.dom.Text;

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

    String[] warrantyDurations = new String[] {
            "30 Days",
            "60 Days",
            "3 Months",
            "6 Months",
            "9 Months",
            "1 Year",
            "2 Years",
            "3 Years",
            "5 Years",
            "7 Years",
            "10 Years",
            "Lifetime",
            "Other"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Reference and setup partTypes spinner adapter
        final Spinner partTypeSpinner = (Spinner) findViewById(R.id.partTypeSpinner);
        ArrayAdapter<String> typeSpinnerAdapter = new ArrayAdapter<String>(
                AddPartActivity.this,
                android.R.layout.simple_spinner_item,
                partTypes);
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partTypeSpinner.setAdapter(typeSpinnerAdapter);

        final Spinner warrantyDurationSpinner = (Spinner) findViewById(R.id.warrantyDurationSpinner);
        ArrayAdapter<String> durationSpinnerAdapter = new ArrayAdapter<String>(
                AddPartActivity.this,
                android.R.layout.simple_spinner_item,
                warrantyDurations);
        warrantyDurationSpinner.setAdapter(durationSpinnerAdapter);

        //Set default date if necessary
        if(mDate == null){
            mDate = new Date();
        }

        //Reference date
        mDateText = (TextView) findViewById(R.id.dateTextView);
        mDateText.setText(DatePickerFragment.formatDate(mDate));

        //Reference DatePickerButton & on click functionality
        Button datePickerButton = (Button) findViewById(R.id.datePickerButton);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(),"date");
            }
        });
        //Reference nameTextView
        final TextView nameTextView = (TextView) findViewById(R.id.addPart_PartName);
        final TextView mfgTextView = (TextView) findViewById(R.id.addPart_ManufacturerName);
        final TextView serialTextView = (TextView) findViewById(R.id.addPart_SerialNumber);
        final TextView notesTextView = (TextView) findViewById(R.id.addPart_Notes);
        //Reference SubmitButton & on click functionality
        Button SubmitButton = (Button) findViewById(R.id.addPart_SubmitButton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate Input
                if(nameTextView.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Must enter a part name.", Toast.LENGTH_SHORT).show();
                } else if(mfgTextView.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Must enter a manufacturer.", Toast.LENGTH_SHORT).show();
                }else {
                    //Assign user input to variables
                    String name = nameTextView.getText().toString();
                    String type = (String) partTypeSpinner.getSelectedItem();
                    String manufacturer = mfgTextView.getText().toString();
                    String duration = (String) warrantyDurationSpinner.getSelectedItem();
                    String serial = serialTextView.getText().toString();
                    String notes = notesTextView.getText().toString();
                    //Declare and package AddPartActivity return intent (on Activity Result)
                    Intent data = new Intent();
                    // Add part
                    Part p = new Part(name, type, mDate, manufacturer, duration, serial, notes);
                    data.putExtra(Part.PART_EXTRA, p);
                    setResult(RESULT_OK, data);
                    //End Activity
                    finish();
                }
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

        public static String formatDate(Date d){
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());

            return sdf.format(d);
        }
    }
}
