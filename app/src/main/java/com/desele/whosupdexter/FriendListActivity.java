package com.desele.whosupdexter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


/**
 * An activity representing a list of Friends. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link FriendDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link FriendListFragment} and the item details
 * (if present) is a {@link FriendDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link FriendListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class FriendListActivity extends AppCompatActivity
        implements FriendListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        final Resources resources = getResources();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(context);
                final View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // commented out
                alertDialogBuilder.setView(promptsView);

                //final EditText userInput = (EditText) promptsView
                //        .findViewById(R.id.editTextDialogUserInput);



                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text

                                        EditText input = (EditText) promptsView.findViewById(R.id.PersonName);
                                        EditText input2 = (EditText) promptsView.findViewById(R.id.Email);
                                        EditText input3 = (EditText) promptsView.findViewById(R.id.Phone);

                                        String theInput = input.getText().toString();
                                        String theInput2 = input2.getText().toString();
                                        String theInput3 = input3.getText().toString();


                                        Friend f = new Friend(theInput, theInput2, resources.getDrawable(R.mipmap.ic_launcher));
                                        f.setPhone(theInput3);
                                        FriendList.friendList.add(f);

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }

        });

        if (findViewById(R.id.friend_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((FriendListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.friend_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link FriendListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(FriendDetailFragment.ARG_ITEM_ID, id);
            FriendDetailFragment fragment = new FriendDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, FriendDetailActivity.class);
            detailIntent.putExtra(FriendDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
