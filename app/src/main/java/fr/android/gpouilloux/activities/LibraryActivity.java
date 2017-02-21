package fr.android.gpouilloux.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.android.gpouilloux.R;
import fr.android.gpouilloux.fragments.BookDetailsFragment;
import fr.android.gpouilloux.fragments.BookListFragment;
import fr.android.gpouilloux.models.Book;

/**
 * Main activity displaying a list of books
 *
 * @author Guillaume Pouilloux <gui.pouilloux@gmail.com>
 */
public class LibraryActivity extends AppCompatActivity implements BookListFragment.OnBookItemClickedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout, new BookListFragment(), BookListFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onBookItemClicked(Book book) {
        displayBookDetailsWithOrientation(book);
    }

    private void displayBookDetailsWithOrientation(Book book) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            BookDetailsFragment bookDetailsFragment = new BookDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("book", book);
            bookDetailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFrameLayout, bookDetailsFragment, BookDetailsFragment.class.getSimpleName())
                    .addToBackStack("fromBookListToBookDetails")
                    .commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            BookDetailsFragment bookDetailsFragment = new BookDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("book", book);
            bookDetailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.bookDetails, bookDetailsFragment, BookDetailsFragment.class.getSimpleName())
                    .commit();
        }
    }
}
