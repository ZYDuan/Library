package com.example.zyd.libraryseat.http;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.zyd.libraryseat.common.CommonRequest;
import com.example.zyd.libraryseat.common.CommonResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.zyd.libraryseat.common.Constant.HANDLER_HTTP_RECEIVE_FAIL;
import static com.example.zyd.libraryseat.common.Constant.HANDLER_HTTP_SEND_FAIL;

/**
 * Created by zyd on 2017/10/30.
 */

public class HttpPostTask extends AsyncTask<String, String, String>{
    private Handler mHandler;
    private ResponseHandler rHandler;

    private CommonRequest request;

    public HttpPostTask(CommonRequest request,
                        Handler mHandler,
                        ResponseHandler rHandler) {
        this.request = request;
        this.mHandler = mHandler;
        this.rHandler = rHandler;
    }
    @Override
    protected String doInBackground(String... params){
        StringBuilder resultBuf = new StringBuilder();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Charset","utf-8");
            connection.setRequestMethod("POST");
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //获得输入流之前写入参数
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(request.getJsonStr().getBytes());
            outputStream.flush();

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line=read.readLine())!=null){
                    resultBuf.append(line);
                }
                return resultBuf.toString();
            }else {
//                // 异常情况，如404/500...
               mHandler.obtainMessage(HANDLER_HTTP_RECEIVE_FAIL,
                        "[" + responseCode + "]" + connection.getResponseMessage()).sendToTarget();
            }

        } catch (IOException e) {
            mHandler.obtainMessage(HANDLER_HTTP_SEND_FAIL,
                  e.getClass().getName() + " : " + e.getMessage()).sendToTarget();
      }
       return resultBuf.toString();
    }

    @Override
    protected  void onPostExecute(String result){
        if(rHandler != null){
            if(!"".equals(result)){
                CommonResponse response = new CommonResponse(result);
                if("0".equals(response.getResCode())||"1".equals(response.getResCode())){
                    rHandler.success(response);
                }else{
                    rHandler.fail(response.getResCode(),response.getResMsg());
                }
            }
        }
    }

//    @Override
//    protected void onPostExecute(String result) {
//        if (rHandler != null) {
//            if (!"".equals(result)) {
//				/* 交易成功时需要在处理返回结果时手动关闭Loading对话框，可以灵活处理连续请求多个接口时Loading框不断弹出、关闭的情况 */
//
//                CommonResponse response = new CommonResponse(result);
//                // 这里response.getResCode()为多少表示业务完成也是和服务器约定好的
//                if ("0".equals(response.getResCode())) { // 正确
//                    rHandler.success(response);
//                } else {
//                    rHandler.fail(response.getResCode(), response.getResMsg());
//                }
//            }
//        }
//    }


}
