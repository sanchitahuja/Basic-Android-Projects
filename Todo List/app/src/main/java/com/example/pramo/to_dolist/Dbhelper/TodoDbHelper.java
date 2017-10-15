package com.example.pramo.to_dolist.Dbhelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pramo on 06-02-2017.
 */

public class TodoDbHelper extends SQLiteOpenHelper {

    public static String DB_NAME="Todo.db";
    public static int DB_VERSION=2;


    public TodoDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoTaskManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if(oldVersion==1 && newVersion==2)
//        {
//            db.execSQL("ALTER TABLE ");
//        }
//        db.execSQL(TodoTaskManager.DROP_TABLE);
//        db.execSQL(TodoTaskManager.CREATE_TABLE);

    }

    public static interface Constants{
        String LBR=" ( ";
        String RBR=" ) ";
        String COMMA=" , ";
        String SEMICOLON=" ; ";
        String TYPE_INT=" INTEGER ";
        String TYPE_TEXT=" TEXT ";
        String TYPE_PK=" PRIMARY KEY ";
        String TYPE_AI=" AUTOINCREMENT ";
        String TYPE_BOOLEAN = " BOOLEAN ";
    }
//    public static interface Commands{
//        String LBR=" { ";
//        String RBR=" } ";
//        String COMMA=" , ";
//        String SEMICOLON=" ; ";
//        String TYPE_INT=" INTEGER ";
//        String TYPE_TEXT=" TEXT ";
//        String TYPE_PK=" PRIMARY KEY ";
//        String TYPE_AI=" AUTOINCREMENT ";
//    }
}
