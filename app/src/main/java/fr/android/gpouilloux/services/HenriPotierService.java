package fr.android.gpouilloux.services;

import java.util.List;

import fr.android.gpouilloux.models.Book;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit service fetching a list of books
 *
 * @author Guillaume Pouilloux <gui.pouilloux@gmail.com>
 */
public interface HenriPotierService {

    // GET books which return a List<Book>
    @GET("books")
    Call<List<Book>> listBooks();

}
