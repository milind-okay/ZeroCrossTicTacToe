package com.wordpress.milindkrohit.zerocrosstictactoe;
        import java.util.ArrayList;
        import java.util.HashMap;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.DatabaseUtils;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "mpassword";
    public static final String COLUMN_CITY = "place";
    public static final String COLUMN_PHONE = "phone";
    public static final String MPOINTS = "mpoints";
    public static final String COLUMN_CHARCOUNT = "maxcharcount";
    public static final String COLUMN_LEVEL = "maxlevel";
    public static final String ISSIGNIN = "issignin";
    public static final String CHAR_LEVEL1 = "charlevel1";
    public static final String CHAR_LEVEL2 = "charlevel2";
    public static final String CHAR_LEVEL3 = "charlevel3";
    public static final String CHAR_LEVEL4 = "charlevel4";
    public static final String CHAR_LEVEL5 = "charlevel5";
    public static final String CHAR_LEVEL6 = "charlevel6";
    public static final String CHAR_LEVEL7 = "charlevel7";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text,email text,mpassword text,place text,issignin integer,mpoints integer,maxcharcount integer," +
                        "maxlevel integer,charlevel1 integer,charlevel2 integer,charlevel3 integer,charlevel4 integer,charlevel5 integer,charlevel6 integer,charlevel7 integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact  (String name, String phone, String email, String mpassword,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("mpassword",mpassword);
        contentValues.put("place", place);
        contentValues.put("issignin", 1);
        contentValues.put("mpoints",0);
        contentValues.put("maxcharcount",0);
        contentValues.put("maxlevel", 1);
        contentValues.put("charlevel1",0);
        contentValues.put("charlevel2",0);
        contentValues.put("charlevel3",0);
        contentValues.put("charlevel4",0);
        contentValues.put("charlevel5",0);
        contentValues.put("charlevel6",0);
        contentValues.put("charlevel7",0);

        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("place", place);
        contentValues.put("issignin", 1);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean updatepass(Integer id, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mpassword", pass);


        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean updatescore (Integer id, int score,int level)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maxcharcount", score);
        contentValues.put("maxlevel", level);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean updatelevel (Integer id,int points, int score, int level)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mpoints",points);
        switch (level){

            case 1:
                contentValues.put("charlevel1", score);
                break;
            case 2:
                contentValues.put("charlevel2", score);
                break;
            case 3:
                contentValues.put("charlevel3", score);
                break;
            case 4:
                contentValues.put("charlevel4", score);
                break;
            case 5:
                contentValues.put("charlevel5", score);
                break;
            case 6:
                contentValues.put("charlevel6", score);
                break;
            case 7:
                contentValues.put("charlevel7", score);
                break;
            default:
                break;

        }


        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean logout(Integer id, Integer log){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("issignin",log);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}

