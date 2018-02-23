package com.example.didact.otroejerciciofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void parte1(View view) {

        Intent i = new Intent(getApplicationContext(), parte1activity.class);

        startActivity(i);

    }

    public void parte2(View view) {

        Intent i = new Intent(getApplicationContext(), parte2activity.class);

        startActivity(i);
    }
    public void ejparte1 (View view) {

        Intent i = new Intent(getApplicationContext(), ejpart1.class);

        startActivity(i);
    }
}