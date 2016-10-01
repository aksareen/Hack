

package com.ibm.mobileappbuilder.businessdirectory20150916145512.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ibmmobileappbuilder.analytics.injector.NetworkLoggerInjector;
import ibmmobileappbuilder.analytics.injector.RetrofitResponseNetworkLoggerInjector;

/**
 * "JsonDataDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class JsonDataDS extends AppNowDatasource<JsonDataDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private JsonDataDSService service;

    public static JsonDataDS getInstance(SearchOptions searchOptions){
        return new JsonDataDS(searchOptions);
    }

    private JsonDataDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = JsonDataDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<JsonDataDSItem> listener) {
        if ("0".equals(id)) {
            NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/jsonDataDS", "GET");
            getItems(new Listener<List<JsonDataDSItem>>() {
                @Override
                public void onSuccess(List<JsonDataDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new JsonDataDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
          NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/jsonDataDS"+id, "GET");
            service.getServiceProxy().getJsonDataDSItemById(id, new Callback<JsonDataDSItem>() {
                @Override
                public void success(JsonDataDSItem result, Response response) {
                    RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(response);
                    listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                    RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(error.getResponse());
                    listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<JsonDataDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<JsonDataDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
        NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/jsonDataDS?skip="+skip+"&limit="+limit+"&conditions="+conditions+"&sort="+sort+"&select=null&populate=null", "GET");
        service.getServiceProxy().queryJsonDataDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<JsonDataDSItem>>() {
            @Override
            public void success(List<JsonDataDSItem> result, Response response) {
                RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(response);
                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(error.getResponse());
                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"text1", "text2", "text3"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/jsonDataDS?distinct="+searchStr+"&conditions="+conditions, "GET");
        service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                 RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(response);
                 result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                 RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(error.getResponse());
                 listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(JsonDataDSItem item, Listener<JsonDataDSItem> listener) {
      NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/jsonDataDS", "POST");
              
        if(item.pictureUri != null){
            service.getServiceProxy().createJsonDataDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createJsonDataDSItem(item, callbackFor(listener));
        
    }

    private Callback<JsonDataDSItem> callbackFor(final Listener<JsonDataDSItem> listener) {
      return new Callback<JsonDataDSItem>() {
          @Override
          public void success(JsonDataDSItem item, Response response) {
              RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(response);
              listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
              RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(error.getResponse());
              listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(JsonDataDSItem item, Listener<JsonDataDSItem> listener) {
      NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/jsonDataDS", "PUT");
              
        if(item.pictureUri != null){
            service.getServiceProxy().updateJsonDataDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateJsonDataDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(JsonDataDSItem item, final Listener<JsonDataDSItem> listener) {
        NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/jsonDataDS", "DELETE");
        service.getServiceProxy().deleteJsonDataDSItemById(item.getIdentifiableId(), new Callback<JsonDataDSItem>() {
            @Override
            public void success(JsonDataDSItem result, Response response) {
                RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(response);
                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(error.getResponse());
                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<JsonDataDSItem> items, final Listener<JsonDataDSItem> listener) {
        NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/jsonDataDS", "POST");
        service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<JsonDataDSItem>>() {
            @Override
            public void success(List<JsonDataDSItem> item, Response response) {
                RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(response);
                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                RetrofitResponseNetworkLoggerInjector.retrofitNetworkLogger().logResponse(error.getResponse());
                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<JsonDataDSItem> items){
        List<String> ids = new ArrayList<>();
        for(JsonDataDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

