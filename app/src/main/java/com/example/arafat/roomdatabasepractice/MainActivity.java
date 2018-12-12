package com.example.arafat.roomdatabasepractice;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    private NameViewModel viewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    //public final String TAG = "MainActivity";

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
        FloatingActionButton addName;
        addName = findViewById(R.id.fab);

        addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddName.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

            }
        });

        //setting up data to the recyclerview :

        viewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        viewModel.getAllName().observe(this, new Observer<List<Name>>() {
            @Override
            public void onChanged(@Nullable List<Name> names) {
                mAdapter.setName(names);
            }
        });

        // Add the functionality to swipe items in the
        // recycler view to delete that item

        // ItemTouchHelper  class will help use to complete this task

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int diretion) {
                    int position = viewHolder.getAdapterPosition();

                    Name myName = mAdapter.getNameAtPostion(position);
                    // make a Toast

                    //delete the word
                    viewModel.delete(myName);
            }
        });
        helper.attachToRecyclerView(mRecyclerView);

    }


    // working for AddName Class for getting and fetching data to add into database

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String value1 = data.getStringExtra(AddName.EXTRA_FIRST_NAME);
            String value2 = data.getStringExtra(AddName.EXTRA_LAST_NAME);

            Name name = new Name(value1, value2);
            viewModel.insert(name);
        }

    }

    // work for deleting all notes
    // 1. creating 3 dot item menu
    //2. add option for #delete_all_data
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_data:
                viewModel.deleteAll();
                Toast.makeText(this, "All Data Deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
