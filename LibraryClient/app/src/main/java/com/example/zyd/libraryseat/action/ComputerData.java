package com.example.zyd.libraryseat.action;

import com.example.zyd.libraryseat.activity.BaseActivity;
import com.example.zyd.libraryseat.common.CommonRequest;
import com.example.zyd.libraryseat.common.CommonResponse;
import com.example.zyd.libraryseat.http.ResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.zyd.libraryseat.common.CommonUrl.getComputer_url;
import static com.example.zyd.libraryseat.common.CommonUrl.getSeatData_url;

/**
 * Created by zyd on 2017/11/12.
 */

//获得智能选座的座位列表
public class ComputerData extends BaseActivity{
    private int floor;
    private String people;
    private ArrayList<HashMap<String,Integer>> list;


    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public ArrayList<HashMap<String, Integer>> getList() {
        return list;
    }

    public void setList(ArrayList<HashMap<String, Integer>> list) {
        this.list = list;
    }

    public void getSeatList(){
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.addRequestParam("people",getPeople());

        sendHttpPostRequest(getComputer_url, commonRequest, new ResponseHandler() {
            @Override
            public void success (CommonResponse response){
                ArrayList<HashMap<String, Integer>> seat = new ArrayList<>();
                ArrayList<HashMap<String, String>> list = new ArrayList<>();
                list = response.getDataList();
                for (int i = 0; i < list.size(); i++) {
                    HashMap<String, Integer> map = new HashMap<>();
                    map.put("row", Integer.parseInt(list.get(i).get("row")));
                    map.put("column", Integer.parseInt(list.get(i).get("column")));
                    setFloor(Integer.parseInt(list.get(i).get("floor")));
                    seat.add(map);
                }

                setList(seat);
            }

            @Override
            public void fail (String code, String msg){

            }
        });
    }
}
