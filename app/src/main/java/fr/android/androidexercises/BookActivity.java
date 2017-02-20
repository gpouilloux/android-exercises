package fr.android.androidexercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    private String bookName = "Garry Whopper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        final TextView messageTextView = (TextView) findViewById(R.id.messageTextView);
        messageTextView.setText(bookName);

        Button sendNameButton = (Button) findViewById(R.id.sendNameButton);
        sendNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set result to book name in intent
                Intent intentBookName = new Intent();
                intentBookName.putExtra("BOOK_NAME", messageTextView.getText());
                setResult(RESULT_OK, intentBookName);

                // finish current activity
                finish();
            }
        });
    }


}
