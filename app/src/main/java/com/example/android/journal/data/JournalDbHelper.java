package com.example.android.journal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.journal.data.JournalContract.JournalEntry;

/**
 * Created by CO2 on 7/1/2018.
 */

public class JournalDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="journal.db";

    private static final int DATABASE_VERSION = 1;

    public JournalDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_JOURNAL_TABLE = "CREATE TABLE " + JournalEntry.TABLE_NAME + "("
                + JournalEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + JournalEntry.COLUMN_JOURNAL_NAME + "TEXT NOT NULL, "
                + JournalEntry.COLUMN_JOURNAL_THOUGHTS + "TEXT NOT NULL, "
                + JournalEntry.COLUMN_JOURNAL_MOOD + "INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_JOURNAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
