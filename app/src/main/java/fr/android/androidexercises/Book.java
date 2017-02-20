package fr.android.androidexercises;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private String name;
    private String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
    }
}
