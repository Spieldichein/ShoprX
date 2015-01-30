package com.uwetrottmann.shopr.context.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ui.MainActivity;

/**
 * Created by Yannick on 30.01.15.
 *
 * This class manages the UI which is there to let the user set the context factors applicable to
 * her scenario.
 */
public class ContextActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_context);

        Button submit = ((Button) findViewById(R.id.button_context_proceed));

        submit.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
