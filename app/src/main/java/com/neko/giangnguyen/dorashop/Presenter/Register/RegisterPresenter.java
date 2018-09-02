package com.neko.giangnguyen.dorashop.Presenter.Register;

import com.neko.giangnguyen.dorashop.Model.Register.Register;
import com.neko.giangnguyen.dorashop.View.Auth.Fragment.IRegisterView;

import java.util.HashMap;

public class RegisterPresenter implements IRegisterPresenter {
    private IRegisterView iRegisterView;
    private Register registerModel;

    public RegisterPresenter(IRegisterView iRegisterView){
        this.iRegisterView = iRegisterView;
        registerModel = new Register();
    }

    @Override
    public void doRegister(HashMap<String, String> paramsInput) {
        this.registerModel.setParamsInput(paramsInput);
        HashMap<String,String> result = this.registerModel.doRegister();

        switch (Integer.parseInt(result.get("code"))){
            case 401: this.iRegisterView.emailIsUsed();
            break;
            case 500: this.iRegisterView.registerFaile();
            break;
            case 200: this.iRegisterView.registerSuccess();
            break;
        }
    }
}
