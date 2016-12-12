package com.example.abc.mindvalley_aliasheer_android_test.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Asheer on 12/12/2016.
 */
// Class made if we further need to save json for offline purposes
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "JsonManager";

    // JSON table name
    private static final String TABLE_Json = "Json";

    // JSON Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_URLS = "regular";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Json + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_URLS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Json);

        // Create tables again
        onCreate(db);
    }
    // Adding new Model
   public void addModel(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_URLS, model.getColor()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_Json, null, values);
        db.close(); // Closing database connection
    }

    // Getting single Model
    Model getModel(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Json, new String[] { KEY_ID, KEY_URLS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Model Json = new Model(
                cursor.getString(0));
        // return Json
        return Json;
    }

    // Getting All Models
    public List<Model.Urls> getAllModel() {
        List<Model.Urls> JSONList = new ArrayList<Model.Urls>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Json;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model.Urls mode=new Model().getUrls();
                mode.setRegular(cursor.getString(1));                // Adding model to list
                JSONList.add(mode);
            } while (cursor.moveToNext());
        }

        // return Model list
        return JSONList;
    }

    // Updating single Model
    public int updateModel(Model.Urls model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_URLS, model.getRegular());

        // updating row
        return db.update(TABLE_Json, values, KEY_URLS + " = ?",
                new String[] { String.valueOf(model.getRegular()) });
    }

    // Deleting single Model
    public void deleteModel(Model.Urls model) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Json, KEY_URLS + " = ?",
                new String[] { String.valueOf(model.getRegular()) });
        db.close();
    }


    // Getting Models count
    public int getModelsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Json;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return Models
        return cursor.getCount();
    }

}
