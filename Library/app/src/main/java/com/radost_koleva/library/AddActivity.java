package com.radost_koleva.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText addTitleTxt, addAuthorTxt, addPagesTxt;
    Button addBookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addAuthorTxt = findViewById(R.id.addAuthorTxt);
        addTitleTxt = findViewById(R.id.addTitleTxt);
        addPagesTxt = findViewById(R.id.addPagesTxt);
        addBookBtn = findViewById(R.id.addBookBtn);

        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db = new MyDBHelper(AddActivity.this);

                if (TextUtils.isEmpty(addTitleTxt.getText().toString())
                        || TextUtils.isEmpty(addAuthorTxt.getText().toString())
                        || TextUtils.isEmpty(addPagesTxt.getText().toString())) {
                    Toast.makeText(AddActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    db.addBook(addTitleTxt.getText().toString().trim(),
                        addAuthorTxt.getText().toString().trim(),
                        Integer.valueOf(addPagesTxt.getText().toString().trim()));
                }
            }
        });
    }
}