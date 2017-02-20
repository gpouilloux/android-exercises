package fr.android.androidexercises;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookItemView extends LinearLayout {

    private TextView nameTextView;
    private TextView priceTextView;
    private ImageView coverImageView;

    public BookItemView(Context context) {
        this(context, null);
    }

    public BookItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // findViewById()
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        priceTextView = (TextView) findViewById(R.id.priceTextView);
        coverImageView = (ImageView) findViewById(R.id.coverImageView);
    }

    public void bindView(Context context, Book book) {
        // setText()
        final String imageURL = "http://img3.wikia.nocookie.net/__cb20120317101541/harrypotter/images/3/37/Gryffindor_Crest.jpg";
        nameTextView.setText(book.name);
        priceTextView.setText(String.valueOf(book.price));
        Glide.with(context)
                .load(imageURL)
                .centerCrop()
                .crossFade()
                .into(coverImageView);
    }
}
