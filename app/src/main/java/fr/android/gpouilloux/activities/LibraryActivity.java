package fr.android.gpouilloux.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import fr.android.gpouilloux.R;
import fr.android.gpouilloux.adapters.BookRecyclerAdapter;
import fr.android.gpouilloux.fragments.BookListFragment;
import fr.android.gpouilloux.models.Book;
import fr.android.gpouilloux.services.HenriPotierService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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

        // Plant logger cf. Android Timber
        Timber.plant(new Timber.DebugTree());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout, new BookListFragment(), BookListFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onBookItemClicked() {
        Timber.d("item clicked");
        // TODO open new fragment with book details
    }
}
