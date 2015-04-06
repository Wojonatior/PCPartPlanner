package cse202.jarekwojo.pcpartplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
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

    private static final String FILE_NAME = "PCPartList.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Reference of listview
        ListView partListView = (ListView) findViewById(R.id.partListView);

        //Reference of adapter and implementation
        mAdapter = new PartAdapter(this, partList);
        partListView.setAdapter(mAdapter);

        partListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Part part = (Part) mAdapter.getItem(position);
                showAlertDialog(part);
                return true;
            }
        });

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
            //Get part
            Part p = data.getParcelableExtra(Part.PART_EXTRA);
            //add part to list
            mAdapter.add(p);
            //Update adapter data set
            mAdapter.notifyDataSetChanged();
        }
    }

    private void saveItems() {
        PrintWriter writer = null;
        try{
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)));
            for(int i=0; i<mAdapter.getCount(); i++){
                writer.println(mAdapter.getItem(i));
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(writer != null){
                writer.close();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mAdapter.getCount() == 0){
            loadItems();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveItems();
    }

    private void loadItems(){
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));
            String name = null;
            String type = null;
            Date date = null;

            while((name = reader.readLine()) != null){
                type = reader.readLine();
                date = Part.FORMAT.parse(reader.readLine());
                mAdapter.add(new Part(name,type,date));
            }
        }catch(FileNotFoundException fnf){
            fnf.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(ParseException pe){
            pe.printStackTrace();
        }finally{
            //Notify adapter updated
            mAdapter.notifyDataSetChanged();
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    private void showAlertDialog(Part part){
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        final Part tmpPart = part;
        alertDialog.setTitle("Delete Part");
        alertDialog.setMessage("Are you sure you want to delete " + part.getName() + "?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        mAdapter.remove(tmpPart);
                        mAdapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
