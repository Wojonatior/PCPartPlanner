package cse202.jarekwojo.pcpartplanner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    //List of parts in the partlist
    List<Part> partList = new ArrayList<>();

    private PartAdapter mAdapter;

    //add part activity request code
    private static final int ADD_PART_REQUEST = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Reference of listview
        ListView partListView = (ListView) findViewById(R.id.partListView);

        //Reference of adapter and implementation
        mAdapter = new PartAdapter(this, partList);
        partListView.setAdapter(mAdapter);

        //Reference of add button
        final Button addPartButton = (Button) findViewById(R.id.addPartButton);

        //Functionality of add button
        addPartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPartIntent = new Intent(MainActivity.this, AddPartActivity.class);
                startActivityForResult(addPartIntent, ADD_PART_REQUEST);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_PART_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(Part.nameExtra);
            String type = data.getStringExtra(Part.typeExtra);
            Date date = (Date) data.getSerializableExtra(Part.dateExtra);
            String manufacturer = data.getStringExtra(Part.mfgExtra);
            String warrantyDuration = data.getStringExtra(Part.warrDurExtra);
            Part p = new Part(name,type,date, manufacturer, warrantyDuration);
            //add part to list
            mAdapter.add(p);
            //Update adapter data set
            mAdapter.notifyDataSetChanged();
        }
    }
}
