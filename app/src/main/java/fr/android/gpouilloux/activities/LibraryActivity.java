package fr.android.gpouilloux.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fr.android.gpouilloux.R;
import fr.android.gpouilloux.fragments.BookDetailsFragment;
import fr.android.gpouilloux.fragments.BookListFragment;
import fr.android.gpouilloux.models.Book;
import timber.log.Timber;

/**
 * Main activity displaying a list of books
 *
 * @author Guillaume Pouilloux <gui.pouilloux@gmail.com>
 */
public class LibraryActivity extends AppCompatActivity implements BookListFragment.OnBookItemClickedListener {

    private Book selectedBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        BookListFragment bookListFragment;

        if (savedInstanceState != null) {
            selectedBook = savedInstanceState.getParcelable("book");
            displayBookDetailsWithOrientation();
            bookListFragment = (BookListFragment) getSupportFragmentManager().findFragmentByTag(BookListFragment.class.getSimpleName());
        } else {
            Timber.plant(new Timber.DebugTree());
            bookListFragment = new BookListFragment();

        }

        Toolbar appToolbar = (Toolbar) findViewById(R.id.appToolbar);
        if (appToolbar != null) {
            appToolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAppBarTitle));
            setSupportActionBar(appToolbar);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout, bookListFragment, BookListFragment.class.getSimpleName())
                .commit();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("book", selectedBook);
    }

    @Override
    public void onBookItemClicked(Book book) {
        selectedBook = book;
        displayBookDetailsWithOrientation();
    }

    private void displayBookDetailsWithOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            BookDetailsFragment bookDetailsFragment = new BookDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("book", selectedBook);
            bookDetailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFrameLayout, bookDetailsFragment, BookDetailsFragment.class.getSimpleName())
                    .addToBackStack("fromBookListToBookDetails")
                    .commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            BookDetailsFragment bookDetailsFragment = new BookDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("book", selectedBook);
            bookDetailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.bookDetails, bookDetailsFragment, BookDetailsFragment.class.getSimpleName())
                    .commit();
        }
    }
}
