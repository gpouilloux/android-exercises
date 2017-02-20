package fr.android.androidexercises;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK_ID = "book_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        int bookId = getIntent().getIntExtra(BookActivity.EXTRA_BOOK_ID, 0);
        Toast.makeText(
                this,
                getResources().getString(R.string.opened_book, bookId),
                Toast.LENGTH_SHORT)
                .show();
    }
}
