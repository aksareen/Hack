
package com.ibm.mobileappbuilder.businessdirectory20150916145512.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface JsonDataDSServiceRest{

	@GET("/app/57ef6bac57acb00300066379/r/jsonDataDS")
	void queryJsonDataDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<JsonDataDSItem>> cb);

	@GET("/app/57ef6bac57acb00300066379/r/jsonDataDS/{id}")
	void getJsonDataDSItemById(@Path("id") String id, Callback<JsonDataDSItem> cb);

	@DELETE("/app/57ef6bac57acb00300066379/r/jsonDataDS/{id}")
  void deleteJsonDataDSItemById(@Path("id") String id, Callback<JsonDataDSItem> cb);

  @POST("/app/57ef6bac57acb00300066379/r/jsonDataDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<JsonDataDSItem>> cb);

  @POST("/app/57ef6bac57acb00300066379/r/jsonDataDS")
  void createJsonDataDSItem(@Body JsonDataDSItem item, Callback<JsonDataDSItem> cb);

  @PUT("/app/57ef6bac57acb00300066379/r/jsonDataDS/{id}")
  void updateJsonDataDSItem(@Path("id") String id, @Body JsonDataDSItem item, Callback<JsonDataDSItem> cb);

  @GET("/app/57ef6bac57acb00300066379/r/jsonDataDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ef6bac57acb00300066379/r/jsonDataDS")
    void createJsonDataDSItem(
        @Part("data") JsonDataDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<JsonDataDSItem> cb);
    
    @Multipart
    @PUT("/app/57ef6bac57acb00300066379/r/jsonDataDS/{id}")
    void updateJsonDataDSItem(
        @Path("id") String id,
        @Part("data") JsonDataDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<JsonDataDSItem> cb);
}

