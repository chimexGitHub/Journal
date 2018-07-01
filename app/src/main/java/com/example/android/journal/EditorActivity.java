package com.example.android.journal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.journal.data.JournalContract.JournalEntry;
import com.example.android.journal.data.JournalDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mNameEditText;

    private Spinner mMoodSpinner;

    private EditText mThoughtsEditText;

    private int mMood = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_journal_name);
        mThoughtsEditText = (EditText) findViewById(R.id.edit_journal_thoughts);
        mMoodSpinner = (Spinner) findViewById(R.id.spinner_mood);

        setupSpinner();
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter moodSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_mood_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        moodSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mMoodSpinner.setAdapter(moodSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mMoodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if(!TextUtils.isEmpty(selection)){
                    if (selection.equals(getString(R.string.mood_happy))) {
                        mMood = JournalEntry.MOOD_HAPPY; // Happy
                    } else if (selection.equals(getString(R.string.mood_sad))) {
                        mMood = JournalEntry.MOOD_SAD; // Sad
                    } else {
                        mMood = JournalEntry.MOOD_NEUTRAL; // Neutral
                    }
                }

                // Because AdapterView is an abstract class, onNothingSelected must be defined

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mMood = 0; //neutral
            }
        });

    }

    private void insertJournal(){
        String nameString = mNameEditText.getText().toString().trim();
        String thoughtString = mThoughtsEditText.getText().toString().trim();


        JournalDbHelper mDbHelper = new JournalDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JournalEntry.COLUMN_JOURNAL_NAME, nameString);
        values.put(JournalEntry.COLUMN_JOURNAL_THOUGHTS, thoughtString);
        values.put(JournalEntry.COLUMN_JOURNAL_MOOD, mMood);

        long newRowId = db.insert(JournalEntry.TABLE_NAME, null, values);

        if(newRowId == -1){
            Toast.makeText(this, "Error with saving Journal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Journal saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save Journal
                insertJournal();
                //exit activity
                finish();

                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
