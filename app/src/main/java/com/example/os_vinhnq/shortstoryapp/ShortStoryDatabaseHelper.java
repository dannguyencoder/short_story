package com.example.os_vinhnq.shortstoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ShortStoryDatabaseHelper extends SQLiteOpenHelper {

    //Database info
    private static final String DATABASE_NAME = "shortStoryDatabase";
    private static final int DATABASE_VERSION = 3;

    //Table names
    private static final String TABLE_SHORT_STORY = "short_story";

    //Short Story table columns
    private static final String KEY_ID = "id";
    private static final String KEY_STORY_NAME = "story_name";
    private static final String KEY_STORY_DESCRIPTION = "story_description";
    private static final String KEY_STORY_CONTENT = "story_content";
    private static final String KEY_STORY_THUMBNAIL_LINK = "story_thumbnail_link";

    private static ShortStoryDatabaseHelper sInstance;

    public static synchronized ShortStoryDatabaseHelper getInstance(Context context) {
        //Use the application context, which will ensure that you don't accidentally leak Activity's context.

        if (sInstance == null) {
            sInstance = new ShortStoryDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private ShortStoryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Called when the database is created for the first time
    //If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SHORT_STORY_TABLE = "CREATE TABLE " + TABLE_SHORT_STORY +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY," + //Define a primary key
                KEY_STORY_NAME + " TEXT," +
                KEY_STORY_DESCRIPTION + " TEXT," +
                KEY_STORY_CONTENT + " TEXT," +
                KEY_STORY_THUMBNAIL_LINK + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(CREATE_SHORT_STORY_TABLE);
    }

    //Called when the database needs to upgraded
    //This method will only be called if a database already exists on disk with the dame DATABASE_NAME
    //but the DATABASE_VERSION is different than the version of the database that exists on disk
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Simplest implementation is to drop all old tables and recreate them
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHORT_STORY);
        onCreate(sqLiteDatabase);
    }

    //CRUD Operations

    //Insert a story into the database
    public void addStory(ShortStory shortStory) {
        //Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        //It's good idea to wrap our insert in a transaction. This helps with performance and ensures
        //consistency of database
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_STORY_NAME, shortStory.getStoryName());
            values.put(KEY_STORY_DESCRIPTION, shortStory.getStoryDescription());
            values.put(KEY_STORY_CONTENT, shortStory.getStoryDescription());
            values.put(KEY_STORY_THUMBNAIL_LINK, shortStory.getStoryThumbnailLink());

            //Notice how we haven't specified the primary key. SQLite auto increment the primary column
            db.insertOrThrow(TABLE_SHORT_STORY, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Short_story_app", "Error when trying to add story  to the database");
        } finally {
            db.endTransaction();
        }
    }

    public List<ShortStory> getAllStory() {
        List<ShortStory> shortStories = new ArrayList<>();

        //SELECT * FROM short_story
        String STORY_SELECT_QUERY = String.format("SELECT * FROM %s", TABLE_SHORT_STORY);

        //getReadableDatabase() and getWritableDatabase() return the same object (except under low disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(STORY_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    ShortStory shortStory = new ShortStory();
                    shortStory.setStoryName(cursor.getString(cursor.getColumnIndex(KEY_STORY_NAME)));
                    shortStory.setStoryDescription(cursor.getString(cursor.getColumnIndex(KEY_STORY_DESCRIPTION)));
                    shortStory.setStoryContent(cursor.getString(cursor.getColumnIndex(KEY_STORY_CONTENT)));
                    shortStory.setStoryThumbnailLink(cursor.getString(cursor.getColumnIndex(KEY_STORY_THUMBNAIL_LINK)));
                    shortStories.add(shortStory);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("Short_story_app", "Error when trying to get story from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return shortStories;
    }
}
