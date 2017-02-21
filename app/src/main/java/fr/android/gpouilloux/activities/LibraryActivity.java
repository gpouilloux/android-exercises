package fr.android.gpouilloux.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

import fr.android.gpouilloux.R;
import fr.android.gpouilloux.adapters.BookRecyclerAdapter;
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
public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        // Plant logger cf. Android Timber
        Timber.plant(new Timber.DebugTree());

        // build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a service
        HenriPotierService service = retrofit.create(HenriPotierService.class);
        Call<List<Book>> call = service.listBooks();

        // enqueue call and display book title
        call.enqueue(new Callback<List<Book>>() {

            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                // log books
                for(Book b : response.body()) {
                    Timber.i("Title: %s", b.getTitle());
                }

                // display book as a list
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bookRecycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(LibraryActivity.this));
                recyclerView.setAdapter(new BookRecyclerAdapter(LayoutInflater.from(LibraryActivity.this), response.body()));
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

}
