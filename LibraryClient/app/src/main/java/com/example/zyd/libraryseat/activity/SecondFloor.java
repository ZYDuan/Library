package com.example.zyd.libraryseat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zyd.libraryseat.R;
import com.example.zyd.libraryseat.action.SeatAction;
import com.example.zyd.libraryseat.views.SeatTable;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondFloor extends BaseActivity {

    private boolean isVisible = true;
    private Button firstfloor;
    private Button secondfloor;
    private Button thirdfloor;
    private Button selectSeat;
    private SeatTable seatTableView;
    private Button visible;
    private Button back;

    public String floor = "2";
    private SeatAction seatAction = new SeatAction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second_floor);

        secondfloor = (Button) findViewById(R.id.secondfloor2);
        firstfloor = (Button) findViewById(R.id.firstfloor2);
        thirdfloor = (Button) findViewById(R.id.thirdfloor2);
        selectSeat = (Button) findViewById(R.id.secondSelectSeat);
        firstfloor.setVisibility(View.INVISIBLE);
        secondfloor.setVisibility(View.INVISIBLE);
        thirdfloor.setVisibility(View.INVISIBLE);
        seatTableView =(SeatTable) findViewById(R.id.secondSeatView);
        visible = (Button) findViewById(R.id.secondVisible);
        back = (Button) findViewById(R.id.back2);

        //获得该楼层座位信息
        seatAction.getData(floor);

        //
        Intent intent = getIntent();
        if(intent.getSerializableExtra("list") != null) {
            ArrayList<HashMap<String , Integer>> list = (ArrayList<HashMap<String, Integer>>) intent.getSerializableExtra("list");
            for(HashMap<String, Integer> x : list){
                seatTableView.addChooseSeat(x.get("row"), x.get("column"));
            }
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondFloor.this, choose.class);
                startActivity(intent);
            }
        });

        visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    firstfloor.setVisibility(View.VISIBLE);
                    secondfloor.setVisibility(View.VISIBLE);
                    thirdfloor.setVisibility(View.VISIBLE);
                } else {
                    firstfloor.setVisibility(View.INVISIBLE);
                    secondfloor.setVisibility(View.INVISIBLE);
                    thirdfloor.setVisibility(View.INVISIBLE);
                    isVisible = true;
                }
            }
        });
        firstfloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SecondFloor.this, selectSeatByself.class);
                startActivity(intent);
            }
        });

        thirdfloor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent(SecondFloor.this, ThirdFloor.class);
                startActivity(intent);

            }
        });
        secondfloor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent(SecondFloor.this, SecondFloor.class);
                startActivity(intent);

            }
        });

        selectSeat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(seatTableView.getSelectedSeat() == null || seatTableView.getSelectedSeat().size() == 0){
                    Toast.makeText(SecondFloor.this, "你还未选取任何座位", Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<String> seat = seatTableView.getSelectedSeat();
                    SharedPreferences sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                    String id = sp.getString("userId", "null");

                    String seatString = seat.toString().replaceAll("\\[|\\]", "");
                    String[] seatArray = seatString.split(", |,");
                    for (int i = 0; i < seatArray.length; i += 2) {
                        seatAction.selectSeat(floor, seatArray[i], seatArray[i + 1], id);
                        ;
                    }

                    Toast.makeText(SecondFloor.this, "选座成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondFloor.this, SecondFloor.class);
                    startActivity(intent);


                }
//                Toast.makeText(SecondFloor.this, seat.toString(), Toast.LENGTH_SHORT).show();
            }
        });



        seatTableView.setScreenName("图书馆二楼");//设置图书馆楼层名
        seatTableView.setMaxSelected(3);//设置最多选中

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2||column==5||column==8||column==11||column==14||column==17||
                        column==20||column==23||column==26) {
                    return false;
                }
                if(row==2||row==5||row==8||row==11){
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(seatAction.findSoldSeat(row, column)){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(14,29);

    }

}
