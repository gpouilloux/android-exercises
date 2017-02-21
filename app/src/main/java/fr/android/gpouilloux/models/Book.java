package fr.android.gpouilloux.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * POJO describing the Book model
 *
 * @author Guillaume Pouilloux <gui.pouilloux@gmail.com>
 */
public class Book implements Parcelable {

    private String isbn;
    private String title;
    private String price;
    private String cover;
    private String[] synopsis;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String[] getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String[] synopsis) {
        this.synopsis = synopsis;
    }

    public String getReadableSynopsis() {
        StringBuilder builder = new StringBuilder();
        for(String s : synopsis) {
            builder.append(s);
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return isbn.equals(book.isbn);

    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    protected Book(Parcel parcel) {
        isbn = parcel.readString();
        title = parcel.readString();
        price = parcel.readString();
        cover = parcel.readString();
        synopsis = (String[]) parcel.readArray(String.class.getClassLoader());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isbn);
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(cover);
        dest.writeArray(synopsis);
    }

}
