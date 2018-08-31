package com.neko.giangnguyen.dorashop.Model.MenuJsonDataProcess;

import com.neko.giangnguyen.dorashop.Model.ObjectClass.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuJsonProcess {
    private String data;
    private List<Category> listCategory;

    public MenuJsonProcess(String data){
        this.data = data;
        this.listCategory = new ArrayList<>();
    }

    public List<Category> getListCategory() {
        return listCategory;
    }

    public List<Category> parserJson(){
        try {
            JSONArray jsonArray = new JSONArray(this.data);

            for (int i = 0; i< jsonArray.length();i++){
                JSONObject object= jsonArray.getJSONObject(i);

                Category category = new Category(
                        object.getInt("id"),
                        object.getString("name"),
                        object.getString("slug"),
                        object.getString("description")
                );

                this.listCategory.add(category);
            }
            
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this.getListCategory();
    }
}
