package de.tum.in.schlichter.shoprx.ui.explanation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;

import de.tum.in.schlichter.shoprx.R;
import de.tum.in.schlichter.shoprx.ui.explanation.MindMap.ClothingTypeFragment;
import de.tum.in.schlichter.shoprx.ui.explanation.MindMap.ColorFragment;
import de.tum.in.schlichter.shoprx.ui.explanation.MindMap.PriceRangeFragment;

/**
 * Created by Nicksteal on 06.04.2015.
 */
public class HelpActivity extends FragmentActivity {

    public interface InitBundle {
        String PUSHING_VIEW = "pushing_view";
        String PUSHED_VIEW = "pushed_view";
    }
    private String pushingView;
    private String pushedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        setContentView(R.layout.empty_frame);

        pushedView = extras.getString(InitBundle.PUSHED_VIEW);
        pushingView = extras.getString(InitBundle.PUSHING_VIEW);
        Log.d("LOL","LOL pushedView"+pushedView);
        Log.d("LOL","LOL pushingView"+pushingView);
        Log.d("LOL","LOL extras"+extras);
        if (pushedView==null)finish();
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();

// work here to change Activity fragments (add, remove, etc.).  Example here of adding.

        if (pushedView.equalsIgnoreCase("price")){
            fragmentTransaction.add(R.id.emptyframe, new PriceRangeFragment(), "priceRangeFragment");
            fragmentTransaction.commit ();
        }
        else if (pushedView.equalsIgnoreCase("type")){
            fragmentTransaction.add(R.id.emptyframe,new ClothingTypeFragment(), "clothingTypeFragment");
            fragmentTransaction.commit ();
        }
        else if (pushedView.equalsIgnoreCase("color")){
            fragmentTransaction.add(R.id.emptyframe,new ColorFragment(), "colorFragment");
            fragmentTransaction.commit ();
        }
        else if (pushedView.equalsIgnoreCase("label")){
           // fragmentTransaction.add(R.id.emptyframe,new ColorFragment(), "colorFragment");
           // fragmentTransaction.commit ();
        }


    }
}
