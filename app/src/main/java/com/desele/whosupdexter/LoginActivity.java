package com.desele.whosupdexter;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Button btnLogin;
    String username, password;
    public static final String USER_NAME = "USERNAME";

    DBHelper2 mydb;
    // private TextView status,role,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);

        mydb = new DBHelper2(this);
        mydb.insertUser("lenny", "1234");

        // Firebase myFirebaseRef = new Firebase("https://amber-heat-169.firebaseio.com/");
        //https://amber-heat-169.firebaseio.com/

        usernameField = (EditText) findViewById(R.id.editText1);
        passwordField = (EditText) findViewById(R.id.editText2);


        username = usernameField.getText().toString();
        password = passwordField.getText().toString();


        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //get status from database

                Cursor resultSet = mydb.getData(username, password);
                resultSet.moveToFirst();
                if (resultSet.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, BuddyListActivity.class);
                    intent.putExtra(USER_NAME, username);
                    finish();
                    startActivity(intent);
                }


            }


        });


    }


}