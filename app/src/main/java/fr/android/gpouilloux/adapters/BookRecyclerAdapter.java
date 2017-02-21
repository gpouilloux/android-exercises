package fr.android.gpouilloux.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.android.gpouilloux.views.BookItemView;
import fr.android.gpouilloux.R;
import fr.android.gpouilloux.models.Book;

/**
 * Adapter used in a recycler view to display a list of books
 *
 * @author Guillaume Pouilloux <gui.pouilloux@gmail.com>
 */
public class BookRecyclerAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private final List<Book> books;

    public BookRecyclerAdapter(LayoutInflater inflater, List<Book> books) {
        this.inflater = inflater;
        this.books = books;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookItemView bookItemView = (BookItemView) holder.itemView;
        bookItemView.bindView(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}