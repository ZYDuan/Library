package com.example.zyd.libraryseat.http;

import com.example.zyd.libraryseat.common.CommonResponse;

/**
 * Created by zyd on 2017/10/30.
 */

public interface ResponseHandler {

    //对返回报文进行处理
    void success(CommonResponse response);
    void fail(String code,String msg);
}
