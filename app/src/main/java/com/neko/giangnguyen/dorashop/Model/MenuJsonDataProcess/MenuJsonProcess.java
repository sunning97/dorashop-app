package com.neko.giangnguyen.dorashop.Model.MenuJsonDataProcess;

import android.util.Log;

import com.neko.giangnguyen.dorashop.ConnectAPI.DownloadJson;
import com.neko.giangnguyen.dorashop.Model.ObjectClass.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MenuJsonProcess {
    private List<Category> listCategory;
    private OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    public MenuJsonProcess(){
        this.listCategory = new ArrayList<>();
    }

    public List<Category> getListCategory() {
        return listCategory;
    }

    public List<Category> parserJsonParent(String data){
        this.listCategory =  this.parseJson(data);

        return this.listCategory;

    }

    private void getSubCategories(){

        FormBody.Builder formBuilder = new FormBody.Builder();

        for (Category category:this.listCategory) {

            formBuilder.add("id",String.valueOf(category.getId()));
            RequestBody formBody = formBuilder.build();
            Request request = new Request.Builder()
                    .url("http://192.168.1.104:8000/api/subcategories")
                    .post(formBody)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                if(!response.body().string().equals(""))
                category.setSubCategories(this.parseJson(response.body().string()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Category> parseJson(String data){
        List<Category> tmp = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            Log.d("AAA",data);

            for (int i = 0; i< jsonArray.length();i++){
                JSONObject object= jsonArray.getJSONObject(i);

                Category category = new Category(
                        object.getInt("id"),
                        object.getString("name"),
                        object.getString("slug"),
                        object.getString("description"),
                        (object.getString("sub_categories").isEmpty()) ? false : true
                );

                tmp.add(category);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tmp;
    }
}
