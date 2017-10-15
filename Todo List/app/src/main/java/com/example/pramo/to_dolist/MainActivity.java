package com.example.pramo.to_dolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pramo.to_dolist.Dbhelper.Todo;
import com.example.pramo.to_dolist.Dbhelper.TodoDbHelper;
import com.example.pramo.to_dolist.Dbhelper.TodoTaskManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button addtaskbtn,clearallbtn;
    public final String TAG="MainActivity";
//    Fragment add;

RecyclerView rv;
    FrameLayout f;
  public static TodoDbHelper dbhelper;
    public static ArrayList<Todo> todoArrayList=new ArrayList<Todo>();;
    RecyclerViewAdapter ra ;

    @Override
    protected void onDestroy() {
        dbhelper.close();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        dbhelper.close();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv= (RecyclerView) findViewById(R.id.rv);

       // f= (FrameLayout) findViewById(R.id.fcontainer);
        addtaskbtn= (Button) findViewById(R.id.addtaskbtn);
        clearallbtn= (Button) findViewById(R.id.clearallbtn);
        dbhelper=new TodoDbHelper(this);
        ra= new RecyclerViewAdapter(this,dbhelper);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(ra);
        ra.update();
       //  add=new addfragment();
        //Bundle bundle= add.getArguments();
//        if(bundle!=null)
//        {
//            String y=bundle.getString("YES");
//            if(y.contentEquals("y"))
//             ra.update();
//        }
        addtaskbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent,111);

//                FragmentManager fm=getSupportFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//
//                ft.replace(R.id.fcontainer,add);
//                ft.commit();

            }
        });
        clearallbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoTaskManager.clearCheck(dbhelper.getWritableDatabase());
                ra.update();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "onActivityResult: ");
        if(requestCode==111&&resultCode==RESULT_OK)
            {      Log.d(TAG, "onActivityResult: req");
                if(data!=null)
                {

                    String d= data.getStringExtra("TASK");
                    if(d!=null) {
                        Boolean x=TodoTaskManager.addTask(dbhelper.getWritableDatabase(), d);
                        Log.d(TAG, "onActivityResult: text received "+d);
                        Log.d(TAG, "onActivityResult: TABLE  +"+x.toString());
                        Toast.makeText(MainActivity.this, "SAVED", Toast.LENGTH_SHORT).show();
                        ra.update();

                    }
                }



            }



        super.onActivityResult(requestCode, resultCode, data);
    }
}
