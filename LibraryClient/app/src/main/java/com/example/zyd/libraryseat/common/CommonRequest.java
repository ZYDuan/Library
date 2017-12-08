package com.example.zyd.libraryseat.common;

import com.example.zyd.libraryseat.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by zyd on 2017/10/29.
 */

public class CommonRequest {
    //请求代码　
    private String requestCode;

    //请求参数
    private HashMap<String, String> requestParam;

    public CommonRequest() {
        requestCode = "";
        requestParam = new HashMap<>();
    }


    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public void addRequestParam(String paramKey, String paramValue) {
        requestParam.put(paramKey, paramValue);
    }

    public String getJsonStr() {
        JSONObject object = new JSONObject();
        JSONObject param = new JSONObject(requestParam);
        try {
            object.put("requestCode", requestCode);
            object.put("requestParam", param);
        } catch (JSONException e) {
            LogUtil.logErr("请求报文组装异常：" + e.getMessage());
        }
        // 打印原始请求报文
        LogUtil.logRequest(object.toString());
        return  String.valueOf(object);
    }
}
