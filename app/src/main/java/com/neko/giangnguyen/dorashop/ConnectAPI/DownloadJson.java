package com.neko.giangnguyen.dorashop.ConnectAPI;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DownloadJson extends AsyncTask<String,Void,String> {
    private String url;
    private String result;
    private HashMap<String,String> params;

    private OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

    public DownloadJson(String url) {
        this.url = url;
        this.params = new HashMap<>();
    }

    public DownloadJson(String url,HashMap<String,String> params) {
        this.url = url;
        this.params = params;
    }

    @Override
    protected String doInBackground(String... strings) {
        if(this.params.isEmpty())
            this.getMethod();
        else
            this.postMethod();
        return this.result;
    }

    @Override
    protected void onPostExecute(String s) {

    }

    private void postMethod(){

        FormBody.Builder formBuilder = new FormBody.Builder();

        for(Map.Entry<String, String> e : this.params.entrySet()) {
            formBuilder.add(e.getKey(), e.getValue());
        }

        RequestBody formBody = formBuilder.build();

        Request request = new Request.Builder()
                .url(this.url)
                .post(formBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            this.result =  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMethod(){
        Request request = new Request.Builder()
                .url(this.url)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            this.result =  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
