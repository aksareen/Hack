
package com.ibm.mobileappbuilder.businessdirectory20150916145512.presenters;

import com.ibm.mobileappbuilder.businessdirectory20150916145512.R;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.ds.ContestDBDSSchemaItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;
import java.util.Map;
import java.util.HashMap;
import ibmmobileappbuilder.analytics.injector.AnalyticsReporterInjector;

public class ContestDetailPresenter extends BasePresenter implements DetailCrudPresenter<ContestDBDSSchemaItem>,
      Datasource.Listener<ContestDBDSSchemaItem> {

    private final CrudDatasource<ContestDBDSSchemaItem> datasource;
    private final DetailView view;

    public ContestDetailPresenter(CrudDatasource<ContestDBDSSchemaItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(ContestDBDSSchemaItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(ContestDBDSSchemaItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(ContestDBDSSchemaItem item) {
        logCrudAction("deleted", item.getIdentifiableId());
        view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
    private void logCrudAction(String action, String id) {
      Map<String, String> paramsMap = new HashMap<>(3);
      //action will be one of created, updated or deleted
      paramsMap.put("action", action);
      paramsMap.put("entity", "ContestDBDSSchemaItem");
      paramsMap.put("identifier", id);

      AnalyticsReporterInjector.analyticsReporter().sendEvent(paramsMap);
    }
}

