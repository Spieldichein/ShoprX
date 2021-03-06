package de.tum.in.schlichter.shoprx.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.tum.in.schlichter.shoprx.R;

/**
 * Created by Nicksteal on 11.04.2015.
 */
public class ImageActivity extends Activity {

    public interface InitBundle2 {
        String IMAGE_URL = "image_url";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_image);
        ImageView imageView = (ImageView) findViewById(R.id.simpleImage);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        String image_url = extras.getString(InitBundle2.IMAGE_URL);
        Picasso.with(this).load(image_url).into(imageView);
        getActionBar().setHomeButtonEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setBackgroundDrawable(null);

    }
}