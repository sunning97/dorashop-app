package com.neko.giangnguyen.dorashop.View.Auth.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.neko.giangnguyen.dorashop.R;

import java.util.regex.Pattern;

public class Register extends Fragment{

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=!])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");


    TextInputLayout fisrt_name_input_layout;
    TextInputLayout last_name_input_layout;
    TextInputLayout email_input_layout;
    TextInputLayout password_input_layout;
    TextInputLayout password_input_confirm_layout;
    EditText first_name_input;
    EditText last_name_input;
    EditText email_input;
    EditText password_input;
    EditText password_confirm_input;
    Button btnRegister;
    Boolean isValidateNoError = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auth_register_fragment,container,false);
        this.addControll(view);
        this.validate();
        this.register();
        return view;
    }

    private void addControll(View view){
        fisrt_name_input_layout = view.findViewById(R.id.first_name_input_layout);
        last_name_input_layout = view.findViewById(R.id.last_name_input_layout);
        email_input_layout = view.findViewById(R.id.email_input_layout);
        password_input_layout = view.findViewById(R.id.password_input_layout);
        password_input_confirm_layout = view.findViewById(R.id.password_input_confirm_layout);
        email_input = view.findViewById(R.id.input_email);
        first_name_input = view.findViewById(R.id.input_first_name);
        last_name_input = view.findViewById(R.id.input_last_name);
        password_input = view.findViewById(R.id.input_password);
        password_confirm_input = view.findViewById(R.id.input_password_confirm);
        btnRegister = view.findViewById(R.id.btn_register);
    }

    private void validate(){
        this.first_name_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateFirstName();
                }
            }
        });

        this.last_name_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateLastName();
                }
            }
        });

        this.email_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateEmail();
                }
            }
        });

        this.password_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validatePassword();
                }
            }
        });

        this.password_confirm_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validatePasswordConfirm();
                }
            }
        });

    }

    private void validateFirstName(){
        if(this.first_name_input.getText().toString().trim().isEmpty()){
            this.fisrt_name_input_layout.setError("Vui lòng nhập vào họ và tên đệm");
            this.isValidateNoError = false;
        } else {
            this.fisrt_name_input_layout.setErrorEnabled(false);
            this.fisrt_name_input_layout.setError("");
            this.isValidateNoError = true;
        }
    }
    private void validateLastName(){
        if(this.last_name_input.getText().toString().trim().isEmpty()){
            this.last_name_input_layout.setError("Vui lòng nhập vào tên");
            this.isValidateNoError = false;
        } else {
            this.last_name_input_layout.setErrorEnabled(false);
            this.last_name_input_layout.setError("");
            this.isValidateNoError = true;
        }
    }
    private void validateEmail(){
        if(this.email_input.getText().toString().trim().isEmpty()){
            this.email_input_layout.setError("Vui lòng nhập vào email");
            this.isValidateNoError = false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email_input.getText().toString().trim()).matches()){
            this.email_input_layout.setError("Vui lòng nhập email đúng định dạng");
            this.isValidateNoError = false;
        } else {
            this.email_input_layout.setErrorEnabled(false);
            this.email_input_layout.setError("");
            this.isValidateNoError = false;
        }
    }
    private void validatePassword(){
        if(this.password_input.getText().toString().trim().isEmpty()){
            this.password_input_layout.setError("Vui lòng nhập vào mật khẩu");
            this.isValidateNoError = false;
        } else if(!PASSWORD_PATTERN.matcher(this.password_input.getText().toString().trim()).matches()){
            this.password_input_layout.setError("Mật khẩu không đúng yêu cầu");
            this.isValidateNoError = false;
        } else {
            this.password_input_layout.setErrorEnabled(false);
            this.password_input_layout.setError("");
            this.isValidateNoError = true;
        }
    }
    private void validatePasswordConfirm(){
        if(this.password_confirm_input.getText().toString().trim().isEmpty()){
            this.password_input_confirm_layout.setError("Vui lòng nhập vào mật khẩu");
            this.isValidateNoError = false;
        } else if(this.password_confirm_input.getText().toString().trim().compareTo(this.password_input.getText().toString().trim()) != 0){
            this.password_input_confirm_layout.setError("Mật khẩu không khớp");
            this.isValidateNoError = false;
        } else {
            this.password_input_layout.setErrorEnabled(false);
            this.password_input_layout.setError("");
            this.isValidateNoError = true;
        }
    }

    private void register(){
        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFirstName();
                validateLastName();
                validateEmail();
                validatePassword();
                validatePasswordConfirm();

                if(isValidateNoError){
                    Toast.makeText(getContext(),"do register",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
