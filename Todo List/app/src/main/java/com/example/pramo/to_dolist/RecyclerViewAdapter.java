package com.example.pramo.to_dolist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pramo.to_dolist.Dbhelper.Todo;
import com.example.pramo.to_dolist.Dbhelper.TodoDbHelper;
import com.example.pramo.to_dolist.Dbhelper.TodoTaskManager;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by pramo on 08-02-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
public final String TAG="RECYCLER VIEW";
    Button editbtn,checkbtn2,delbtn;
    EditText et;
//    Todo td;
    private Context context;
    TodoDbHelper dbHelper;
//    MainActivity ma=new MainActivity();
    public RecyclerViewAdapter(Context context,TodoDbHelper dbHelper) {
        
        this.context = context;
        this.dbHelper=dbHelper;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.task,parent,false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
       final Todo td=MainActivity.todoArrayList.get(position);
        final int pos=position;
        Log.d(TAG, "onBindViewHolder: position"+position);
        Log.d(TAG, "onBindViewHolder: id"+td.getTid());
        Log.d(TAG, "onBindViewHolder: task"+td.getTask());
        Log.d(TAG, "onBindViewHolder: check"+td.isCheck());


        et=holder.et;
        et.setEnabled(false);
        et.setText(td.getTask());
        editbtn=holder.editbtn;
        checkbtn2=holder.checkbtn;
        if(td.isCheck())
            checkbtn2.setText("CHECKED");
        else
            checkbtn2.setText("CHECK");
        delbtn=holder.delbtn;
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task =td.getTask();
                int id =td.getTid();
                Log.d(TAG, "onClick: position "+position  );
                Log.d(TAG, "onClick: task "+td.getTask());
                Log.d(TAG, "onClick: id "+td.getTid());
                TodoTaskManager.deltask(dbHelper.getWritableDatabase(),td.getTid());
                Toast.makeText(context,"DELETED",Toast.LENGTH_SHORT);
                update();
            }
        });
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task =td.getTask();
                int id =td.getTid();
                Toast.makeText(context,"CHANGES start",Toast.LENGTH_SHORT);

                if(editbtn.getText().equals("EDIT")) {
                    et.setEnabled(true);
                    et.setCursorVisible(true);
                    editbtn.setText("SAVE");

                }
                else
                {
                    String txt=et.getText().toString();
                    if(txt!=null&&!txt.isEmpty()) {
                        TodoTaskManager.editTask(dbHelper.getWritableDatabase(), MainActivity.todoArrayList.get(position).getTid(),txt);
                        editbtn.setText("EDIT");
                        et.setCursorVisible(false);
                        et.setEnabled(false);
                        Toast.makeText(context,"CHANGES SAVED",Toast.LENGTH_SHORT);
                        update();


                    }
                }
            }
        });
        checkbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");

                if(checkbtn2.getText().toString().equalsIgnoreCase("CHECK"))
                {
                    Log.d(TAG, "onClick: check  string");
                    checkbtn2.setText("CHECKED");
                    //checkbtn2.setla
                   boolean c= TodoTaskManager.check(dbHelper.getWritableDatabase(),MainActivity.todoArrayList.get(position).getTid(),true);
                    Log.d(TAG, "onClick: status of table check "+c);


                    Log.d(TAG, "onClick: TOAST ");
                    Toast.makeText(context,"CHECKED",Toast.LENGTH_LONG).show();
                    update();
                }
                else
                {
                    Log.d(TAG, "onClick: Uncheck");
                    TodoTaskManager.check(dbHelper.getWritableDatabase(),MainActivity.todoArrayList.get(position).getTid(),false);
                    checkbtn2.setText("CHECK");
                    Toast.makeText(context,"UNCHECKED",Toast.LENGTH_SHORT).show();
                    update();

                }

            }
        });
    }
  

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+MainActivity.todoArrayList.size());return MainActivity.todoArrayList.size();
    }

    static class Holder extends RecyclerView.ViewHolder{
        EditText et;
        Button editbtn,checkbtn,delbtn;

        public Holder(View itemView) {
            super(itemView);
            et= (EditText) itemView.findViewById(R.id.ettask);
            editbtn= (Button) itemView.findViewById(R.id.edittaskbtn);
            delbtn=(Button) itemView.findViewById(R.id.deletetaskbtn);
            checkbtn=(Button) itemView.findViewById(R.id.checkbtn);
        }
    }
    public void update()
    {
        if(  MainActivity.todoArrayList==null)
            Log.d(TAG, "update: todoarraylist null hai  ");
        if(dbHelper==null)
            Log.d(TAG, "update: dbhelper null hai  ");

    MainActivity.todoArrayList=TodoTaskManager.getAllTasks(dbHelper.getReadableDatabase());
        notifyDataSetChanged();
    }
}
