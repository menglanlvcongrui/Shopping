package com.example.administrator.shopping.utils;


import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/28.
 */
public class OkHttpUtils<T> {

    //处理当前类对象的单例模式
    private OkHttpClient client;
    private OkHttpUtils() {
        client = new OkHttpClient();
    }

    private static OkHttpUtils utils;

    public static OkHttpUtils getInstance() {
        if (utils == null) {
            synchronized (OkHttpUtils.class) {
                if (utils == null) {
                    utils = new OkHttpUtils();
                }
            }
        }
        return utils;
    }


    //处理异步的get请求的结果回传
    public interface DataCallback<T>{
        void getData (T t);
    }
    public DataCallback dataCallback;


    // 使用同步方法获取Response对象
    public Response getResponseSync (String url){
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);


        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    //封装一个方法进行get请求
    public String getStringSync (String url){

        try {
            String s = getResponseSync(url).body().string();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    //通过异步方法获取下载结果，该结果类型为泛型指定的任意类型
    public void  getStringAsync (final Activity context, String url, final Class<T> clazz , final DataCallback dataCallback) {
        this.dataCallback = dataCallback;

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("mtag","  onresponse  "+Thread.currentThread().getName());

//json解析
                final T t = new Gson().fromJson(response.body().string(),clazz);


                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//在主线程中通过接口对象调用抽象方法
                        //目的是为了让getData方法中的代码运行在主线程
                        //如果不写runOnUiThread方法的调用，getData中的代码将运行在子线程
                        dataCallback.getData(t);
                    }
                });
            }
        });

    }

    //针对于同步Post请求的封装
    public String postSync (String url, Map<String,String> map) {

        FormBody.Builder fb = new FormBody.Builder();
        Set<String> set =  map.keySet();

        for (String s:set) {
            String value = map.get(s);
            fb.add(s,value);
        }

        RequestBody body = fb.build();

        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();


        Call call = client.newCall(request);


        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



//针对于异步Post请求的封装

    public void postAsync(final Activity activity, String url, Map<String,String> map, final Class<T> clazz, final DataCallback dataCallback){
        this.dataCallback = dataCallback;

        FormBody.Builder fb = new FormBody.Builder();
        Set<String> set =  map.keySet();

        for (String s:set) {
            String value = map.get(s);
            fb.add(s,value);
        }

        RequestBody body = fb.build();

        final Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();


        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final T t = new Gson().fromJson(response.body().string(),clazz);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dataCallback.getData(t);
                    }
                });
            }
        });
    }
}