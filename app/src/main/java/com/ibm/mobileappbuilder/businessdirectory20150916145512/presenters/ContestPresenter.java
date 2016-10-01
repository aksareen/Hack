
package com.ibm.mobileappbuilder.businessdirectory20150916145512.presenters;

import com.ibm.mobileappbuilder.businessdirectory20150916145512.R;
import com.ibm.mobileappbuilder.businessdirectory20150916145512.ds.ContestDBDSSchemaItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;
import java.util.Map;
import java.util.HashMap;
import ibmmobileappbuilder.analytics.injector.AnalyticsReporterInjector;

public class ContestPresenter extends BasePresenter implements ListCrudPresenter<ContestDBDSSchemaItem>,
      Datasource.Listener<ContestDBDSSchemaItem>{

    private final CrudDatasource<ContestDBDSSchemaItem> crudDatasource;
    private final CrudListView<ContestDBDSSchemaItem> view;

    public ContestPresenter(CrudDatasource<ContestDBDSSchemaItem> crudDatasource,
                                         CrudListView<ContestDBDSSchemaItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(ContestDBDSSchemaItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<ContestDBDSSchemaItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(ContestDBDSSchemaItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(ContestDBDSSchemaItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(ContestDBDSSchemaItem item) {
        logCrudAction("deleted", item.getIdentifiableId());
        view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
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

