package com.example.android.journal;

/**
 * Created by CO2 on 7/1/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.journal.data.JournalContract.JournalEntry;

/**
 * {@link JournalCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class JournalCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link JournalCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public JournalCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
             TextView nameTextView = (TextView) view.findViewById(R.id.name);
              TextView summaryTextView = (TextView) view.findViewById(R.id.summary);


                int nameColumnIndex = cursor.getColumnIndex(JournalEntry.COLUMN_JOURNAL_NAME);
                int thoughtsColumnIndex = cursor.getColumnIndex(JournalEntry.COLUMN_JOURNAL_THOUGHTS);
                String journalName = cursor.getString(nameColumnIndex);
                String journalThoughts = cursor.getString(thoughtsColumnIndex);

                nameTextView.setText(journalName);
                summaryTextView.setText(journalThoughts);
    }
}