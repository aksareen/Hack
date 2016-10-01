package com.ibm.mobileappbuilder.businessdirectory20150916145512.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.presenters.ContestPresenter;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.SelectionBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.ViewHolder;
import java.util.List;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.ds.ContestDBDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;
import ibmmobileappbuilder.mvp.view.CrudListView;
import ibmmobileappbuilder.ds.CrudDatasource;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;
import static ibmmobileappbuilder.analytics.injector.PageViewBehaviorInjector.pageViewBehavior;

/**
 * "ContestFragment" listing
 */
public class ContestFragment extends ListGridFragment<ContestDBDSSchemaItem> implements CrudListView<ContestDBDSSchemaItem> {

    private CrudDatasource<ContestDBDSSchemaItem> datasource;

    // "Add" button
    private FabBehaviour fabBehavior;

    public static ContestFragment newInstance(Bundle args) {
        ContestFragment fr = new ContestFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
addBehavior(pageViewBehavior("Contest"));
        setPresenter(new ContestPresenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        // Multiple selection
        SelectionBehavior<ContestDBDSSchemaItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<ContestDBDSSchemaItem>() {
            @Override
            public void onSelected(List<ContestDBDSSchemaItem> selectedItems) {
                getPresenter().deleteItems(selectedItems);
            }
        });
        addBehavior(selectionBehavior);
        // FAB button
        fabBehavior = new FabBehaviour(this, R.drawable.ic_add_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().addForm();
            }
        });
        addBehavior(fabBehavior);
    }

    protected SearchOptions getSearchOptions() {
      SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
      return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.contest_item;
    }

    @Override
    protected Datasource<ContestDBDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("contest-database"),
              URI.create("https://8686dfe4-0f82-4076-85b2-f913867b94a6-bluemix:4cc3822ee1be0c837889b02242eebb034ac6fbd53e5490e5077362dc37f6ac49@8686dfe4-0f82-4076-85b2-f913867b94a6-bluemix.cloudant.com/contest-database"),
              ContestDBDSSchemaItem.class,
              getSearchOptions(),
              null
      );
      return datasource;
    }

    @Override
    protected void bindView(ContestDBDSSchemaItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.duration != null){
            title.setText(item.duration);
            
        }
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        if (item.name != null){
            subtitle.setText(item.name);
            
        }
    }

    @Override
    protected void itemClicked(final ContestDBDSSchemaItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(ContestDBDSSchemaItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), ContestDetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showAdd() {
        startActivityForResult(generateIntentToAddOrUpdateItem(null,
                        0,
                        getActivity(),
                        ContestDBDSSchemaItemFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(ContestDBDSSchemaItem item, int position) {
    startActivityForResult(
                generateIntentToAddOrUpdateItem(item,
                        position,
                        getActivity(),
                        ContestDBDSSchemaItemFormActivity.class
                ), Constants.MODE_EDIT
        );
    }
}

