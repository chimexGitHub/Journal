package com.example.android.journal.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by CO2 on 7/1/2018.
 */

public final class JournalContract {
    private JournalContract() {
    }

    public static final class JournalEntry implements BaseColumns {

            public static final String CONTENT_AUTHORITY = "com.example.android.journal";

            public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

            public static final String PATH_JOURNALS = "journals";

            public static final class PetEntry implements BaseColumns {

            public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_JOURNALS);

            public static final String CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_JOURNALS;


            public static final String CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_JOURNALS;

        }

        public final static String TABLE_NAME = "journals";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_JOURNAL_NAME = "name";
        public final static String COLUMN_JOURNAL_THOUGHTS = "thoughts";
        public final static String COLUMN_JOURNAL_MOOD = "mood";


        public static final int MOOD_NEUTRAL = 0;
        public static final int MOOD_HAPPY = 1;
        public static final int MOOD_SAD = 2;

        /**
         * Returns whether or not the given mood is {@link #MOOD_NEUTRAL}, {@link #MOOD_HAPPY},
         * or {@link #MOOD_SAD}.
         */
        public static boolean isValidMood(int mood) {
            if (mood == MOOD_NEUTRAL || mood == MOOD_HAPPY || mood == MOOD_SAD) {
                return true;
            }
            return false;
        }
    }
}