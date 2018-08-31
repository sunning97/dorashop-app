package com.neko.giangnguyen.dorashop.View.FlashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.neko.giangnguyen.dorashop.R;
import com.neko.giangnguyen.dorashop.View.Home.HomeActivity;

public class FlashAvtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_avtivity);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent goHomeActivity = new Intent(FlashAvtivity.this, HomeActivity.class);
                    startActivity(goHomeActivity);
                }
            }
        });

        thread.start();
    }
}
