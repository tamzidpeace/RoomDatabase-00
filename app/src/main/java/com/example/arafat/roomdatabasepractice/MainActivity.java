package com.example.arafat.roomdatabasepractice;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    //public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    private Button addName;
    private NameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set recyclerview
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //setting up layout-manager and adapter
        mLayoutManager = new LinearLayoutManager(this);
        final NameListAdapter mAdapter = new NameListAdapter(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        // button work

     /*   addName = findViewById(R.id.add_name);

        addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddName.class);
                startActivity(intent);

            }
        });*/

        //setting up data to the recyclerview :

        viewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        viewModel.getAllName().observe(this, new Observer<List<Name>>() {
            @Override
            public void onChanged(@Nullable List<Name> names) {
                mAdapter.setName(names);
            }
        });

    }


}
