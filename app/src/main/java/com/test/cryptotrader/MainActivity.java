package com.test.cryptotrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button nextButton;
    public static final String EXTRA_NEXT_SCREEN = "NEXT.SCREEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextScreen();
            }
        });

    }

    private void nextScreen(){
        Intent intent = new Intent(this,CurrencyDisplayActivity.class );
        startActivity(intent);
    }
}
