package com.example.uas_pwpb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertDataActivity extends AppCompatActivity {

    DatabaseReference dbr;
    EditText edt_Title, edt_desc;
    Button btn_submit;
    String Data_id, ACTION,title,desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        dbr = FirebaseDatabase.getInstance().getReference("Data");
        inicomponents();

//        Bundle bundle = getIntent().getExtras();
//        final String title = bundle.getString(NotesActivity.TITLE);
//        final String desc = bundle.getString(NotesActivity.DESC);
//        final String ACTION = bundle.getString(NotesActivity.ACTION);

//        if (ACTION == "Edit"){
//            btn_submit.setText("Edit");
//            edt_Title.setText(title);
//            edt_desc.setText(desc);
//        }


        addData();
//        updateData();
    }

    private void addData() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edt_Title.getText().toString().trim();
                String desc = edt_desc.getText().toString().trim();
                SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy' 'hh:mm:ss");
                String date_n_time = date.format(new Date());

                if (!TextUtils.isEmpty(title)) {
                    String id = dbr.push().getKey();

                    data_input dataInput = new data_input(date_n_time, id, title, desc);
                    dbr.child(id).setValue(dataInput);

                    Toast.makeText(InsertDataActivity.this, "Data Added ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InsertDataActivity.this, "you should add Tittle", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void updateData() {
        String label = btn_submit.getText().toString();
        if (label.equals("Edit")) {
            String title = edt_Title.getText().toString().trim();
            String desc = edt_desc.getText().toString().trim();
            SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy' 'hh/mm/ss");
            String date_n_time = date.format(new Date().toString());

            if (!TextUtils.isEmpty(title)) {
                edt_Title.setError("error");
            }
            updates(Data_id,title,desc,date_n_time);
        }
    }

    private boolean updates(String id, String title, String desc, String date_n_time) {
        DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference("Data")
                .child(id);

        data_input dataInput = new data_input(id, title, desc, date_n_time);

        dbreference.setValue(dataInput);

        Toast.makeText(this, "Data Update Success ", Toast.LENGTH_SHORT).show();

        return true;
    }


    private void inicomponents() {
        edt_Title = (EditText) findViewById(R.id.edt_Tittle);
        edt_desc = (EditText) findViewById(R.id.edt_desc);
        btn_submit = (Button) findViewById(R.id.btn_submit);
    }
}
