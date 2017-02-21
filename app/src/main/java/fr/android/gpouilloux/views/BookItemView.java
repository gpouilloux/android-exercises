package fr.android.gpouilloux.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.android.gpouilloux.R;
import fr.android.gpouilloux.models.Book;

/**
 * Custom view displaying a book with a linear layout
 *
 * @author Guillaume Pouilloux <gui.pouilloux@gmail.com>
 */
public class BookItemView extends LinearLayout {

    private TextView titleTextView;
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
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        coverImageView = (ImageView) findViewById(R.id.coverImageView);
    }

    public void bindView(Book book) {
        titleTextView.setText(book.getTitle());
        Glide.with(this.getContext())
                .load(book.getCover())
                .centerCrop()
                .crossFade()
                .into(coverImageView);
    }


}
