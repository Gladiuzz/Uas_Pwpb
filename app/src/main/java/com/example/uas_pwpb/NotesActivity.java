package com.example.uas_pwpb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity implements RecyclerViewAdapter.OnUserActionListener {

    DatabaseReference dbr;
    List<data_input> inputan;
    FloatingActionButton btn_input;
    RecyclerView rvData;

    public static final String TITLE = "title";
    public static final String DESC = "desc";
    public static final String ACTION = "action";
    public static final String ID = "id";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        context = this;

        btn_input =  findViewById(R.id.floatingBtn_input);
        rvData =  findViewById(R.id.recyclerView);

        inputan = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvData.setLayoutManager(layoutManager);


        dbr = FirebaseDatabase.getInstance().getReference("Data");
        enter_input();
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                inputan.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    data_input dataInput = dataSnapshot1.getValue(data_input.class);

                    inputan.add(dataInput);
                }

                RecyclerViewAdapter Data_adapter = new RecyclerViewAdapter(NotesActivity.this, inputan,NotesActivity.this);
                rvData.setAdapter(Data_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onUserAction(data_input dataInput) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Choose Option")
//                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        data_input data_input = new data_input();
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString(TITLE, data_input.getTitle());
//                        bundle.putString(DESC, data_input.getDesription());
//                        bundle.putString(ACTION, "Edit");
//
//                        startActivity(new Intent(NotesActivity.this, InsertDataActivity.class).putExtras(bundle));
//                    }
//                })
//                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        DatabaseReference db_delete = FirebaseDatabase.getInstance().getReference("Data")
//                                .child(ID);
//
//                        db_delete.removeValue();
//
//                        Toast.makeText(context, "Delete success", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
    }

    private void enter_input() {
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotesActivity.this, InsertDataActivity.class));
            }
        });

    }


}
