package com.radost_koleva.library;

import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.radost_koleva.library.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    TextView bookTitle, bookAuthor, bookPages;
    Button updateBookBtn, deleteBookBtn;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        bookTitle = findViewById(R.id.updateTitleTxt);
        bookAuthor =findViewById(R.id.updateAuthorTxt);
        bookPages = findViewById(R.id.updatePagesTxt);
        updateBookBtn = findViewById(R.id.updateBookBtn);
        deleteBookBtn = findViewById(R.id.deleteBookBtn);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        updateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper dbHelper = new MyDBHelper(UpdateActivity.this);

                if (TextUtils.isEmpty(bookTitle.getText().toString())
                        || TextUtils.isEmpty(bookAuthor.getText().toString())
                        || TextUtils.isEmpty(bookPages.getText().toString())) {
                    Toast.makeText(UpdateActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    title = bookTitle.getText().toString().trim();
                    author = bookAuthor.getText().toString().trim();
                    pages = bookPages.getText().toString().trim();
                    dbHelper.updateData(id, title, author, pages);
                }
            }
        });

        deleteBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            id = getIntent().getStringExtra("id");
            author = getIntent().getStringExtra("author");
            title = getIntent().getStringExtra("title");
            pages = getIntent().getStringExtra("pages");

            bookTitle.setText(title);
            bookAuthor.setText(author);
            bookPages.setText(pages);
            Log.d("stev", title+" "+author+" "+pages);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure you want to delete " + title + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDBHelper myDb = new MyDBHelper(UpdateActivity.this);
                myDb.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}