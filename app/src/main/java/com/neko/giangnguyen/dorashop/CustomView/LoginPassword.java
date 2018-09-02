package com.neko.giangnguyen.dorashop.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.neko.giangnguyen.dorashop.R;

@SuppressLint("AppCompatCustomView")
public class LoginPassword extends EditText{
    Drawable eyeVisible,eyeHidden;

    Boolean isPasswordVisible = false;
    Boolean eye = false;
    Boolean isValidator = false;

    public LoginPassword(Context context) {
        super(context);
        this.inital(null);
    }

    public LoginPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.inital(attrs);
    }

    public LoginPassword(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.inital(attrs);
    }

    public LoginPassword(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.inital(attrs);
    }

    private void inital(AttributeSet attributeSet){

        if(attributeSet != null){
            TypedArray typedArray = getContext()
                    .getTheme()
                    .obtainStyledAttributes(attributeSet,R.styleable.LoginPassword,0,0);
            this.eye = typedArray.getBoolean(R.styleable.LoginPassword_eyeHidden,false);
            this.isValidator = typedArray.getBoolean(R.styleable.LoginPassword_validator,false);

            typedArray.recycle();
        }
        eyeHidden = ContextCompat.getDrawable(getContext(),R.drawable.visibility_black);
        eyeVisible = ContextCompat.getDrawable(getContext(),R.drawable.visibility_off_black);
        this.setIcon();
    }

    private void setIcon(){
        setInputType(InputType.TYPE_CLASS_TEXT | (this.isPasswordVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawable = this.eye && !this.isPasswordVisible ? this.eyeVisible : this.eyeHidden;
        drawable.setAlpha((int) (255 * .60f));
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - getCompoundDrawables()[2].getBounds().width())){
            this.isPasswordVisible = !this.isPasswordVisible;
            this.setIcon();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
