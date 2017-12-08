package com.example.zyd.libraryseat.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zyd.libraryseat.R;
import com.example.zyd.libraryseat.entity.Seat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.value;

public class getApi extends AppCompatActivity {
    private Button get;
    private TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_api);

        get = (Button) findViewById(R.id.get);
        show = (TextView) findViewById(R.id.show);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder resultBuf = new StringBuilder();
                        try {
                            URL url = new URL("http://www.lewei50.com/api/V1/user/getSensorsWithGateway");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                            connection.setRequestMethod("GET");
                            connection.setRequestProperty("userkey","fde93bdb8f");
                            connection.setRequestProperty("sensorType","");
                            connection.setConnectTimeout(80000);
                            connection.setReadTimeout(80000);
                            connection.setDoInput(true);
                            connection.setDoOutput(true);

                            InputStream in = connection.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                            String line;
                            while((line = reader.readLine()) != null)
                            {
                                resultBuf.append(line);
                            }
                            if(resultBuf.toString() != null) {
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = resultBuf.toString();
                                Log.e("zyd", resultBuf.toString());

                                handler.sendMessage(msg);
                            }else{
                                show.setText("xxxx");
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                ArrayList<Seat> seatList = new ArrayList<>();
                try {
                     seatList = getData(msg.obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                StringBuffer buffer = new StringBuffer();
                for(int i = 0;i<seatList.size();i++){
                    buffer.append("id:  " + seatList.get(i).getId());
                    buffer.append("\n value"+seatList.get(i).getValue());
                }
                show.setText(buffer.toString());
            }
        }
    };

    //提取返回报文中所需的数据
    private ArrayList<Seat> getData(String s) throws JSONException {

        ArrayList<Seat> list = new ArrayList<>();


        JSONArray jsonArray = new JSONArray(s);
        for(int i = 0; i < jsonArray.length();i++){
            Seat seat = new Seat();
            String s1 = jsonArray.getString(i);
            JSONObject jsonObject = new JSONObject(s1);
            String id = jsonObject.getString("idName");
            seat.setId(id);
            JSONArray sensors = jsonObject.getJSONArray("sensors");

            for(int j = 0; j < sensors.length(); j++){
                String s2 = sensors.getString(j);
                JSONObject sensorJSON = new JSONObject(s2);
                if(sensorJSON != null) {
                    String sensorValue = sensorJSON.getString("value");
                    seat.setValue(sensorValue);
                }else{
                    seat.setValue("无");
                }
            }
            list.add(seat);
        }
        return list;
    }
}
