package com.zyd.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.ResultSet;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.zyd.entity.Seat;
import com.zyd.service.SeatService;
import com.zyd.utils.DBUtils;

import aj.org.objectweb.asm.Handle;

public class GetData implements ServletContextListener{
	private SeatService seatService;
	public void setSeatService(SeatService seatService) {
		seatService = seatService;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		startTimer();
		
	}

	private void startTimer() {
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				//连接乐联网获得实时数据
                        StringBuilder resultBuf = new StringBuilder();
                        try {
                            URL url = new URL("http://www.lewei50.com/api/V1/user/getSensorsWithGateway");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                            connection.setRequestMethod("GET");
                            connection.setRequestProperty("userkey","5cac68c72afe4dd3aff1d01d732e37f0");
                            connection.setRequestProperty("sensorType","");
                            connection.setConnectTimeout(8000);
                            connection.setReadTimeout(8000);
                            connection.setDoInput(true);
                            connection.setDoOutput(true);

                            InputStream in = connection.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                            String line;
                            
                            while((line = reader.readLine()) != null){
                                resultBuf.append(line);
                            }
                            //解析json数据并将数据存入数据库
                            ArrayList<Seat> seats = getSeats(resultBuf.toString());
                            reader.close();
                            in.close();
                            if(connection!=null) {
                            		connection.disconnect();
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
			}

			
		};
//		每10s获得实时数据
		Timer timer = new Timer();
	    timer.schedule(task, 0, 10*1000);
	}
	
	
	private ArrayList<Seat> getSeats(String s)  {
		 ArrayList<Seat> list = new ArrayList<>();

		 	String aString = s.substring(1, s.length()-1);
	        JSONObject jsonObject = new JSONObject(aString);
	        
	            JSONArray sensors = jsonObject.getJSONArray("sensors");

	            for(int j = 0; j < sensors.length(); j++){
		            Seat seat = new Seat();
		            JSONObject sensorJSON = sensors.getJSONObject(j);
		            int sid = Integer.parseInt(sensorJSON.getString("idName"));
	                seat.setSid(sid);
	                
	                int sensorValue = Integer.parseInt(sensorJSON.getString("value"));
					seat.setIsFull(sensorValue);
					Seat ss = new Seat();
					String sql = String.format("select * from %s where sid = %s", DBUtils.Table_Seat,sid);
					try {
						ResultSet resultSet = (ResultSet) DBUtils.query(sql);
						while(resultSet.next()) {
							ss.setPriority(resultSet.getInt("PRIORITY"));
							ss.setIsFull(resultSet.getInt("ISFULL"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ss.getIsFull() == 0 && seat.getIsFull() == 1) {
						int i = ss.getPriority()+1;
						ss.setPriority(i);
					}
        				
					String query = String.format("UPDATE %s SET ISFULL = %s , PRIORITY = %s WHERE SID = %s", DBUtils.Table_Seat,
								seat.getIsFull(),ss.getPriority(),seat.getSid());
					try {
						DBUtils.update(query);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					list.add(seat);
	            }
	            
	        
	        return list;
	}

 
	
}
