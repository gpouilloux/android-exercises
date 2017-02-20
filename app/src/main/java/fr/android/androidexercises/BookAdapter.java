package fr.android.androidexercises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private final List<Book> books;
    private final LayoutInflater layoutInflater;

    public BookAdapter(Context context, List<Book> books) {
        this.books = books;
        // LayoutInflater.from()
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Book getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookItemView bookItemView;
        if (convertView == null) {
            bookItemView = (BookItemView) this.layoutInflater.inflate(R.layout.custom_view_item_book, parent, false);
        } else {
            bookItemView = (BookItemView) convertView;
        }
        bookItemView.bindView(getItem(position));
        return bookItemView;
    }

}
