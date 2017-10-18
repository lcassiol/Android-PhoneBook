package com.example.lcassiol.android_phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcass on 10/10/2017.
 */

public class ContactDAO extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String TABLE = "Contact";
    private static final String DATABASE = "PhoneBook";

    public ContactDAO(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABLE + " (id INTEGER PRIMARY KEY, " +
                                                  "name TEXT NOT NULL, " +
                                                  "email TEXT, " +
                                                  "site TEXT, " +
                                                  "phone TEXT, " +
                                                  "adress TEXT, " +
                                                  "pathPhoto TEXT);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion==1){
            String sql = "ALTER TABLE " + TABLE + " ADD COLUMN pathPhoto TEXT";
            db.execSQL(sql);
        }
    }


    public void insertContact(Contact contact){
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("site", contact.getSite());
        values.put("email", contact.getEmail());
        values.put("adress", contact.getAdress());
        values.put("phone", contact.getPhone());
        values.put("pathPhoto", contact.getPhoto());

        getWritableDatabase().insert(TABLE, null, values);
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {contact.getId().toString()};
        db.delete(TABLE,"id=?",args);
    }

    public void updateContact(Contact contact){
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("email", contact.getEmail());
        values.put("site", contact.getSite());
        values.put("adress", contact.getAdress());
        values.put("phone", contact.getPhone());
        values.put("pathPhoto", contact.getPhoto());

        String[] idToUpdate = {contact.getId().toString()};
        getWritableDatabase().update(TABLE, values, "id=?", idToUpdate);
    }

   public boolean isContact(String phone){
       String[] parameters = {phone};
       Cursor cursor = getReadableDatabase().rawQuery("SELECT phone FROM " + TABLE + " WHERE phone=?", parameters);
       int total = cursor.getCount();
       return total > 0;

   }

    public List<Contact> getList(){
        List<Contact> contacs = new ArrayList<Contact>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE + ";", null);

        while(cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex("id")));
            contact.setName(cursor.getString(cursor.getColumnIndex("name")));
            contact.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            contact.setSite(cursor.getString(cursor.getColumnIndex("site")));
            contact.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            contact.setAdress(cursor.getString(cursor.getColumnIndex("adress")));
            contact.setPhoto(cursor.getString(cursor.getColumnIndex("pathPhoto")));
            contacs.add(contact);
        }
        cursor.close();

        return contacs;
    }

}
