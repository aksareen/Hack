

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
 * "GymsScreen1DS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class GymsScreen1DS extends AppNowDatasource<GymsScreen1DSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private GymsScreen1DSService service;

    public static GymsScreen1DS getInstance(SearchOptions searchOptions){
        return new GymsScreen1DS(searchOptions);
    }

    private GymsScreen1DS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = GymsScreen1DSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<GymsScreen1DSItem> listener) {
        if ("0".equals(id)) {
            NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/gymsScreen1DS", "GET");
            getItems(new Listener<List<GymsScreen1DSItem>>() {
                @Override
                public void onSuccess(List<GymsScreen1DSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new GymsScreen1DSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
          NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/gymsScreen1DS"+id, "GET");
            service.getServiceProxy().getGymsScreen1DSItemById(id, new Callback<GymsScreen1DSItem>() {
                @Override
                public void success(GymsScreen1DSItem result, Response response) {
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
    public void getItems(final Listener<List<GymsScreen1DSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<GymsScreen1DSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
        NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/gymsScreen1DS?skip="+skip+"&limit="+limit+"&conditions="+conditions+"&sort="+sort+"&select=null&populate=null", "GET");
        service.getServiceProxy().queryGymsScreen1DSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<GymsScreen1DSItem>>() {
            @Override
            public void success(List<GymsScreen1DSItem> result, Response response) {
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
        return new String[]{"name", "description", "phone", "address", "rating"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/gymsScreen1DS?distinct="+searchStr+"&conditions="+conditions, "GET");
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
    public void create(GymsScreen1DSItem item, Listener<GymsScreen1DSItem> listener) {
      NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/gymsScreen1DS", "POST");
              
        if(item.pictureUri != null){
            service.getServiceProxy().createGymsScreen1DSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createGymsScreen1DSItem(item, callbackFor(listener));
        
    }

    private Callback<GymsScreen1DSItem> callbackFor(final Listener<GymsScreen1DSItem> listener) {
      return new Callback<GymsScreen1DSItem>() {
          @Override
          public void success(GymsScreen1DSItem item, Response response) {
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
    public void updateItem(GymsScreen1DSItem item, Listener<GymsScreen1DSItem> listener) {
      NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/gymsScreen1DS", "PUT");
              
        if(item.pictureUri != null){
            service.getServiceProxy().updateGymsScreen1DSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateGymsScreen1DSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(GymsScreen1DSItem item, final Listener<GymsScreen1DSItem> listener) {
        NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/gymsScreen1DS", "DELETE");
        service.getServiceProxy().deleteGymsScreen1DSItemById(item.getIdentifiableId(), new Callback<GymsScreen1DSItem>() {
            @Override
            public void success(GymsScreen1DSItem result, Response response) {
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
    public void deleteItems(List<GymsScreen1DSItem> items, final Listener<GymsScreen1DSItem> listener) {
        NetworkLoggerInjector.networkLogger().logRequest("https://ibm-pods.buildup.io/app/57ef6bac57acb00300066379/r/gymsScreen1DS", "POST");
        service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<GymsScreen1DSItem>>() {
            @Override
            public void success(List<GymsScreen1DSItem> item, Response response) {
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

    protected List<String> collectIds(List<GymsScreen1DSItem> items){
        List<String> ids = new ArrayList<>();
        for(GymsScreen1DSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

