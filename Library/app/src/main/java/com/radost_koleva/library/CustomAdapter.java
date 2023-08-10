package com.radost_koleva.library;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList bookId, bookTitle, bookAuthor, bookPages;

    CustomAdapter(Activity activity, Context context, ArrayList bookId, ArrayList bookTitle,
                  ArrayList bookAuthor, ArrayList bookPages) {
        this.activity = activity;
        this.context = context;
        this.bookAuthor = bookAuthor;
        this.bookId = bookId;
        this.bookPages = bookPages;
        this.bookTitle = bookTitle;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {

        holder.bookIdTxt.setText(String.valueOf(bookId.get(position)));
        holder.bookTitleTxt.setText(String.valueOf(bookTitle.get(position)));
        holder.bookAuthorTxt.setText(String.valueOf(bookAuthor.get(position)));
        holder.bookPagesTxt.setText(String.valueOf(bookPages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(bookId.get(position)));
                intent.putExtra("title", String.valueOf(bookTitle.get(position)));
                intent.putExtra("author", String.valueOf(bookAuthor.get(position)));
                intent.putExtra("pages", String.valueOf(bookPages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView bookIdTxt, bookTitleTxt, bookAuthorTxt, bookPagesTxt;
        LinearLayout mainLayout;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            bookIdTxt = itemView.findViewById(R.id.bookIdTxt);
            bookTitleTxt = itemView.findViewById(R.id.bookTitleTxt);
            bookPagesTxt = itemView.findViewById(R.id.bookPagesTxt);
            bookAuthorTxt = itemView.findViewById(R.id.bookAuthorTxt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
