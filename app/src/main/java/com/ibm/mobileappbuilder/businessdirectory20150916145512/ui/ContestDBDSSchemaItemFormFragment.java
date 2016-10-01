
package com.ibm.mobileappbuilder.businessdirectory20150916145512.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.presenters.ContestFormPresenter;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.R;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.views.DateTimePicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.util.Date;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.ds.ContestDBDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;
import static ibmmobileappbuilder.analytics.injector.PageViewBehaviorInjector.pageViewBehavior;

public class ContestDBDSSchemaItemFormFragment extends FormFragment<ContestDBDSSchemaItem> {

    private CrudDatasource<ContestDBDSSchemaItem> datasource;

    public static ContestDBDSSchemaItemFormFragment newInstance(Bundle args){
        ContestDBDSSchemaItemFormFragment fr = new ContestDBDSSchemaItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public ContestDBDSSchemaItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new ContestFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

        addBehavior(pageViewBehavior("Contest"));
    }

    @Override
    protected ContestDBDSSchemaItem newItem() {
        return new ContestDBDSSchemaItem();
    }

    @Override
    protected int getLayout() {
        return R.layout.contest_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ContestDBDSSchemaItem item, View view) {
        
        bindString(R.id.duration, item.duration, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.duration = s.toString();
            }
        });
        
        
        bindDateTimePicker(R.id.endtime, item.endTime, new DateTimePicker.DateSelectedListener() {
            @Override
            public void onDateSelected(Date selected) {
                item.endTime = selected;
            }
        });
        
        
        bindString(R.id.name, item.name, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.name = s.toString();
            }
        });
        
        
        bindString(R.id.platform, item.platform, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.platform = s.toString();
            }
        });
        
        
        bindDateTimePicker(R.id.starttime, item.startTime, new DateTimePicker.DateSelectedListener() {
            @Override
            public void onDateSelected(Date selected) {
                item.startTime = selected;
            }
        });
        
        
        bindString(R.id.url, item.url, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.url = s.toString();
            }
        });
        
    }

    @Override
    public Datasource<ContestDBDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("contest-database"),
              URI.create("https://8686dfe4-0f82-4076-85b2-f913867b94a6-bluemix:4cc3822ee1be0c837889b02242eebb034ac6fbd53e5490e5077362dc37f6ac49@8686dfe4-0f82-4076-85b2-f913867b94a6-bluemix.cloudant.com/contest-database"),
              ContestDBDSSchemaItem.class,
              new SearchOptions(),
              null
      );
        return datasource;
    }
}

