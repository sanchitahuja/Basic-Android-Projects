//package com.example.pramo.to_dolist;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.pramo.to_dolist.Dbhelper.Todo;
//import com.example.pramo.to_dolist.Dbhelper.TodoTaskManager;
//
///**
// * Created by pramo on 08-02-2017.
// */
//
//public class addfragment extends Fragment{
//Button addnew;
//    EditText etaddtask;
//    View itemview=null;
//    MainActivity ma=new MainActivity();
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        itemview=inflater.inflate(R.layout.addfragment,container,false);
//        addnew= (Button) itemview.findViewById(R.id.addtasktolistbtn);
//        etaddtask= (EditText) itemview.findViewById(R.id.etaddtask);
//        addnew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              String txt=etaddtask.getText().toString();
//                if(txt!=null&&(!txt.isEmpty()))
//                {
//                   TodoTaskManager.addTask(MainActivity.dbhelper.getWritableDatabase(),txt);
//                    etaddtask.setText("");
//                    Bundle b=new Bundle();
//                    b.putString("YES","y");
//                    setArguments(b);
//                    itemview.setLayoutParams(new ViewGroup.LayoutParams(0,0));
//
////                    ma.refreshtodo();
//                }
//                else
//                {
//                    Toast.makeText(itemview.getContext(),"PLESE ENTER SOMETING APPROPRIATE",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        return itemview;
//    }
//}
