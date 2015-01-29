package com.uwetrottmann.shopr.stereotype.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.stereotype.controller.StereotypeDeterminator;
import com.uwetrottmann.shopr.stereotype.dto.StereotypeForm;
import com.uwetrottmann.shopr.stereotype.stereotypes.AbstractStereotype;
import com.uwetrottmann.shopr.stereotype.user.User;

import java.util.List;

/**
 * Created by yannick on 29.01.15.
 *
 * The Activity managing the stereotype selection. It uses the two fragments in order to build the appropriate
 * screens.
 */
public class StereotypeActivity extends FragmentActivity implements
        StereotypeFormFragment.OnStereotypeFormSubmit {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stereotype_activity);

        if (findViewById(R.id.stereotype_fragment_container) != null) {
            // no large screen

            if (savedInstanceState != null) {
                return;
            }

            StereotypeFormFragment firstFragment = new StereotypeFormFragment();

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            transaction.add(R.id.stereotype_fragment_container, firstFragment)
                    .commit();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stereotype, menu);
        MenuItem restartButton = menu.findItem(R.id.action_restart);
        restartButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) { //FIXME Restart Button - set default behavior
                //Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                //startActivity(intent);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onSubmit(StereotypeForm form) {
        StereotypeDeterminator determinator = new StereotypeDeterminator();
        List<AbstractStereotype> stereotypes = determinator
                .determinePotentialStereotypes(form);
        User.getUser().setPotentialStereotypes(stereotypes);
        Log.i(StereotypeActivity.class.toString(),
                "Determined stereotypes are "
                        + stereotypes.get(0).getStereotype().name() + ", "
                        + stereotypes.get(1).getStereotype().name() + " and "
                        + stereotypes.get(2).getStereotype().name() + ".");
        StereotypeStyleFragment styleFragment = new StereotypeStyleFragment();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.stereotype_fragment_container, styleFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();
    }
}
