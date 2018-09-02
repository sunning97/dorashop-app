package com.neko.giangnguyen.dorashop.Model.Register;

import android.util.Log;

import com.neko.giangnguyen.dorashop.ConnectAPI.DownloadJson;
import com.neko.giangnguyen.dorashop.View.Home.HomeActivity;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Register{
    private DownloadJson downloadJson;
    private HashMap<String,String> paramsInput;
    public Register(){
        downloadJson = new DownloadJson();
    }

    public HashMap<String,String> doRegister(){
        String data = "";
        HashMap<String,String> result = new HashMap<>();

        this.downloadJson.setParams(paramsInput);
        this.downloadJson.setUrl(HomeActivity.API_URL+"register");
        this.downloadJson.execute();

        try {

            data = downloadJson.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (this.downloadJson.getStatusCode()){
            case 401:
                {
                result.put("code","401");
                result.put("json",data);
                }
                break;
            case 500:
                {
                    result.put("code","500");
                    result.put("json",data);
                }
                break;
            case 200:
            {
                result.put("code","200");
                result.put("json",data);
            }
            break;
        }

        return result;
    }

    public HashMap<String, String> getParamsInput() {
        return paramsInput;
    }

    public void setParamsInput(HashMap<String, String> paramsInput) {
        this.paramsInput = paramsInput;
    }
}
