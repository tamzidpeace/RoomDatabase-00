package com.example.arafat.roomdatabasepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddName extends AppCompatActivity {

    private EditText enterFirstName, enterLastName;
    private final String  TAG = "AddName";

    public static final String EXTRA_FIRST_NAME = "com.example.android.wordlistsql.FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "com.example.android.wordlistsql.LAST_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);

        Button saveButton;
        enterFirstName = findViewById(R.id.enter_first_name);
        enterLastName = findViewById(R.id.enter_last_name);
        saveButton = findViewById(R.id.save_info);

        // RESULT_CANCELED = 0 and RESULT_OK = -1

        // ajira button er kaj, mejaj kahrap hosse :( :(

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(enterFirstName.getText()) || TextUtils.isEmpty(enterLastName.getText())) {
                    Toast.makeText(getApplicationContext(), "Enter Your Information", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name1 = enterFirstName.getText().toString();
                    String name2 = enterLastName.getText().toString();
                    replyIntent.putExtra(EXTRA_FIRST_NAME, name1);
                    replyIntent.putExtra(EXTRA_LAST_NAME, name2);
                    setResult(RESULT_OK, replyIntent);
                    Log.d(TAG,name1);
                }
                finish();
            }
        });
    }
}
