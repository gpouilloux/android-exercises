package fr.android.androidexercises;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        // Get image view and load image form URL
        final ImageView downloadImageView = (ImageView) findViewById(R.id.downloadImageView);
        final String imageURL = "http://img3.wikia.nocookie.net/__cb20120317101541/harrypotter/images/3/37/Gryffindor_Crest.jpg";
        Glide.with(this)
            .load(imageURL)
            .centerCrop()
            .crossFade()
            .into(downloadImageView);
        //
    }

}
