package com.uwetrottmann.shopr.context.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.Toast;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.context.model.ScenarioContext;
import com.uwetrottmann.shopr.eval.Statistics;
import com.uwetrottmann.shopr.ui.MainActivity;

/**
 * Created by Yannick on 30.01.15.
 *
 * This class manages the UI which is there to let the user set the context factors applicable to
 * her scenario.
 */
public class ContextActivity extends FragmentActivity{

    private ContextShopFragment mShopFragment;
    private ScenarioContext mScenarioContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScenarioContext = ScenarioContext.getInstance();

        setContentView(R.layout.fragment_only_activity);

        if (savedInstanceState != null){
            return;
        }

        mShopFragment = new ContextShopFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_only_container, mShopFragment).commit();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.context, menu);
        //Restart Button disabled here
        return true;
    }

    /**
     * This method is called by the fragment in order to create the next fragment. We could do
     * something like a calculation of the needed sizes for the map or something like this here.
     */
    public void changeToSecondScreen(){
        ContextOtherFragment otherFragment = new ContextOtherFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_only_container, otherFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * This method is called as soon as the context determination is finished. Then we are going to
     * the main activity.
     */
    public void contextDeterminationFinished() {
        mScenarioContext.logScenarioContext();
        Toast.makeText(this, R.string.startRecommendation, Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        Statistics.get().startRecommendingItems(); // Set this, which states, that the real recommendation process begins.
        finish();
    }

}
