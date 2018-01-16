package com.creative.sng.app.retrofit;

import com.creative.sng.app.menu.MainFragment;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by GS on 2017-05-31.
 */
public interface RetrofitService {

    @FormUrlEncoded
    @POST("Login/loginCheckApp")
    Call<LoginDatas> loginData(@FieldMap Map<String, Object> fields);

    @FormUrlEncoded
    @POST("{title}/{sub}")
    Call<Datas> sendData(@Path("title") String title, @Path("sub") String sub, @FieldMap Map<String, Object> fields);

    @GET("local/geo/transcoord")
    Call<MapResponseData> geoTransCoord(@Query("x") double longitude, @Query("y") double latitude
            , @Query("apikey") String apikey, @Query("fromCoord") String fromCoord, @Query("toCoord") String toCoord, @Query("output") String output);

    @FormUrlEncoded
    @POST("{title}/{sub}")
    Call<Datas> listData(@Path("title") String title, @Path("sub") String sub, @FieldMap Map<String, Object> fields);

    @GET("{title}/{sub}")
    Call<Datas> listData(@Path("title") String title, @Path("sub") String sub);

    @GET("{title}/{sub}/{path}")
    Call<Datas> listData(@Path("title") String title, @Path("sub") String sub, @Path(value = "path", encoded = true) String path);

    @GET("{title}/{sub}/{path}/{path2}")
    Call<Datas> listData(@Path("title") String title, @Path("sub") String sub, @Path("path") String path, @Path(value = "path2", encoded = true) String path2);

    @GET("{title}/{sub}/{path}/{path2}/{path3}")
    Call<Datas> listData(@Path("title") String title, @Path("sub") String sub, @Path("path") String path, @Path("path2") String path2, @Path("path3") String path3);

    @GET("{title}/{sub}/{path}/{path2}")
    Call<Datas> listDataQ(@Path("title") String title, @Path("sub") String sub, @Path("path") String path, @Path("path2") String path2, @Query("gear_cd") String param);

    @FormUrlEncoded
    @POST("{title}/{sub}")
    Call<Datas> insertData(@Path("title") String title, @Path("sub") String sub, @FieldMap Map<String, Object> fields);

    @FormUrlEncoded
    @PUT("{title}/{sub}")
    Call<Datas> updateData(@Path("title") String title, @Path(value = "sub", encoded = true) String sub, @FieldMap Map<String, Object> fields);

    @DELETE("{title}/{sub}/{path}")
    Call<Datas> deleteData(@Path("title") String title, @Path("sub") String sub, @Path("path") String path);

    @DELETE("{title}/{sub}/{path}/{path2}/{path3}")
    Call<Datas> deleteData(@Path("title") String title, @Path("sub") String sub, @Path("path") String path, @Path("path2") String path2, @Path("path3") String path3);

    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    static final int CONNECT_TIMEOUT = 15;
    static final int WRITE_TIMEOUT = 15;
    static final int READ_TIMEOUT = 15;

    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build();

    public static final Retrofit rest_api = new Retrofit.Builder()
            .baseUrl(MainFragment.ipAddress+MainFragment.contextPath+"/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();


}
