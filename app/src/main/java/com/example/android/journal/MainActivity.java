package com.example.android.journal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.journal.data.JournalContract;
import com.example.android.journal.data.JournalContract.JournalEntry;
import com.example.android.journal.data.JournalDbHelper;


public class MainActivity extends AppCompatActivity {

    private JournalDbHelper mDbHelper;

    private void displayDatabaseInfo() {
              String[] projection = {
                      JournalEntry._ID,
                      JournalEntry.COLUMN_JOURNAL_NAME,
                      JournalEntry.COLUMN_JOURNAL_THOUGHTS,
                      JournalEntry.COLUMN_JOURNAL_MOOD};
        Cursor cursor = getContentResolver().query(
                JournalEntry.BASE_CONTENT_URI,
                projection,
                      null,
                      null,                   // Selection criteria
                      null);               // The sort order for the returned rows

                TextView displayView = (TextView) findViewById(R.id.text_view_pet);
               // Find the ListView which will be populated with the pet data
                        ListView journalListView = (ListView) findViewById(R.id.list);

              try {
                  displayView.setText("The journal table contains " + cursor.getCount() + " journals.\n\n");
                   displayView.append(JournalEntry._ID + " - " +
                           JournalEntry.COLUMN_JOURNAL_NAME + " - " +
                           JournalEntry.COLUMN_JOURNAL_THOUGHTS + " - " +
                           JournalEntry.COLUMN_JOURNAL_MOOD + "\n");


                           JournalCursorAdapter adapter = new JournalCursorAdapter(this, cursor);

                     // Figure out the index of each column
                           int idColumnIndex = cursor.getColumnIndex(JournalEntry._ID);
                      int nameColumnIndex = cursor.getColumnIndex(JournalEntry.COLUMN_JOURNAL_NAME);
                        int thoughtsColumnIndex = cursor.getColumnIndex(JournalEntry.COLUMN_JOURNAL_THOUGHTS);
                        int moodColumnIndex = cursor.getColumnIndex(JournalEntry.COLUMN_JOURNAL_MOOD);


                              // Iterate through all the returned rows in the cursor
                                       while (cursor.moveToNext()) {
                     // Use that index to extract the String or Int value of the word
                                        // at the current row the cursor is on.
                                         int currentID = cursor.getInt(idColumnIndex);
                 String currentName = cursor.getString(nameColumnIndex);
                                String currentThought = cursor.getString(thoughtsColumnIndex);
                                int currentMood = cursor.getInt(moodColumnIndex);
                                // Display the values from each column of the current row in the cursor in the TextView
                                       displayView.append(("\n" + currentID + " - " +
                                                       currentName + " - " +
                                               currentThought + " - " +
                                               currentMood ));

                            }
                 } finally {
                        // Always close the cursor when you're done reading from it. This releases all its
                  // resources and makes it invalid.
                  cursor.close();
               }
             // Attach the adapter to the ListView.
        journalListView.setAdapter(adapter);
    }

    private void insertJournal(){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new JournalDbHelper(this);

        insertJournal();
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

       // JournalDbHelper mDbHelper = new JournalDbHelper(this);
        String[] projection = {
                JournalEntry._ID,
                JournalEntry.COLUMN_JOURNAL_NAME,
                JournalEntry.COLUMN_JOURNAL_THOUGHTS,
                JournalEntry.COLUMN_JOURNAL_MOOD
        };

      /**  Cursor cursor = db.query(
                JournalEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        ); */

      Cursor cursor = getContentResolver().query(JournalEntry.CONTENT_URI, projection, null, null, null);



}
