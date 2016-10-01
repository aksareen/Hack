
package com.ibm.mobileappbuilder.businessdirectory20150916145512.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.presenters.ContestDetailPresenter;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MailAction;
import ibmmobileappbuilder.actions.OpenUriAction;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.analytics.injector.AnalyticsReporterInjector;
import ibmmobileappbuilder.analytics.AnalyticsReporter;
import static ibmmobileappbuilder.analytics.model.AnalyticsInfo.Builder.analyticsInfo;
import static ibmmobileappbuilder.analytics.injector.PageViewBehaviorInjector.pageViewBehavior;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.ds.ContestDBDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class ContestDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<ContestDBDSSchemaItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<ContestDBDSSchemaItem> datasource;
    private AnalyticsReporter analyticsReporter;
    public static ContestDetailFragment newInstance(Bundle args){
        ContestDetailFragment fr = new ContestDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public ContestDetailFragment(){
        super();
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

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
addBehavior(pageViewBehavior("ContestDetail"));
        analyticsReporter = AnalyticsReporterInjector.analyticsReporter(getActivity());
        // the presenter for this view
        setPresenter(new ContestDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<ContestDBDSSchemaItem>) getPresenter()).editForm(getItem());
            }
        }));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.contestdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ContestDBDSSchemaItem item, View view) {
        if (item.duration != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.duration);
            
        }
        if (item.endTime != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(DateFormat.getMediumDateFormat(getActivity()).format(item.endTime) + " " + DateFormat.getTimeFormat(getActivity()).format(item.endTime));
            
        }
        if (item.name != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.name);
            
        }
        if (item.platform != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.platform);
            
        }
        if (item.startTime != null){
            
            TextView view4 = (TextView) view.findViewById(R.id.view4);
            view4.setText(DateFormat.getMediumDateFormat(getActivity()).format(item.startTime) + " " + DateFormat.getTimeFormat(getActivity()).format(item.startTime));
            bindAction(view4, new MailAction(
            new ActivityIntentLauncher()
            , DateFormat.getMediumDateFormat(getActivity()).format(item.startTime) + " " + DateFormat.getTimeFormat(getActivity()).format(item.startTime)));
        }
        if (item.url != null){
            
            TextView view5 = (TextView) view.findViewById(R.id.view5);
            view5.setText(item.url);
            bindAction(view5, new OpenUriAction(
            new ActivityIntentLauncher()
            , item.url));
        }
    }

    @Override
    protected void onShow(ContestDBDSSchemaItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), ContestDBDSSchemaItemFormActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, Constants.MODE_EDIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu, menu);

        MenuItem item = menu.findItem(R.id.action_delete);
        ColorUtils.tintIcon(item, R.color.textBarColor, getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            ((DetailCrudPresenter<ContestDBDSSchemaItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        ContestDBDSSchemaItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.duration != null ? item.duration : "" ) + "\n" +
                    (item.endTime != null ? DateFormat.getMediumDateFormat(getActivity()).format(item.endTime) + " " + DateFormat.getTimeFormat(getActivity()).format(item.endTime) : "" ) + "\n" +
                    (item.name != null ? item.name : "" ) + "\n" +
                    (item.platform != null ? item.platform : "" ) + "\n" +
                    (item.startTime != null ? DateFormat.getMediumDateFormat(getActivity()).format(item.startTime) + " " + DateFormat.getTimeFormat(getActivity()).format(item.startTime) : "" ) + "\n" +
                    (item.url != null ? item.url : "" ));
        analyticsReporter.sendEvent(analyticsInfo()
                            .withAction("share")
                            .withTarget((item.duration != null ? item.duration : "" ) + "\n" +
                    (item.endTime != null ? DateFormat.getMediumDateFormat(getActivity()).format(item.endTime) + " " + DateFormat.getTimeFormat(getActivity()).format(item.endTime) : "" ) + "\n" +
                    (item.name != null ? item.name : "" ) + "\n" +
                    (item.platform != null ? item.platform : "" ) + "\n" +
                    (item.startTime != null ? DateFormat.getMediumDateFormat(getActivity()).format(item.startTime) + " " + DateFormat.getTimeFormat(getActivity()).format(item.startTime) : "" ) + "\n" +
                    (item.url != null ? item.url : "" ))
                            .withDataSource("ContestDBDS")
                            .build().toMap()
        );
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

