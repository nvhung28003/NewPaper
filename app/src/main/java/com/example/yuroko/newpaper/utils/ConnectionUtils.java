package com.example.yuroko.newpaper.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ConnectionUtils {
    public final static String BASE_URL = "http://api.feedytv.com/";
    public static void getHome(RequestParams params, JsonHttpResponseHandler responseHandler)
    {
        String key= "service/merge-data/article/list-article";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key,params,responseHandler);
    }
    public static void getHome_last_publish_time(String url,RequestParams params, JsonHttpResponseHandler responseHandler)
    {
        String key= "service/merge-data/article/list-article?last_publish_time=";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key+url,params,responseHandler);
    }

    public static void get(String url,RequestParams params, JsonHttpResponseHandler responseHandler)
    {
        String key= "service/merge-data/article/list-article?category_id=";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key+url,params,responseHandler);
    }
    public static void get_last_publish_time(String url,String url2,RequestParams params, JsonHttpResponseHandler responseHandler)
    {
        String key= "service/merge-data/article/list-article?category_id=";
        String key2="&last_publish_time=";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key+url+key2+url2,params,responseHandler);
    }
    public static void getallcategory(RequestParams params, JsonHttpResponseHandler responseHandler)
    {
        String key= "service/category/all-category";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key,params,responseHandler);
    }
    public static void getdetail(String url,RequestParams params, JsonHttpResponseHandler responseHandler)
    {
        String key= "api/article/show/";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key+url,params,responseHandler);
    }
    public static void getbottomarticle(String id,String category_id,String site_name,RequestParams params, JsonHttpResponseHandler responseHandler)
    {
        String key = "/service/merge-data/article/bottom-article";
        String key1= "?id=";
        String key2="&category_id=";
        String key3 = "&site_name=";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key+key1+id+key2+category_id+key3+site_name,params,responseHandler);
    }
    public static void getSeach(String search,RequestParams params,JsonHttpResponseHandler responseHandler)
    {
        String key = "service/merge-data/article/search";
        String key1= "?search=";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key+key1+search,params,responseHandler);
    }
    public static void getMoreSeach(String search,String last_publish_time,RequestParams params,JsonHttpResponseHandler responseHandler)
    {
        String key = "service/merge-data/article/search";
        String key1= "?search=";
        String key2= "&last_publish_time=";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL+key+key1+search+key2+last_publish_time,params,responseHandler);
    }
}
