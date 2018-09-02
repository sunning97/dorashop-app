package com.neko.giangnguyen.dorashop.View.Auth.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.neko.giangnguyen.dorashop.R;

import java.util.regex.Pattern;

public class Login extends Fragment {

    TextInputLayout textEmailLayout;
    EditText emailInput;
    TextInputLayout textPasswordLayout;
    EditText passwordInput;
    Button btnLogin;
    Boolean isValidateNoError = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.auth_login_fragment, container, false);

        this.addControll(view);
        this.validate();
        this.login();
        return view;
    }

    private void addControll(View view) {
        textEmailLayout = view.findViewById(R.id.email_input_layout);
        emailInput = view.findViewById(R.id.input_email);
        textPasswordLayout = view.findViewById(R.id.password_input_layout);
        passwordInput = view.findViewById(R.id.input_password);
        btnLogin = view.findViewById(R.id.btn_login);
    }

    private void validate() {
        emailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateEmail();
                }
            }
        });

        passwordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validatePassword();
                }
            }
        });
    }

    private void validateEmail() {

        if (emailInput.getText().toString().trim().isEmpty()) {
            textEmailLayout.setError("Email không được để trống");
            isValidateNoError = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString().trim()).matches()) {
            textEmailLayout.setError("Vui lòng nhập email đúng định dạng");
            isValidateNoError = false;
        } else {
            isValidateNoError = true;
            textEmailLayout.setError("");
            textEmailLayout.setErrorEnabled(false);
        }

    }

    private void validatePassword() {

        if (passwordInput.getText().toString().trim().isEmpty()) {
            textPasswordLayout.setError("Mật khẩu không được để trống");
            isValidateNoError = false;
        } else if (passwordInput.getText().toString().trim().length() < 6) {
            textPasswordLayout.setError("Mật khẩu phải lớn hơn 6 kí tự");
            isValidateNoError = false;
        } else {
            isValidateNoError = true;
            textPasswordLayout.setError("");
            textPasswordLayout.setErrorEnabled(false);
        }

    }

    private void login() {
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmail();
                validatePassword();
                if (isValidateNoError) {
                    Toast.makeText(getContext(), "Ok Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
