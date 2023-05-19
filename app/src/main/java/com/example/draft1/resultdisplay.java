package com.example.draft1;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resultdisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultdisplay);
        Intent intent = this.getIntent();
        String result = intent.getStringExtra("result");
        TextView print1 = (TextView) findViewById(R.id.result);
        print1.setText(result);
    }
}
