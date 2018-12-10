package com.example.arafat.roomdatabasepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddName extends AppCompatActivity {

    private EditText enterFirstName, enterLastName;
    private Button saveButton;

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);

        enterFirstName = findViewById(R.id.enter_first_name);
        enterLastName = findViewById(R.id.enter_last_name);
        saveButton = findViewById(R.id.save_info);

        // RESULT_CANCELED = 0 and RESULT_OK = -1

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(enterFirstName.getText()) && TextUtils.isEmpty(enterLastName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name1 = enterFirstName.getText().toString() + "gg" + enterLastName.getText().toString();
                    replyIntent.putExtra("string", name1);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
