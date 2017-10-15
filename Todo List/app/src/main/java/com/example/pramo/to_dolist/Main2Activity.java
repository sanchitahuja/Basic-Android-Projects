package com.example.pramo.to_dolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    public final String TAG="Main2";
EditText etaddtask;
    Button addtasktolistbtn;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i=getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etaddtask= (EditText) findViewById(R.id.etaddtask);
        addtasktolistbtn= (Button) findViewById(R.id.addtasktolistbtn);
        addtasktolistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt=etaddtask.getText().toString();
                Log.d(TAG, "onClick: txt"+txt);
                if(txt!=null&&!txt.isEmpty())
                {
                    Log.d(TAG, "onClick: in the if block of txt null one ");
               Intent intent=new Intent();
                       intent.putExtra("TASK",txt);
                setResult(RESULT_OK,intent);
            //    Intent j=new Intent(Main2Activity.this,MainActivity.class);
//               startActivityForResult(j,1111);
                    finish();
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"IT CANNOT BE EMPTY",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
