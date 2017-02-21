package fr.android.gpouilloux.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.android.gpouilloux.R;
import fr.android.gpouilloux.models.Book;


public class BookDetailsFragment extends Fragment {

    private Book book;
    private ImageView coverImageView;
    private TextView titleTextView;
    private TextView synopsisTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_details_fragment, container, false);

        book = this.getArguments().getParcelable("book");
        coverImageView = (ImageView) view.findViewById(R.id.coverImageView);
        titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        synopsisTextView = (TextView) view.findViewById(R.id.synopsisTextView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView.setText(book.getTitle());
        synopsisTextView.setText("TODO");
        Glide.with(this.getContext())
                .load(book.getCover())
                .centerCrop()
                .crossFade()
                .into(coverImageView);

    }
}
