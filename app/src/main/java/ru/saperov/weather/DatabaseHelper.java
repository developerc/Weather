package ru.saperov.weather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saperov on 05.02.16.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DATABASE_NAME = "mydatabase3.db";
    private static final int DATABASE_VERSION = 1;
   /* private static final String DATABASE_TABLE = "cats";

    public static final String CAT_NAME_COLUMN = "cat_name";
    public static final String PHONE_COLUMN = "phone";
    public static final String AGE_COLUMN = "age";
    public static final String COLUMN_ID = BaseColumns._ID;

    // Рефакторинг имён для констант
    public static final String COLUMN_CAT_NAME = "cat_name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_AGE = "age";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + CAT_NAME_COLUMN
            + " text not null, " + PHONE_COLUMN + " integer, " + AGE_COLUMN
            + " integer);";*/
    private static final String DATABASE_TABLE = "forecasts";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TEMP = "temp";
    public static final String COLUMN_ICON = "icon";
    public static final String COLUMN_DT_TXT = "dt_txt";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_TEMP + " text, "
            + COLUMN_ICON + " text, "
            + COLUMN_DT_TXT + " text"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // Создаём новую таблицу
        onCreate(db);
    }

    public void addFrcst(Frcst frcst){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, frcst.getName());
        values.put(COLUMN_TEMP, frcst.getTemp());
        values.put(COLUMN_ICON, frcst.getIcon());
        values.put(COLUMN_DT_TXT, frcst.getDt_txt());
        // Вставляем строку в таблицу
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    public List<Frcst> getAllFrcsts(){
        ArrayList<Frcst> frcstList = new ArrayList<Frcst>();
        //выбираем всю таблицу
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Проходим по всем строкам и добавляем в список
        if (cursor.moveToFirst()) {
            do {
                Frcst frcst = new Frcst();
                frcst.setID(Integer.parseInt(cursor.getString(0)));
                frcst.setName(cursor.getString(1));
                frcst.setTemp(cursor.getString(2));
                frcst.setIcon(cursor.getString(3));
                frcst.setDt_txt(cursor.getString(4));
                frcstList.add(frcst);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return frcstList;
    }

    //получить число прогнозов
    public int getFrcstCount(){
        String countQuery = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    //удалить все прогнозы
    public void delAllFrcsts(){
        SQLiteDatabase db = this.getWritableDatabase();
        String delQuery = "DELETE FROM " + DATABASE_TABLE +";";
        db.execSQL(delQuery);
        db.close();
    }

    //удалить таблицу и создать
    public void upgradeFrcst() {
        SQLiteDatabase db = this.getWritableDatabase();
        String delQuery = "DROP TABLE  " + DATABASE_TABLE+";";
        // Удаляем старую таблицу и создаём новую
        db.execSQL(delQuery);
        // Создаём новую таблицу
        onCreate(db);
    }

    /*
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CAT_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhoneNumber());
        values.put(COLUMN_AGE, contact.getAge());

        // Вставляем строку в таблицу
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    // Получить контакт
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE, new String[] { COLUMN_ID,
                        COLUMN_CAT_NAME, COLUMN_PHONE, COLUMN_AGE }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));

        return contact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        // Выбираем всю таблицу
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Проходим по всем строкам и добавляем в список
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contact.setAge(Integer.parseInt(cursor.getString(3)));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // Получить число контактов
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();

        return count;
    }*/
}
