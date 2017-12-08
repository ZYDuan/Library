package com.example.zyd.libraryseat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zyd.libraryseat.R;

public class choose extends AppCompatActivity {
    private Button btnbySelf;
    private Button btnbyComputer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        btnbySelf = (Button) findViewById(R.id.byMyselfBtn);
        btnbyComputer = (Button) findViewById(R.id.byComputerBt);

        btnbySelf.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(choose.this,selectSeatByself.class);
                startActivity(intent);
            }
        });
        btnbyComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(choose.this,selectByComputer.class);
                startActivity(intent);
            }
        });
    }
}
