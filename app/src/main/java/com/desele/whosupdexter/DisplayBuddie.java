package com.desele.whosupdexter;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by lenmor on 23/01/16.
 */
public class DisplayBuddie extends Activity {

    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;


    TextView name;
    TextView phone;
    TextView ID;
    TextView status;

    int id_To_Update = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        ID = (TextView) findViewById(R.id.editTextID);
        name = (TextView) findViewById(R.id.editTextName);
        status = (TextView) findViewById(R.id.editTextStatus);
        phone = (TextView) findViewById(R.id.editTextPhone);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("ID");

            if (Value > 0) {
                // means this is the view part not the add contact part

                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String I = rs.getString(rs.getColumnIndex(DBHelper.BUDDIES_COLUMN_ID));
                String nam = rs.getString(rs.getColumnIndex(DBHelper.BUDDIES_COLUMN_NAME));
                String phon = rs.getString(rs.getColumnIndex(DBHelper.BUDDIES_COLUMN_PHONE));
                String statu = rs.getString(rs.getColumnIndex(DBHelper.BUDDIES_COLUMN_STATUS));

                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);


                name.setText((CharSequence) nam);
                name.setFocusable(false);
                name.setClickable(false);

                phone.setText((CharSequence) phon);
                phone.setFocusable(false);
                phone.setClickable(false);

                status.setText((CharSequence) statu);
                status.setFocusable(false);
                status.setClickable(false);

                ID.setText((CharSequence) I);
                ID.setFocusable(false);
                ID.setClickable(false);

            }
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("ID");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.display_contact, menu);
            } else {
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
        }
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);

                status.setEnabled(true);
                status.setFocusableInTouchMode(true);
                status.setClickable(true);

                ID.setEnabled(true);
                ID.setFocusableInTouchMode(true);
                ID.setClickable(true);

                return true;

            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteBuddy(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), BuddyListActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }



    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("ID");
            if (Value > 0) {
                if (mydb.updateBuddy(Integer.parseInt(ID.getText().toString()), name.getText().toString(), Integer.parseInt(status.getText().toString()), phone.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), BuddyListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertBuddy(name.getText().toString(), Integer.parseInt(ID.getText().toString()), Integer.parseInt(status.getText().toString()), phone.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), BuddyListActivity.class);
                startActivity(intent);
            }
        }
    }

}
