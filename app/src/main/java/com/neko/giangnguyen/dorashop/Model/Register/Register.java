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

    public Boolean doSregister(){
        String data = "";
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

        if(this.downloadJson.getStatusCode() == 401){
            return false;
        }

        return true;
    }

    public HashMap<String, String> getParamsInput() {
        return paramsInput;
    }

    public void setParamsInput(HashMap<String, String> paramsInput) {
        this.paramsInput = paramsInput;
    }
}
