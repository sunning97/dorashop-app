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

    private List<Category> parseJson(String data){
        List<Category> tmp = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i< jsonArray.length();i++){
                JSONObject object= jsonArray.getJSONObject(i);
                List<Category> sub = null;
                if(!object.isNull("sub_categories")){
                    String a = object.getString("sub_categories");
                    sub = this.parseJson(a);
                }

                Category category = new Category(
                        object.getInt("id"),
                        object.getString("name"),
                        object.getString("slug"),
                        object.getString("description"),
                        (object.isNull("sub_categories")) ? false : true
                );
                if(!object.isNull("sub_categories")) category.setSubCategories(sub);
                tmp.add(category);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tmp;
    }
}
