package com.example.pramo.to_dolist.Dbhelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.example.pramo.to_dolist.Dbhelper.TodoDbHelper.Constants.*;

/**
 * Created by pramo on 06-02-2017.
 */

public class TodoTaskManager {
    public static final String TAG="todotaskmanager";
    public static String TABLE_NAME="TaskList";
    public interface Columns{
        public String ID ="task_id";
        public String TASK ="task_text";
        public String CHECK="task_check";
    }
    public static String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+LBR+
            Columns.ID+TYPE_INT+TYPE_PK+TYPE_AI+COMMA
            +Columns.TASK+TYPE_TEXT+COMMA
            +Columns.CHECK+TYPE_BOOLEAN+
            RBR+SEMICOLON;
    public static String DROP_TABLE="DROP TABLE "+TABLE_NAME+SEMICOLON;
    public static boolean addTask(SQLiteDatabase db,String task)
    {
        if(db==null||db.isReadOnly())
        {
            return false;
        }
        ContentValues cv=new ContentValues();
        cv.put(Columns.TASK,task);
        cv.put(Columns.CHECK,false);
        db.insert(TABLE_NAME,null,cv);
      db.close();
        Log.d(TAG, "addTask: ");
        return true;
    }
    public static boolean editTask(SQLiteDatabase db,int tid,String task)
    {
        if(db==null||db.isReadOnly())
        {
            return false;
        }
        ContentValues cv=new ContentValues();
        cv.put(Columns.TASK,task);
       String whereClause=Columns.ID+" = ? ";
        String projections[]={String.valueOf(tid)};
        db.update(TABLE_NAME,cv,whereClause,projections);
       db.close();
        return true;
    }
    public static boolean check(SQLiteDatabase db,int tid,boolean ch)
    {
        if(db==null||db.isReadOnly())
        {
            return false;
        }
        ContentValues cv=new ContentValues();
        if(ch==true)
        cv.put(Columns.CHECK,true);
        else
            cv.put(Columns.CHECK,false);
        String whereClause=Columns.ID+" = ? ";
        String projections[]={String.valueOf(tid)};
        db.update(TABLE_NAME,cv,whereClause,projections);
        return true;
    }
    public static boolean deltask(SQLiteDatabase db,int tid)
    {
        if(db==null||db.isReadOnly())
        {
            return false;
        }
        String whereClause=Columns.ID+" = ? ";
        String projections[]={String.valueOf(tid)};
        db.delete(TABLE_NAME,whereClause,projections);

        return true;
    }
    public static  boolean clearCheck(SQLiteDatabase db)
    {
        if(db==null||db.isReadOnly())
        {
            return false;
        }
        String whereClause=Columns.CHECK+" = ? ";
        String projections[]={"1"};
        db.delete(TABLE_NAME,whereClause,projections);
        return true;
    }
    public static ArrayList<Todo> getAllTasks(SQLiteDatabase db)
    {
        ArrayList<Todo> arr=new ArrayList<Todo>();
        if(db!=null) {
            String projections[] = {Columns.ID, Columns.TASK, Columns.CHECK};
            Cursor c = db.query(TABLE_NAME, projections, null, null, null, null,Columns.ID );
            c.moveToFirst();
            Log.d(TAG, "getAllTasks: count of coluns " + c.getColumnCount() + " rows count " + c.getCount());

            if (c.getCount() > 0) {

                while (!c.isLast()) {
                    Log.d(TAG, "getAllTasks: id index " + c.getColumnIndex(Columns.ID) + "  task index " + c.getColumnIndex(Columns.TASK) + " task check " + c.getColumnIndex(Columns.CHECK));
                    int tid = c.getInt(c.getColumnIndex(Columns.ID));
                    Log.d(TAG, "getAllTasks: task id " + tid);
                    String task = c.getString(c.getColumnIndex(Columns.TASK));
                    Log.d(TAG, "getAllTasks: task details " + task);
                    int check = c.getInt(c.getColumnIndex(Columns.CHECK));
                    Log.d(TAG, "getAllTasks: check value " + check);
                   // boolean b = Boolean.valueOf(check);
                    boolean b=check>0;
                    arr.add(new Todo(tid, task, b));
                    c.moveToNext();
                }
                if (c.isLast()) {
                    int tid = c.getInt(c.getColumnIndex(Columns.ID));
                    String task = c.getString(c.getColumnIndex(Columns.TASK));
                    int check = c.getInt(c.getColumnIndex(Columns.CHECK));
                    boolean b = check>0;
                    Log.d(TAG, "getAllTasks: boolean value"+ b);
                    arr.add(new Todo(tid, task, b));
                }
                c.close();
            }
        }
        return arr;
    }


}
