package com.wordpress.milindkrohit.zerocrosstictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MilindZeroCross.db";
    public static final String TABLE_NAME = "score";
    public static final String COLUMN_ID = "id";
    public static final String FIRST_PLAYER = "first_player";
    public static final String SECOND_PLAYER = "second_player";
    public static final String FIRST_PLAYER_SCORE = "first_player_score";
    public static final String SECOND_PLAYER_SCORE = "second_player_score";
    public static final String TIES = "ties";
    public static final String TURN = "turn";
    public static final String PAIR = "pairno";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table score " +
                        "(id integer primary key, first_player text,first_player_score integer ,second_player text,second_player_score integer,ties integer,turn integer,pairno integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS score");
        onCreate(db);
    }

    public boolean insertPlayers(String first_player, String second_player,int turn,int pair_no)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_player", first_player);
        contentValues.put("second_player", second_player);
        contentValues.put("first_player_score",0);
        contentValues.put("second_player_score",0);
        contentValues.put("ties",0);
        contentValues.put("turn", turn);
        contentValues.put("pairno", pair_no);

        db.insert("score", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from score where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }
    public boolean updatePlayer(Integer id,String first_player,String second_player,int turn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_player",first_player);
        contentValues.put("second_player",second_player);
        contentValues.put("first_player_score",0);
        contentValues.put("second_player_score",0);
        contentValues.put("ties",0);
        contentValues.put("turn",turn);
        db.update("score", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean updatepair (Integer id, int pair_no)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("pairno",pair_no);
        db.update("score", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateScore (Integer id, int first_player_score,int second_player_score,int ties)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_player_score",first_player_score);
        contentValues.put("second_player_score",second_player_score);
        contentValues.put("ties",ties);
        db.update("score", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
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
            array_list.add(res.getString(res.getColumnIndex(FIRST_PLAYER)));
            res.moveToNext();
        }
        return array_list;
    }
}

