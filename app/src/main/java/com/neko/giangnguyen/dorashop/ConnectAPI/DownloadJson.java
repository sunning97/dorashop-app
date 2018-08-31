package com.neko.giangnguyen.dorashop.ConnectAPI;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

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
    }

    public DownloadJson(String url,HashMap<String,String> params) {
        this.url = url;
        this.params = params;
    }

    @Override
    protected String doInBackground(String... strings) {
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("email","mrcatbro97@gmail.com")
                .addFormDataPart("password","password")
                .setType(MultipartBody.FORM)
                .build();
        Request request = new Request.Builder()
                .url(this.url)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            this.result =  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("AAA",s);
    }
}
