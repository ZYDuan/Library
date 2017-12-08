package com.example.zyd.libraryseat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zyd.libraryseat.R;
import com.example.zyd.libraryseat.action.ComputerData;

import java.util.ArrayList;
import java.util.HashMap;

public class selectByComputer extends AppCompatActivity {

    private Button btOne;
    private Button btTwo;
    private Button btThree;
    private ComputerData data = new ComputerData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_by_computer);

        btOne = (Button) findViewById(R.id.one);
        btTwo = (Button) findViewById(R.id.two);
        btThree = (Button) findViewById(R.id.three);

        btOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setPeople("1");
                data.getSeatList();
                ArrayList<HashMap<String, Integer>> list = data.getList();
                int floor = data.getFloor();

                switch (floor) {
                    case 1:
                        Intent intent = new Intent(selectByComputer.this, selectSeatByself.class);
                        Bundle bundle=new Bundle();
                        bundle.putParcelableArrayList("list", (ArrayList)data.getList());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intents = new Intent(selectByComputer.this, SecondFloor.class);
                        Bundle bundles=new Bundle();
                        bundles.putParcelableArrayList("list", (ArrayList)data.getList());
                        intents.putExtras(bundles);
                        startActivity(intents);
                        break;
                    case 3:
                        Intent intentss = new Intent(selectByComputer.this, ThirdFloor.class);
                        Bundle bundless=new Bundle();
                        bundless.putParcelableArrayList("list", (ArrayList)data.getList());
                        intentss.putExtras(bundless);
                        startActivity(intentss);
                        break;
                }
            }
        });

        btTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setPeople("2");
                data.getSeatList();
                ArrayList<HashMap<String, Integer>> list = data.getList();
                int floor = data.getFloor();

                switch (floor) {
                    case 1:
                        Intent intent = new Intent(selectByComputer.this, selectSeatByself.class);
                        Bundle bundle=new Bundle();
                        bundle.putParcelableArrayList("list", (ArrayList)data.getList());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intents = new Intent(selectByComputer.this, SecondFloor.class);
                        Bundle bundles=new Bundle();
                        bundles.putParcelableArrayList("list", (ArrayList)data.getList());
                        intents.putExtras(bundles);
                        startActivity(intents);
                        break;
                    case 3:
                        Intent intentss = new Intent(selectByComputer.this, ThirdFloor.class);
                        Bundle bundless=new Bundle();
                        bundless.putParcelableArrayList("list", (ArrayList)data.getList());
                        intentss.putExtras(bundless);
                        startActivity(intentss);
                        break;
                }
            }
        });

        btThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setPeople("3");
                data.getSeatList();
                ArrayList<HashMap<String, Integer>> list = data.getList();
                int floor = data.getFloor();

                switch (floor) {
                    case 1:
                        Intent intent = new Intent(selectByComputer.this, selectSeatByself.class);
                        Bundle bundle=new Bundle();
                        bundle.putParcelableArrayList("list", (ArrayList)data.getList());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intents = new Intent(selectByComputer.this, SecondFloor.class);
                        Bundle bundles=new Bundle();
                        bundles.putParcelableArrayList("list", (ArrayList)data.getList());
                        intents.putExtras(bundles);
                        startActivity(intents);
                        break;
                    case 3:
                        Intent intentss = new Intent(selectByComputer.this, ThirdFloor.class);
                        Bundle bundless=new Bundle();
                        bundless.putParcelableArrayList("list", (ArrayList)data.getList());
                        intentss.putExtras(bundless);
                        startActivity(intentss);
                        break;
                }
            }
        });
    }

}
