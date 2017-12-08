package com.example.zyd.libraryseat.action;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.zyd.libraryseat.activity.BaseActivity;
import com.example.zyd.libraryseat.activity.LoginActivity;
import com.example.zyd.libraryseat.activity.choose;
import com.example.zyd.libraryseat.activity.selectSeatByself;
import com.example.zyd.libraryseat.common.CommonRequest;
import com.example.zyd.libraryseat.common.CommonResponse;
import com.example.zyd.libraryseat.http.ResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.zyd.libraryseat.common.CommonUrl.getSeatData_url;
import static com.example.zyd.libraryseat.common.CommonUrl.selectSeat_url;

/**
 * Created by zyd on 2017/11/11.
 */

public class SeatAction extends BaseActivity {
    private ArrayList<HashMap<String, Integer>> seatData = new ArrayList<>();

    public ArrayList<HashMap<String, Integer>> getSeatData() {
        return seatData;
    }

    public void setSeatData(ArrayList<HashMap<String, Integer>> seatData) {
        this.seatData = seatData;
    }

    //获得座位信息
    public void getData(String floor) {


    CommonRequest commonRequest = new CommonRequest();
        commonRequest.addRequestParam("floor",floor);

    sendHttpPostRequest(getSeatData_url, commonRequest, new ResponseHandler() {
        @Override
        public void success (CommonResponse response){
            ArrayList<HashMap<String, Integer>> seat = new ArrayList<>();
            ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
            list = response.getDataList();
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("row", Integer.parseInt(list.get(i).get("row")));
                map.put("column", Integer.parseInt(list.get(i).get("column")));
                seat.add(map);
            }

            setSeatData(seat);

        }

        @Override
        public void fail (String code, String msg){

        }
    });
}
    public boolean findSoldSeat(int row, int column) {

        ArrayList<HashMap<String,Integer>> array = new ArrayList<>();
        array = getSeatData();
        for(int i = 0;i<array.size();i++) {

            if ((array.get(i).get("row")== row ) && ( array.get(i).get("column")==column)){
                return true;
            }
        }
        return false;
    }

    public void selectSeat(String floor,  String row, String column, String id){

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.addRequestParam("id",id);
        commonRequest.addRequestParam("floor", floor);
        commonRequest.addRequestParam("column", column);
        commonRequest.addRequestParam("row", row);
        sendHttpPostRequest(selectSeat_url, commonRequest, new ResponseHandler() {
            @Override
            public void success (CommonResponse response) {
                if (response.getResCode().equals("0")) {
                    Log.e("zyd", "选位成功");
                }
            }

            @Override
            public void fail (String code, String msg){
                    Log.e("zyd",msg);
            }
        });
    }
}
