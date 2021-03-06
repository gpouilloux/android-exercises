package fr.android.gpouilloux.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.android.gpouilloux.R;
import fr.android.gpouilloux.adapters.BookRecyclerAdapter;
import fr.android.gpouilloux.decorations.BasicDividerItemDecoration;
import fr.android.gpouilloux.models.Book;
import fr.android.gpouilloux.services.HenriPotierService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


/**
 * Fragment displaying a list of books
 *
 * @author Guillaume Pouilloux <gui.pouilloux@gmail.com>
 */
public class BookListFragment extends Fragment {

    private ArrayList<Book> books = new ArrayList<>();
    BookRecyclerAdapter adapter;
    private OnBookItemClickedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // cast context to listener
        if (context instanceof OnBookItemClickedListener) {
            listener = (OnBookItemClickedListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new BookRecyclerAdapter(LayoutInflater.from(getContext()), books, listener);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bookRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new BasicDividerItemDecoration(view.getContext()));
        recyclerView.setAdapter(adapter);

        if (savedInstanceState != null) {
            books.clear();
            books.addAll(savedInstanceState.<Book>getParcelableArrayList("books"));
            adapter.notifyDataSetChanged();
        }
        else if (books.isEmpty()) {
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
                    // display book as a list
                    Timber.d("Received books from Xebia WS");
                    books.clear();
                    books.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Book>> call, Throwable t) {
                    Timber.e(t);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("books", books);
    }

    public interface OnBookItemClickedListener {
        void onBookItemClicked(Book book);
    }
}
