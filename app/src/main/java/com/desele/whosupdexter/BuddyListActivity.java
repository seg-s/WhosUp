package com.desele.whosupdexter;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BuddyListActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    ListView listView;
    DBHelper mydb;
    Button btnChangeStatus;


    //HARCODED, get from LOG-IN
    int userID=1;

    /*
    SQLiteDatabase mydatabase = openOrCreateDatabase("your database name",MODE_PRIVATE,null);
    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);");
    mydatabase.execSQL("INSERT INTO TutorialsPoint VALUES('admin','admin');");

    Cursor resultSet = mydatbase.rawQuery("Select * from TutorialsPoint",null);
resultSet.moveToFirst();
String username = resultSet.getString(1);
String password = resultSet.getString(2);

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_list);

        //INTENT from LOGIN
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginActivity.USER_NAME);

        TextView text1 = (TextView)findViewById(R.id.username);
        text1.setText("Welcome " + username);



        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView1);


        //########## DATABASE STUFF ##########

        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllBuddies();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);


        listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("ID", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), DisplayBuddie.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });


        btnChangeStatus = (Button) findViewById(R.id.btnChangeStatus);

        btnChangeStatus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //get status from database

                Cursor resultSet = mydb.getData(userID);
                resultSet.moveToFirst();
                int ORIG_ID = resultSet.getInt(0);
                String ORIG_NAME = resultSet.getString(1);
                int status = resultSet.getInt(2);
                String ORIG_PHONE = resultSet.getString(3);


                status = (status==1) ? 0 : 1;

               //change status in button
                Button p1_button = (Button)findViewById(R.id.btnChangeStatus);
                p1_button.setText(status == 1 ? "AVAILABLE" : "BUSY");


                //change status in database
                mydb.updateBuddy(ORIG_ID, ORIG_NAME, status, ORIG_PHONE);


                //go to activity where we can change the status

                //need intent to pass
               // Intent i = new Intent(BuddyListActivity.this, ChangeStatusActivity.class);

                //start target
               // startActivity(i);
            }

        });



        //####################################

        /*

        // Defined Array values to show in ListView
        String[] values = new String[] { "Lenny",
                "Simon",
                "Dexter",
                "Sebastian",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };



        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });

        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.item1:Bundle dataBundle = new Bundle();
                dataBundle.putInt("ID", 0);

                Intent intent = new Intent(getApplicationContext(),DisplayBuddie.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }


}
