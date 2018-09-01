package com.neko.giangnguyen.dorashop.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import com.neko.giangnguyen.dorashop.R;

@SuppressLint("AppCompatCustomView")
public class LoginEmail extends EditText{

    Drawable clearVisible,clearHidden;

    Boolean isClearVisible = false;

    public LoginEmail(Context context) {
        super(context);
        this.inital();
    }

    public LoginEmail(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.inital();
    }

    public LoginEmail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.inital();
    }

    public LoginEmail(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.inital();
    }

    private void inital(){
        this.clearHidden = ContextCompat.getDrawable(getContext(),R.drawable.ic_none);
        this.clearVisible = ContextCompat.getDrawable(getContext(),R.drawable.ic_clear_black);

        this.setIcon();
    }

    private void setIcon(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawable = this.isClearVisible ? this.clearVisible : this.clearHidden;
        drawable.setAlpha((int) (255 * .60f));
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(lengthAfter == 0 && start == 0){
            this.isClearVisible = false;
            this.inital();
        } else {
            this.isClearVisible = true;
            this.inital();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - getCompoundDrawables()[2].getBounds().width())){
            setText("");
            this.inital();
            invalidate();
        }

        return super.onTouchEvent(event);
    }
}
