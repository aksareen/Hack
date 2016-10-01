

package com.ibm.mobileappbuilder.businessdirectory20150916145512.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.businessdirectory20150916145512.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;
import static ibmmobileappbuilder.analytics.injector.PageViewBehaviorInjector.pageViewBehavior;

/**
 * CategoriesFragment menu fragment.
 */
public class CategoriesFragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public CategoriesFragment(){
        super();
    }

    // Factory method
    public static CategoriesFragment newInstance(Bundle args) {
        CategoriesFragment fragment = new CategoriesFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          addBehavior(pageViewBehavior("Categories"));
      }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("Upcoming Contest")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(ContestActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Previous Contest")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(PreviousContestActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.categories_item;
    }
}

