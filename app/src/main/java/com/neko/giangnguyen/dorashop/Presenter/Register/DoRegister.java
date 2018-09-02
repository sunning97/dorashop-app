package com.neko.giangnguyen.dorashop.Presenter.Register;

import android.util.Log;

import com.neko.giangnguyen.dorashop.ConnectAPI.DownloadJson;
import com.neko.giangnguyen.dorashop.Model.Register.Register;
import com.neko.giangnguyen.dorashop.View.Auth.Fragment.IRegister;
import com.neko.giangnguyen.dorashop.View.Home.HomeActivity;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class DoRegister implements IDoRegister{
    private IRegister iRegister;
    private Register registerModel;
    public DoRegister(IRegister iRegister){
        this.iRegister = iRegister;
        registerModel = new Register();
    }

    @Override
    public void doRegister(HashMap<String, String> paramsInput) {
        this.registerModel.setParamsInput(paramsInput);
        if(!this.registerModel.doSregister()){
            this.iRegister.emailIsUsed();
        }
    }
}
