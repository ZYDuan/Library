package com.example.zyd.libraryseat.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.zyd.libraryseat.common.CommonRequest;
import com.example.zyd.libraryseat.common.Constant;
import com.example.zyd.libraryseat.http.HttpPostTask;
import com.example.zyd.libraryseat.http.ResponseHandler;
import com.example.zyd.libraryseat.util.LogUtil;

/**
 * Created by zyd on 2017/10/30.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void sendHttpPostRequest(String url, CommonRequest request, ResponseHandler responseHandler) {
        new HttpPostTask(request, mHandler, responseHandler).execute(url);
    }

    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Constant.HANDLER_HTTP_SEND_FAIL) {
                LogUtil.logErr(msg.obj.toString());
            } else if (msg.what == Constant.HANDLER_HTTP_RECEIVE_FAIL) {
                LogUtil.logErr(msg.obj.toString());
            }
        }
    };
}

